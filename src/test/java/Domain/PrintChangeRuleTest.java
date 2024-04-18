package Domain;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.InsnList;
import Domain.ChangeRules.PrintChangeRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class PrintChangeRuleTest {
    @Test
    public void testPrintChangeRuleAddedInstructions() throws IOException {
        // Record
        PrintChangeRule printChangeRule = new PrintChangeRule();

        ClassReaderASM classReader = new ClassReaderASM();
        ClassNode inputClass = classReader.createClassNode("target/classes/TestClasses/BasicClass.class");
        int originalSize = inputClass.getMethods().get(0).getInstructions().size();

        // Replay
        ClassNode outputClass = printChangeRule.applyRule(inputClass);

        // Verify
        Assertions.assertEquals(inputClass.getClassName(), outputClass.getClassName());
        Assertions.assertEquals(1, outputClass.getMethods().size());

        InsnList outputInstructions = outputClass.getMethods().get(0).getInstructions();
        Assertions.assertEquals( originalSize + 6, outputInstructions.size());
    }

    @Test
    public void testPrintChangeRuleAddedInitialPrint() throws IOException {
        // Record
        PrintChangeRule printChangeRule = new PrintChangeRule();

        ClassReaderASM classReader = new ClassReaderASM();
        ClassNode inputClass = classReader.createClassNode("target/classes/TestClasses/BasicClass.class");

        // Replay
        ClassNode outputClass = printChangeRule.applyRule(inputClass);

        // Verify
        InsnList outputInstructions = outputClass.getMethods().get(0).getInstructions();
        Assertions.assertTrue(outputInstructions.get(0).isFieldInsnNode());
        Assertions.assertEquals("out",
                outputInstructions.get(0).getFieldInsnNode().getFieldName());

        Assertions.assertTrue(outputInstructions.get(1).isLdcNode());
        Assertions.assertEquals("Starting TestClasses/BasicClass.<init>",
                outputInstructions.get(1).getLdcNode().getValue());

        Assertions.assertTrue(outputInstructions.get(2).isMethodInsnNode());
        Assertions.assertEquals("println",
                outputInstructions.get(2).getMethodInsnNode().getMethodName());

    }

    @Test
    public void testPrintChangeRuleAddedReturnPrint() throws IOException {
        // Record
        PrintChangeRule printChangeRule = new PrintChangeRule();

        ClassReaderASM classReader = new ClassReaderASM();
        ClassNode inputClass = classReader.createClassNode("target/classes/TestClasses/BasicClass.class");

        // Replay
        ClassNode outputClass = printChangeRule.applyRule(inputClass);

        // Verify
        InsnList outputInstructions = outputClass.getMethods().get(0).getInstructions();
        Assertions.assertTrue(outputInstructions.get(outputInstructions.size()-5).isFieldInsnNode());
        Assertions.assertEquals("out",
                outputInstructions.get(outputInstructions.size()-5).getFieldInsnNode().getFieldName());

        Assertions.assertTrue(outputInstructions.get(outputInstructions.size()-4).isLdcNode());
        Assertions.assertEquals("Ending TestClasses/BasicClass.<init>",
                outputInstructions.get(outputInstructions.size()-4).getLdcNode().getValue());

        Assertions.assertTrue(outputInstructions.get(outputInstructions.size()-3).isMethodInsnNode());
        Assertions.assertEquals("println",
                outputInstructions.get(outputInstructions.size()-3).getMethodInsnNode().getMethodName());

    }

    @Test
    public void testPrintChangeRuleAddedInstructionsManyMethods() throws IOException {
        // Record
        PrintChangeRule printChangeRule = new PrintChangeRule();

        ClassReaderASM classReader = new ClassReaderASM();
        ClassNode inputClass = classReader.createClassNode("target/classes/TestClasses/EagerSingleton.class");
        int originalSize1 = inputClass.getMethods().get(0).getInstructions().size();
        int originalSize2 = inputClass.getMethods().get(1).getInstructions().size();
        int originalSize3 = inputClass.getMethods().get(2).getInstructions().size();

        // Replay
        ClassNode outputClass = printChangeRule.applyRule(inputClass);

        // Verify
        Assertions.assertEquals(inputClass.getClassName(), outputClass.getClassName());
        Assertions.assertEquals(3, outputClass.getMethods().size());

        InsnList outputInstructions = outputClass.getMethods().get(0).getInstructions();
        Assertions.assertEquals( originalSize1 + 6, outputInstructions.size());
        outputInstructions = outputClass.getMethods().get(1).getInstructions();
        Assertions.assertEquals( originalSize2 + 3, outputInstructions.size());
        outputInstructions = outputClass.getMethods().get(2).getInstructions();
        Assertions.assertEquals( originalSize3 + 6, outputInstructions.size());
    }

}
