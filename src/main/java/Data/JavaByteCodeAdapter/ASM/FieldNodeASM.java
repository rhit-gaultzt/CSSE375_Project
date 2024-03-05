package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.IOException;

public class FieldNodeASM implements FieldNode {
    private org.objectweb.asm.tree.FieldNode asmFieldNode;

    public FieldNodeASM(org.objectweb.asm.tree.FieldNode asmFieldNode) {
        this.asmFieldNode = asmFieldNode;
    }

    @Override
    public String getName() {
        return this.asmFieldNode.name;
    }

    @Override
    public ClassNode getType() {
        org.objectweb.asm.tree.ClassNode typeClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(Type.getType(this.asmFieldNode.desc).getClassName());
            typeClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(typeClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
        }
        return new ClassNodeASM(typeClassNode);
    }

    @Override
    public int getAccess() {
        return this.asmFieldNode.access;
    }


    @Override
    public boolean isStatic() {
        return (this.asmFieldNode.access & Opcodes.ACC_STATIC) != 0;
    }

}
