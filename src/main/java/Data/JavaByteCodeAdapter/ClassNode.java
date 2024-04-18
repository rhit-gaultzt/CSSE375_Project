package Data.JavaByteCodeAdapter;

import java.util.List;

public interface ClassNode {
    String getClassName();
    String getShortenedClassName();
    String getFileName();
    int getAccess();
    ClassNode getSuperClass();
    List<ClassNode> getInterfaces();
    List<FieldNode> getFields();
    List<MethodNode> getMethods();
    String getSourceFile();
    boolean isValid();
    boolean isAbstract();
    org.objectweb.asm.tree.ClassNode getClassNode();
}

