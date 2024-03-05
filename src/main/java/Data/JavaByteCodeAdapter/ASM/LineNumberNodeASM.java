package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.LineNumberNode;

public class LineNumberNodeASM implements LineNumberNode {
    private org.objectweb.asm.tree.LineNumberNode asmLineNumberNode;

    public LineNumberNodeASM(org.objectweb.asm.tree.LineNumberNode asmLineNumberNode) {
        this.asmLineNumberNode = asmLineNumberNode;
    }

    @Override
    public int getLineNumber() {
        return this.asmLineNumberNode.line;
    }
}
