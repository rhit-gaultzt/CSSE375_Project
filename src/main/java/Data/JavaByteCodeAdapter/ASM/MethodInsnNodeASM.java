package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.MethodInsnNode;

import java.io.IOException;

public class MethodInsnNodeASM implements MethodInsnNode {
    private org.objectweb.asm.tree.MethodInsnNode asmMethodInsnNode;

    public MethodInsnNodeASM(org.objectweb.asm.tree.MethodInsnNode asmMethodInsnNode) {
        this.asmMethodInsnNode = asmMethodInsnNode;
    }

    @Override
    public ClassNodeASM getMethodOwner() {
        org.objectweb.asm.tree.ClassNode typeClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(this.asmMethodInsnNode.owner);
            typeClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(typeClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
        }
        return new ClassNodeASM(typeClassNode);
    }

    @Override
    public String getMethodName() {
        return this.asmMethodInsnNode.name;
    }

    @Override
    public boolean isInitInsn() {
        return this.asmMethodInsnNode.name.equals("<init>");
    }
}
