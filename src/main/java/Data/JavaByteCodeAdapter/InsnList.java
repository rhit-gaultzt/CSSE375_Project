package Data.JavaByteCodeAdapter;

import java.util.Iterator;

public interface InsnList extends Iterable<AbstractInsnNode> {
    AbstractInsnNode get(int i);
    int size();
    Iterator<AbstractInsnNode> iterator();
}
