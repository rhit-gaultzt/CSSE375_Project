package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.Options;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class IssueTest {

    @Test
    public void testConstructIssueWithClassNode() {
        ClassNode node = EasyMock.mock(ClassNode.class);
        Options options = EasyMock.mock(Options.class);
        String rule = "testRule";
        int line = 100;
        String message = "testMessage";
        Severity severity = Severity.WARNING;
        String filename = "testFile";
        String classname = "testClass";

        EasyMock.expect(node.getClassName()).andReturn(classname);
        EasyMock.expect(node.getFileName()).andReturn(filename);
        EasyMock.expect(options.get("severity")).andReturn(severity.toString());

        EasyMock.replay(node, options);

        Issue issue = new Issue(rule, node, line, options, message);

        EasyMock.verify(node, options);

        Assertions.assertEquals(rule, issue.getRule());
        Assertions.assertEquals(line, issue.getLine());
        Assertions.assertEquals(message, issue.getMessage());
        Assertions.assertEquals(severity, issue.getSeverity());
        Assertions.assertEquals(filename, issue.getFile());
        Assertions.assertEquals(classname, issue.getClassValue());
    }

    @Test
    public void testUpdateIssue() {
        ClassNode node = EasyMock.mock(ClassNode.class);
        Options options = EasyMock.mock(Options.class);
        String rule = "testRule";
        int line = 100;
        Severity severity = Severity.WARNING;
        String filename = "testFile";
        String classname = "testClass";

        EasyMock.expect(node.getClassName()).andReturn(classname);
        EasyMock.expect(node.getFileName()).andReturn(filename);
        EasyMock.expect(options.get("severity")).andReturn(severity.toString());

        EasyMock.replay(node, options);

        Issue issue = new Issue();
        issue.update(rule, node, line, options);

        EasyMock.verify(node, options);

        Assertions.assertEquals(rule, issue.getRule());
        Assertions.assertEquals(line, issue.getLine());
        Assertions.assertEquals(severity, issue.getSeverity());
        Assertions.assertEquals(filename, issue.getFile());
        Assertions.assertEquals(classname, issue.getClassValue());
    }
}
