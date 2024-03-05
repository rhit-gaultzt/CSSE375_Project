package Domain.ClassNameChecks;

import Data.Options;
import Domain.Issue;

import java.util.List;

public interface ClassNameCheck {
    List<Issue> doClassNameCheck(String className, Options options);
    Options getDefaultOptions();
}
