package Domain;

import Data.JavaByteCodeAdapter.*;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.Options;
import Domain.Rules.PrincipleLeastKnowledgeRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PrincipleLeastKnowledgeRuleTest {

    @Test
    public void plkNoClasses() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        // Replay
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - no classes so should be no issues
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1Class1MethodNoInstructions() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(0);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - no instructions so should be no issues
        EasyMock.verify(classNode, methodNode, insnList);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsThis() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode = EasyMock.mock(MethodInsnNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(1).times(2);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode);
        EasyMock.expect(abstractInsnNode.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode.getMethodInsnNode()).andReturn(methodInsnNode);
        EasyMock.expect(methodInsnNode.isInitInsn()).andReturn(false);
        // method called is on this
        EasyMock.expect(methodInsnNode.getMethodOwner()).andReturn(classNode);
        EasyMock.expect(classNode.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList, abstractInsnNode, methodInsnNode);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling this so should be no issues
        EasyMock.verify(classNode, methodNode, insnList, abstractInsnNode, methodInsnNode);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsFieldType() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        FieldNode fieldNode = EasyMock.mock(FieldNode.class);
        ClassNode fieldClassNode = EasyMock.mock(ClassNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode = EasyMock.mock(MethodInsnNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode);
        String fieldClassName = "java.util.HashMap";

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode.getType()).andReturn(fieldClassNode);
        EasyMock.expect(fieldClassNode.isValid()).andReturn(true);
        EasyMock.expect(fieldClassNode.getClassName()).andReturn(fieldClassName).times(2);
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(1).times(2);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode);
        EasyMock.expect(abstractInsnNode.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode.getMethodInsnNode()).andReturn(methodInsnNode);
        EasyMock.expect(methodInsnNode.isInitInsn()).andReturn(false);
        // method called is on field
        EasyMock.expect(methodInsnNode.getMethodOwner()).andReturn(fieldClassNode);
        EasyMock.expect(fieldClassNode.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, fieldNode, fieldClassNode,
                insnList, abstractInsnNode, methodInsnNode);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling field type so should be no issues
        EasyMock.verify(classNode, methodNode, fieldNode, fieldClassNode,
                insnList, abstractInsnNode, methodInsnNode);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsArgumentType() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        ClassNode argumentClassNode = EasyMock.mock(ClassNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode = EasyMock.mock(MethodInsnNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        ArrayList<ClassNode> argumentNodes = new ArrayList<>();
        argumentNodes.add(argumentClassNode);
        String argumentClassName = "java.util.HashMap";

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(argumentNodes);
        EasyMock.expect(argumentClassNode.getClassName()).andReturn(argumentClassName).times(2);
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(1).times(2);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode);
        EasyMock.expect(abstractInsnNode.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode.getMethodInsnNode()).andReturn(methodInsnNode);
        EasyMock.expect(methodInsnNode.isInitInsn()).andReturn(false);
        // method called is on argument
        EasyMock.expect(methodInsnNode.getMethodOwner()).andReturn(argumentClassNode);
        EasyMock.expect(argumentClassNode.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, argumentClassNode,
                insnList, abstractInsnNode, methodInsnNode);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling argument type so should be no issues
        EasyMock.verify(classNode, methodNode, argumentClassNode,
                insnList, abstractInsnNode, methodInsnNode);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsInitializedType() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        ClassNode initializedClassNode = EasyMock.mock(ClassNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode1 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode1 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNode2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode2 = EasyMock.mock(MethodInsnNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        String initializedClassName = "java.util.HashMap";

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(2).times(3);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode1);
        EasyMock.expect(abstractInsnNode1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode1.getMethodInsnNode()).andReturn(methodInsnNode1);
        // initializing type method
        EasyMock.expect(methodInsnNode1.getMethodOwner()).andReturn(initializedClassNode);
        EasyMock.expect(methodInsnNode1.isInitInsn()).andReturn(true);
        EasyMock.expect(initializedClassNode.getClassName()).andReturn(initializedClassName);
        // calling method on initialized type
        EasyMock.expect(insnList.get(1)).andReturn(abstractInsnNode2);
        EasyMock.expect(abstractInsnNode2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode2.getMethodInsnNode()).andReturn(methodInsnNode2);
        EasyMock.expect(methodInsnNode2.getMethodOwner()).andReturn(initializedClassNode);
        EasyMock.expect(methodInsnNode2.isInitInsn()).andReturn(false);
        EasyMock.expect(initializedClassNode.getClassName()).andReturn(initializedClassName);
        EasyMock.expect(initializedClassNode.isValid()).andReturn(true).times(2);

        // Replay
        EasyMock.replay(classNode, methodNode, initializedClassNode, insnList,
                abstractInsnNode1, methodInsnNode1, abstractInsnNode2, methodInsnNode2);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling initialized type so should be no issues
        EasyMock.verify(classNode, methodNode, initializedClassNode, insnList,
                abstractInsnNode1, methodInsnNode1, abstractInsnNode2, methodInsnNode2);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsOtherType() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        String info = "INFO";
        optionsValues.add(info);
        Options options = new Options(optionsKeys, optionsValues);
        int lineNumber = 3;

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        ClassNode argumentClassNode = EasyMock.mock(ClassNode.class);
        ClassNode otherClassNode = EasyMock.mock(ClassNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode1 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode1 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNode2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode2 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNodeLine = EasyMock.mock(AbstractInsnNode.class);
        LineNumberNode lineNumberNode = EasyMock.mock(LineNumberNode.class);

        String className = "java.util.ArrayList";
        String fileName = "fileName";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        String methodName = "add";
        ArrayList<ClassNode> argumentNodes = new ArrayList<>();
        argumentNodes.add(argumentClassNode);
        String argumentClassName = "java.util.HashMap";
        String otherClassName = "java.util.HashSet";
        String expectedMessage = "method add makes call to java.util.HashSet violating the Principle of Least Knowledge";

        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(argumentNodes);
        EasyMock.expect(argumentClassNode.getClassName()).andReturn(argumentClassName).times(2);
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(3).times(4);
        // method called is on argument - say this method returns of type otherClass
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode1);
        EasyMock.expect(abstractInsnNode1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode1.getMethodInsnNode()).andReturn(methodInsnNode1);
        EasyMock.expect(methodInsnNode1.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode1.getMethodOwner()).andReturn(argumentClassNode);
        EasyMock.expect(argumentClassNode.isValid()).andReturn(true);
        // line number node called to set the current line number
        EasyMock.expect(insnList.get(1)).andReturn(abstractInsnNodeLine);
        EasyMock.expect(abstractInsnNodeLine.isMethodInsnNode()).andReturn(false);
        EasyMock.expect(abstractInsnNodeLine.isLineNumberNode()).andReturn(true);
        EasyMock.expect(abstractInsnNodeLine.getLineNumberNode()).andReturn(lineNumberNode);
        EasyMock.expect(lineNumberNode.getLineNumber()).andReturn(lineNumber);
        // method is called on otherClass - this violates principle of least knowledge
        EasyMock.expect(insnList.get(2)).andReturn(abstractInsnNode2);
        EasyMock.expect(abstractInsnNode2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode2.getMethodInsnNode()).andReturn(methodInsnNode2);
        EasyMock.expect(methodInsnNode2.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode2.getMethodOwner()).andReturn(otherClassNode);
        EasyMock.expect(otherClassNode.getClassName()).andReturn(otherClassName);
        EasyMock.expect(otherClassNode.isValid()).andReturn(true);
        EasyMock.expect(methodNode.getName()).andReturn(methodName);
        EasyMock.expect(classNode.getFileName()).andReturn(fileName);

        // Replay
        EasyMock.replay(classNode, methodNode, argumentClassNode, otherClassNode,
                insnList, abstractInsnNode1, methodInsnNode1, abstractInsnNode2,
                methodInsnNode2, abstractInsnNodeLine, lineNumberNode);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling other type so should have issue with all information
        EasyMock.verify(classNode, methodNode, argumentClassNode, otherClassNode,
                insnList, abstractInsnNode1, methodInsnNode1, abstractInsnNode2,
                methodInsnNode2, abstractInsnNodeLine, lineNumberNode);
        assertEquals(1, issues.size());
        assertEquals(Severity.INFO, issues.get(0).severity);
        assertEquals(lineNumber, issues.get(0).line);
        assertEquals(className, issues.get(0).classValue);
        assertEquals(fileName, issues.get(0).file);
        assertEquals(expectedMessage, issues.get(0).message);
    }

    @Test
    public void plkManyClassesCallsManyOtherTypes() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        String info = "INFO";
        optionsValues.add(info);
        Options options = new Options(optionsKeys, optionsValues);
        int lineNumber1 = 3;
        int lineNumber2 = 10;
        int lineNumber3 = 5;

        ClassNode classNode1 = EasyMock.mock(ClassNode.class);
        ClassNode classNode2 = EasyMock.mock(ClassNode.class);
        MethodNode methodNode1 = EasyMock.mock(MethodNode.class);
        MethodNode methodNode2 = EasyMock.mock(MethodNode.class);
        MethodNode methodNode3 = EasyMock.mock(MethodNode.class);
        ClassNode otherClassNode1 = EasyMock.mock(ClassNode.class);
        ClassNode otherClassNode2 = EasyMock.mock(ClassNode.class);
        ClassNode otherClassNode3 = EasyMock.mock(ClassNode.class);
        InsnList insnList1 = EasyMock.mock(InsnList.class);
        InsnList insnList2 = EasyMock.mock(InsnList.class);
        InsnList insnList3 = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode1 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode1 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNode2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode2 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNode3 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode3 = EasyMock.mock(MethodInsnNode.class);
        AbstractInsnNode abstractInsnNodeLine1 = EasyMock.mock(AbstractInsnNode.class);
        LineNumberNode lineNumberNode1 = EasyMock.mock(LineNumberNode.class);
        AbstractInsnNode abstractInsnNodeLine2 = EasyMock.mock(AbstractInsnNode.class);
        LineNumberNode lineNumberNode2 = EasyMock.mock(LineNumberNode.class);
        AbstractInsnNode abstractInsnNodeLine3 = EasyMock.mock(AbstractInsnNode.class);
        LineNumberNode lineNumberNode3 = EasyMock.mock(LineNumberNode.class);

        String className1 = "java.util.ArrayList";
        String fileName1 = "fileName1";
        String className2 = "java.util.LinkedList";
        String fileName2 = "fileName2";
        classNodes.put(className1, classNode1);
        classNodes.put(className2, classNode2);
        ArrayList<MethodNode> methodNodes1 = new ArrayList<>();
        methodNodes1.add(methodNode1);
        methodNodes1.add(methodNode2);
        ArrayList<MethodNode> methodNodes2 = new ArrayList<>();
        methodNodes2.add(methodNode3);
        String methodName1 = "add";
        String methodName2 = "get";
        String methodName3 = "contains";
        String otherClassName1 = "java.util.HashMap";
        String otherClassName2 = "java.util.HashSet";
        String otherClassName3 = "java.util.Arrays";
        String expectedMessage1 = "method add makes call to java.util.HashMap violating the Principle of Least Knowledge";
        String expectedMessage2 = "method get makes call to java.util.HashSet violating the Principle of Least Knowledge";
        String expectedMessage3 = "method contains makes call to java.util.Arrays violating the Principle of Least Knowledge";

        EasyMock.expect(classNode1.getClassName()).andReturn(className1).times(3);
        EasyMock.expect(classNode1.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode1.getMethods()).andReturn(methodNodes1);
        EasyMock.expect(methodNode1.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode1.getInstructions()).andReturn(insnList1);
        EasyMock.expect(insnList1.size()).andReturn(2).times(3);

        // line number node 1 called to set the current line number
        EasyMock.expect(insnList1.get(0)).andReturn(abstractInsnNodeLine1);
        EasyMock.expect(abstractInsnNodeLine1.isMethodInsnNode()).andReturn(false);
        EasyMock.expect(abstractInsnNodeLine1.isLineNumberNode()).andReturn(true);
        EasyMock.expect(abstractInsnNodeLine1.getLineNumberNode()).andReturn(lineNumberNode1);
        EasyMock.expect(lineNumberNode1.getLineNumber()).andReturn(lineNumber1);
        // method is called on otherClass1 - this violates principle of least knowledge
        EasyMock.expect(insnList1.get(1)).andReturn(abstractInsnNode1);
        EasyMock.expect(abstractInsnNode1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode1.getMethodInsnNode()).andReturn(methodInsnNode1);
        EasyMock.expect(methodInsnNode1.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode1.getMethodOwner()).andReturn(otherClassNode1);
        EasyMock.expect(otherClassNode1.isValid()).andReturn(true);
        EasyMock.expect(otherClassNode1.getClassName()).andReturn(otherClassName1);
        EasyMock.expect(methodNode1.getName()).andReturn(methodName1);
        EasyMock.expect(classNode1.getFileName()).andReturn(fileName1);

        EasyMock.expect(methodNode2.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode2.getInstructions()).andReturn(insnList2);
        EasyMock.expect(insnList2.size()).andReturn(2).times(3);

        // line number node 2 called to set the current line number
        EasyMock.expect(insnList2.get(0)).andReturn(abstractInsnNodeLine2);
        EasyMock.expect(abstractInsnNodeLine2.isMethodInsnNode()).andReturn(false);
        EasyMock.expect(abstractInsnNodeLine2.isLineNumberNode()).andReturn(true);
        EasyMock.expect(abstractInsnNodeLine2.getLineNumberNode()).andReturn(lineNumberNode2);
        EasyMock.expect(lineNumberNode2.getLineNumber()).andReturn(lineNumber2);
        // method is called on otherClass2 - this violates principle of least knowledge
        EasyMock.expect(insnList2.get(1)).andReturn(abstractInsnNode2);
        EasyMock.expect(abstractInsnNode2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode2.getMethodInsnNode()).andReturn(methodInsnNode2);
        EasyMock.expect(methodInsnNode2.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode2.getMethodOwner()).andReturn(otherClassNode2);
        EasyMock.expect(otherClassNode2.isValid()).andReturn(true);
        EasyMock.expect(otherClassNode2.getClassName()).andReturn(otherClassName2);
        EasyMock.expect(methodNode2.getName()).andReturn(methodName2);
        EasyMock.expect(classNode1.getFileName()).andReturn(fileName1);

        EasyMock.expect(classNode2.getClassName()).andReturn(className2).times(2);
        EasyMock.expect(classNode2.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode2.getMethods()).andReturn(methodNodes2);
        EasyMock.expect(methodNode3.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode3.getInstructions()).andReturn(insnList3);
        EasyMock.expect(insnList3.size()).andReturn(2).times(3);

        // line number node 3 called to set the current line number
        EasyMock.expect(insnList3.get(0)).andReturn(abstractInsnNodeLine3);
        EasyMock.expect(abstractInsnNodeLine3.isMethodInsnNode()).andReturn(false);
        EasyMock.expect(abstractInsnNodeLine3.isLineNumberNode()).andReturn(true);
        EasyMock.expect(abstractInsnNodeLine3.getLineNumberNode()).andReturn(lineNumberNode3);
        EasyMock.expect(lineNumberNode3.getLineNumber()).andReturn(lineNumber3);
        // method is called on otherClass3 - this violates principle of least knowledge
        EasyMock.expect(insnList3.get(1)).andReturn(abstractInsnNode3);
        EasyMock.expect(abstractInsnNode3.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode3.getMethodInsnNode()).andReturn(methodInsnNode3);
        EasyMock.expect(methodInsnNode3.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode3.getMethodOwner()).andReturn(otherClassNode3);
        EasyMock.expect(otherClassNode3.isValid()).andReturn(true);
        EasyMock.expect(otherClassNode3.getClassName()).andReturn(otherClassName3);
        EasyMock.expect(methodNode3.getName()).andReturn(methodName3);
        EasyMock.expect(classNode2.getFileName()).andReturn(fileName2);

        // Replay
        EasyMock.replay(classNode1, classNode2, methodNode1, methodNode2, methodNode3,
                otherClassNode1, otherClassNode2, otherClassNode3, insnList1, insnList2, insnList3,
                abstractInsnNode1, methodInsnNode1, abstractInsnNode2, methodInsnNode2,
                abstractInsnNode3, methodInsnNode3, abstractInsnNodeLine1, lineNumberNode1,
                abstractInsnNodeLine2, lineNumberNode2, abstractInsnNodeLine3, lineNumberNode3);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling other types so should have 3 issues with all information
        EasyMock.verify(classNode1, classNode2, methodNode1, methodNode2, methodNode3,
                otherClassNode1, otherClassNode2, otherClassNode3, insnList1, insnList2, insnList3,
                abstractInsnNode1, methodInsnNode1, abstractInsnNode2, methodInsnNode2,
                abstractInsnNode3, methodInsnNode3, abstractInsnNodeLine1, lineNumberNode1,
                abstractInsnNodeLine2, lineNumberNode2, abstractInsnNodeLine3, lineNumberNode3);

        assertEquals(3, issues.size());
        assertEquals(Severity.INFO, issues.get(0).severity);
        assertEquals(lineNumber3, issues.get(0).line);
        assertEquals(className2, issues.get(0).classValue);
        assertEquals(fileName2, issues.get(0).file);
        assertEquals(expectedMessage3, issues.get(0).message);

        assertEquals(Severity.INFO, issues.get(1).severity);
        assertEquals(lineNumber1, issues.get(1).line);
        assertEquals(className1, issues.get(1).classValue);
        assertEquals(fileName1, issues.get(1).file);
        assertEquals(expectedMessage1, issues.get(1).message);

        assertEquals(Severity.INFO, issues.get(2).severity);
        assertEquals(lineNumber2, issues.get(2).line);
        assertEquals(className1, issues.get(2).classValue);
        assertEquals(fileName1, issues.get(2).file);
        assertEquals(expectedMessage2, issues.get(2).message);
    }

    @Test
    public void plk1ClassMethodCalls1WhitelistedClass() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        String whitelistedClassName = "java.util.HashMap";
        optionsKeys.add("whitelistedClass1");
        optionsValues.add(whitelistedClassName);
        Options options = new Options(optionsKeys, optionsValues);

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass = EasyMock.mock(ClassNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(1).times(2);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode);
        EasyMock.expect(abstractInsnNode.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode.getMethodInsnNode()).andReturn(methodInsnNode);
        EasyMock.expect(methodInsnNode.isInitInsn()).andReturn(false);
        // method called is on whitelistedClass
        EasyMock.expect(methodInsnNode.getMethodOwner()).andReturn(whitelistedClass);
        EasyMock.expect(whitelistedClass.getClassName()).andReturn(whitelistedClassName);
        EasyMock.expect(whitelistedClass.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList, abstractInsnNode,
                methodInsnNode, whitelistedClass);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling whitelisted class so should be no issues
        EasyMock.verify(classNode, methodNode, insnList, abstractInsnNode,
                methodInsnNode, whitelistedClass);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsManyWhitelistedClass() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        String whitelistedClassName1 = "java.util.HashMap";
        String whitelistedClassName2 = "java.util.HashSet";
        String whitelistedClassName3 = "java.util.Arrays";
        optionsKeys.add("whitelistedClass1");
        optionsValues.add(whitelistedClassName1);
        optionsKeys.add("whitelistedClass2");
        optionsValues.add(whitelistedClassName2);
        optionsKeys.add("whitelistedClass3");
        optionsValues.add(whitelistedClassName3);
        Options options = new Options(optionsKeys, optionsValues);

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode1 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode1 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass1 = EasyMock.mock(ClassNode.class);
        AbstractInsnNode abstractInsnNode2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode2 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass2 = EasyMock.mock(ClassNode.class);
        AbstractInsnNode abstractInsnNode3 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode3 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass3 = EasyMock.mock(ClassNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(3).times(4);
        // method called on whitelistedClass1
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode1);
        EasyMock.expect(abstractInsnNode1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode1.getMethodInsnNode()).andReturn(methodInsnNode1);
        EasyMock.expect(methodInsnNode1.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode1.getMethodOwner()).andReturn(whitelistedClass1);
        EasyMock.expect(whitelistedClass1.getClassName()).andReturn(whitelistedClassName1);
        EasyMock.expect(whitelistedClass1.isValid()).andReturn(true);
        // method called on whitelistedClass2
        EasyMock.expect(insnList.get(1)).andReturn(abstractInsnNode2);
        EasyMock.expect(abstractInsnNode2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode2.getMethodInsnNode()).andReturn(methodInsnNode2);
        EasyMock.expect(methodInsnNode2.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode2.getMethodOwner()).andReturn(whitelistedClass2);
        EasyMock.expect(whitelistedClass2.getClassName()).andReturn(whitelistedClassName2);
        EasyMock.expect(whitelistedClass2.isValid()).andReturn(true);
        // method called on whitelistedClass3
        EasyMock.expect(insnList.get(2)).andReturn(abstractInsnNode3);
        EasyMock.expect(abstractInsnNode3.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode3.getMethodInsnNode()).andReturn(methodInsnNode3);
        EasyMock.expect(methodInsnNode3.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode3.getMethodOwner()).andReturn(whitelistedClass3);
        EasyMock.expect(whitelistedClass3.getClassName()).andReturn(whitelistedClassName3);
        EasyMock.expect(whitelistedClass3.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList, abstractInsnNode1,
                abstractInsnNode2, abstractInsnNode3, methodInsnNode1,
                methodInsnNode2, methodInsnNode3, whitelistedClass1,
                whitelistedClass2, whitelistedClass3);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling whitelisted classes so should be no issues
        EasyMock.verify(classNode, methodNode, insnList, abstractInsnNode1,
                abstractInsnNode2, abstractInsnNode3, methodInsnNode1,
                methodInsnNode2, methodInsnNode3, whitelistedClass1,
                whitelistedClass2, whitelistedClass3);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCalls1WhitelistedPackage() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        String whitelistedPackageName = "java.util";
        optionsKeys.add("whitelistedPackage1");
        optionsValues.add(whitelistedPackageName);
        Options options = new Options(optionsKeys, optionsValues);
        String whitelistedClassName = "java.util.HashMap";

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass = EasyMock.mock(ClassNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(1).times(2);
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode);
        EasyMock.expect(abstractInsnNode.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode.getMethodInsnNode()).andReturn(methodInsnNode);
        EasyMock.expect(methodInsnNode.isInitInsn()).andReturn(false);
        // method called is on class in whitelistedPackage
        EasyMock.expect(methodInsnNode.getMethodOwner()).andReturn(whitelistedClass);
        EasyMock.expect(whitelistedClass.getClassName()).andReturn(whitelistedClassName);
        EasyMock.expect(whitelistedClass.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList, abstractInsnNode,
                methodInsnNode, whitelistedClass);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling whitelisted class so should be no issues
        EasyMock.verify(classNode, methodNode, insnList, abstractInsnNode,
                methodInsnNode, whitelistedClass);
        assertEquals(0, issues.size());
    }

    @Test
    public void plk1ClassMethodCallsManyWhitelistedPackages() {
        // Record
        Rule rule = new PrincipleLeastKnowledgeRule();
        Map<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        String whitelistedPackage1 = "java.util";
        String whitelistedPackage2 = "java.lang";
        optionsKeys.add("whitelistedPackage1");
        optionsValues.add(whitelistedPackage1);
        optionsKeys.add("whitelistedPackage2");
        optionsValues.add(whitelistedPackage2);
        Options options = new Options(optionsKeys, optionsValues);
        String whitelistedClassName1 = "java.util.HashMap";
        String whitelistedClassName2 = "java.util.HashSet";
        String whitelistedClassName3 = "java.lang.Runtime";

        ClassNode classNode = EasyMock.mock(ClassNode.class);
        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        InsnList insnList = EasyMock.mock(InsnList.class);
        AbstractInsnNode abstractInsnNode1 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode1 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass1 = EasyMock.mock(ClassNode.class);
        AbstractInsnNode abstractInsnNode2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode2 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass2 = EasyMock.mock(ClassNode.class);
        AbstractInsnNode abstractInsnNode3 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode methodInsnNode3 = EasyMock.mock(MethodInsnNode.class);
        ClassNode whitelistedClass3 = EasyMock.mock(ClassNode.class);

        String className = "java.util.ArrayList";
        classNodes.put(className, classNode);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);

        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getArgumentTypes()).andReturn(new ArrayList<>());
        EasyMock.expect(methodNode.getInstructions()).andReturn(insnList);
        EasyMock.expect(insnList.size()).andReturn(3).times(4);
        // method called on whitelistedClass1
        EasyMock.expect(insnList.get(0)).andReturn(abstractInsnNode1);
        EasyMock.expect(abstractInsnNode1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode1.getMethodInsnNode()).andReturn(methodInsnNode1);
        EasyMock.expect(methodInsnNode1.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode1.getMethodOwner()).andReturn(whitelistedClass1);
        EasyMock.expect(whitelistedClass1.getClassName()).andReturn(whitelistedClassName1);
        EasyMock.expect(whitelistedClass1.isValid()).andReturn(true);
        // method called on whitelistedClass2
        EasyMock.expect(insnList.get(1)).andReturn(abstractInsnNode2);
        EasyMock.expect(abstractInsnNode2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode2.getMethodInsnNode()).andReturn(methodInsnNode2);
        EasyMock.expect(methodInsnNode2.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode2.getMethodOwner()).andReturn(whitelistedClass2);
        EasyMock.expect(whitelistedClass2.getClassName()).andReturn(whitelistedClassName2);
        EasyMock.expect(whitelistedClass2.isValid()).andReturn(true);
        // method called on whitelistedClass3
        EasyMock.expect(insnList.get(2)).andReturn(abstractInsnNode3);
        EasyMock.expect(abstractInsnNode3.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(abstractInsnNode3.getMethodInsnNode()).andReturn(methodInsnNode3);
        EasyMock.expect(methodInsnNode3.isInitInsn()).andReturn(false);
        EasyMock.expect(methodInsnNode3.getMethodOwner()).andReturn(whitelistedClass3);
        EasyMock.expect(whitelistedClass3.getClassName()).andReturn(whitelistedClassName3);
        EasyMock.expect(whitelistedClass3.isValid()).andReturn(true);

        // Replay
        EasyMock.replay(classNode, methodNode, insnList, abstractInsnNode1,
                abstractInsnNode2, abstractInsnNode3, methodInsnNode1,
                methodInsnNode2, methodInsnNode3, whitelistedClass1,
                whitelistedClass2, whitelistedClass3);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - calling whitelisted classes so should be no issues
        EasyMock.verify(classNode, methodNode, insnList, abstractInsnNode1,
                abstractInsnNode2, abstractInsnNode3, methodInsnNode1,
                methodInsnNode2, methodInsnNode3, whitelistedClass1,
                whitelistedClass2, whitelistedClass3);
        assertEquals(0, issues.size());
    }

    @Test
    public void plkRuleOptionsTest() {
        // Record
        String key0 = "severity";
        String value0 = "INFO";

        PrincipleLeastKnowledgeRule rule = new PrincipleLeastKnowledgeRule();

        // Replay
        Options actualOptions = rule.getDefaultOptions();

        // Verify
        assertEquals(1, actualOptions.attributes.size());
        assertEquals(value0, actualOptions.get(key0));
    }

    @Test
    public void plkRuleConcreteBasicNoViolation() throws IOException {
        // Record
        String className = "TestClasses.C";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, plkRule.getDefaultOptions());

        // Verify - does not violate plk so should have no issues
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void plkRuleConcreteBasicViolation() throws IOException {
        // Record
        String expectedMessage1 = "method test makes call to java.util.HashMap violating the Principle of Least Knowledge";
        int line1 = 12;
        String expectedMessage2 = "method test makes call to TestClasses.C violating the Principle of Least Knowledge";
        int line2 = 13;
        String className = "TestClasses.mime";
        String fileName = "mime.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, plkRule.getDefaultOptions());

        // Verify - violates plk
        Assertions.assertEquals(2, issues.size());
        Assertions.assertEquals(expectedMessage1, issues.get(0).message);
        Assertions.assertEquals(line1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);

        Assertions.assertEquals(expectedMessage2, issues.get(1).message);
        Assertions.assertEquals(line2, issues.get(1).line);
        Assertions.assertEquals(className, issues.get(1).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(1).severity);
        Assertions.assertEquals(fileName, issues.get(1).file);
    }

    @Test
    public void plkRuleConcreteBasicViolationWithWhitelistClass() throws IOException {
        // Record
        String expectedMessage = "method test makes call to TestClasses.C violating the Principle of Least Knowledge";
        int line = 13;
        String className = "TestClasses.mime";
        String fileName = "mime.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        optionsKeys.add("whitelistedClass1");
        optionsValues.add("java.util.HashMap");
        Options options = new Options(optionsKeys, optionsValues);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, options);

        // Verify - violates plk
        Assertions.assertEquals(1, issues.size());

        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(line, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void plkRuleConcreteBasicViolationWithWhitelistPackage() throws IOException {
        // Record
        String expectedMessage = "method test makes call to TestClasses.C violating the Principle of Least Knowledge";
        int line = 13;
        String className = "TestClasses.mime";
        String fileName = "mime.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        optionsKeys.add("whitelistedPackage1");
        optionsValues.add("java.util");
        Options options = new Options(optionsKeys, optionsValues);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, options);

        // Verify - violates plk
        Assertions.assertEquals(1, issues.size());

        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(line, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void plkRuleThisNoWhitelist() throws IOException {
        // Record
        String className = "Domain.Rules.PrincipleLeastKnowledgeRule";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, plkRule.getDefaultOptions());

        // Verify - violates plk
        Assertions.assertEquals(22, issues.size());
    }

    @Test
    public void plkRuleConcreteSelfWithWhitelist() throws IOException {
        // Record
        String className = "Domain.Rules.PrincipleLeastKnowledgeRule";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        PrincipleLeastKnowledgeRule plkRule = new PrincipleLeastKnowledgeRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        optionsKeys.add("whitelistedPackage1");
        optionsValues.add("java.util");
        optionsKeys.add("whitelistedPackage2");
        optionsValues.add("java.lang");
        optionsKeys.add("whitelistedPackage3");
        optionsValues.add("Data.JavaByteCodeAdapter");
        optionsKeys.add("whitelistedClass1");
        optionsValues.add("Domain.Severity");
        Options options = new Options(optionsKeys, optionsValues);

        // Replay
        List<Issue> issues = plkRule.apply(
                classNodes, options);

        // Verify - does not violate plk with whitelisted classes
        Assertions.assertEquals(0, issues.size());
    }


}
