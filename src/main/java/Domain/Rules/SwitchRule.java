package Domain.Rules;

import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwitchRule implements Rule {

    private final String ruleName = "SwitchRule";

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
        for (MethodNode methodNode : classNode.getMethods()) {
            issues.addAll(checkMethod(classNode, methodNode, options));
        }
        return issues;
    }

    private List<Issue> checkMethod(ClassNode classNode, MethodNode methodNode, Options options) {
        List<Issue> issues = new ArrayList<>();
        Map<Integer, VariableInfo> variableCounts = new HashMap<>();
        int lineNumber = -1;
        InsnList instructions = methodNode.getInstructions();

        for (int i = 0; i < instructions.size(); i++) {
            AbstractInsnNode instruction = instructions.get(i);
            if (instruction.isSwitchNode()) {
                issues.add(createIssue(classNode, methodNode, lineNumber, options));
            } else if (instruction.isIfNode()){
                addVariableIfOccurrence(variableCounts, instruction.getJumpNode(), lineNumber);
            } else if (instruction.isLineNumberNode()) {
                lineNumber = instruction.getLineNumberNode().getLineNumber();
            }
        }
        issues.addAll(getIfIssues(variableCounts, classNode, methodNode, options));
        issues = filterDuplicates(issues);
        return issues;
    }

    private void addVariableIfOccurrence(Map<Integer, VariableInfo> variableCounts,
                                         JumpNode ifJumpNode, int lineNumber) {
        int varIndex = ifJumpNode.getComparisonVariableIndex();
        if (!variableCounts.containsKey(varIndex)) {
            variableCounts.put(varIndex, new VariableInfo(1, lineNumber));
        } else {
            variableCounts.get(varIndex).count++;
        }
    }

    private List<Issue> getIfIssues(Map<Integer, VariableInfo> variableCounts, ClassNode classNode,
                                    MethodNode methodNode, Options options) {
        List<Issue> issues = new ArrayList<>();

        for (Map.Entry<Integer, VariableInfo> entry : variableCounts.entrySet()) {
            if (entry.getValue().count >= 3) {
                issues.add(createIssue(classNode, methodNode, entry.getValue().lineNumber, options));
            }
        }

        return issues;
    }

    private Issue createIssue(ClassNode classNode, MethodNode methodNode,
                              int lineNumber, Options options) {
        Issue issue = new Issue();
        issue.severity = Severity.valueOf(options.get("severity"));
        issue.rule = this.ruleName;
        issue.classValue = classNode.getClassName();
        issue.file = classNode.getFileName();
        issue.line = lineNumber;
        issue.message = "method " + methodNode.getName() +
                " has a switch statement or is comparing the same variable to many values";
        return issue;
    }

    private List<Issue> filterDuplicates(List<Issue> issues) {
        List<Issue> result = new ArrayList<>();
        List<Integer> lineNumbers = new ArrayList<>();
        for (Issue issue : issues) {
            if (!lineNumbers.contains(issue.line)) {
                result.add(issue);
                lineNumbers.add(issue.line);
            }
        }
        return result;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("INFO");
        return new Options(optionsKeys, optionsValues);
    }

    public class VariableInfo {
        int count;
        int lineNumber;

        public VariableInfo(int count, int lineNumber) {
            this.count = count;
            this.lineNumber = lineNumber;
        }

    }
}
