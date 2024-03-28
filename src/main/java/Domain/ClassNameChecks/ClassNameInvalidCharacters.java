package Domain.ClassNameChecks;

import Data.Options;
import Domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class ClassNameInvalidCharacters implements ClassNameCheck {

    @Override
    public List<Issue> doClassNameCheck(String className, Options options) {
        ArrayList<Issue> issues = new ArrayList<>();
        for (int i = 1; i < className.length(); i++) {
            char character = className.charAt(i);
            if (!Character.isJavaIdentifierPart(character)) {
                Issue issue = new Issue();
                issue.setMessage("class " + className + " has invalid character \'" + character + "\'");
                issues.add(issue);
            }
        }
        return issues;
    }

    @Override
    public Options getDefaultOptions() {
        return new Options(new ArrayList<>(), new ArrayList<>());
    }
}
