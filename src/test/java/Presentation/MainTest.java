package Presentation;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.OptionsReaderYAML;
import Domain.ClassStreamHandler;
import Domain.Issue;
import Domain.Rule;
import Domain.RuleHandler;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    @Test
    public void testCloseStreams() throws IOException {
        ClassStreamHandler streamHandler = new ClassStreamHandler();
        byte[] input1 = new byte[] {1,2,3,4,5,6,7,8};
        byte[] input2 = new byte[] {2,4,6,8,1,3,5,7};
        InputStream sut1 = Files.newInputStream(new File("src/test/java/Presentation/MainTest.java").toPath());
        InputStream sut2 = Files.newInputStream(new File("src/main/java/Presentation/Main.java").toPath());
        List<InputStream> streams = new ArrayList<>();
        streams.add(sut1);
        streams.add(sut2);

        streamHandler.closeStreams(streams);

        Assertions.assertThrows(IOException.class, sut1::read);
        Assertions.assertThrows(IOException.class, sut2::read);
    }

    @Test
    public void testFindIssuesBasic() throws IOException {
        Main main = new Main();
        String[] files = { "target/classes/TestClasses/mime.class", "target/classes/TestClasses/symbolism.class" };
        List<Issue> issues = main.findIssues(files, new OptionsReaderYAML("./config.yaml"),
                new ChangeJarOutput("./testOutput.jar"), new CLIGetClasses(), new ClassReaderASM(), new ClassStreamHandler());

        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("PrincipleLeastKnowledgeRule", issues.get(0).getRule());
        Assertions.assertEquals(13, issues.get(0).getLine());
        Assertions.assertEquals("method test makes call to TestClasses.C violating the Principle of Least Knowledge", issues.get(0).getMessage());
        Assertions.assertEquals("ClassNameRule", issues.get(2).getRule());
        Assertions.assertEquals(-1, issues.get(2).getLine());
        Assertions.assertEquals("class symbolism does not begin with a capital letter", issues.get(2).getMessage());
        Assertions.assertEquals("ClassNameRule", issues.get(3).getRule());
        Assertions.assertEquals(-1, issues.get(3).getLine());
        Assertions.assertEquals("class mime does not begin with a capital letter", issues.get(3).getMessage());
    }

    @Test
    public void testFindIssuesWithSwitch() throws IOException {
        Main main = new Main();
        String[] files = { "target/classes/TestClasses/mime.class", "target/classes/TestClasses/SwitchStatementClass.class" };
        List<Issue> issues = main.findIssues(files, new OptionsReaderYAML("./config.yaml"),
                new ChangeJarOutput("./testOutput.jar"), new CLIGetClasses(), new ClassReaderASM(), new ClassStreamHandler());

        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("PrincipleLeastKnowledgeRule", issues.get(0).getRule());
        Assertions.assertEquals(13, issues.get(0).getLine());
        Assertions.assertEquals("method test makes call to TestClasses.C violating the Principle of Least Knowledge", issues.get(0).getMessage());
        Assertions.assertEquals("SwitchRule", issues.get(2).getRule());
        Assertions.assertEquals(5, issues.get(2).getLine());
        Assertions.assertEquals("method doSwitch has a switch statement or is comparing the same variable to many values", issues.get(2).getMessage());
        Assertions.assertEquals("ClassNameRule", issues.get(3).getRule());
        Assertions.assertEquals(-1, issues.get(3).getLine());
        Assertions.assertEquals("class mime does not begin with a capital letter", issues.get(3).getMessage());
    }

    @Test
    public void testHandleChangeRules() throws IOException {
        // Record
        Main main = new Main();
        RuleHandler ruleHandler = EasyMock.mock(RuleHandler.class);
        ChangeJarOutput changeJarOutput = EasyMock.mock(ChangeJarOutput.class);
        List<ClassNode> classNodes = new ArrayList<>();

        EasyMock.expect(ruleHandler.applyChangeRules()).andReturn(classNodes);
        changeJarOutput.saveClassesAsJar(classNodes, "test.jar");
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(ruleHandler, changeJarOutput);
        main.handleChangeRules(ruleHandler, changeJarOutput, new String[]{"test.jar"});

        // Verify
        EasyMock.verify(ruleHandler, changeJarOutput);
    }

    @Test
    public void testHandleChangeRulesInvalid() throws IOException {
        // Record
        Main main = new Main();
        RuleHandler ruleHandler = EasyMock.mock(RuleHandler.class);
        ChangeJarOutput changeJarOutput = EasyMock.mock(ChangeJarOutput.class);

        // Replay
        EasyMock.replay(ruleHandler, changeJarOutput);
        main.handleChangeRules(ruleHandler, changeJarOutput, new String[]{"Class.class", "test.jar"});

        // Verify
        EasyMock.verify(ruleHandler, changeJarOutput);
    }

    @Test
    public void testFindIssuesNoneCharacterization() throws IOException {
        // Record
        Main main = new Main();
        String[] args = {"a.class"};
        OptionsReaderYAML optionsReader = EasyMock.mock(OptionsReaderYAML.class);
        ChangeJarOutput jarOuput = EasyMock.mock(ChangeJarOutput.class);
        CLIGetClasses getClasses = EasyMock.mock(CLIGetClasses.class);
        ClassReader classReader = EasyMock.mock(ClassReader.class);
        ClassStreamHandler streamHandler = EasyMock.mock(ClassStreamHandler.class);

        EasyMock.expect(getClasses.getClasses(args, true)).andReturn(args);
        EasyMock.expect(streamHandler.getClassStreams(args)).andReturn(new HashMap<>());
        EasyMock.expect(optionsReader.readOptions()).andReturn(new HashMap<>());

        // Replay
        EasyMock.replay(optionsReader, jarOuput, getClasses, classReader, streamHandler);
        List<Issue> issues = main.findIssues(args, optionsReader, jarOuput,
                getClasses, classReader, streamHandler);

        // Verify
        EasyMock.verify(optionsReader, jarOuput, getClasses, classReader, streamHandler);
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void testFindIssuesWithClassesCharacterization() throws IOException {
        // Record
        Main main = new Main();
        String[] args = {"a.class"};
        OptionsReaderYAML optionsReader = EasyMock.mock(OptionsReaderYAML.class);
        ChangeJarOutput jarOuput = EasyMock.mock(ChangeJarOutput.class);
        CLIGetClasses getClasses = EasyMock.mock(CLIGetClasses.class);
        ClassReader classReader = EasyMock.mock(ClassReader.class);
        ClassStreamHandler streamHandler = EasyMock.mock(ClassStreamHandler.class);
        InputStream stream = EasyMock.mock(InputStream.class);
        ClassNode node = EasyMock.mock(ClassNode.class);

        HashMap<String, InputStream> streams = new HashMap<>();
        streams.put(args[0], stream);

        EasyMock.expect(getClasses.getClasses(args, true)).andReturn(args);
        EasyMock.expect(streamHandler.getClassStreams(args)).andReturn(streams);
        EasyMock.expect(optionsReader.readOptions()).andReturn(new HashMap<>());
        EasyMock.expect(classReader.createClassNode(stream)).andReturn(node);
        stream.close();
        EasyMock.expectLastCall();
        EasyMock.expect(node.getSourceFile()).andReturn("c.class").times(3);
        EasyMock.expect(node.isAbstract()).andReturn(false);
        EasyMock.expect(node.getSuperClass()).andReturn(node);
        EasyMock.expect(node.getClassName()).andReturn("c").times(6);
        EasyMock.expect(node.getFields()).andReturn(new ArrayList<>()).times(4);
        EasyMock.expect(node.getMethods()).andReturn(new ArrayList<>()).times(7);
        EasyMock.expect(node.isValid()).andReturn(true);
        EasyMock.expect(node.getFileName()).andReturn("c.class").times(2);
        EasyMock.expect(node.getInterfaces()).andReturn(new ArrayList<>());
        EasyMock.expect(node.getShortenedClassName()).andReturn("c");

        // Replay
        EasyMock.replay(optionsReader, jarOuput, getClasses, classReader, streamHandler, stream, node);
        List<Issue> issues = main.findIssues(args, optionsReader, jarOuput,
                getClasses, classReader, streamHandler);

        // Verify
        EasyMock.verify(optionsReader, jarOuput, getClasses, classReader, streamHandler, stream, node);
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals("ClassNameRule", issues.get(0).getRule());
        Assertions.assertEquals("class c does not begin with a capital letter", issues.get(0).getMessage());
        Assertions.assertEquals(-1, issues.get(0).getLine());
    }
}