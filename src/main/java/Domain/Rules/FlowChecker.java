package Domain.Rules;

import Data.JavaByteCodeAdapter.AbstractInsnNode;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.InsnList;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowChecker implements Rule {

    public static final int JUMP_SWITCH_THRESHOLD = 10;

    @Override
    public Options getDefaultOptions() {
        Options options = new Options(new ArrayList<String>(), new ArrayList<String>());
        options.put("TooManyJumpsAndSwitches", Severity.WARNING.toString());
        return options;
    }

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> node: classNodes.entrySet()) {
            ClassNode classNode = node.getValue();
            for (MethodNode method : classNode.getMethods()) {
                int countOfSwitchOrJump = 0;
                InsnList instructions = method.getInstructions();
                for (int index = 0; index < instructions.size(); index++) {
                    AbstractInsnNode instruction = instructions.get(index);
                    if (instruction.isJumpNode() || instruction.isSwitchNode()) {
                        countOfSwitchOrJump++;
                    }
                }
                if (countOfSwitchOrJump > JUMP_SWITCH_THRESHOLD) {
                    String fileName = classNode.getFileName();
                    String className = classNode.getClassName();
                    String message = "Method \"" + method.getName() + "\" within class \"" + className + "\" contains too many jumps or switches. Consider narrowing them down or creating new methods. ";
                    issues.add(createIssue("TooManyJumpsAndSwitches", -1, fileName, className, message, options.get("TooManyJumpsAndSwitches")));
                }
            }
        }
        return issues;
    }

    public Issue createIssue (String rule, int line, String fileName, String className, String message, String enumCatch) {
        if (enumCatch.equals("INFO")) {
            return new Issue(rule, line, fileName, className, message, Severity.INFO);
        } else if (enumCatch.equals("WARNING")) {
            return new Issue(rule, line, fileName, className, message, Severity.WARNING);
        } else if (enumCatch.equals("ERROR")) {
            return new Issue(rule, line, fileName, className, message, Severity.ERROR);
        } else {
            return new Issue(rule, line, fileName, className, message, Severity.SUPPRESS);
        }
    }
}
