package Data.JavaByteCodeAdapter;

public interface MethodInsnNode {
    ClassNode getMethodOwner();
    String getMethodName();
    boolean isInitInsn();
}
