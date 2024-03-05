package Domain.Rules;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DependencyInversionPrincipleRule implements Rule {

    public final static int LINE_NUMBER = -1;

    @Override
    public Options getDefaultOptions() {
        Options options = new Options(new ArrayList<String>(), new ArrayList<String>());
        options.put("SimilarClassWarning", Severity.WARNING.toString());
        options.put("DepInvPrincipleInfo", Severity.INFO.toString());
        return options;
    }

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> node: classNodes.entrySet()) {
            ClassNode classInQuestion = node.getValue();
            if (checkForInterface(classInQuestion)) {
                String infoMessage = "Dependency Inversion Principle found in class \"" + classInQuestion.getClassName() + "\"";

                issues.add(createIssue("DepInvPrincipleInfo", LINE_NUMBER, classInQuestion.getFileName(), classInQuestion.getClassName(), infoMessage, options.get("DepInvPrincipleInfo")));
            } else {
                issues.addAll(checkForSimilarClasses(classInQuestion, classNodes, options));
            }
        }
        return issues;
    }

    // Checks for interface: TRUE IF INTERFACE EXISTS, FALSE IF NOT
    public boolean checkForInterface (ClassNode node) {
        return !node.getInterfaces().isEmpty();
    }

    // Checks for Abstract: TRUE IF ABSTRACT EXISTS, FALSE IF NOT
    public boolean checkForAbstract (ClassNode node) {
        return !(node.getSuperClass() == null);
    }

    public List<Issue> checkForSimilarClasses (ClassNode classInQuestion, Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> node: classNodes.entrySet()) {
            if (!classInQuestion.getClassName().equals(node.getValue().getClassName())) {
                issues.addAll(compareMethods(classInQuestion.getMethods(), node.getValue().getMethods(), classInQuestion.getClassName(), node.getValue().getClassName(), classInQuestion.getFileName(), node.getValue().getFileName(), options));
            }
        }
        return issues;
    }

    public List<Issue> compareMethods (List<MethodNode> methodSetOne, List<MethodNode> methodSetTwo, String classOneName, String classTwoName, String classOneFile, String classTwoFile, Options options) {
        List<Issue> issues = new ArrayList<>();
        for (MethodNode methodFromOne : methodSetOne) {
            for (MethodNode methodFromTwo : methodSetTwo) {
                if (stringChecker(methodFromTwo.getName(), methodFromOne.getName())) {
                    for (ClassNode argumentsFromTwo : methodFromTwo.getArgumentTypes()) {
                        for (ClassNode argumentsFromOne : methodFromOne.getArgumentTypes()) {
                            if (stringChecker(argumentsFromOne.getClassName(), argumentsFromTwo.getClassName())) {
                                String message = "DependencyInversionPrinciple Violation: Class \"" + classOneName + "\" found to be similar to \"" + classTwoName + "\"";
                                issues.add(createIssue("SimilarClassWarning", LINE_NUMBER, classOneFile, classOneFile + ", " + classTwoFile, message, options.get("SimilarClassWarning")));
                                return issues;
                            }
                        }
                    }
                }
            }
        }
        return issues;
    }

    public boolean stringChecker (String stringOne, String stringTwo) {
        return stringOne.equals(stringTwo);
    }

    // Pulled from NoMisleadingClassRule. Thus, this works.
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
