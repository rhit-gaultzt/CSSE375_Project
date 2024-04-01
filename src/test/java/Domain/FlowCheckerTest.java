package Domain;

import Data.JavaByteCodeAdapter.AbstractInsnNode;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.InsnList;
import Data.JavaByteCodeAdapter.MethodNode;
import Domain.Rules.FlowChecker;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowCheckerTest {

//    @Test
//    public void testCheckerWithoutIssue () {
//        ClassNode mockClassOne = EasyMock.mock(ClassNode.class);
//        MethodNode mockMethodOne = EasyMock.mock(MethodNode.class);
//        MethodNode mockMethodTwo = EasyMock.mock(MethodNode.class);
//        MethodNode mockMethodThree = EasyMock.mock(MethodNode.class);
//        MethodNode mockMethodFour = EasyMock.mock(MethodNode.class);
//        MethodNode mockMethodFive = EasyMock.mock(MethodNode.class);
//
//        List<ClassNode> nodes = new ArrayList<>();
//        nodes.add(mockClassNode);
//    }
//
//    EasyMock.replay(methodOne, methodTwo, classOne, classTwo);

    @Test
    public void testApplyMethodWithNoJumpsAndSwitches() {
        // Create mock objects

        ClassNode classNode = EasyMock.createMock(ClassNode.class);
        MethodNode methodNode = EasyMock.createMock(MethodNode.class);
        InsnList instructions = EasyMock.createMock(InsnList.class);
        AbstractInsnNode instructionOne = EasyMock.createMock(AbstractInsnNode.class);

        List<MethodNode> methods = new ArrayList<>();
        methods.add(methodNode);

        EasyMock.expect(classNode.getMethods()).andReturn(methods).once();
        EasyMock.expect(methodNode.getInstructions()).andReturn(instructions).once();
        EasyMock.expect(instructions.size()).andReturn(1).times(2);
        EasyMock.expect(instructions.get(0)).andReturn(instructionOne).once();
        EasyMock.expect(instructionOne.isJumpNode()).andReturn(false).once();
        EasyMock.expect(instructionOne.isSwitchNode()).andReturn(false).once();

        FlowChecker checker = new FlowChecker();
        Map<String, ClassNode> map = new HashMap<>();
        map.put("classNode", classNode);

        EasyMock.replay(classNode, methodNode, instructions, instructionOne);
        List<Issue> actualIssues = checker.apply(map, checker.getDefaultOptions());
        EasyMock.verify(classNode, methodNode, instructions, instructionOne);

        Assertions.assertEquals(0, actualIssues.size());
    }

    @Test
    public void testApplyMethodWithOneJumpsAndSwitches() {
        // Create mock objects

        ClassNode classNode = EasyMock.createMock(ClassNode.class);
        MethodNode methodNode = EasyMock.createMock(MethodNode.class);
        InsnList instructions = EasyMock.createMock(InsnList.class);
        AbstractInsnNode instructionOne = EasyMock.createMock(AbstractInsnNode.class);

        List<MethodNode> methods = new ArrayList<>();
        methods.add(methodNode);

        EasyMock.expect(classNode.getMethods()).andReturn(methods).once();
        EasyMock.expect(methodNode.getInstructions()).andReturn(instructions).once();
        EasyMock.expect(instructions.size()).andReturn(1).times(2);
        EasyMock.expect(instructions.get(0)).andReturn(instructionOne).once();
        EasyMock.expect(instructionOne.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionOne.isSwitchNode()).andReturn(false).anyTimes();

        FlowChecker checker = new FlowChecker();
        Map<String, ClassNode> map = new HashMap<>();
        map.put("classNode", classNode);

        EasyMock.replay(classNode, methodNode, instructions, instructionOne);
        List<Issue> actualIssues = checker.apply(map, checker.getDefaultOptions());
        EasyMock.verify(classNode, methodNode, instructions, instructionOne);

        Assertions.assertEquals(0, actualIssues.size());
    }

    @Test
    public void testApplyMethodWithFiveJumpsAndSwitches() {
        // Create mock objects

        ClassNode classNode = EasyMock.createMock(ClassNode.class);
        MethodNode methodNode = EasyMock.createMock(MethodNode.class);
        InsnList instructions = EasyMock.createMock(InsnList.class);
        AbstractInsnNode instructionOne = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionTwo = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionThree = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFour = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFive = EasyMock.createMock(AbstractInsnNode.class);

        List<MethodNode> methods = new ArrayList<>();
        methods.add(methodNode);

        EasyMock.expect(classNode.getMethods()).andReturn(methods).once();
        EasyMock.expect(methodNode.getInstructions()).andReturn(instructions).once();
        EasyMock.expect(instructions.size()).andReturn(5).times(6);
        EasyMock.expect(instructions.get(0)).andReturn(instructionOne).once();
        EasyMock.expect(instructionOne.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionOne.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(1)).andReturn(instructionTwo).once();
        EasyMock.expect(instructionTwo.isJumpNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructionTwo.isSwitchNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructions.get(2)).andReturn(instructionThree).once();
        EasyMock.expect(instructionThree.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionThree.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(3)).andReturn(instructionFour).once();
        EasyMock.expect(instructionFour.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFour.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(4)).andReturn(instructionFive).once();
        EasyMock.expect(instructionFive.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFive.isSwitchNode()).andReturn(false).anyTimes();

        FlowChecker checker = new FlowChecker();
        Map<String, ClassNode> map = new HashMap<>();
        map.put("classNode", classNode);

        EasyMock.replay(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive);
        List<Issue> actualIssues = checker.apply(map, checker.getDefaultOptions());
        EasyMock.verify(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive);

        Assertions.assertEquals(0, actualIssues.size());
    }

    @Test
    public void testApplyMethodWithMoreThanTenJumpsAndSwitches() {
        // Create mock objects

        ClassNode classNode = EasyMock.createMock(ClassNode.class);
        MethodNode methodNode = EasyMock.createMock(MethodNode.class);
        InsnList instructions = EasyMock.createMock(InsnList.class);
        AbstractInsnNode instructionOne = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionTwo = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionThree = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFour = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFive = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionSix = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionSeven = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionEight = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionNine = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionTen = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionEleven = EasyMock.createMock(AbstractInsnNode.class);


        List<MethodNode> methods = new ArrayList<>();
        methods.add(methodNode);

        EasyMock.expect(classNode.getMethods()).andReturn(methods).once();
        EasyMock.expect(methodNode.getInstructions()).andReturn(instructions).once();
        EasyMock.expect(instructions.size()).andReturn(11).times(12);
        EasyMock.expect(instructions.get(0)).andReturn(instructionOne).once();
        EasyMock.expect(instructionOne.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionOne.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(1)).andReturn(instructionTwo).once();
        EasyMock.expect(instructionTwo.isJumpNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructionTwo.isSwitchNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructions.get(2)).andReturn(instructionThree).once();
        EasyMock.expect(instructionThree.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionThree.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(3)).andReturn(instructionFour).once();
        EasyMock.expect(instructionFour.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFour.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(4)).andReturn(instructionFive).once();
        EasyMock.expect(instructionFive.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFive.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(5)).andReturn(instructionSix).once();
        EasyMock.expect(instructionSix.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionSix.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(6)).andReturn(instructionSeven).once();
        EasyMock.expect(instructionSeven.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionSeven.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(7)).andReturn(instructionEight).once();
        EasyMock.expect(instructionEight.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionEight.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(8)).andReturn(instructionNine).once();
        EasyMock.expect(instructionNine.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionNine.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(9)).andReturn(instructionTen).once();
        EasyMock.expect(instructionTen.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionTen.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(10)).andReturn(instructionEleven).once();
        EasyMock.expect(instructionEleven.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionEleven.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(classNode.getFileName()).andReturn("ClassNode.java").anyTimes();
        EasyMock.expect(classNode.getClassName()).andReturn("ClassNode").anyTimes();
        EasyMock.expect(methodNode.getName()).andReturn("methodNode").anyTimes();

        FlowChecker checker = new FlowChecker();
        Map<String, ClassNode> map = new HashMap<>();
        map.put("classNode", classNode);

        EasyMock.replay(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive, instructionSix, instructionSeven, instructionEight, instructionNine, instructionTen, instructionEleven);
        List<Issue> actualIssues = checker.apply(map, checker.getDefaultOptions());
        EasyMock.verify(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive, instructionSix, instructionSeven, instructionEight, instructionNine, instructionTen, instructionEleven);

        Assertions.assertEquals(1, actualIssues.size());
    }

    @Test
    public void testApplyTwoMethodWithMoreThanTenJumpsAndSwitches() {
        // Create mock objects

        ClassNode classNode = EasyMock.createMock(ClassNode.class);
        MethodNode methodNode = EasyMock.createMock(MethodNode.class);
        InsnList instructions = EasyMock.createMock(InsnList.class);
        AbstractInsnNode instructionOne = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionTwo = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionThree = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFour = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionFive = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionSix = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionSeven = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionEight = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionNine = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionTen = EasyMock.createMock(AbstractInsnNode.class);
        AbstractInsnNode instructionEleven = EasyMock.createMock(AbstractInsnNode.class);


        List<MethodNode> methods = new ArrayList<>();
        methods.add(methodNode);
        methods.add(methodNode);

        EasyMock.expect(classNode.getMethods()).andReturn(methods).once();
        EasyMock.expect(methodNode.getInstructions()).andReturn(instructions).anyTimes();
        EasyMock.expect(instructions.size()).andReturn(11).anyTimes();
        EasyMock.expect(instructions.get(0)).andReturn(instructionOne).anyTimes();
        EasyMock.expect(instructionOne.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionOne.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(1)).andReturn(instructionTwo).anyTimes();
        EasyMock.expect(instructionTwo.isJumpNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructionTwo.isSwitchNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructions.get(2)).andReturn(instructionThree).anyTimes();
        EasyMock.expect(instructionThree.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionThree.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(3)).andReturn(instructionFour).anyTimes();
        EasyMock.expect(instructionFour.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFour.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(4)).andReturn(instructionFive).anyTimes();
        EasyMock.expect(instructionFive.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionFive.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(5)).andReturn(instructionSix).anyTimes();
        EasyMock.expect(instructionSix.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionSix.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(6)).andReturn(instructionSeven).anyTimes();
        EasyMock.expect(instructionSeven.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionSeven.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(7)).andReturn(instructionEight).anyTimes();
        EasyMock.expect(instructionEight.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionEight.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(8)).andReturn(instructionNine).anyTimes();
        EasyMock.expect(instructionNine.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionNine.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(9)).andReturn(instructionTen).anyTimes();
        EasyMock.expect(instructionTen.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionTen.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(instructions.get(10)).andReturn(instructionEleven).anyTimes();
        EasyMock.expect(instructionEleven.isJumpNode()).andReturn(true).anyTimes();
        EasyMock.expect(instructionEleven.isSwitchNode()).andReturn(false).anyTimes();
        EasyMock.expect(classNode.getFileName()).andReturn("ClassNode.java").anyTimes();
        EasyMock.expect(classNode.getClassName()).andReturn("ClassNode").anyTimes();
        EasyMock.expect(methodNode.getName()).andReturn("methodNode").anyTimes();

        FlowChecker checker = new FlowChecker();
        Map<String, ClassNode> map = new HashMap<>();
        map.put("classNode", classNode);

        EasyMock.replay(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive, instructionSix, instructionSeven, instructionEight, instructionNine, instructionTen, instructionEleven);
        List<Issue> actualIssues = checker.apply(map, checker.getDefaultOptions());
        EasyMock.verify(classNode, methodNode, instructions, instructionOne, instructionTwo, instructionThree, instructionFour, instructionFive, instructionSix, instructionSeven, instructionEight, instructionNine, instructionTen, instructionEleven);

        Assertions.assertEquals(2, actualIssues.size());
    }
}
