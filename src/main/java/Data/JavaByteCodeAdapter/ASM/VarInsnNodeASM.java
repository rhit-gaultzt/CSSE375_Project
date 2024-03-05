package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.VarInsnNode;

public class VarInsnNodeASM implements VarInsnNode {
    private org.objectweb.asm.tree.VarInsnNode asmVarInsnNode;

    public VarInsnNodeASM(org.objectweb.asm.tree.VarInsnNode asmVarInsnNode) {
        this.asmVarInsnNode = asmVarInsnNode;
    }
}
