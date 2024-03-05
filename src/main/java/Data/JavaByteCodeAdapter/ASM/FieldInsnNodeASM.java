package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldInsnNode;
import org.objectweb.asm.Type;

import java.io.IOException;

public class FieldInsnNodeASM implements FieldInsnNode {

    private org.objectweb.asm.tree.FieldInsnNode asmFieldInsnNode;

    public FieldInsnNodeASM(org.objectweb.asm.tree.FieldInsnNode asmFieldInsnNode) {
        this.asmFieldInsnNode = asmFieldInsnNode;
    }

    @Override
    public ClassNode getFieldOwner() {
        org.objectweb.asm.tree.ClassNode typeClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(Type.getReturnType(this.asmFieldInsnNode.owner).getClassName());
            typeClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(typeClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
        }
        return new ClassNodeASM(typeClassNode);
    }

    @Override
    public String getFieldName() {
        return this.asmFieldInsnNode.name;
    }
}
