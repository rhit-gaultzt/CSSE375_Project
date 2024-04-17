package Data.JavaByteCodeAdapter;

public interface InsnList extends Iterable<AbstractInsnNode> {
    AbstractInsnNode get(int i);
    int size();
}
