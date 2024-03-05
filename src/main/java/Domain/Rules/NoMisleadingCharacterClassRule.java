package Domain.Rules;

import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoMisleadingCharacterClassRule implements Rule {

    public final static String RULE = "MisleadingClassCharacter";

    @Override
    public Options getDefaultOptions() {
        Options options = new Options(new ArrayList<String>(), new ArrayList<String>());
        options.put("1 class", Severity.WARNING.toString());
        options.put("1 method", Severity.WARNING.toString());
        options.put("1 field", Severity.WARNING.toString());
        options.put("0 class", Severity.WARNING.toString());
        options.put("0 method", Severity.WARNING.toString());
        options.put("0 field", Severity.WARNING.toString());
        options.put("I class", Severity.INFO.toString());
        options.put("I method", Severity.INFO.toString());
        options.put("I field", Severity.INFO.toString());
        options.put("l class", Severity.INFO.toString());
        options.put("l method", Severity.INFO.toString());
        options.put("l field", Severity.INFO.toString());
        options.put("O class", Severity.INFO.toString());
        options.put("O method", Severity.INFO.toString());
        options.put("O field", Severity.INFO.toString());

        return options;
    }

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        // Loop through every single class in the map
        for (Map.Entry<String, ClassNode> node: classNodes.entrySet()) {
            ClassNode classNode = node.getValue();
            List<MethodNode> methods = classNode.getMethods();
            List<FieldNode> fields = classNode.getFields();
            int line = -1;
            String name = classNode.getClassName();
            String fileName = classNode.getFileName();
            for (FieldNode field : fields) {
                issues.addAll(stringChecker(field.getName(), "field", line, fileName, name, options));
            }

            for (MethodNode method : methods) {
                issues.addAll(stringChecker(method.getName(), "method", line, fileName, name, options));
            }
        }
        return issues;
    }

    public List<Issue> stringChecker (String name, String type, int line, String fileName, String className, Options options) {
        List<Issue> issues = new ArrayList<>();
        if (name.contains("1")) {
            String message = "Number 1 was found within " + type + " name " + "\"" + name + "\"";
            String enumCatch = options.get("1 " + type);
            issues.add(createIssue(line, fileName, className, message, enumCatch));
        }
        if (name.contains("I")) {
            String message = "Uppercase I was found within " + type + " name " + "\"" + name + "\"";
            String enumCatch = options.get("I " + type);
            issues.add(createIssue(line, fileName, className, message, enumCatch));
        }
        if (name.contains("l")) {
            String message = "Lowercase l was found within " + type + " name " + "\"" + name + "\"";
            String enumCatch = options.get("l " + type);
            issues.add(createIssue(line, fileName, className, message, enumCatch));
        }
        if (name.contains("0")) {
            String message = "Number 0 was found within " + type + " name " + "\"" + name + "\"";
            String enumCatch = options.get("0 " + type);
            issues.add(createIssue(line, fileName, className, message, enumCatch));
        }
        if (name.contains("O")) {
            String message = "Uppercase O was found within " + type + " name " + "\"" + name + "\"";
            String enumCatch = options.get("O " + type);
            issues.add(createIssue(line, fileName, className, message, enumCatch));
        }
        return issues;

    }

    public Issue createIssue (int line, String fileName, String className, String message, String enumCatch) {
        if (enumCatch.equals("INFO")) {
            return new Issue(RULE, line, fileName, className, message, Severity.INFO);
        } else if (enumCatch.equals("WARNING")) {
            return new Issue(RULE, line, fileName, className, message, Severity.WARNING);
        } else if (enumCatch.equals("ERROR")) {
            return new Issue(RULE, line, fileName, className, message, Severity.ERROR);
        } else {
            return new Issue(RULE, line, fileName, className, message, Severity.SUPPRESS);
        }
    }
}
