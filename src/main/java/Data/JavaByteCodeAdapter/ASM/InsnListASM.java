package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.AbstractInsnNode;
import Data.JavaByteCodeAdapter.InsnList;

public class InsnListASM implements InsnList {
    private org.objectweb.asm.tree.InsnList asmInsnList;

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

}
