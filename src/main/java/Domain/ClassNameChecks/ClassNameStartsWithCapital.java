package Domain.ClassNameChecks;

import Data.Options;
import Domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class ClassNameStartsWithCapital implements ClassNameCheck {

    @Override
    public List<Issue> doClassNameCheck(String className, Options options) {
        List<Issue> issues = new ArrayList<>();
        if (!Character.isUpperCase(className.charAt(0))) {
            Issue issue = new Issue();
            issue.message = "class " + className + " does not begin with a capital letter";
            issues.add(issue);
        }
        return issues;
    }

    @Override
    public Options getDefaultOptions() {
        return new Options(new ArrayList<>(), new ArrayList<>());
    }
}
