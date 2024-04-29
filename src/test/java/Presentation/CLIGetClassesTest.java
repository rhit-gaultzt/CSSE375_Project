package Presentation;

import Data.InputReader;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.PrintStream;

public class CLIGetClassesTest {

    @Test
    public void initialPromptTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expected = "Welcome to the Java Linter. You can either " +
                "specify classes as initial arguments or by specifying their paths here";

        output.println(expected);
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(input, output);
        cliGetClasses.initialPrompt();

        // Verify
        EasyMock.verify(input, output);
    }

    @Test
    public void getClassNameTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput = "What class would you like to lint?\n";
        String inputString = "testJar.jar";

        output.println(expectedOutput);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString);

        // Replay
        EasyMock.replay(input, output);
        String actual = cliGetClasses.getClassName();

        // Verify
        Assertions.assertEquals(inputString, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void wantsToAddMoreClassesTrueTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput = "Would you like to add another class? (y/n)\n";
        String inputString = "y";

        boolean expected = true;

        output.println(expectedOutput);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString);

        // Replay
        EasyMock.replay(input, output);
        boolean actual = cliGetClasses.wantsToAddMoreClasses();

        // Verify
        Assertions.assertEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void wantsToAddMoreClassesFalseTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput = "Would you like to add another class? (y/n)\n";
        String inputString = "n";

        boolean expected = false;

        output.println(expectedOutput);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString);

        // Replay
        EasyMock.replay(input, output);
        boolean actual = cliGetClasses.wantsToAddMoreClasses();

        // Verify
        Assertions.assertEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void wantsToAddMoreClassesInvalidTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput1 = "Would you like to add another class? (y/n)\n";
        String inputString1 = "f";
        String expectedOutput2 = "Please input y or n\n";
        String inputString2 = "y";


        boolean expected = true;

        output.println(expectedOutput1);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString1);
        output.println(expectedOutput2);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString2);

        // Replay
        EasyMock.replay(input, output);
        boolean actual = cliGetClasses.wantsToAddMoreClasses();

        // Verify
        Assertions.assertEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void getClassesFromCLIBasicTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput1 = "Welcome to the Java Linter. You can either " +
                "specify classes as initial arguments or by specifying their paths here";
        String expectedOutput2 = "What class would you like to lint?\n";
        String inputString1 = "testJar.jar";
        String expectedOutput3 = "Would you like to add another class? (y/n)\n";
        String inputString2 = "n";

        String[] expected = {inputString1};

        output.println(expectedOutput1);
        EasyMock.expectLastCall();
        output.println(expectedOutput2);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString1);
        output.println(expectedOutput3);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString2);

        // Replay
        EasyMock.replay(input, output);
        String[] actual = cliGetClasses.getClassesFromCLI(true);

        // Verify
        Assertions.assertArrayEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void getClassesFromCLIMutlipleTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expectedOutput1 = "Welcome to the Java Linter. You can either " +
                "specify classes as initial arguments or by specifying their paths here";
        String expectedOutput2 = "What class would you like to lint?\n";
        String inputString1 = "testJar.jar";
        String expectedOutput3 = "Would you like to add another class? (y/n)\n";
        String inputString2 = "y";
        String expectedOutput4 = "What class would you like to lint?\n";
        String inputString3 = "./target/classes/TestClasses/SwitchStatementClass.class";
        String expectedOutput5 = "Would you like to add another class? (y/n)\n";
        String inputString4 = "n";


        String[] expected = {inputString1, inputString3};

        output.println(expectedOutput1);
        EasyMock.expectLastCall();
        output.println(expectedOutput2);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString1);
        output.println(expectedOutput3);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString2);
        output.println(expectedOutput4);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString3);
        output.println(expectedOutput5);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString4);

        // Replay
        EasyMock.replay(input, output);
        String[] actual = cliGetClasses.getClassesFromCLI(true);

        // Verify
        Assertions.assertArrayEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void getClassesTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String[] args = new String[0];

        String expectedOutput1 = "Welcome to the Java Linter. You can either " +
                "specify classes as initial arguments or by specifying their paths here";
        String expectedOutput2 = "What class would you like to lint?\n";
        String inputString1 = "testJar.jar";
        String expectedOutput3 = "Would you like to add another class? (y/n)\n";
        String inputString2 = "y";
        String expectedOutput4 = "What class would you like to lint?\n";
        String inputString3 = "./target/classes/TestClasses/SwitchStatementClass.class";
        String expectedOutput5 = "Would you like to add another class? (y/n)\n";
        String inputString4 = "n";


        String[] expected = {inputString1, inputString3};

        output.println(expectedOutput1);
        EasyMock.expectLastCall();
        output.println(expectedOutput2);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString1);
        output.println(expectedOutput3);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString2);
        output.println(expectedOutput4);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString3);
        output.println(expectedOutput5);
        EasyMock.expectLastCall();
        EasyMock.expect(input.nextLine()).andReturn(inputString4);

        // Replay
        EasyMock.replay(input, output);
        String[] actual = cliGetClasses.getClasses(args, true);

        // Verify
        Assertions.assertArrayEquals(expected, actual);
        EasyMock.verify(input, output);
    }

    @Test
    public void getClassesHasArgsTest() {
        // Record
        InputReader input = EasyMock.mock(InputReader.class);
        PrintStream output = EasyMock.mock(PrintStream.class);
        CLIGetClasses cliGetClasses = new CLIGetClasses(input, output);

        String expected1 = "testJar.jar";
        String expected2 = "./target/classes/TestClasses/SwitchStatementClass.class";
        String expected3 = "java.util.HashMap";

        String[] args = {expected1, expected2, expected3};

        // Replay
        EasyMock.replay(input, output);
        String[] actual = cliGetClasses.getClasses(args, true);

        // Verify
        Assertions.assertArrayEquals(args, actual);
        EasyMock.verify(input, output);
    }
}
