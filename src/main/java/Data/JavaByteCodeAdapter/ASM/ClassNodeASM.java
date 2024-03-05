package Data.JavaByteCodeAdapter.ASM;


import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import Data.JavaByteCodeAdapter.MethodNode;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassNodeASM implements ClassNode {
    private org.objectweb.asm.tree.ClassNode asmClassNode;

    public ClassNodeASM(org.objectweb.asm.tree.ClassNode asmClassNode) {
        this.asmClassNode = asmClassNode;
    }

    @Override
    public String getClassName() {
        return Type.getObjectType(this.asmClassNode.name).getClassName();
    }

    @Override
    public String getShortenedClassName() {
        String className = this.getClassName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    @Override
    public String getFileName() {
        return this.asmClassNode.sourceFile;
    }

    @Override
    public int getAccess() {
        return this.asmClassNode.access;
    }

    @Override
    public ClassNode getSuperClass() {
        org.objectweb.asm.tree.ClassNode superClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(this.asmClassNode.superName);
            superClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(superClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
        } catch (NullPointerException e) {
            return null;
        }
        return new ClassNodeASM(superClassNode);
    }

    @Override
    public List<ClassNode> getInterfaces() {
        ArrayList<ClassNode> classNodes = new ArrayList<>();
        for (String interfaceName : this.asmClassNode.interfaces) {
            org.objectweb.asm.tree.ClassNode interfaceClassNode = null;
            try {
                org.objectweb.asm.ClassReader classReader =
                        new org.objectweb.asm.ClassReader(interfaceName);
                interfaceClassNode = new org.objectweb.asm.tree.ClassNode();
                classReader.accept(interfaceClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
            } catch (IOException e) {
            }
            if (interfaceClassNode != null) {
                classNodes.add(new ClassNodeASM(interfaceClassNode));
            }
        }
        return classNodes;
    }

    @Override
    public List<FieldNode> getFields() {
        List<org.objectweb.asm.tree.FieldNode> asmFieldNodes = asmClassNode.fields;
        List<FieldNode> fieldNodes = new ArrayList<>();
        for (org.objectweb.asm.tree.FieldNode asmFieldNode : asmFieldNodes) {
            fieldNodes.add(new FieldNodeASM(asmFieldNode));
        }
        return fieldNodes;
    }

    @Override
    public List<MethodNode> getMethods() {
        List<org.objectweb.asm.tree.MethodNode> asmMethodNodes = asmClassNode.methods;
        List<MethodNode> methodNodes = new ArrayList<>();
        for (org.objectweb.asm.tree.MethodNode asmMethodNode : asmMethodNodes) {
            methodNodes.add(new MethodNodeASM(asmMethodNode));
        }
        return methodNodes;
    }

    @Override
    public String getSourceFile() {
        return this.asmClassNode.sourceFile;
    }

    @Override
    public boolean isValid() {
        return this.asmClassNode != null;
    }


    @Override
    public boolean isAbstract() {
        return (this.asmClassNode.access & org.objectweb.asm.Opcodes.ACC_ABSTRACT) != 0;
    }

}
