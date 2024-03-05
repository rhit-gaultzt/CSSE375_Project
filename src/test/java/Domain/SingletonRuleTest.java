package Domain;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Rules.SingletonRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SingletonRuleTest {
    @Test
    public void singletonRuleEmptyClass() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        String className = "java.util.ArrayList";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);

        // has no fields - not singleton
        EasyMock.expect(classNode.getFields()).andReturn(new ArrayList<>());

        // Replay
        EasyMock.replay(classNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleHasFieldsNoneOfSelf() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        String className = "java.util.ArrayList";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);
        FieldNode fieldNode1 = EasyMock.mock(FieldNode.class);
        FieldNode fieldNode2 = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode1);
        fieldNodes.add(fieldNode2);
        ClassNode field1ClassNode = EasyMock.mock(ClassNode.class);
        String field1ClassName = "java.util.HashMap";
        ClassNode field2ClassNode = EasyMock.mock(ClassNode.class);


        // has fields but not self - not singleton
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode1.getType()).andReturn(field1ClassNode);
        EasyMock.expect(field1ClassNode.isValid()).andReturn(true);
        EasyMock.expect(field1ClassNode.getClassName()).andReturn(field1ClassName);
        EasyMock.expect(classNode.getClassName()).andReturn(className);

        EasyMock.expect(fieldNode2.getType()).andReturn(field2ClassNode);
        EasyMock.expect(field2ClassNode.isValid()).andReturn(false); // is primitive

        // Replay
        EasyMock.replay(classNode, fieldNode1, fieldNode2, field1ClassNode, field2ClassNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode, fieldNode1, fieldNode2, field1ClassNode, field2ClassNode);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleNoMethods() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        String className = "java.util.ArrayList";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);
        FieldNode fieldNode = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode);


        // does have field of self
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode.getType()).andReturn(classNode);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has no methods - not singleton
        EasyMock.expect(classNode.getMethods()).andReturn(new ArrayList<>());

        // Replay
        EasyMock.replay(classNode, fieldNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode, fieldNode);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleHasMethodsNotReturningSelf() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        String className = "java.util.ArrayList";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);

        FieldNode fieldNode = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode);

        MethodNode methodNode1 = EasyMock.mock(MethodNode.class);
        MethodNode methodNode2 = EasyMock.mock(MethodNode.class);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode1);
        methodNodes.add(methodNode2);
        ClassNode method1ClassNode = EasyMock.mock(ClassNode.class);
        String method1ClassName = "java.util.HashMap";
        ClassNode method2ClassNode = EasyMock.mock(ClassNode.class);


        // does have field of self
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode.getType()).andReturn(classNode);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has static method but not of self - not singleton
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode1.getType()).andReturn(method1ClassNode);
        EasyMock.expect(methodNode1.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode1.isStatic()).andReturn(true);
        EasyMock.expect(method1ClassNode.isValid()).andReturn(true);
        EasyMock.expect(method1ClassNode.getClassName()).andReturn(method1ClassName);
        EasyMock.expect(classNode.getClassName()).andReturn(className);

        EasyMock.expect(methodNode2.getType()).andReturn(method2ClassNode);
        EasyMock.expect(methodNode2.isConstructor()).andReturn(false);
        EasyMock.expect(method2ClassNode.isValid()).andReturn(false); // is primitive

        // Replay
        EasyMock.replay(classNode, fieldNode, methodNode1, methodNode2, method1ClassNode, method2ClassNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode, fieldNode, methodNode1, methodNode2, method1ClassNode, method2ClassNode);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRulePublicConstructor() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        String className = "java.util.ArrayList";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);

        FieldNode fieldNode = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode);

        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        MethodNode constructorNode = EasyMock.mock(MethodNode.class);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        methodNodes.add(constructorNode);


        // does have field of self
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode.getType()).andReturn(classNode);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has static method returning self
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getType()).andReturn(classNode);
        EasyMock.expect(methodNode.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode.isStatic()).andReturn(true);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has public constructor - not Singleton
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.isConstructor()).andReturn(false);
        EasyMock.expect(constructorNode.isConstructor()).andReturn(true);
        EasyMock.expect(constructorNode.isPublic()).andReturn(true);


        // Replay
        EasyMock.replay(classNode, fieldNode, methodNode, constructorNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode, fieldNode, methodNode, constructorNode);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleIsSingleton() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "java.util.ArrayList";
        String classFile = "classFile.java";
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        classNodes.put(className, classNode);

        FieldNode fieldNode = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes = new ArrayList<>();
        fieldNodes.add(fieldNode);

        MethodNode methodNode = EasyMock.mock(MethodNode.class);
        MethodNode constructorNode = EasyMock.mock(MethodNode.class);
        ArrayList<MethodNode> methodNodes = new ArrayList<>();
        methodNodes.add(methodNode);
        methodNodes.add(constructorNode);

        String expectedMessage = "This class appears to be a Singleton";


        // does have field of self
        EasyMock.expect(classNode.getFields()).andReturn(fieldNodes);
        EasyMock.expect(fieldNode.getType()).andReturn(classNode);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has static method returning self
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.getType()).andReturn(classNode);
        EasyMock.expect(methodNode.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode.isStatic()).andReturn(true);
        EasyMock.expect(classNode.isValid()).andReturn(true);
        EasyMock.expect(classNode.getClassName()).andReturn(className).times(2);

        // has no public constructor
        EasyMock.expect(classNode.getMethods()).andReturn(methodNodes);
        EasyMock.expect(methodNode.isConstructor()).andReturn(false);
        EasyMock.expect(constructorNode.isConstructor()).andReturn(true);
        EasyMock.expect(constructorNode.isPublic()).andReturn(false);

        // IS A SINGLETON
        EasyMock.expect(classNode.getClassName()).andReturn(className);
        EasyMock.expect(classNode.getFileName()).andReturn(classFile);

        // Replay
        EasyMock.replay(classNode, fieldNode, methodNode, constructorNode);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode, fieldNode, methodNode, constructorNode);
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(classFile, issues.get(0).file);
    }

    @Test
    public void singletonRuleIsSingletonManyClasses() {
        // Record
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        Options options = new Options(optionsKeys, optionsValues);
        String className1 = "java.util.ArrayList";
        String classFile1 = "classFile.java";
        ClassNode classNode1 = EasyMock.mock(ClassNode.class);
        String className2 = "java.util.HashMap";
        String classFile2 = "classFile.java";
        ClassNode classNode2 = EasyMock.mock(ClassNode.class);
        String className3 = "java.util.Arrays";
        ClassNode classNode3 = EasyMock.mock(ClassNode.class);
        classNodes.put(className1, classNode1);
        classNodes.put(className2, classNode2);
        classNodes.put(className3, classNode3);

        FieldNode fieldNode1 = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes1 = new ArrayList<>();
        fieldNodes1.add(fieldNode1);

        FieldNode fieldNode2 = EasyMock.mock(FieldNode.class);
        ArrayList<FieldNode> fieldNodes2 = new ArrayList<>();
        fieldNodes2.add(fieldNode2);

        MethodNode methodNode1 = EasyMock.mock(MethodNode.class);
        MethodNode constructorNode1 = EasyMock.mock(MethodNode.class);
        ArrayList<MethodNode> methodNodes1 = new ArrayList<>();
        methodNodes1.add(methodNode1);
        methodNodes1.add(constructorNode1);

        MethodNode methodNode2_1 = EasyMock.mock(MethodNode.class);
        MethodNode methodNode2_2 = EasyMock.mock(MethodNode.class);
        MethodNode constructorNode2 = EasyMock.mock(MethodNode.class);
        ArrayList<MethodNode> methodNodes2 = new ArrayList<>();
        methodNodes2.add(methodNode2_1);
        methodNodes2.add(methodNode2_2);
        methodNodes2.add(constructorNode2);

        String expectedMessage = "This class appears to be a Singleton";

        // Singleton 1
        // Does have field of self
        EasyMock.expect(classNode1.getFields()).andReturn(fieldNodes1);
        EasyMock.expect(fieldNode1.getType()).andReturn(classNode1);
        EasyMock.expect(classNode1.isValid()).andReturn(true);
        EasyMock.expect(classNode1.getClassName()).andReturn(className1).times(2);

        // has static method returning self
        EasyMock.expect(classNode1.getMethods()).andReturn(methodNodes1);
        EasyMock.expect(methodNode1.getType()).andReturn(classNode1);
        EasyMock.expect(methodNode1.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode1.isStatic()).andReturn(true);
        EasyMock.expect(classNode1.isValid()).andReturn(true);
        EasyMock.expect(classNode1.getClassName()).andReturn(className1).times(2);

        // has no public constructor
        EasyMock.expect(classNode1.getMethods()).andReturn(methodNodes1);
        EasyMock.expect(methodNode1.isConstructor()).andReturn(false);
        EasyMock.expect(constructorNode1.isConstructor()).andReturn(true);
        EasyMock.expect(constructorNode1.isPublic()).andReturn(false);

        // IS A SINGLETON
        EasyMock.expect(classNode1.getClassName()).andReturn(className1);
        EasyMock.expect(classNode1.getFileName()).andReturn(classFile1);

        // Singleton 2
        // does have field of self
        EasyMock.expect(classNode2.getFields()).andReturn(fieldNodes2);
        EasyMock.expect(fieldNode2.getType()).andReturn(classNode2);
        EasyMock.expect(classNode2.isValid()).andReturn(true);
        EasyMock.expect(classNode2.getClassName()).andReturn(className2).times(2);

        // has static method returning self
        EasyMock.expect(classNode2.getMethods()).andReturn(methodNodes2);
        EasyMock.expect(methodNode2_1.getType()).andReturn(classNode1);
        EasyMock.expect(methodNode2_1.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode2_1.isStatic()).andReturn(false);
        EasyMock.expect(classNode1.isValid()).andReturn(true);
        EasyMock.expect(methodNode2_2.getType()).andReturn(classNode2);
        EasyMock.expect(methodNode2_2.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode2_2.isStatic()).andReturn(true);
        EasyMock.expect(classNode2.isValid()).andReturn(true);
        EasyMock.expect(classNode2.getClassName()).andReturn(className2).times(2);

        // has no public constructor
        EasyMock.expect(classNode2.getMethods()).andReturn(methodNodes2);
        EasyMock.expect(methodNode2_1.isConstructor()).andReturn(false);
        EasyMock.expect(methodNode2_2.isConstructor()).andReturn(false);
        EasyMock.expect(constructorNode2.isConstructor()).andReturn(true);
        EasyMock.expect(constructorNode2.isPublic()).andReturn(false);

        // IS A SINGLETON
        EasyMock.expect(classNode2.getClassName()).andReturn(className2);
        EasyMock.expect(classNode2.getFileName()).andReturn(classFile2);

        // Not a Singleton since does not have field of self
        EasyMock.expect(classNode3.getFields()).andReturn(new ArrayList<>());

        // Replay
        EasyMock.replay(classNode1, classNode2, classNode3, fieldNode1,
                fieldNode2, methodNode1, constructorNode1,
                methodNode2_1, methodNode2_2, constructorNode2);
        List<Issue> issues = singletonRule.apply(classNodes, options);

        // Verify
        EasyMock.verify(classNode1, classNode2, classNode3, fieldNode1,
                fieldNode2, methodNode1, constructorNode1,
                methodNode2_1, methodNode2_2, constructorNode2);
        Assertions.assertEquals(2, issues.size());

        Assertions.assertEquals(expectedMessage, issues.get(1).message);
        Assertions.assertEquals(-1, issues.get(1).line);
        Assertions.assertEquals(className1, issues.get(1).classValue);
        Assertions.assertEquals(Severity.WARNING, issues.get(1).severity);
        Assertions.assertEquals(classFile1, issues.get(1).file);

        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className2, issues.get(0).classValue);
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(classFile2, issues.get(0).file);
    }

    @Test
    public void singletonRuleOptionsTest() {
        // Record
        String key0 = "severity";
        String value0 = "INFO";

        SingletonRule rule = new SingletonRule();

        // Replay
        Options actualOptions = rule.getDefaultOptions();

        // Verify
        assertEquals(1, actualOptions.attributes.size());
        assertEquals(value0, actualOptions.get(key0));
    }

    @Test
    public void singletonRuleConcreteBasicNonSingleton() throws IOException {
        // Record
        String className = "TestClasses.C";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - not a singleton so should have no issues
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleConcreteBasicSingleton() throws IOException {
        // Record
        String expectedMessage = "This class appears to be a Singleton";
        String className = "TestClasses.BasicSingleton";
        String fileName = "BasicSingleton.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - is a singleton
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void singletonRuleConcreteEagerSingleton() throws IOException {
        // Record
        String expectedMessage = "This class appears to be a Singleton";
        String className = "TestClasses.EagerSingleton";
        String fileName = "EagerSingleton.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - is a singleton
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void singletonRuleConcreteSynchronizedSingleton() throws IOException {
        // Record
        String expectedMessage = "This class appears to be a Singleton";
        String className = "TestClasses.SynchronizedSingleton";
        String fileName = "SynchronizedSingleton.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - is a singleton
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void singletonRuleConcreteDoubleCheckLockSingleton() throws IOException {
        // Record
        String expectedMessage = "This class appears to be a Singleton";
        String className = "TestClasses.DoubleCheckLockSingleton";
        String fileName = "DoubleCheckLockSingleton.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - is a singleton
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }

    @Test
    public void singletonRuleConcreteOnSelf() throws IOException {
        // Record
        String className = "Domain.Rules.SingletonRule";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - not a singleton so should have no issues
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void singletonRuleConcreteJavaLangRuntime() throws IOException {
        // Record
        String expectedMessage = "This class appears to be a Singleton";
        String className = "java.lang.Runtime";
        String fileName = "Runtime.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        SingletonRule singletonRule = new SingletonRule();
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = singletonRule.apply(
                classNodes, singletonRule.getDefaultOptions());

        // Verify - is a singleton
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).message);
        Assertions.assertEquals(-1, issues.get(0).line);
        Assertions.assertEquals(className, issues.get(0).classValue);
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(fileName, issues.get(0).file);
    }


}
