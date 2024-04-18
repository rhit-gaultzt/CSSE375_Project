package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.LdcNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class LdcNodeASM implements LdcNode {
    private LdcInsnNode ldcInsnNode;

    public LdcNodeASM(LdcInsnNode ldcInsnNode) {
        this.ldcInsnNode = ldcInsnNode;
    }

    @Override
    public String getValue() {
        if (this.ldcInsnNode.cst instanceof String) {
            return (String) this.ldcInsnNode.cst;
        }
        return "Unknown";
    }
}
