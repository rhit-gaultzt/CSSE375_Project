package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.*;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;

import java.util.Arrays;
import java.util.List;

public class AbstractInsnNodeASM implements AbstractInsnNode {
    private org.objectweb.asm.tree.AbstractInsnNode asmAbstractInsnNode;
    private final List<Integer> conditionalJumps = Arrays.asList(Opcodes.IF_ICMPEQ, Opcodes.IF_ICMPNE,
            Opcodes.IF_ICMPLT, Opcodes.IF_ICMPGE, Opcodes.IF_ICMPGT, Opcodes.IF_ICMPLE, Opcodes.IF_ACMPEQ,
            Opcodes.IF_ACMPNE, Opcodes.IFNULL, Opcodes.IFNONNULL);
    private final List<Integer> unconditionalJumps = Arrays.asList(Opcodes.GOTO, Opcodes.JSR,
            Opcodes.RET, Opcodes.TABLESWITCH, Opcodes.LOOKUPSWITCH);

    public AbstractInsnNodeASM(org.objectweb.asm.tree.AbstractInsnNode asmAbstractInsnNode) {
        this.asmAbstractInsnNode = asmAbstractInsnNode;
    }

    @Override
    public boolean isLineNumberNode() {
        return this.asmAbstractInsnNode instanceof org.objectweb.asm.tree.LineNumberNode;
    }

    @Override
    public LineNumberNode getLineNumberNode() {
        if (isLineNumberNode()) {
            return new LineNumberNodeASM((org.objectweb.asm.tree.LineNumberNode) asmAbstractInsnNode);
        }
        return null;
    }

    @Override
    public boolean isMethodInsnNode() {
        return this.asmAbstractInsnNode instanceof org.objectweb.asm.tree.MethodInsnNode;
    }

    @Override
    public MethodInsnNode getMethodInsnNode() {
        if (isMethodInsnNode()) {
            return new MethodInsnNodeASM((org.objectweb.asm.tree.MethodInsnNode) asmAbstractInsnNode);
        }
        return null;
    }

    @Override
    public boolean isVarInsnNode() {
        return this.asmAbstractInsnNode instanceof org.objectweb.asm.tree.VarInsnNode;
    }

    @Override
    public VarInsnNode getVarInsnNode() {
        if (isVarInsnNode()) {
            return new VarInsnNodeASM((org.objectweb.asm.tree.VarInsnNode) asmAbstractInsnNode);
        }
        return null;
    }

    @Override
    public boolean isFieldInsnNode() {
        return this.asmAbstractInsnNode instanceof org.objectweb.asm.tree.FieldInsnNode;
    }

    @Override
    public FieldInsnNode getFieldInsnNode() {
        if (isFieldInsnNode()) {
            return new FieldInsnNodeASM((org.objectweb.asm.tree.FieldInsnNode) asmAbstractInsnNode);
        }
        return null;
    }

    @Override
    public boolean isJumpNode() {

        return this.conditionalJumps.contains(this.asmAbstractInsnNode.getOpcode()) ||
                this.unconditionalJumps.contains(this.asmAbstractInsnNode.getOpcode());
    }

    @Override
    public JumpNode getJumpNode() {
        if (isJumpNode()) {
            return new JumpNodeASM((org.objectweb.asm.tree.JumpInsnNode) asmAbstractInsnNode);
        }
        return null;
    }

    @Override
    public boolean isSwitchNode() {
        return (this.asmAbstractInsnNode.getType() == org.objectweb.asm.tree.AbstractInsnNode.LOOKUPSWITCH_INSN ||
                this.asmAbstractInsnNode.getType() == org.objectweb.asm.tree.AbstractInsnNode.TABLESWITCH_INSN);
    }

    @Override
    public boolean isIfNode() {
        return this.conditionalJumps.contains(this.asmAbstractInsnNode.getOpcode());
    }
}
