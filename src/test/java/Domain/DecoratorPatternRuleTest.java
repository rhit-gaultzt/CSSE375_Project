package Domain;

import Data.JavaByteCodeAdapter.*;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.Options;
import Domain.Rules.DecoratorPatternRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecoratorPatternRuleTest {


    @Test
    public void validDecorator() {
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass1    = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass2    = EasyMock.mock(ClassNode.class);
        MethodNode method1_1            = EasyMock.mock(MethodNode.class);
        MethodNode method1_2            = EasyMock.mock(MethodNode.class);
        MethodNode method2_1            = EasyMock.mock(MethodNode.class);
        MethodNode method2_2            = EasyMock.mock(MethodNode.class);
        MethodNode method3_1            = EasyMock.mock(MethodNode.class);
        MethodNode method3_2            = EasyMock.mock(MethodNode.class);
        MethodNode method4_1            = EasyMock.mock(MethodNode.class);
        MethodNode method4_2            = EasyMock.mock(MethodNode.class);
        FieldNode field1_1            = EasyMock.mock(FieldNode.class);
        FieldNode field1_2            = EasyMock.mock(FieldNode.class);
        FieldNode field2_1            = EasyMock.mock(FieldNode.class);
        FieldNode field2_2            = EasyMock.mock(FieldNode.class);
        FieldNode field3_1            = EasyMock.mock(FieldNode.class);
        FieldNode field3_2            = EasyMock.mock(FieldNode.class);
        FieldNode field4_1            = EasyMock.mock(FieldNode.class);
        FieldNode field4_2            = EasyMock.mock(FieldNode.class);
        InsnList         insnList1_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList1_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_2    = EasyMock.mock(InsnList.class);
        AbstractInsnNode aInsnNode1_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode   mInsnNode1_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_2 = EasyMock.mock(MethodInsnNode.class);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingMethodSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingSuperCallSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);

        EasyMock.expect(class1.getSourceFile()).andReturn("file1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("file2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("file3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("file4").anyTimes();
        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();
        EasyMock.expect(dummyClass1.getClassName()).andReturn("dummyClass1").anyTimes();
        EasyMock.expect(dummyClass2.getClassName()).andReturn("dummyClass2").anyTimes();
        EasyMock.expect(method1_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method1_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method2_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method2_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method3_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method3_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method4_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method4_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodName()).andReturn("method2").anyTimes();

        // Record - is Abstract
        EasyMock.expect(class1.isAbstract()).andReturn(false);
        EasyMock.expect(class2.isAbstract()).andReturn(true);
        EasyMock.expect(class3.isAbstract()).andReturn(false);
        EasyMock.expect(class4.isAbstract()).andReturn(false);

        // Record - super class
        EasyMock.expect(class1.getSuperClass()).andReturn(null);
        EasyMock.expect(class2.getSuperClass()).andReturn(null);
        EasyMock.expect(class3.getSuperClass()).andReturn(class2);
        EasyMock.expect(class4.getSuperClass()).andReturn(class2);

        // Record - interface
        EasyMock.expect(class1.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class2.getInterfaces()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(class3.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class4.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();


        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }}).anyTimes();

        // Record - validate is constructor
        EasyMock.expect(method1_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method2_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method3_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method4_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isConstructor()).andReturn(false).anyTimes();

        // Record - validate is public
        EasyMock.expect(method1_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isPublic()).andReturn(true).anyTimes();

        // Record - validate Constructor Args
        EasyMock.expect(method1_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(dummyClass1);
            add(dummyClass2);
        }}).anyTimes();
        EasyMock.expect(method2_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method3_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method4_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field4_1);
            add(field4_2);
        }}).anyTimes();

        // Record - Validate Fields
        EasyMock.expect(field1_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field1_2.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field2_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field2_2.getType()).andReturn(class1).anyTimes();
        EasyMock.expect(field3_1.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field3_2.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_1.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_2.getType()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1).anyTimes();
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2).anyTimes();
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1).anyTimes();
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2).anyTimes();
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1).anyTimes();
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2).anyTimes();
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1).anyTimes();
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2).anyTimes();

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1).anyTimes();
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2).anyTimes();
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1).anyTimes();
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2).anyTimes();
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1).anyTimes();
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2).anyTimes();
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1).anyTimes();
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2).anyTimes();
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1).anyTimes();
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2).anyTimes();
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1).anyTimes();
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2).anyTimes();
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1).anyTimes();
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2).anyTimes();
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1).anyTimes();
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2).anyTimes();

        // Class Calls
        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3).anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4).anyTimes();

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class1).anyTimes();

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Replay
        EasyMock.replay(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);


        // Execute
        Rule rule          = new DecoratorPatternRule();
        List<Issue> issues = rule.apply(classes, options);


        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("class2", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("abstract decorator"));
        Assertions.assertTrue(issues.get(0).message.contains("class1"));
        Assertions.assertEquals("class4", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("decorator"));
        Assertions.assertTrue(issues.get(1).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("decorator"));
        Assertions.assertTrue(issues.get(2).message.contains("class1"));

        EasyMock.verify(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);
    }


    @Test
    public void missingMethodImplementation () {
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass1    = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass2    = EasyMock.mock(ClassNode.class);
        MethodNode method1_1            = EasyMock.mock(MethodNode.class);
        MethodNode method1_2            = EasyMock.mock(MethodNode.class);
        MethodNode method2_1            = EasyMock.mock(MethodNode.class);
        MethodNode method2_2            = EasyMock.mock(MethodNode.class);
        MethodNode method3_1            = EasyMock.mock(MethodNode.class);
        MethodNode method3_2            = EasyMock.mock(MethodNode.class);
        MethodNode method4_1            = EasyMock.mock(MethodNode.class);
        MethodNode method4_2            = EasyMock.mock(MethodNode.class);
        FieldNode field1_1            = EasyMock.mock(FieldNode.class);
        FieldNode field1_2            = EasyMock.mock(FieldNode.class);
        FieldNode field2_1            = EasyMock.mock(FieldNode.class);
        FieldNode field2_2            = EasyMock.mock(FieldNode.class);
        FieldNode field3_1            = EasyMock.mock(FieldNode.class);
        FieldNode field3_2            = EasyMock.mock(FieldNode.class);
        FieldNode field4_1            = EasyMock.mock(FieldNode.class);
        FieldNode field4_2            = EasyMock.mock(FieldNode.class);
        InsnList         insnList1_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList1_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_2    = EasyMock.mock(InsnList.class);
        AbstractInsnNode aInsnNode1_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode   mInsnNode1_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_2 = EasyMock.mock(MethodInsnNode.class);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingMethodSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingSuperCallSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);

        EasyMock.expect(class1.getSourceFile()).andReturn("file1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("file2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("file3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("file4").anyTimes();
        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();
        EasyMock.expect(dummyClass1.getClassName()).andReturn("dummyClass1").anyTimes();
        EasyMock.expect(dummyClass2.getClassName()).andReturn("dummyClass2").anyTimes();
        EasyMock.expect(method1_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method1_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method2_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method2_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method3_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method3_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method4_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method4_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodName()).andReturn("method2").anyTimes();

        // Record - is Abstract
        EasyMock.expect(class1.isAbstract()).andReturn(false);
        EasyMock.expect(class2.isAbstract()).andReturn(true);
        EasyMock.expect(class3.isAbstract()).andReturn(false);
        EasyMock.expect(class4.isAbstract()).andReturn(false);

        // Record - super class
        EasyMock.expect(class1.getSuperClass()).andReturn(null);
        EasyMock.expect(class2.getSuperClass()).andReturn(null);
        EasyMock.expect(class3.getSuperClass()).andReturn(class2);
        EasyMock.expect(class4.getSuperClass()).andReturn(class2);

        // Record - interface
        EasyMock.expect(class1.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class2.getInterfaces()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(class3.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class4.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();


        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
        }}).anyTimes();
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }}).anyTimes();

        // Record - validate is constructor
        EasyMock.expect(method1_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method2_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method3_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method4_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isConstructor()).andReturn(false).anyTimes();

        // Record - validate is public
        EasyMock.expect(method1_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isPublic()).andReturn(true).anyTimes();

        // Record - validate Constructor Args
        EasyMock.expect(method1_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(dummyClass1);
            add(dummyClass2);
        }}).anyTimes();
        EasyMock.expect(method2_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method3_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method4_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field4_1);
            add(field4_2);
        }}).anyTimes();

        // Record - Validate Fields
        EasyMock.expect(field1_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field1_2.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field2_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field2_2.getType()).andReturn(class1).anyTimes();
        EasyMock.expect(field3_1.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field3_2.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_1.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_2.getType()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1).anyTimes();
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2).anyTimes();
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1).anyTimes();
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2).anyTimes();
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1).anyTimes();
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2).anyTimes();
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1).anyTimes();
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2).anyTimes();

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1).anyTimes();
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2).anyTimes();
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1).anyTimes();
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2).anyTimes();
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1).anyTimes();
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2).anyTimes();
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1).anyTimes();
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2).anyTimes();
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1).anyTimes();
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2).anyTimes();
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1).anyTimes();
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2).anyTimes();
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1).anyTimes();
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2).anyTimes();
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1).anyTimes();
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2).anyTimes();

        // Class Calls
        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3).anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4).anyTimes();

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class1).anyTimes();

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Replay
        EasyMock.replay(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);


        // Execute
        Rule rule          = new DecoratorPatternRule();
        List<Issue> issues = rule.apply(classes, options);


        // Verify
        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("class2", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("did not implement"));
        Assertions.assertTrue(issues.get(0).message.contains("method2"));
        Assertions.assertTrue(issues.get(0).message.contains("class1"));
        Assertions.assertEquals("class2", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("abstract decorator"));
        Assertions.assertTrue(issues.get(1).message.contains("class1"));
        Assertions.assertEquals("class4", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("decorator"));
        Assertions.assertTrue(issues.get(2).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(3).classValue);
        Assertions.assertTrue(issues.get(3).message.contains("decorator"));
        Assertions.assertTrue(issues.get(3).message.contains("class1"));

        EasyMock.verify(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);
    }


    @Test
    public void missingSuperCall () {
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass1    = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass2    = EasyMock.mock(ClassNode.class);
        MethodNode method1_1            = EasyMock.mock(MethodNode.class);
        MethodNode method1_2            = EasyMock.mock(MethodNode.class);
        MethodNode method2_1            = EasyMock.mock(MethodNode.class);
        MethodNode method2_2            = EasyMock.mock(MethodNode.class);
        MethodNode method3_1            = EasyMock.mock(MethodNode.class);
        MethodNode method3_2            = EasyMock.mock(MethodNode.class);
        MethodNode method4_1            = EasyMock.mock(MethodNode.class);
        MethodNode method4_2            = EasyMock.mock(MethodNode.class);
        FieldNode field1_1            = EasyMock.mock(FieldNode.class);
        FieldNode field1_2            = EasyMock.mock(FieldNode.class);
        FieldNode field2_1            = EasyMock.mock(FieldNode.class);
        FieldNode field2_2            = EasyMock.mock(FieldNode.class);
        FieldNode field3_1            = EasyMock.mock(FieldNode.class);
        FieldNode field3_2            = EasyMock.mock(FieldNode.class);
        FieldNode field4_1            = EasyMock.mock(FieldNode.class);
        FieldNode field4_2            = EasyMock.mock(FieldNode.class);
        InsnList         insnList1_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList1_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_2    = EasyMock.mock(InsnList.class);
        AbstractInsnNode aInsnNode1_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode   mInsnNode1_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_2 = EasyMock.mock(MethodInsnNode.class);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingMethodSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingSuperCallSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);

        EasyMock.expect(class1.getSourceFile()).andReturn("file1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("file2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("file3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("file4").anyTimes();
        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();
        EasyMock.expect(dummyClass1.getClassName()).andReturn("dummyClass1").anyTimes();
        EasyMock.expect(dummyClass2.getClassName()).andReturn("dummyClass2").anyTimes();
        EasyMock.expect(method1_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method1_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method2_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method2_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method3_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method3_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method4_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method4_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodName()).andReturn("method2").anyTimes();

        // Record - is Abstract
        EasyMock.expect(class1.isAbstract()).andReturn(false);
        EasyMock.expect(class2.isAbstract()).andReturn(true);
        EasyMock.expect(class3.isAbstract()).andReturn(false);
        EasyMock.expect(class4.isAbstract()).andReturn(false);

        // Record - super class
        EasyMock.expect(class1.getSuperClass()).andReturn(null);
        EasyMock.expect(class2.getSuperClass()).andReturn(null);
        EasyMock.expect(class3.getSuperClass()).andReturn(class2);
        EasyMock.expect(class4.getSuperClass()).andReturn(class2);

        // Record - interface
        EasyMock.expect(class1.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class2.getInterfaces()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(class3.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class4.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();


        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }}).anyTimes();

        // Record - validate is constructor
        EasyMock.expect(method1_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method2_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method3_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method4_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isConstructor()).andReturn(false).anyTimes();

        // Record - validate is public
        EasyMock.expect(method1_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isPublic()).andReturn(true).anyTimes();

        // Record - validate Constructor Args
        EasyMock.expect(method1_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(dummyClass1);
            add(dummyClass2);
        }}).anyTimes();
        EasyMock.expect(method2_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method3_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method4_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field4_1);
            add(field4_2);
        }}).anyTimes();

        // Record - Validate Fields
        EasyMock.expect(field1_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field1_2.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field2_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field2_2.getType()).andReturn(class1).anyTimes();
        EasyMock.expect(field3_1.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field3_2.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_1.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_2.getType()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1).anyTimes();
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2).anyTimes();
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1).anyTimes();
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2).anyTimes();
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1).anyTimes();
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2).anyTimes();
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1).anyTimes();
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2).anyTimes();

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1).anyTimes();
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2).anyTimes();
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1).anyTimes();
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2).anyTimes();
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1).anyTimes();
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2).anyTimes();
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1).anyTimes();
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2).anyTimes();
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1).anyTimes();
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2).anyTimes();
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1).anyTimes();
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2).anyTimes();
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1).anyTimes();
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2).anyTimes();
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1).anyTimes();
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2).anyTimes();

        // Class Calls
        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3).anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4).anyTimes();

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class1).anyTimes();

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Replay
        EasyMock.replay(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);


        // Execute
        Rule rule          = new DecoratorPatternRule();
        List<Issue> issues = rule.apply(classes, options);


        // Verify
        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("class2", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("abstract decorator"));
        Assertions.assertTrue(issues.get(0).message.contains("class1"));
        Assertions.assertEquals("class4", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("decorator"));
        Assertions.assertTrue(issues.get(1).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(3).classValue);
        Assertions.assertTrue(issues.get(3).message.contains("decorator"));
        Assertions.assertTrue(issues.get(3).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("did not call super"));
        Assertions.assertTrue(issues.get(2).message.contains("method2"));
        Assertions.assertTrue(issues.get(2).message.contains("class2"));

        EasyMock.verify(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);
    }


    @Test
    public void extraClass () {
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass1    = EasyMock.mock(ClassNode.class);
        ClassNode        dummyClass2    = EasyMock.mock(ClassNode.class);
        MethodNode method1_1            = EasyMock.mock(MethodNode.class);
        MethodNode method1_2            = EasyMock.mock(MethodNode.class);
        MethodNode method2_1            = EasyMock.mock(MethodNode.class);
        MethodNode method2_2            = EasyMock.mock(MethodNode.class);
        MethodNode method3_1            = EasyMock.mock(MethodNode.class);
        MethodNode method3_2            = EasyMock.mock(MethodNode.class);
        MethodNode method4_1            = EasyMock.mock(MethodNode.class);
        MethodNode method4_2            = EasyMock.mock(MethodNode.class);
        FieldNode field1_1            = EasyMock.mock(FieldNode.class);
        FieldNode field1_2            = EasyMock.mock(FieldNode.class);
        FieldNode field2_1            = EasyMock.mock(FieldNode.class);
        FieldNode field2_2            = EasyMock.mock(FieldNode.class);
        FieldNode field3_1            = EasyMock.mock(FieldNode.class);
        FieldNode field3_2            = EasyMock.mock(FieldNode.class);
        FieldNode field4_1            = EasyMock.mock(FieldNode.class);
        FieldNode field4_2            = EasyMock.mock(FieldNode.class);
        InsnList         insnList1_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList1_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList2_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList3_2    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_1    = EasyMock.mock(InsnList.class);
        InsnList         insnList4_2    = EasyMock.mock(InsnList.class);
        AbstractInsnNode aInsnNode1_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode1_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode2_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode3_2_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_1_2 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_1 = EasyMock.mock(AbstractInsnNode.class);
        AbstractInsnNode aInsnNode4_2_2 = EasyMock.mock(AbstractInsnNode.class);
        MethodInsnNode   mInsnNode1_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode1_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode2_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode3_2_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_1_2 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_1 = EasyMock.mock(MethodInsnNode.class);
        MethodInsnNode   mInsnNode4_2_2 = EasyMock.mock(MethodInsnNode.class);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingMethodSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.get("missingSuperCallSeverity", Severity.class, "DecoratorPatternRule")).andReturn(Severity.WARNING);

        EasyMock.expect(class1.getSourceFile()).andReturn("file1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("file2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("file3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("file4").anyTimes();
        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();
        EasyMock.expect(dummyClass1.getClassName()).andReturn("dummyClass1").anyTimes();
        EasyMock.expect(dummyClass2.getClassName()).andReturn("dummyClass2").anyTimes();
        EasyMock.expect(method1_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method1_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method2_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method2_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method3_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method3_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(method4_1.getName()).andReturn("method1").anyTimes();
        EasyMock.expect(method4_2.getName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_1_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodName()).andReturn("method2").anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodName()).andReturn("method1").anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodName()).andReturn("method2").anyTimes();

        // Record - is Abstract
        EasyMock.expect(class1.isAbstract()).andReturn(false);
        EasyMock.expect(class2.isAbstract()).andReturn(true);
        EasyMock.expect(class3.isAbstract()).andReturn(false);
        EasyMock.expect(class4.isAbstract()).andReturn(false);

        // Record - super class
        EasyMock.expect(class1.getSuperClass()).andReturn(null);
        EasyMock.expect(class2.getSuperClass()).andReturn(null);
        EasyMock.expect(class3.getSuperClass()).andReturn(class2);
        EasyMock.expect(class4.getSuperClass()).andReturn(class2);

        // Record - interface
        EasyMock.expect(class1.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class2.getInterfaces()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(class3.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();
        EasyMock.expect(class4.getInterfaces()).andReturn(new ArrayList<>()).anyTimes();


        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }}).anyTimes();

        // Record - validate is constructor
        EasyMock.expect(method1_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method2_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method3_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isConstructor()).andReturn(false).anyTimes();
        EasyMock.expect(method4_1.isConstructor()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isConstructor()).andReturn(false).anyTimes();

        // Record - validate is public
        EasyMock.expect(method1_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method1_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method2_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method3_2.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_1.isPublic()).andReturn(true).anyTimes();
        EasyMock.expect(method4_2.isPublic()).andReturn(true).anyTimes();

        // Record - validate Constructor Args
        EasyMock.expect(method1_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(dummyClass1);
            add(dummyClass2);
        }}).anyTimes();
        EasyMock.expect(method2_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method3_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
            add(class1);
        }}).anyTimes();
        EasyMock.expect(method4_1.getArgumentTypes()).andReturn(new ArrayList<ClassNode>(){{
        }}).anyTimes();

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
        }}).anyTimes();
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
        }}).anyTimes();
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
        }}).anyTimes();
        EasyMock.expect(class4.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field4_1);
            add(field4_2);
        }}).anyTimes();

        // Record - Validate Fields
        EasyMock.expect(field1_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field1_2.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field2_1.getType()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(field2_2.getType()).andReturn(class1).anyTimes();
        EasyMock.expect(field3_1.getType()).andReturn(dummyClass2).anyTimes();
        EasyMock.expect(field3_2.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_1.getType()).andReturn(class2).anyTimes();
        EasyMock.expect(field4_2.getType()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1).anyTimes();
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2).anyTimes();
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1).anyTimes();
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2).anyTimes();
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1).anyTimes();
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2).anyTimes();
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1).anyTimes();
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2).anyTimes();

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1).anyTimes();
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2).anyTimes();
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1).anyTimes();
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2).anyTimes();
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1).anyTimes();
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2).anyTimes();
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1).anyTimes();
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2).anyTimes();
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1).anyTimes();
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2).anyTimes();
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1).anyTimes();
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2).anyTimes();
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1).anyTimes();
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2).anyTimes();
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1).anyTimes();
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true).anyTimes();

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1).anyTimes();
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2).anyTimes();
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1).anyTimes();
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2).anyTimes();
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1).anyTimes();
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2).anyTimes();
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1).anyTimes();
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2).anyTimes();
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1).anyTimes();
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2).anyTimes();
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1).anyTimes();
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2).anyTimes();
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1).anyTimes();
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2).anyTimes();
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1).anyTimes();
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2).anyTimes();

        // Class Calls
        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3).anyTimes();
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4).anyTimes();

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class1).anyTimes();
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class1).anyTimes();

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(dummyClass1).anyTimes();
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(dummyClass1).anyTimes();

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class2).anyTimes();
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2).anyTimes();

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Replay
        EasyMock.replay(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);


        // Execute
        Rule rule          = new DecoratorPatternRule();
        List<Issue> issues = rule.apply(classes, options);


        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("class2", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("abstract decorator"));
        Assertions.assertTrue(issues.get(0).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("decorator"));
        Assertions.assertTrue(issues.get(2).message.contains("class1"));
        Assertions.assertEquals("class3", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("did not call super"));
        Assertions.assertTrue(issues.get(1).message.contains("method2"));
        Assertions.assertTrue(issues.get(1).message.contains("class2"));

        EasyMock.verify(options, class1, class2, class3, class4, dummyClass1,
                dummyClass2, method1_1, method1_2, method2_1, method2_2,
                method3_1, method3_2, method4_1, method4_2, field1_1,
                field1_2, field2_1, field2_2, field3_1, field3_2, field4_1,
                field4_2, insnList1_1, insnList1_2, insnList2_1, insnList2_2,
                insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);
    }




    @Test
    public void applyIntegration() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.DecoratorPattern.ClassWrapping", classReader.createClassNode("TestClasses.DecoratorPattern.ClassWrapping"));
        classNodes.put("TestClasses.DecoratorPattern.Wrapper", classReader.createClassNode("TestClasses.DecoratorPattern.Wrapper"));
        classNodes.put("TestClasses.DecoratorPattern.WrapperImplementation1", classReader.createClassNode("TestClasses.DecoratorPattern.WrapperImplementation1"));
        classNodes.put("TestClasses.DecoratorPattern.WrapperImplementation2", classReader.createClassNode("TestClasses.DecoratorPattern.WrapperImplementation2"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");
        options.put("missingMethodSeverity", "WARNING");
        options.put("missingSuperCallSeverity", "WARNING");

        // Execute
        Rule rule = new DecoratorPatternRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(5, issues.size());
        Assertions.assertEquals("TestClasses.DecoratorPattern.Wrapper", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("method2"));
        Assertions.assertTrue(issues.get(0).message.contains("ClassWrapping"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);

        Assertions.assertEquals("TestClasses.DecoratorPattern.Wrapper", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("TestClasses.DecoratorPattern.ClassWrapping"));
        Assertions.assertEquals(Severity.INFO, issues.get(1).severity);

        Assertions.assertEquals("TestClasses.DecoratorPattern.WrapperImplementation2", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("TestClasses.DecoratorPattern.ClassWrapping"));
        Assertions.assertEquals(Severity.INFO, issues.get(2).severity);

        Assertions.assertEquals("TestClasses.DecoratorPattern.WrapperImplementation1", issues.get(3).classValue);
        Assertions.assertTrue(issues.get(3).message.contains("method2"));
        Assertions.assertTrue(issues.get(3).message.contains("TestClasses.DecoratorPattern.Wrapper"));
        Assertions.assertEquals(Severity.WARNING, issues.get(3).severity);

        Assertions.assertEquals("TestClasses.DecoratorPattern.WrapperImplementation1", issues.get(4).classValue);
        Assertions.assertTrue(issues.get(4).message.contains("TestClasses.DecoratorPattern.ClassWrapping"));
        Assertions.assertEquals(Severity.INFO, issues.get(4).severity);
    }


}
