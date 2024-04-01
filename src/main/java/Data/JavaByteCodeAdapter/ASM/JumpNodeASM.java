package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.AbstractInsnNode;
import Data.JavaByteCodeAdapter.JumpNode;
import Data.JavaByteCodeAdapter.VarInsnNode;

public class JumpNodeASM implements JumpNode {
    private org.objectweb.asm.tree.JumpInsnNode asmJumpNode;

    public JumpNodeASM(org.objectweb.asm.tree.JumpInsnNode asmJumpNode) {
        this.asmJumpNode = asmJumpNode;
    }

    @Override
    public boolean isIf() {
        AbstractInsnNode previous = new AbstractInsnNodeASM(asmJumpNode.getPrevious());
        AbstractInsnNode twoPrevious = new AbstractInsnNodeASM(asmJumpNode.getPrevious());
        return previous.isVarInsnNode() || twoPrevious.isVarInsnNode();
    }

    @Override
    public int getComparisonVariableIndex() {
        if (isIf()) {
            VarInsnNode var = getConditionVariable();
            return var.getVarIndex();
        }
        return -1;
    }

    private VarInsnNode getConditionVariable() {
        AbstractInsnNode previous = new AbstractInsnNodeASM(asmJumpNode.getPrevious());
        AbstractInsnNode twoPrevious = new AbstractInsnNodeASM(asmJumpNode.getPrevious());
        if (previous.isVarInsnNode()) {
            return previous.getVarInsnNode();
        } else if (twoPrevious.isVarInsnNode()) {
            return twoPrevious.getVarInsnNode();
        } else {
            return null;
        }

    }
}
