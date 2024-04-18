package Data.JavaByteCodeAdapter;

public interface AbstractInsnNode {
    boolean isLineNumberNode();
    LineNumberNode getLineNumberNode();
    boolean isMethodInsnNode();
    MethodInsnNode getMethodInsnNode();
    boolean isVarInsnNode();
    VarInsnNode getVarInsnNode();
    boolean isFieldInsnNode();
    FieldInsnNode getFieldInsnNode();
    boolean isJumpNode();
    JumpNode getJumpNode();
    boolean isSwitchNode();
    boolean isIfNode();
    boolean isLdcNode();
    LdcNode getLdcNode();
}
