package Domain;

public class Issue {
    public String rule;
    public int line;
    public String file;
    public String classValue;
    public String message;
    public Severity severity;

    public Issue() {

    }

    public Issue (String rule, int line, String file, String classValue, String message, Severity severity) {
        this.rule = rule;
        this.line = line;
        this.file = file;
        this.classValue = classValue;
        this.message = message;
        this.severity = severity;
    }
}
