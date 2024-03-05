package Domain;

import Data.DirectedGraphModel.Graph;
import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.DirectedGraphModel.LineColor;
import Data.DirectedGraphModel.LineStyle;
import Data.JavaByteCodeAdapter.*;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.Options;
import Domain.Rules.HollywoodPrincipleRule;
import guru.nidi.graphviz.attribute.Style;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HollywoodPrincipalRuleTest {


    @Test
    public void applyValidDefault() {

        // Setup
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        MethodNode       method1_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method1_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_2      = EasyMock.mock(MethodNode.class);
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

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "HollywoodPrincipleRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.hasKey("ignore")).andReturn(false);

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
                add(method1_1);
                add(method1_2);
            }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }});
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }});
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }});

        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class2.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class3.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class4.isValid()).andReturn(true).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1);
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2);
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1);
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2);
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1);
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2);
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1);
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2);

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1);
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2);
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1);
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2);
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1);
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2);
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1);
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2);
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1);
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2);
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1);
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2);
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1);
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2);
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1);
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2);

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true);

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1);
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2);
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1);
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2);
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1);
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2);
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1);
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2);
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1);
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2);
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1);
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2);
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1);
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2);
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1);
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2);

        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.replay(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);

        // Execute
        Rule rule          = new HollywoodPrincipleRule();
        List<Issue> issues = rule.apply(classes, options);

        // Verify
        Assertions.assertEquals(0, issues.size());
        EasyMock.verify(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
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
    public void applyInvalidSingleCycleDefault() {

        // Setup
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        MethodNode       method1_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method1_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_2      = EasyMock.mock(MethodNode.class);
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

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "HollywoodPrincipleRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.hasKey("ignore")).andReturn(false);

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }});
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }});
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }});

        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.getSourceFile()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class2.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class3.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class4.isValid()).andReturn(true).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1);
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2);
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1);
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2);
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1);
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2);
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1);
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2);

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1);
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2);
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1);
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2);
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1);
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2);
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1);
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2);
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1);
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2);
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1);
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2);
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1);
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2);
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1);
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2);

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true);

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1);
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2);
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1);
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2);
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1);
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2);
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1);
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2);
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1);
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2);
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1);
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2);
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1);
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2);
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1);
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2);

        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2);

        EasyMock.replay(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);

        // Execute
        Rule rule          = new HollywoodPrincipleRule();
        List<Issue> issues = rule.apply(classes, options);

        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("class4", issues.get(0).classValue);
        Assertions.assertEquals("class3", issues.get(1).classValue);
        Assertions.assertEquals("class2", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("class3"));
        Assertions.assertTrue(issues.get(0).message.contains("class2"));
        Assertions.assertTrue(issues.get(1).message.contains("class4"));
        Assertions.assertTrue(issues.get(1).message.contains("class2"));
        Assertions.assertTrue(issues.get(2).message.contains("class3"));
        Assertions.assertTrue(issues.get(2).message.contains("class4"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(1).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(2).severity);

        EasyMock.verify(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
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
    public void applyInvalidMultipleCycleDefault() {

        // Setup
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        MethodNode       method1_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method1_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_2      = EasyMock.mock(MethodNode.class);
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

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "HollywoodPrincipleRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.hasKey("ignore")).andReturn(false);

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }});
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }});
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }});

        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.getSourceFile()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class2.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class3.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class4.isValid()).andReturn(true).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1);
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2);
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1);
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2);
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1);
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2);
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1);
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2);

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1);
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2);
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1);
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2);
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1);
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2);
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1);
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2);
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1);
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2);
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1);
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2);
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1);
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2);
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1);
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2);

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true);

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1);
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2);
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1);
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2);
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1);
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2);
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1);
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2);
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1);
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2);
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1);
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2);
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1);
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2);
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1);
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2);

        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class3);

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class1);
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class1);

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.replay(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);

        // Execute
        Rule rule          = new HollywoodPrincipleRule();
        List<Issue> issues = rule.apply(classes, options);

        // Verify
        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("class4", issues.get(0).classValue);
        Assertions.assertEquals("class3", issues.get(1).classValue);
        Assertions.assertEquals("class2", issues.get(2).classValue);
        Assertions.assertEquals("class1", issues.get(3).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("class3"));
        Assertions.assertTrue(issues.get(1).message.contains("class4"));
        Assertions.assertTrue(issues.get(2).message.contains("class1"));
        Assertions.assertTrue(issues.get(3).message.contains("class2"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(1).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(2).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(3).severity);

        EasyMock.verify(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
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
    public void applyInvalidMultipleCycleAlt() {

        // Setup
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        MethodNode       method1_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method1_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_2      = EasyMock.mock(MethodNode.class);
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

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "HollywoodPrincipleRule")).andReturn(Severity.INFO);
        EasyMock.expect(options.hasKey("ignore")).andReturn(true);
        EasyMock.expect(options.get("ignore")).andReturn("class4, class3");

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }});

        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.getSourceFile()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("class2").anyTimes();

        EasyMock.expect(class1.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class2.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class3.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class4.isValid()).andReturn(true).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1);
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2);
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1);
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2);

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1);
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2);
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1);
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2);
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1);
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2);
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1);
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2);

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true);

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1);
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2);
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1);
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2);
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1);
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2);
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1);
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2);

        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class3);

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class1);
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class3);

        EasyMock.replay(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);

        // Execute
        Rule rule          = new HollywoodPrincipleRule();
        List<Issue> issues = rule.apply(classes, options);

        // Verify
        Assertions.assertEquals(2, issues.size());
        Assertions.assertEquals("class2", issues.get(0).classValue);
        Assertions.assertEquals("class1", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("class1"));
        Assertions.assertTrue(issues.get(1).message.contains("class2"));
        Assertions.assertEquals(Severity.INFO, issues.get(0).severity);
        Assertions.assertEquals(Severity.INFO, issues.get(1).severity);

        EasyMock.verify(options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
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
    public void applyGraph() {

        // Setup
        Graph            graph          = EasyMock.mock(Graph.class);
        Options          options        = EasyMock.mock(Options.class);
        ClassNode        class1         = EasyMock.mock(ClassNode.class);
        ClassNode        class2         = EasyMock.mock(ClassNode.class);
        ClassNode        class3         = EasyMock.mock(ClassNode.class);
        ClassNode        class4         = EasyMock.mock(ClassNode.class);
        MethodNode       method1_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method1_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method2_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method3_2      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_1      = EasyMock.mock(MethodNode.class);
        MethodNode       method4_2      = EasyMock.mock(MethodNode.class);
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

        Map<String, ClassNode> classes  = new HashMap<>();
        classes.put("class1", class1);
        classes.put("class2", class2);
        classes.put("class3", class3);
        classes.put("class4", class4);

        // Record
        EasyMock.expect(options.get("severity", Severity.class, "HollywoodPrincipleRule")).andReturn(Severity.WARNING);
        EasyMock.expect(options.hasKey("ignore")).andReturn(false);

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
            add(method2_2);
        }});
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method3_1);
            add(method3_2);
        }});
        EasyMock.expect(class4.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method4_1);
            add(method4_2);
        }});

        EasyMock.expect(class1.getClassName()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getClassName()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getClassName()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getClassName()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.getSourceFile()).andReturn("class1").anyTimes();
        EasyMock.expect(class2.getSourceFile()).andReturn("class2").anyTimes();
        EasyMock.expect(class3.getSourceFile()).andReturn("class3").anyTimes();
        EasyMock.expect(class4.getSourceFile()).andReturn("class4").anyTimes();

        EasyMock.expect(class1.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class2.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class3.isValid()).andReturn(true).anyTimes();
        EasyMock.expect(class4.isValid()).andReturn(true).anyTimes();

        EasyMock.expect(method1_1.getInstructions()).andReturn(insnList1_1);
        EasyMock.expect(method1_2.getInstructions()).andReturn(insnList1_2);
        EasyMock.expect(method2_1.getInstructions()).andReturn(insnList2_1);
        EasyMock.expect(method2_2.getInstructions()).andReturn(insnList2_2);
        EasyMock.expect(method3_1.getInstructions()).andReturn(insnList3_1);
        EasyMock.expect(method3_2.getInstructions()).andReturn(insnList3_2);
        EasyMock.expect(method4_1.getInstructions()).andReturn(insnList4_1);
        EasyMock.expect(method4_2.getInstructions()).andReturn(insnList4_2);

        EasyMock.expect(insnList1_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList1_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList2_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList3_2.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_1.size()).andReturn(2).anyTimes();
        EasyMock.expect(insnList4_2.size()).andReturn(2).anyTimes();

        EasyMock.expect(insnList1_1.get(0)).andReturn(aInsnNode1_1_1);
        EasyMock.expect(insnList1_1.get(1)).andReturn(aInsnNode1_1_2);
        EasyMock.expect(insnList1_2.get(0)).andReturn(aInsnNode1_2_1);
        EasyMock.expect(insnList1_2.get(1)).andReturn(aInsnNode1_2_2);
        EasyMock.expect(insnList2_1.get(0)).andReturn(aInsnNode2_1_1);
        EasyMock.expect(insnList2_1.get(1)).andReturn(aInsnNode2_1_2);
        EasyMock.expect(insnList2_2.get(0)).andReturn(aInsnNode2_2_1);
        EasyMock.expect(insnList2_2.get(1)).andReturn(aInsnNode2_2_2);
        EasyMock.expect(insnList3_1.get(0)).andReturn(aInsnNode3_1_1);
        EasyMock.expect(insnList3_1.get(1)).andReturn(aInsnNode3_1_2);
        EasyMock.expect(insnList3_2.get(0)).andReturn(aInsnNode3_2_1);
        EasyMock.expect(insnList3_2.get(1)).andReturn(aInsnNode3_2_2);
        EasyMock.expect(insnList4_1.get(0)).andReturn(aInsnNode4_1_1);
        EasyMock.expect(insnList4_1.get(1)).andReturn(aInsnNode4_1_2);
        EasyMock.expect(insnList4_2.get(0)).andReturn(aInsnNode4_2_1);
        EasyMock.expect(insnList4_2.get(1)).andReturn(aInsnNode4_2_2);

        EasyMock.expect(aInsnNode1_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode1_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode2_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode3_2_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_1_2.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_1.isMethodInsnNode()).andReturn(true);
        EasyMock.expect(aInsnNode4_2_2.isMethodInsnNode()).andReturn(true);

        EasyMock.expect(aInsnNode1_1_1.getMethodInsnNode()).andReturn(mInsnNode1_1_1);
        EasyMock.expect(aInsnNode1_1_2.getMethodInsnNode()).andReturn(mInsnNode1_1_2);
        EasyMock.expect(aInsnNode1_2_1.getMethodInsnNode()).andReturn(mInsnNode1_2_1);
        EasyMock.expect(aInsnNode1_2_2.getMethodInsnNode()).andReturn(mInsnNode1_2_2);
        EasyMock.expect(aInsnNode2_1_1.getMethodInsnNode()).andReturn(mInsnNode2_1_1);
        EasyMock.expect(aInsnNode2_1_2.getMethodInsnNode()).andReturn(mInsnNode2_1_2);
        EasyMock.expect(aInsnNode2_2_1.getMethodInsnNode()).andReturn(mInsnNode2_2_1);
        EasyMock.expect(aInsnNode2_2_2.getMethodInsnNode()).andReturn(mInsnNode2_2_2);
        EasyMock.expect(aInsnNode3_1_1.getMethodInsnNode()).andReturn(mInsnNode3_1_1);
        EasyMock.expect(aInsnNode3_1_2.getMethodInsnNode()).andReturn(mInsnNode3_1_2);
        EasyMock.expect(aInsnNode3_2_1.getMethodInsnNode()).andReturn(mInsnNode3_2_1);
        EasyMock.expect(aInsnNode3_2_2.getMethodInsnNode()).andReturn(mInsnNode3_2_2);
        EasyMock.expect(aInsnNode4_1_1.getMethodInsnNode()).andReturn(mInsnNode4_1_1);
        EasyMock.expect(aInsnNode4_1_2.getMethodInsnNode()).andReturn(mInsnNode4_1_2);
        EasyMock.expect(aInsnNode4_2_1.getMethodInsnNode()).andReturn(mInsnNode4_2_1);
        EasyMock.expect(aInsnNode4_2_2.getMethodInsnNode()).andReturn(mInsnNode4_2_2);

        EasyMock.expect(mInsnNode1_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode1_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode1_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode2_1_1.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_1_2.getMethodOwner()).andReturn(class2);
        EasyMock.expect(mInsnNode2_2_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode2_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode3_1_1.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_1_2.getMethodOwner()).andReturn(class3);
        EasyMock.expect(mInsnNode3_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode3_2_2.getMethodOwner()).andReturn(class4);

        EasyMock.expect(mInsnNode4_1_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_1_2.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_1.getMethodOwner()).andReturn(class4);
        EasyMock.expect(mInsnNode4_2_2.getMethodOwner()).andReturn(class2);

        graph.addNode("class1");
        graph.addNode("class2");
        graph.addNode("class3");
        graph.addNode("class4");

        graph.addEdge("class4", "class2", LineColor.RED, LineStyle.SOLID);
        graph.addEdge("class3", "class4", LineColor.RED, LineStyle.SOLID);
        graph.addEdge("class2", "class4", LineColor.RED, LineStyle.SOLID);
        graph.addEdge("class2", "class3", LineColor.RED, LineStyle.SOLID);
        graph.addEdge("class1", "class4", LineColor.BLACK, LineStyle.SOLID);
        graph.addEdge("class1", "class3", LineColor.BLACK, LineStyle.SOLID);
        graph.addEdge("class1", "class2", LineColor.BLACK, LineStyle.SOLID);

        graph.exportPNG(EasyMock.anyInt(), EasyMock.anyObject());

        EasyMock.expectLastCall();

        EasyMock.replay(graph, options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
                aInsnNode1_1_1, aInsnNode1_1_2, aInsnNode1_2_1, aInsnNode1_2_2,
                aInsnNode2_1_1, aInsnNode2_1_2, aInsnNode2_2_1, aInsnNode2_2_2,
                aInsnNode3_1_1, aInsnNode3_1_2, aInsnNode3_2_1, aInsnNode3_2_2,
                aInsnNode4_1_1, aInsnNode4_1_2, aInsnNode4_2_1, aInsnNode4_2_2,
                mInsnNode1_1_1, mInsnNode1_1_2, mInsnNode1_2_1, mInsnNode1_2_2,
                mInsnNode2_1_1, mInsnNode2_1_2, mInsnNode2_2_1, mInsnNode2_2_2,
                mInsnNode3_1_1, mInsnNode3_1_2, mInsnNode3_2_1, mInsnNode3_2_2,
                mInsnNode4_1_1, mInsnNode4_1_2, mInsnNode4_2_1, mInsnNode4_2_2);

        // Execute
        Rule rule          = new HollywoodPrincipleRule(graph);
        List<Issue> issues = rule.apply(classes, options);

        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("class4", issues.get(0).classValue);
        Assertions.assertEquals("class3", issues.get(1).classValue);
        Assertions.assertEquals("class2", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("class3"));
        Assertions.assertTrue(issues.get(0).message.contains("class2"));
        Assertions.assertTrue(issues.get(1).message.contains("class4"));
        Assertions.assertTrue(issues.get(1).message.contains("class2"));
        Assertions.assertTrue(issues.get(2).message.contains("class3"));
        Assertions.assertTrue(issues.get(2).message.contains("class4"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(1).severity);
        Assertions.assertEquals(Severity.WARNING, issues.get(2).severity);

        EasyMock.verify(graph, options, class1, class2, class3, class4, method1_1,
                method1_2, method2_1, method2_2, method3_1, method3_2,
                method4_1, method4_2, insnList1_1, insnList1_2, insnList2_1,
                insnList2_2, insnList3_1, insnList3_2, insnList4_1, insnList4_2,
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
        classNodes.put("TestClasses.HollywoodPrincipalA", classReader.createClassNode("TestClasses.HollywoodPrincipalA"));
        classNodes.put("TestClasses.HollywoodPrincipalB", classReader.createClassNode("TestClasses.HollywoodPrincipalB"));
        classNodes.put("TestClasses.HollywoodPrincipalC", classReader.createClassNode("TestClasses.HollywoodPrincipalC"));
        classNodes.put("TestClasses.HollywoodPrincipalD", classReader.createClassNode("TestClasses.HollywoodPrincipalD"));
        classNodes.put("TestClasses.HollywoodPrincipalE", classReader.createClassNode("TestClasses.HollywoodPrincipalE"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "WARNING");

        // Execute
        Rule rule = new HollywoodPrincipleRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("TestClasses.HollywoodPrincipalE", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("HollywoodPrincipalC"));
        Assertions.assertTrue(issues.get(0).message.contains("HollywoodPrincipalD"));
        Assertions.assertEquals("TestClasses.HollywoodPrincipalC", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("HollywoodPrincipalE"));
        Assertions.assertTrue(issues.get(1).message.contains("HollywoodPrincipalD"));
        Assertions.assertEquals("TestClasses.HollywoodPrincipalD", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("HollywoodPrincipalE"));
        Assertions.assertTrue(issues.get(2).message.contains("HollywoodPrincipalC"));
    }


    @Test
    public void applyIntegrationGraph() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.HollywoodPrincipalA", classReader.createClassNode("TestClasses.HollywoodPrincipalA"));
        classNodes.put("TestClasses.HollywoodPrincipalB", classReader.createClassNode("TestClasses.HollywoodPrincipalB"));
        classNodes.put("TestClasses.HollywoodPrincipalC", classReader.createClassNode("TestClasses.HollywoodPrincipalC"));
        classNodes.put("TestClasses.HollywoodPrincipalD", classReader.createClassNode("TestClasses.HollywoodPrincipalD"));
        classNodes.put("TestClasses.HollywoodPrincipalE", classReader.createClassNode("TestClasses.HollywoodPrincipalE"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "WARNING");

        // Execute
        Rule rule = new HollywoodPrincipleRule(new GraphImplementationNidi());
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("TestClasses.HollywoodPrincipalE", issues.get(0).classValue);
        Assertions.assertTrue(issues.get(0).message.contains("HollywoodPrincipalC"));
        Assertions.assertTrue(issues.get(0).message.contains("HollywoodPrincipalD"));
        Assertions.assertEquals("TestClasses.HollywoodPrincipalC", issues.get(1).classValue);
        Assertions.assertTrue(issues.get(1).message.contains("HollywoodPrincipalE"));
        Assertions.assertTrue(issues.get(1).message.contains("HollywoodPrincipalD"));
        Assertions.assertEquals("TestClasses.HollywoodPrincipalD", issues.get(2).classValue);
        Assertions.assertTrue(issues.get(2).message.contains("HollywoodPrincipalE"));
        Assertions.assertTrue(issues.get(2).message.contains("HollywoodPrincipalC"));
    }



}
