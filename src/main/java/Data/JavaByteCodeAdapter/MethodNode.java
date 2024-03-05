package Data.JavaByteCodeAdapter;

import java.util.List;

public interface MethodNode {
    String getName();
    ClassNode getType();
    int getAccess();
    List<ClassNode> getArgumentTypes();
    List<LocalVariableNode> getLocalVariables();
    InsnList getInstructions();
    boolean isConstructor();
    boolean isPublic();
    boolean isStatic();
}
