package Domain.Rules;

import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrincipleLeastKnowledgeRule implements Rule {

    private final String ruleName = "PrincipleLeastKnowledgeRule";

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
        int lineNumber = -1;

        // getting valid classes or packages for anything in this class - this, fields, and whitelisted
        List<String> classValidNames = new ArrayList<>();
        classValidNames.add(classNode.getClassName());
        List<String> whitelistedPackages = new ArrayList<>();
        List<FieldNode> fieldNodes = classNode.getFields();
        for (FieldNode fieldNode : fieldNodes) {
            ClassNode fieldClassNode = fieldNode.getType();
            if (fieldClassNode.isValid()) {
                classValidNames.add(fieldClassNode.getClassName());
            }
        }
        classValidNames.addAll(this.getWhitelistedClassNames(options));
        whitelistedPackages.addAll(this.getWhitelistedPackages(options));

        List<MethodNode> methodNodes = classNode.getMethods();
        for (MethodNode methodNode : methodNodes) {
            List<String> methodValidNames = new ArrayList<>();
            List<ClassNode> arguments = methodNode.getArgumentTypes();

            // getting valid classes for anything in this method - arguments
            for (ClassNode argument : arguments) {
                methodValidNames.add(argument.getClassName());
            }

            InsnList instructions = methodNode.getInstructions();
            for (int i = 0; i < instructions.size(); i++) {
                AbstractInsnNode abstractInsnNode = instructions.get(i);
                if (abstractInsnNode.isMethodInsnNode()) {
                    MethodInsnNode methodInsnNode = abstractInsnNode.getMethodInsnNode();
                    ClassNode methodOwner = methodInsnNode.getMethodOwner();
                    if (methodOwner.isValid()) {
                        String methodOwnerName = methodOwner.getClassName();

                        boolean isWhitelistedPackage = false;
                        for (String whitelistedPackage : whitelistedPackages) {
                            if (methodOwnerName.contains(whitelistedPackage)) {
                                isWhitelistedPackage = true;
                            }
                        }

                        // if method instruction is init (= new), then is allowed to be called
                        if (methodInsnNode.isInitInsn()) {
                            methodValidNames.add(methodOwnerName);
                        }

                        // if method call is not valid for class or method,
                        // then violates principle of least knowledge
                        else if (!(classValidNames.contains(methodOwnerName)
                                || methodValidNames.contains(methodOwnerName)
                                || isWhitelistedPackage)) {

                            Issue issue = new Issue();
                            issue.severity = Severity.valueOf(options.get("severity"));
                            issue.rule = this.ruleName;
                            issue.classValue = classNode.getClassName();
                            issue.file = classNode.getFileName();
                            issue.line = lineNumber;
                            issue.message = "method " + methodNode.getName() + " makes call to "
                                    + methodOwnerName + " violating the Principle of Least Knowledge";
                            issues.add(issue);
                            methodValidNames.add(methodOwnerName); // reducing duplication
                        }
                    }
                } else if (abstractInsnNode.isLineNumberNode()) {
                    LineNumberNode lineNumberNode = abstractInsnNode.getLineNumberNode();
                    lineNumber = lineNumberNode.getLineNumber();
                }

            }
        }
        return issues;
    }

    private List<String> getWhitelistedClassNames(Options options) {
        ArrayList<String> whitelistedClassNames = new ArrayList<>();
        for (Map.Entry<String, String> entry : options.attributes.entrySet()) {
            if (entry.getKey().contains("whitelistedClass")) {
                whitelistedClassNames.add(entry.getValue());
            }
        }
        return whitelistedClassNames;
    }

    private List<String> getWhitelistedPackages(Options options) {
        ArrayList<String> whitelistedPackages = new ArrayList<>();
        for (Map.Entry<String, String> entry : options.attributes.entrySet()) {
            if (entry.getKey().contains("whitelistedPackage")) {
                whitelistedPackages.add(entry.getValue());
            }
        }
        return whitelistedPackages;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("INFO");
        return new Options(optionsKeys, optionsValues);
    }
}
