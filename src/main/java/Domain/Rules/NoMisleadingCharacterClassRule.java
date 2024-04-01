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
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        addOptions(new String[] {"1", "0"}, options, Severity.WARNING);
        addOptions(new String[] {"I", "l", "O"}, options, Severity.INFO);

        return options;
    }

    private void addOptions(String[] symbols, Options options, Severity warning) {
        for (String i : symbols) {
            options.put(i + " class", warning.toString());
            options.put(i + " method", warning.toString());
            options.put(i + " field", warning.toString());
        }
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
        final String[] symbols = {"1", "I", "l", "0", "O"};
        final String[] variants = {"Number", "Uppercase", "Lowercase", "Number", "Uppercase"};

        for (int i=0; i<symbols.length; i++) {
            if (name.contains(symbols[i])) {
                String message = variants[i] + " " + symbols[i] + " was found within " + type + " name \"" + name + "\"";
                String enumCatch = options.get(symbols[i] + " " + type);
                issues.add(new Issue(RULE, line, fileName, className, message, Severity.valueOf(enumCatch)));
            }
        }
        return issues;

    }
}
