package Presentation;

import Domain.Issue;
import Domain.Severity;

import java.util.HashMap;
import java.util.List;


public class CLIOutput implements Output {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_LINE  = "\u001B[4m";
    private static final String ANSI_ERROR = "\u001B[31m";
    private static final String ANSI_GREY  = "\u001B[37m";
    private final HashMap<Severity, String> ansi_colors;


    public CLIOutput() {
        ansi_colors = new HashMap<>();
        ansi_colors.put(Severity.ERROR, "\u001B[31m");
        ansi_colors.put(Severity.WARNING, "\u001B[33m");
        ansi_colors.put(Severity.INFO, "\u001B[34m");
        ansi_colors.put(Severity.SUPPRESS, "\u001B[37m");
    }


    @Override
    public void outputIssues(List<Issue> issues) {
        HashMap<Severity, Integer> total = new HashMap<>();
        for (Severity severity : Severity.values()) total.put(severity, 0);

        issues.sort((Issue a, Issue b) -> {
            String labelA = a.getFile() + a.getLine() + a.getClassValue();
            String labelB = b.getFile() + b.getLine() + b.getClassValue();
            return labelA.compareTo(labelB);
        });

        String filename      = "";
        String classname     = "";
        StringBuilder buffer = new StringBuilder();
        for (Issue issue: issues) {
            if (issue.getSeverity() == Severity.SUPPRESS) continue;
            if (!classname.equals(issue.getClassValue())) {
                filename  = issue.getFile();
                classname = issue.getClassValue();
                buffer.append(String.format("\n%s%s (%s)%s\n", ANSI_LINE,
                        classname, filename, ANSI_RESET));
            }

            String color = ansi_colors.get(issue.getSeverity());
            buffer.append(String.format("\t%s%s%s%s %s%s", color,
                    issue.getSeverity(), ANSI_RESET, ANSI_GREY, issue.getRule(), ANSI_RESET));

            if (issue.getLine() != -1)
                buffer.append(String.format(" (Line %s)", issue.getLine()));
            buffer.append(String.format("\n\t%s\n\n", issue.getMessage()));

            total.put(issue.getSeverity(), total.get(issue.getSeverity()) + 1);
        }

        // Final Results
        buffer.append(String.format("\n%sFinal Results%s\n", ANSI_LINE, ANSI_RESET));
        for (Severity severity : Severity.values()) {
            String color = ansi_colors.get(severity);
            buffer.append(String.format("\t%s%s%s: %s\n", color,
                    severity.toDisplayString(), ANSI_RESET,
                    total.get(severity).toString()));
        }


        System.out.println(buffer);
    }

    @Override
    public void outputError(String error) {
        System.out.printf("%sLinter Error%s%n", ANSI_LINE, ANSI_RESET);
        System.out.printf("%s%s%s%n", ANSI_ERROR, error, ANSI_RESET);
    }
}
