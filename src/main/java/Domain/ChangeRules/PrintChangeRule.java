package Domain.ChangeRules;

import Data.JavaByteCodeAdapter.ASM.ClassNodeASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Domain.ChangeRule;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class PrintChangeRule implements ChangeRule {

    @Override
    public ClassNode applyRule(ClassNode classNode) {
        org.objectweb.asm.tree.ClassNode outputClassNode = classNode.getClassNode();
        for (MethodNode method : outputClassNode.methods) {
            InsnList instructions = method.instructions;
           instructions.insert(getPrintInstruction(outputClassNode, method, "Starting"));

            ListIterator<AbstractInsnNode> iterator = instructions.iterator();
            while (iterator.hasNext()) {
                AbstractInsnNode insn = iterator.next();
                if (insn.getOpcode() == Opcodes.RETURN) {
                    instructions.insertBefore(insn, getPrintInstruction(outputClassNode, method, "Ending"));
                }
            }
        }
        return new ClassNodeASM(outputClassNode);
    }

    private InsnList getPrintInstruction(org.objectweb.asm.tree.ClassNode outputClassNode, MethodNode method,
                                         String initialString) {
        InsnList instructions = new InsnList();
        instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
        instructions.add(new LdcInsnNode(initialString + " " + outputClassNode.name + "." + method.name));
        instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
        return instructions;
    }
}
