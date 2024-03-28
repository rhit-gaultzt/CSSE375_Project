package Presentation;

import Data.OptionsReaderYAML;
import Domain.Issue;
import Domain.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    public void testSetupRules() {
        Main main = new Main();
        List<Rule> rules = main.setupRules();

        Assertions.assertEquals(11, rules.size());
    }

    @Test
    public void testFindIssues() throws IOException {
        Main main = new Main();
        String[] files = { "target/classes/TestClasses/mime.class", "target/classes/TestClasses/symbolism.class" };
        List<Issue> issues = main.findIssues(files, new OptionsReaderYAML("./config.yaml"));

        Assertions.assertEquals(3, issues.size());
        Assertions.assertEquals("PrincipleLeastKnowledgeRule", issues.get(0).rule);
        Assertions.assertEquals("ClassNameRule", issues.get(1).rule);
        Assertions.assertEquals("ClassNameRule", issues.get(2).rule);
    }
}