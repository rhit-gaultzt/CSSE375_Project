package Presentation;

import Domain.Issue;

import java.util.List;

public interface Output {
    void outputIssues(List<Issue> issues);
    void outputJarLocation(String path);
    void outputError(String error);
}
