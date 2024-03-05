package Domain;

public enum Severity {
    INFO,
    WARNING,
    ERROR,
    SUPPRESS;

    public String toDisplayString() {
        switch(this) {
            case INFO:
                return "Info";
            case WARNING:
                return "Warning";
            case ERROR:
                return "Error";
            case SUPPRESS:
                return "Suppress";
            default:
                return "invalid";
        }
    }


}
