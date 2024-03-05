package Domain.Rules;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.JavaByteCodeAdapter.LocalVariableNode;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VariableNameConventionRule implements Rule {


    public enum Format {
        PASCAL,
        CAMEL,
        SNAKE,
        SCREAMING_SNAKE
    }


    protected enum Scope {
        FIELD,
        LOCAL;

        public String toDisplayString() {
            if (this == FIELD) {
                return "Field";
            }
            return "Local Method Variable";
        }
    }


    private Severity severity;
    private Format formatStatic;
    private Format formatVariable;


    @Override
    public Options getDefaultOptions() {
        return new Options(new ArrayList<String>(){{
                add("severity");
                add("variable");
                add("static");
            }}, new ArrayList<String>(){{
                add("WARNING");
                add("CAMEL");
                add("SCREAMING_SNAKE");
            }});
    }


    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        this.setupConfig(options);

        for (String classname : classNodes.keySet()) {
            ClassNode node     = classNodes.get(classname);
            String    filename = node.getSourceFile();

            for (FieldNode field : node.getFields()) {
                String name      = field.getName();
                Boolean isStatic = field.isStatic();
                Issue issue      = this.getFieldIssue(name, Scope.FIELD, null, isStatic, filename, classname);
                if (issue != null) issues.add(issue);
            }

            for (MethodNode method : node.getMethods()) {
                String methodName = method.getName();
                for (LocalVariableNode field : method.getLocalVariables()) {
                    String name = field.getName();
                    Issue issue = this.getFieldIssue(name, Scope.LOCAL, methodName, false, filename, classname);
                    if (issue != null) issues.add(issue);
                }
            }
        }

        return issues;
    }


    private void setupConfig (Options options) {
        this.severity       = options.get("severity", Severity.class, this.getClass().getSimpleName());
        this.formatStatic   = options.get("static", Format.class, this.getClass().getSimpleName());
        this.formatVariable = options.get("variable", Format.class, this.getClass().getSimpleName());
    }


    private Issue getFieldIssue (String name, Scope scope, String method,
                                 Boolean isStatic, String filename,
                                 String className)
    {
        Format format = isStatic ? this.formatStatic : this.formatVariable;
        String rule   = this.getClass().getSimpleName();
        String loc    = (scope == Scope.LOCAL) ? method + "." + name : name;
        String msg    = String.format("The %s \"%s\" does not follow "
                            + "the required %s naming "
                            + "convention", scope.toDisplayString(),
                            loc, format);
        if (this.isCase(name, format)) { return null; }
        return new Issue(rule, -1, filename, className, msg, this.severity);
    }


    private boolean isCase (String text, Format format) {
        switch (format) {
            case PASCAL:
                return this.isPascalCase(text);
            case CAMEL:
                return this.isCamelCase(text);
            case SNAKE:
                return this.isSnakeCase(text);
            case SCREAMING_SNAKE:
                return this.isScreamingSnakeCase(text);
        }
        return false;
    }


    // https://stackoverflow.com/questions/1128305/regex-for-pascalcased-words-aka-camelcased-with-leading-uppercase-letter
    public boolean isPascalCase(String text) {
         return text.matches("([A-Z0-9][a-z0-9]+)*");
    }


    // https://regex101.com/r/uR7tT3/1
    public boolean isCamelCase(String text) {
        return text.matches("^[a-z0-9]+([A-Z0-9][a-z0-9]+)*$");
    }


    // https://stackoverflow.com/questions/66472965/regex-validate-string-as-alphabetic-capital-or-capital-snake-case
    public boolean isSnakeCase(String text) {
        return text.matches("^[a-z0-9]+(?:_[a-z0-9]+)*$");
    }


    // https://stackoverflow.com/questions/66472965/regex-validate-string-as-alphabetic-capital-or-capital-snake-case
    public boolean isScreamingSnakeCase(String text) {
        return text.matches("^[A-Z0-9]+(?:_[A-Z0-9]+)*$");
    }


}
