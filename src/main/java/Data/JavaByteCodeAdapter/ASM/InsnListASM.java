package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.AbstractInsnNode;
import Data.JavaByteCodeAdapter.InsnList;

import java.util.Iterator;

public class InsnListASM implements InsnList {
    private final org.objectweb.asm.tree.InsnList asmInsnList;

    public InsnListASM(org.objectweb.asm.tree.InsnList asmInsnList) {
        this.asmInsnList = asmInsnList;
    }

    @Override
    public AbstractInsnNode get(int i) {
        return new AbstractInsnNodeASM(asmInsnList.get(i));
    }


    @Override
    public int size() {
        return this.asmInsnList.size();
    }

    @Override
    public Iterator<AbstractInsnNode> iterator() {
        return new Iterator<AbstractInsnNode>() {
            private final Iterator<org.objectweb.asm.tree.AbstractInsnNode> iter = asmInsnList.iterator();
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }
            @Override
            public AbstractInsnNode next() {
                return new AbstractInsnNodeASM(iter.next());
            }
        };
    }

}
