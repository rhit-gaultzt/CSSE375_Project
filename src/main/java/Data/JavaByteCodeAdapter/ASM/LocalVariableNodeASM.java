package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.LocalVariableNode;

public class LocalVariableNodeASM implements LocalVariableNode {


    private final org.objectweb.asm.tree.LocalVariableNode localVariableNode;


    LocalVariableNodeASM(org.objectweb.asm.tree.LocalVariableNode localVariableNode) {
        this.localVariableNode = localVariableNode;
    }


    @Override
    public String getName() {
        return localVariableNode.name;
    }


}
