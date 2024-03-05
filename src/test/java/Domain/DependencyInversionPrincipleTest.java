package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.FieldNode;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Rules.DependencyInversionPrincipleRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class DependencyInversionPrincipleTest {

    @Test
    public void testStringCheckerOneSame () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        String stringOne = "Test";
        String stringTwo = "Test";

        boolean expected = true;
        boolean actual = rule.stringChecker(stringOne, stringTwo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testStringCheckerTwoSame () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        String stringOne = "methodNameOne";
        String stringTwo = "methodNameOne";

        boolean expected = true;
        boolean actual = rule.stringChecker(stringOne, stringTwo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testStringCheckerOneDifferent () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        String stringOne = "methodNameOne";
        String stringTwo = "methodNameTwo";

        boolean expected = false;
        boolean actual = rule.stringChecker(stringOne, stringTwo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testStringCheckerTwoDifferent () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        String stringOne = "checkStringValue";
        String stringTwo = "addOneToList";

        boolean expected = false;
        boolean actual = rule.stringChecker(stringOne, stringTwo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addIssueSimilarClassWarning () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        Issue expected = new Issue("SimilarClassWarning", -1, "class.java", "class", "DependencyInversionPrinciple Violation", Severity.WARNING);
        Issue actual = rule.createIssue("SimilarClassWarning", -1, "class.java", "class", "DependencyInversionPrinciple Violation", "WARNING");

        Assertions.assertEquals(expected.rule, actual.rule);
        Assertions.assertEquals(expected.file, actual.file);
        Assertions.assertEquals(expected.classValue, actual.classValue);
        Assertions.assertEquals(expected.message, actual.message);
    }

    @Test
    public void addIssueDepInvPrincipleInfo () {
        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        Issue expected = new Issue("DepInvPrincipleInfo", -1, "class.java", "class", "Dependency Inversion Principle found in class", Severity.INFO);
        Issue actual = rule.createIssue("DepInvPrincipleInfo", -1, "class.java", "class", "Dependency Inversion Principle found in class", "INFO");

        Assertions.assertEquals(expected.rule, actual.rule);
        Assertions.assertEquals(expected.file, actual.file);
        Assertions.assertEquals(expected.classValue, actual.classValue);
        Assertions.assertEquals(expected.message, actual.message);
    }

    @Test
    public void testCompareMethods() {
        MethodNode methodOne = EasyMock.createMock(MethodNode.class);
        MethodNode methodTwo = EasyMock.createMock(MethodNode.class);

        ClassNode classOne = EasyMock.createMock(ClassNode.class);
        ClassNode classTwo = EasyMock.createMock(ClassNode.class);

        Options options = new Options(new ArrayList<String>(), new ArrayList<String>());
        options.put("SimilarClassWarning", Severity.WARNING.toString());

        EasyMock.expect(methodOne.getName()).andReturn("methodOne").anyTimes();
        EasyMock.expect(methodTwo.getName()).andReturn("methodTwo").anyTimes();
        EasyMock.expect(methodTwo.getArgumentTypes()).andReturn(Arrays.asList(classOne)).anyTimes();
        EasyMock.expect(methodOne.getArgumentTypes()).andReturn(Arrays.asList(classTwo)).anyTimes();
        EasyMock.expect(classOne.getClassName()).andReturn("com.example.ClassOne").anyTimes();
        EasyMock.expect(classTwo.getClassName()).andReturn("com.example.ClassTwo").anyTimes();
        EasyMock.replay(methodOne, methodTwo, classOne, classTwo);

        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        List<Issue> result = rule.compareMethods(Arrays.asList(methodOne), Arrays.asList(methodTwo), "ClassOne", "ClassTwo", "file1", "file2", options);

        Assertions.assertEquals(0, result.size());

        EasyMock.verify(methodOne, methodTwo, classOne, classTwo);
    }

    @Test
    public void testCheckForInterfaceWithInterface() {
        ClassNode mockClassNode = EasyMock.mock(ClassNode.class);

        List<ClassNode> interfaces = new ArrayList<>();
        ClassNode mockInterface = EasyMock.mock(ClassNode.class);
        interfaces.add(mockInterface);

        List<ClassNode> classes =new ArrayList<>();
        classes.add(mockInterface);
        classes.add(mockClassNode);

        EasyMock.expect(mockClassNode.getInterfaces()).andReturn(interfaces).anyTimes();
        EasyMock.replay(mockClassNode, mockInterface);

        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        boolean result = rule.checkForInterface(mockClassNode);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckForInterfaceWithoutInterface() {
        ClassNode mockClassNode = EasyMock.mock(ClassNode.class);
        List<ClassNode> nodes = new ArrayList<>();
        nodes.add(mockClassNode);

        List<ClassNode> interfaces = new ArrayList<>();
        EasyMock.expect(mockClassNode.getInterfaces()).andReturn(interfaces).anyTimes();
        EasyMock.replay(mockClassNode);

        DependencyInversionPrincipleRule rule = new DependencyInversionPrincipleRule();
        boolean result = rule.checkForInterface(mockClassNode);
        Assertions.assertFalse(result);
    }
}
