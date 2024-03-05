package Domain.Rules;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.Options;
import Domain.ClassNameChecks.ClassNameCheck;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassNameRule implements Rule {

    private List<ClassNameCheck> classNameChecks;
    private final String ruleName = "ClassNameRule";
    private final int lineNumber = -1;

    public ClassNameRule(List<ClassNameCheck> classNameChecks) {
        this.classNameChecks = classNameChecks;
    }

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        ArrayList<Issue> issues = new ArrayList<>();
        for (Map.Entry<String, ClassNode> entry : classNodes.entrySet()) {
            String shortClassName = entry.getValue().getShortenedClassName();
            for (ClassNameCheck check : classNameChecks) {
                List<Issue> checkIssues = check.doClassNameCheck(shortClassName, options);
                for (Issue issue : checkIssues) {
                    issue.classValue = entry.getValue().getClassName();
                    issue.file = entry.getValue().getFileName();
                    issue.line = this.lineNumber;
                    issue.rule = this.ruleName;
                    issue.severity = Severity.valueOf(options.get("severity"));
                }
                issues.addAll(checkIssues);
            }
        }
        return issues;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        Options options = new Options(optionsKeys, optionsValues);
        for (ClassNameCheck check : classNameChecks) {
            options.applyDefault(check.getDefaultOptions());
        }
        return options;
    }
}
