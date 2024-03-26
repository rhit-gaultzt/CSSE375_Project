package Domain.Rules;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.lang.reflect.Method;
import java.util.*;

public class ObserverPatternRule implements Rule {

    public final static String[] listForAdd = new String[]{"observer", "add", "register", "attach", "append", "join", "connect", "create", "produce", "push"};
    public final static String[] listForRemove = new String[]{"observer", "remove", "delete", "detach", "separate", "disconnect", "take", "pull", "peel"};
    public final static String[] listForNotify = new String[]{"observer", "inform", "notify", "tell", "advise", "alert", "warn", "report", "communicate", "send"};

    public final static int LINE_NUMBER = -1;

    @Override
    public Options getDefaultOptions() {
        Options options = new Options(new ArrayList<String>(), new ArrayList<String>());
        options.put("ObserverIdentified", Severity.INFO.toString());
        return options;
    }

    @Override
    public List<Issue> apply (Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<Issue>();

        for (Map.Entry<String, ClassNode> node : classNodes.entrySet()) { // Loop through every class
            ClassNode classNode = node.getValue();
            if (findObservee(classNode)) { // If the class found is an observee
                for (Map.Entry<String, ClassNode> classNodeInQuestion : classNodes.entrySet()) { // Loop through every class to find an observer
                    ClassNode classNodeInExamination = classNodeInQuestion.getValue();
                    if (classNodeInExamination.getClassName().contains("Observer")) { // If the class name contains observer
                        for (MethodNode observerMethod : classNodeInExamination.getMethods()) { // Loop through every method of observer
                            if (observerMethod.getName().contains("update")) { // If the class contains update
                                String infoMessage = "Observer Pattern Found";
                                issues.add(createIssue("ObserverIdentified", LINE_NUMBER, classNodeInExamination.getFileName(), classNodeInExamination.getClassName(), infoMessage, options.get("ObserverIdentified")));
                            }
                        }
                    }
                }
            }
        }
        return issues;
    }

    public boolean findObservee (ClassNode node) {
        return hasObserveeMethod(node, listForAdd) &&
                hasObserveeMethod(node, listForRemove) &&
                hasObserveeMethod(node, listForNotify);
    }

    private boolean hasObserveeMethod(ClassNode node, String[] listForType) {
        for (MethodNode method : node.getMethods()) {
            if (isObserveeMethod(method, listForType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isObserveeMethod(MethodNode method, String[] listForType) {
        return stringContainsItemFromList(method.getName(), listForType) &&
                methodContainsObserverArgument(method.getArgumentTypes());
    }

    public boolean methodContainsObserverArgument (List<ClassNode> arguments) {
        for (ClassNode argument : arguments) {
            if (argument.getClassName().contains("Observer")) {
                return true;
            }
        }
        return false;
    }

    public boolean stringContainsItemFromList (String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
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
