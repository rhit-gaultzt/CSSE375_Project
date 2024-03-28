package Domain.Rules;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingletonRule implements Rule {

    private final String ruleName = "SingletonRule";
    private final int line = -1;
    private final String message = "This class appears to be a Singleton";


    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> entry : classNodes.entrySet()) {
            ClassNode classNode = entry.getValue();
            if (hasFieldOfOwnType(classNode)
                    && hasStaticMethodReturningOwnType(classNode)
                    && hasNoPublicConstructor(classNode)) {
                Issue issue = new Issue(ruleName, classNode, line, options, message);
                issues.add(issue);
            }
        }
        return issues;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("INFO");
        return new Options(optionsKeys, optionsValues);
    }

    private boolean hasFieldOfOwnType(ClassNode classNode) {

        List<FieldNode> fieldNodes = classNode.getFields();
        for (FieldNode fieldNode : fieldNodes) {
            ClassNode fieldNodeType = fieldNode.getType();
            if (fieldNodeType.isValid() &&
                    fieldNodeType.getClassName().equals(classNode.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasStaticMethodReturningOwnType(ClassNode classNode) {
        List<MethodNode> methodNodes = classNode.getMethods();
        for (MethodNode methodNode : methodNodes) {
            ClassNode methodNodeType = methodNode.getType();
            if (!methodNode.isConstructor() && methodNodeType.isValid() &&
                    methodNode.isStatic() &&
                    methodNodeType.getClassName().equals(classNode.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNoPublicConstructor(ClassNode classNode) {
        List<MethodNode> methodNodes = classNode.getMethods();
        for (MethodNode methodNode : methodNodes) {
            if (methodNode.isConstructor() && methodNode.isPublic()) {
                return false;
            }
        }
        return true;
    }

}
