package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.InsnList;
import Data.JavaByteCodeAdapter.LocalVariableNode;
import Data.JavaByteCodeAdapter.MethodNode;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodNodeASM implements MethodNode {
    private org.objectweb.asm.tree.MethodNode asmMethodNode;

    public MethodNodeASM(org.objectweb.asm.tree.MethodNode asmMethodNode) {
        this.asmMethodNode = asmMethodNode;
    }

    @Override
    public String getName() {
        return this.asmMethodNode.name;
    }

    @Override
    public ClassNode getType() {
        org.objectweb.asm.tree.ClassNode typeClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(Type.getReturnType(this.asmMethodNode.desc).getClassName());
            typeClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(typeClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
        }
        return new ClassNodeASM(typeClassNode);
    }

    @Override
    public int getAccess() {
        return this.asmMethodNode.access;
    }

    @Override
    public List<ClassNode> getArgumentTypes() {
        ArrayList<ClassNode> argumentTypes = new ArrayList<>();
        for (Type argType : Type.getArgumentTypes(this.asmMethodNode.desc)) {
            org.objectweb.asm.tree.ClassNode argTypeClassNode = null;
            try {
                org.objectweb.asm.ClassReader classReader =
                        new org.objectweb.asm.ClassReader(argType.getClassName());
                argTypeClassNode = new org.objectweb.asm.tree.ClassNode();
                classReader.accept(argTypeClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
            } catch (IOException e) {
            }
            if (argTypeClassNode != null) {
                argumentTypes.add(new ClassNodeASM(argTypeClassNode));
            }
        }
        return argumentTypes;
    }

    @Override
    public InsnList getInstructions() {
        return new InsnListASM(this.asmMethodNode.instructions);
    }


    @Override
    public List<LocalVariableNode> getLocalVariables() {
        ArrayList<LocalVariableNode> localVariable = new ArrayList<>();
        try {
            for (org.objectweb.asm.tree.LocalVariableNode variable : this.asmMethodNode.localVariables) {
                localVariable.add(new LocalVariableNodeASM(variable));
            }
        } catch (NullPointerException e) {
        }
        return localVariable;
    }

    @Override
    public boolean isConstructor() {
        return this.asmMethodNode.name.equals("<init>");
    }

    @Override
    public boolean isPublic() {
        return (this.asmMethodNode.access & Opcodes.ACC_PUBLIC) != 0;
    }

    @Override
    public boolean isStatic() {
        return (this.asmMethodNode.access & Opcodes.ACC_STATIC) != 0;
    }


}
