package Data.JavaByteCodeAdapter;

public interface FieldNode {
    String getName();
    ClassNode getType();
    int getAccess();
    boolean isStatic();
}
