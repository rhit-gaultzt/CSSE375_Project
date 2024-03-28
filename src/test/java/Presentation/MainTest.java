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

        Assertions.assertEquals(10, rules.size());
    }

    @Test
    public void testCloseStreams() throws IOException {
        Main main = new Main();
        byte[] input1 = new byte[] {1,2,3,4,5,6,7,8};
        byte[] input2 = new byte[] {2,4,6,8,1,3,5,7};
        InputStream sut1 = Files.newInputStream(new File("src/test/java/Presentation/MainTest.java").toPath());
        InputStream sut2 = Files.newInputStream(new File("src/main/java/Presentation/Main.java").toPath());
        List<InputStream> streams = new ArrayList<>();
        streams.add(sut1);
        streams.add(sut2);

        main.closeStreams(streams);

        Assertions.assertThrows(IOException.class, sut1::read);
        Assertions.assertThrows(IOException.class, sut2::read);
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