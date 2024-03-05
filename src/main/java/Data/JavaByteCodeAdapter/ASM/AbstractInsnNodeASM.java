package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.*;

public class AbstractInsnNodeASM implements AbstractInsnNode {
    private org.objectweb.asm.tree.AbstractInsnNode asmAbstractInsnNode;

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
        return (this.asmAbstractInsnNode.getType() == org.objectweb.asm.tree.AbstractInsnNode.JUMP_INSN);
    }

    @Override
    public boolean isSwitchNode() {
        return (this.asmAbstractInsnNode.getType() == org.objectweb.asm.tree.AbstractInsnNode.LOOKUPSWITCH_INSN ||
                this.asmAbstractInsnNode.getType() == org.objectweb.asm.tree.AbstractInsnNode.TABLESWITCH_INSN);
    }
}
