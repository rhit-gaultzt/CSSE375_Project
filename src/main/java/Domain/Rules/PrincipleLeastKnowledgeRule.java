package Domain.Rules;

import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrincipleLeastKnowledgeRule implements Rule {

    private static final String RULE_NAME = "PrincipleLeastKnowledgeRule";

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> entry : classNodes.entrySet()) {
            ClassNode classNode = entry.getValue();
            issues.addAll(checkClass(classNode, options));
        }
        return issues;
    }

    private List<Issue> checkClass(ClassNode classNode, Options options) {
        List<Issue> issues = new ArrayList<>();
        List<String> externalValidNames = getValidExternalNames(classNode, options);
        LineNumber lineNumber = new LineNumber();

        for (MethodNode methodNode : classNode.getMethods()) {
            issues.addAll(checkMethod(classNode, options, externalValidNames, methodNode, lineNumber));
        }

        if (issues.size() > 0) {
            issues.add(issueValidClasses(classNode, externalValidNames, options));
        }

        return issues;
    }

    public Issue issueValidClasses(ClassNode classNode, List<String> externalValidNames, Options options) {
        StringBuilder classList = new StringBuilder();
        classList.append("Class ").append(classNode.getClassName()).append(" can access: \n");
        for (String valid : externalValidNames) {
            classList.append("\t").append(valid);
            if (!externalValidNames.get(externalValidNames.size()-1).equals(valid)) {
                classList.append("\n");
            }
        }
        return new Issue(RULE_NAME, classNode, 0, options, classList.toString());
    }

    private List<Issue> checkMethod(ClassNode classNode, Options options, List<String> externalValidNames, MethodNode methodNode, LineNumber lineNumber) {
        List<String> methodValidNames = new ArrayList<>();
        List<Issue> methodIssues = new ArrayList<>();

        for (ClassNode argument : methodNode.getArgumentTypes()) {
            methodValidNames.add(argument.getClassName());
        }

        for (AbstractInsnNode instruction : methodNode.getInstructions()) {
            if (instruction.isLineNumberNode()) {
                LineNumberNode lineNumberNode = instruction.getLineNumberNode();
                lineNumber.setLine(lineNumberNode.getLineNumber());
            } else if (instruction.isMethodInsnNode()) {
                MethodInsnNode methodCall = instruction.getMethodInsnNode();
                if (instructionHasIssue(externalValidNames, methodValidNames, methodCall)) {
                    String message = "method " + methodNode.getName() + " makes call to "
                            + methodCall.getMethodOwner().getClassName() + " violating the Principle of Least Knowledge";
                    methodIssues.add(new Issue(RULE_NAME, classNode, lineNumber.getLine(), options, message));
                }
            }
        }
        return methodIssues;
    }

    private boolean instructionHasIssue(List<String> validExternalNames, List<String> methodValidNames, MethodInsnNode methodInsnNode) {
        ClassNode methodOwner = methodInsnNode.getMethodOwner();
        if (methodOwner.isValid()) {
            String methodOwnerName = methodOwner.getClassName();
            boolean validOwner = isValidOwner(validExternalNames, methodValidNames, methodOwnerName);
            methodValidNames.add(methodOwnerName);

            return !(validOwner || methodInsnNode.isInitInsn());
        }
        return true;
    }

    private boolean isValidOwner(List<String> validExternalNames, List<String> methodValidNames, String methodOwnerName) {
        for (String whitelistedPackage : validExternalNames) {
            if (methodOwnerName.contains(whitelistedPackage)) {
                return true;
            }
        }

        return methodValidNames.contains(methodOwnerName);
    }

    private List<String> getValidExternalNames(ClassNode classNode, Options options) {
        List<String> classValidNames = new ArrayList<>();
        classValidNames.add(classNode.getClassName());
        List<FieldNode> fieldNodes = classNode.getFields();
        for (FieldNode fieldNode : fieldNodes) {
            ClassNode fieldClassNode = fieldNode.getType();
            if (fieldClassNode.isValid()) {
                classValidNames.add(fieldClassNode.getClassName());
            }
        }
        classValidNames.addAll(this.getWhitelisted(options, "whitelistedClass"));
        classValidNames.addAll(this.getWhitelisted(options, "whitelistedPackage"));
        return classValidNames;
    }

    private List<String> getWhitelisted(Options options, String filter) {
        ArrayList<String> whitelisted = new ArrayList<>();
        for (Map.Entry<String, String> entry : options.attributes.entrySet()) {
            if (entry.getKey().contains(filter)) {
                whitelisted.add(entry.getValue());
            }
        }
        return whitelisted;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("INFO");
        return new Options(optionsKeys, optionsValues);
    }

    private static class LineNumber {
        private int lineNumber = -1;
        public int getLine() {
            return  lineNumber;
        }
        public void setLine(int newLine) {
            lineNumber = newLine;
        }
    }
}
