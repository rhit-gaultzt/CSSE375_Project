package Domain;

import Data.JavaByteCodeAdapter.ClassNode;

public interface ChangeRule {
    ClassNode applyRule(ClassNode classNode);
}
