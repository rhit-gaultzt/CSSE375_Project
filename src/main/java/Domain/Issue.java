package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.Options;

public class Issue {
    private String rule;
    private int line;
    private String file;
    private String classValue;
    private String message;
    private Severity severity;

    public Issue() {

    }

    public Issue (String rule, ClassNode node, int line, Options options, String message) {
        update(rule, node, line, options);
        this.message = message;
    }

    public Issue (String rule, int line, String file, String classValue, String message, Severity severity) {
        this.rule = rule;
        this.line = line;
        this.file = file;
        this.classValue = classValue;
        this.message = message;
        this.severity = severity;
    }

    public void update(String rule, ClassNode node, int line, Options options) {
        this.rule = rule;
        this.line = line;
        this.classValue = node.getClassName();
        this.file = node.getFileName();
        String severity = options.get("severity");
        this.severity = severity == null ? Severity.SUPPRESS : Severity.valueOf(severity);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getClassValue() {
        return classValue;
    }

    public String getFile() {
        return file;
    }

    public String getMessage() {
        return message;
    }

    public String getRule() {
        return rule;
    }
}
