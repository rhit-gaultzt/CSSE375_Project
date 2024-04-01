package Domain;

import Domain.Rules.NoMisleadingCharacterClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;


public class NoMisleadingCharacterClassTest {

    @Test
    public void testStringlowercaselForClass () {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        List<Issue> issueList = rule.stringChecker("testWith_l", "method", 0, "class", "class", rule.getDefaultOptions());
        Issue actualIssue = issueList.get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Lowercase l was found within method name \"testWith_l\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringlowercaselForMethod() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("methodl", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Lowercase l was found within class name \"methodl\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringlowercaselForField() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("fieldName", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Lowercase l was found within class name \"fieldName\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseIForClass() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("NameI", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase I was found within class name \"NameI\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseIForMetho() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("methodI", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase I was found within class name \"methodI\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseIForField() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("VALI", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase I was found within class name \"VALI\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber1ForClass() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("Name1", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 1 was found within class name \"Name1\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber1ForMethod() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("method1", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 1 was found within class name \"method1\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber1ForField() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("VAL1", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 1 was found within class name \"VAL1\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber0ForClass() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("Name0", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 0 was found within class name \"Name0\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber0ForMethod() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("method0", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 0 was found within class name \"method0\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringNumber0ForField() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("VAL0", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Number 0 was found within class name \"VAL0\"", Severity.WARNING);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseOForClass() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("NameO", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase O was found within class name \"NameO\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseOForMethod() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("methodO", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase O was found within class name \"methodO\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testStringUppercaseOForField() {
        NoMisleadingCharacterClassRule rule = new NoMisleadingCharacterClassRule();
        Issue actualIssue = rule.stringChecker("VALO", "class", 0, "class", "class", rule.getDefaultOptions()).get(0);

        String actualRule = actualIssue.getRule();
        String actualClassValue = actualIssue.getClassValue();
        String actualMessage = actualIssue.getMessage();

        Issue expected = new Issue("MisleadingClassCharacter", 0, "class.java", "class", "Uppercase O was found within class name \"VALO\"", Severity.INFO);
        String expectedRule = expected.getRule();
        String expectedClassValue = expected.getClassValue();
        String expectedMessage = expected.getMessage();

        Assertions.assertEquals(expectedRule, actualRule);
        Assertions.assertEquals(expectedClassValue, actualClassValue);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


}
