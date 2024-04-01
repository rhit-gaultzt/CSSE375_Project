package Domain;

import Data.Dictionary;
import Data.DictionaryAPIAdapter;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.Options;
import Domain.ClassNameChecks.ClassNameCheck;
import Domain.ClassNameChecks.ClassNameInvalidCharacters;
import Domain.ClassNameChecks.ClassNameInvalidWords;
import Domain.ClassNameChecks.ClassNameStartsWithCapital;
import Domain.Rules.ClassNameRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ClassNameRuleTest {

    @Test
    public void classNameStartsWithCapitalTest() {
        // Record
        ClassNameCheck classNameStartsWithCapital = new ClassNameStartsWithCapital();
        String className = "Zack";
        Options options = new Options(new ArrayList<>(), new ArrayList<>()); // does not matter for this check

        // Replay
        List<Issue> issues = classNameStartsWithCapital.doClassNameCheck(className, options);

        // Verify - className is capital so should have no issues
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameDoesNotStartWithCapitalTest() {
        // Record
        ClassNameCheck classNameStartsWithCapital = new ClassNameStartsWithCapital();
        String className = "zack";
        Options options = new Options(new ArrayList<>(), new ArrayList<>()); // does not matter for this check
        String expectedMessage = "class zack does not begin with a capital letter";

        // Replay
        List<Issue> issues = classNameStartsWithCapital.doClassNameCheck(className, options);

        // Verify - className is not capital so should have issue
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameNoInvalidCharactersTest() {
        // Record
        ClassNameCheck classNameInvalidCharacters = new ClassNameInvalidCharacters();
        String className = "Zack";
        Options options = new Options(new ArrayList<>(), new ArrayList<>()); // does not matter for this check

        // Replay
        List<Issue> issues = classNameInvalidCharacters.doClassNameCheck(className, options);

        // Verify - className does not have any invalid characters so should be empty
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameOneInvalidCharactersTest() {
        // Record
        ClassNameCheck classNameInvalidCharacters = new ClassNameInvalidCharacters();
        String className = "House-Maker";
        Options options = new Options(new ArrayList<>(), new ArrayList<>()); // does not matter for this check
        String expectedMessage = "class House-Maker has invalid character \'-\'";

        // Replay
        List<Issue> issues = classNameInvalidCharacters.doClassNameCheck(className, options);

        // Verify - className does have invalid character - so should have issue
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameManyInvalidCharactersTest() {
        // Record
        ClassNameCheck classNameInvalidCharacters = new ClassNameInvalidCharacters();
        String className = "House-M@ker=Maker";
        Options options = new Options(new ArrayList<>(), new ArrayList<>()); // does not matter for this check
        String expectedMessage1 = "class House-M@ker=Maker has invalid character \'-\'";
        String expectedMessage2 = "class House-M@ker=Maker has invalid character \'@\'";
        String expectedMessage3 = "class House-M@ker=Maker has invalid character \'=\'";

        // Replay
        List<Issue> issues = classNameInvalidCharacters.doClassNameCheck(className, options);

        // Verify - className does have 3 invalid characters - so should have s issue
        assertEquals(3, issues.size());
        assertEquals(expectedMessage1, issues.get(0).getMessage());
        assertEquals(expectedMessage2, issues.get(1).getMessage());
        assertEquals(expectedMessage3, issues.get(2).getMessage());
    }

    @Test
    public void classNameSingleNounTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("false");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "House";

        EasyMock.expect(dictionary.isNoun(className)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid noun - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameNotNounOnlyAllowNounTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("false");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "See";
        String expectedMessage = "class name contains invalid word: See is not a noun";

        EasyMock.expect(dictionary.isNoun(className)).andReturn(false);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className is not noun - issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameSingleVerbTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("false");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "See";

        EasyMock.expect(dictionary.isVerb(className)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid verb - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameNotVerbOnlyAllowVerbTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("false");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Person";
        String expectedMessage = "class name contains invalid word: Person is not a verb";

        EasyMock.expect(dictionary.isVerb(className)).andReturn(false);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className is not verb - issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameSingleAnyWordTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("true");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Blue";

        EasyMock.expect(dictionary.isWord(className)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid word - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameNotWordAnyWordAllowedVerbTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("true");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Xyzhde";
        String expectedMessage = "class name contains invalid word: Xyzhde is not a word";

        EasyMock.expect(dictionary.isWord(className)).andReturn(false);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className is not word - issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameAllowAnything() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("true");
        optionsKeys.add("allowAnything");
        optionsValues.add("true");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Xyzhde";

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className can be anything - no issue
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameAllowNounAndVerbIsVerbTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "See";

        EasyMock.expect(dictionary.isNoun(className)).andReturn(false);
        EasyMock.expect(dictionary.isVerb(className)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid verb - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameAllowNounAndVerbIsNounTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Person";

        EasyMock.expect(dictionary.isNoun(className)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid noun - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameAllowNounAndVerbIsNeitherTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Blue";
        String expectedMessage = "class name contains invalid word: Blue is not a noun or verb";

        EasyMock.expect(dictionary.isNoun(className)).andReturn(false);
        EasyMock.expect(dictionary.isVerb(className)).andReturn(false);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className is not valid noun or verb - has issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameAllowNoneTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("false");
        optionsKeys.add("allowVerb");
        optionsValues.add("false");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String className = "Person";
        String expectedMessage = "class name contains invalid word: Person";

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className is not valid - 1 issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameCompoundPassTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("true");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String word1 = "Bike";
        String word2 = "Running";
        String className = word1 + word2;

        EasyMock.expect(dictionary.isNoun(word1)).andReturn(true);
        EasyMock.expect(dictionary.isNoun(word2)).andReturn(false);
        EasyMock.expect(dictionary.isVerb(word2)).andReturn(true);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has valid words - no issues
        EasyMock.verify(dictionary);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameCompound1FailTest() {
        // Record
        Dictionary dictionary = EasyMock.mock(Dictionary.class);
        ClassNameCheck classNameInvalidWords = new ClassNameInvalidWords(dictionary);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("true");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("false");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        Options options = new Options(optionsKeys, optionsValues);
        String word1 = "Bike";
        String word2 = "Running";
        String className = word1 + word2;
        String expectedMessage = "class name contains invalid word: Running is not a noun";

        EasyMock.expect(dictionary.isNoun(word1)).andReturn(true);
        EasyMock.expect(dictionary.isNoun(word2)).andReturn(false);

        // Replay
        EasyMock.replay(dictionary);
        List<Issue> issues = classNameInvalidWords.doClassNameCheck(className, options);

        // Verify - className has invalid word - 1 issue
        EasyMock.verify(dictionary);
        assertEquals(1, issues.size());
        assertEquals(expectedMessage, issues.get(0).getMessage());
    }

    @Test
    public void classNameRule1Check1ClassMocked() {
        // Record
        ClassNameCheck check = EasyMock.mock(ClassNameCheck.class);
        ArrayList<ClassNameCheck> checks = new ArrayList<>();
        checks.add(check);
        ClassNameRule rule = new ClassNameRule(checks);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        Options options = new Options(optionsKeys, optionsValues);
        ClassNode classNode = EasyMock.mock(ClassNode.class);
        String fullName = "java.util.ArrayList";
        String shortenedName = "ArrayList";
        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(fullName, classNode);
        List<Issue> checkIssues = new ArrayList<>();

        EasyMock.expect(classNode.getShortenedClassName()).andReturn(shortenedName);
        EasyMock.expect(check.doClassNameCheck(shortenedName, options)).andReturn(checkIssues);

        // Replay - calls the check with shortened name and combines issues
        EasyMock.replay(check, classNode);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - no issues
        EasyMock.verify(check, classNode);
        assertEquals(0, issues.size());
    }

    @Test
    public void classNameRule2Check2ClassWithIssueMocked() {
        // Record
        ClassNameCheck check1 = EasyMock.mock(ClassNameCheck.class);
        ClassNameCheck check2 = EasyMock.mock(ClassNameCheck.class);
        ArrayList<ClassNameCheck> checks = new ArrayList<>();
        checks.add(check1);
        checks.add(check2);
        ClassNameRule rule = new ClassNameRule(checks);
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        String warning = "WARNING";
        optionsValues.add(warning);
        Options options = new Options(optionsKeys, optionsValues);
        ClassNode classNode1 = EasyMock.mock(ClassNode.class);
        ClassNode classNode2 = EasyMock.mock(ClassNode.class);
        String fileName1 = "filename/";
        String fullName1 = "java.util.ArrayList";
        String shortenedName1 = "ArrayList";
        String fullName2 = "java.util.HashMap";
        String shortenedName2 = "HashMap";
        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(fullName1, classNode1);
        classNodes.put(fullName2, classNode2);
        Issue issue = new Issue();
        String message = "this is a message";
        issue.setMessage(message);
        List<Issue> check1Issues = new ArrayList<>();
        check1Issues.add(issue);
        String ruleName = "ClassNameRule";

        EasyMock.expect(classNode1.getShortenedClassName()).andReturn(shortenedName1);
        EasyMock.expect(classNode2.getShortenedClassName()).andReturn(shortenedName2);
        EasyMock.expect(check1.doClassNameCheck(shortenedName1, options)).andReturn(check1Issues);
        EasyMock.expect(classNode1.getClassName()).andReturn(fullName1);
        EasyMock.expect(classNode1.getFileName()).andReturn(fileName1);
        EasyMock.expect(check1.doClassNameCheck(shortenedName2, options)).andReturn(new ArrayList<>());
        EasyMock.expect(check2.doClassNameCheck(shortenedName1, options)).andReturn(new ArrayList<>());
        EasyMock.expect(check2.doClassNameCheck(shortenedName2, options)).andReturn(new ArrayList<>());

        // Replay - calls the check with shortened name and combines issues
        EasyMock.replay(check1, check2, classNode1, classNode2);
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify - has 1 issue with all information
        EasyMock.verify(check1, check2, classNode1, classNode2);
        assertEquals(1, issues.size());
        assertEquals(message, issues.get(0).getMessage());
        assertEquals(Severity.WARNING, issues.get(0).getSeverity());
        assertEquals(ruleName, issues.get(0).getRule());
        assertEquals(fullName1, issues.get(0).getClassValue());
        assertEquals(fileName1, issues.get(0).getFile());
        assertEquals(-1, issues.get(0).getLine());
    }

    @Test
    public void classNameStartsWithCapitalDefaultOptionsTest() {
        // Record
        ClassNameStartsWithCapital check = new ClassNameStartsWithCapital();

        // Replay
        Options options = check.getDefaultOptions();

        // Verify
        assertEquals(0, options.attributes.size());
    }

    @Test
    public void classNameInvalidCharactersOptionsTest() {
        // Record
        ClassNameInvalidCharacters check = new ClassNameInvalidCharacters();

        // Replay
        Options options = check.getDefaultOptions();

        // Verify
        assertEquals(0, options.attributes.size());
    }

    @Test
    public void classNameInvalidWordsOptionsTest() {
        // Record
        ClassNameInvalidWords check = new ClassNameInvalidWords(null);
        String key1 = "allowCompoundName";
        String value1 = "true";
        String key2 = "allowNoun";
        String value2 = "true";
        String key3 = "allowVerb";
        String value3 = "true";
        String key4 = "allowAnyWord";
        String value4 = "false";
        String key5 = "allowAnything";
        String value5 = "false";

        // Replay
        Options options = check.getDefaultOptions();

        // Verify
        assertEquals(5, options.attributes.size());
        assertEquals(value1, options.get(key1));
        assertEquals(value2, options.get(key2));
        assertEquals(value3, options.get(key3));
        assertEquals(value4, options.get(key4));
        assertEquals(value5, options.get(key5));
    }

    @Test
    public void classNameRuleOptionsTest() {
        // Record
        String key0 = "severity";
        String value0 = "WARNING";
        String key1 = "allowCompoundName";
        String value1 = "true";
        String key2 = "allowNoun";
        String value2 = "true";
        String key3 = "allowVerb";
        String value3 = "true";
        String key4 = "allowAnyWord";
        String value4 = "false";
        String key5 = "allowAnything";
        String value5 = "false";
        ArrayList<String> optionsKeys1 = new ArrayList<>();
        ArrayList<String> optionsValues1 = new ArrayList<>();
        optionsKeys1.add(key1);
        optionsValues1.add(value1);
        optionsKeys1.add(key2);
        optionsValues1.add(value2);
        optionsKeys1.add(key3);
        optionsValues1.add(value3);
        ArrayList<String> optionsKeys2 = new ArrayList<>();
        ArrayList<String> optionsValues2 = new ArrayList<>();
        optionsKeys2.add(key4);
        optionsValues2.add(value4);
        optionsKeys2.add(key5);
        optionsValues2.add(value5);
        Options options1 = new Options(optionsKeys1, optionsValues1);
        Options options2 = new Options(optionsKeys2, optionsValues2);
        ClassNameCheck check1 = EasyMock.mock(ClassNameCheck.class);
        ClassNameCheck check2 = EasyMock.mock(ClassNameCheck.class);
        ArrayList<ClassNameCheck> checks = new ArrayList<>();
        checks.add(check1);
        checks.add(check2);
        ClassNameRule rule = new ClassNameRule(checks);

        EasyMock.expect(check1.getDefaultOptions()).andReturn(options1);
        EasyMock.expect(check2.getDefaultOptions()).andReturn(options2);

        // Replay
        EasyMock.replay(check1, check2);
        Options options = rule.getDefaultOptions();

        // Verify
        EasyMock.verify(check1, check2);
        assertEquals(6, options.attributes.size());
        assertEquals(value0, options.get(key0));
        assertEquals(value1, options.get(key1));
        assertEquals(value2, options.get(key2));
        assertEquals(value3, options.get(key3));
        assertEquals(value4, options.get(key4));
        assertEquals(value5, options.get(key5));
    }

    @Test
    public void classNameRuleConcreteNoViolations() throws IOException {
        // Record
        String className = "TestClasses.BasicClass";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        ClassNameRule classNameRule = new ClassNameRule(new ArrayList<ClassNameCheck>() {{
            add(new ClassNameStartsWithCapital());
            add(new ClassNameInvalidCharacters());
            add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
        }});
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = classNameRule.apply(
                classNodes, classNameRule.getDefaultOptions());

        // Verify - not class name violations
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void classNameRuleConcreteNotCapitalized() throws IOException {
        // Record
        String expectedMessage = "class mime does not begin with a capital letter";
        String className = "TestClasses.mime";
        String fileName = "mime.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        ClassNameRule classNameRule = new ClassNameRule(new ArrayList<ClassNameCheck>() {{
            add(new ClassNameStartsWithCapital());
            add(new ClassNameInvalidCharacters());
            add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
        }});
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = classNameRule.apply(
                classNodes, classNameRule.getDefaultOptions());

        // Verify - starts without capital
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals(expectedMessage, issues.get(0).getMessage());
        Assertions.assertEquals(-1, issues.get(0).getLine());
        Assertions.assertEquals(className, issues.get(0).getClassValue());
        Assertions.assertEquals(Severity.WARNING, issues.get(0).getSeverity());
        Assertions.assertEquals(fileName, issues.get(0).getFile());
    }

    @Test
    public void classNameRuleConcreteAllowVerbNotVerb() throws IOException {
        // Record
        String expectedMessage1 = "class symbolism does not begin with a capital letter";
        String expectedMessage2 = "class name contains invalid word: symbolism is not a verb";
        String className = "TestClasses.symbolism";
        String fileName = "symbolism.java";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        ClassNameRule classNameRule = new ClassNameRule(new ArrayList<ClassNameCheck>() {{
            add(new ClassNameStartsWithCapital());
            add(new ClassNameInvalidCharacters());
            add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
        }});
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("severity");
        optionsValues.add("WARNING");
        optionsKeys.add("allowCompound");
        optionsValues.add("true");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowNoun");
        optionsValues.add("false");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");

        Options options = new Options(optionsKeys, optionsValues);

        // Replay
        List<Issue> issues = classNameRule.apply(
                classNodes, options);

        // Verify - starts with capital and is not a verb
        Assertions.assertEquals(2, issues.size());
        Assertions.assertEquals(expectedMessage1, issues.get(0).getMessage());
        Assertions.assertEquals(-1, issues.get(0).getLine());
        Assertions.assertEquals(className, issues.get(0).getClassValue());
        Assertions.assertEquals(Severity.WARNING, issues.get(0).getSeverity());
        Assertions.assertEquals(fileName, issues.get(0).getFile());

        Assertions.assertEquals(expectedMessage2, issues.get(1).getMessage());
        Assertions.assertEquals(-1, issues.get(1).getLine());
        Assertions.assertEquals(className, issues.get(1).getClassValue());
        Assertions.assertEquals(Severity.WARNING, issues.get(1).getSeverity());
        Assertions.assertEquals(fileName, issues.get(1).getFile());
    }

    @Test
    public void classNameRuleConcreteOnSelf() throws IOException {
        // Record
        String className = "Domain.Rules.ClassNameRule";
        ClassNode classNode = new ClassReaderASM().createClassNode(className);
        ClassNameRule classNameRule = new ClassNameRule(new ArrayList<ClassNameCheck>() {{
            add(new ClassNameStartsWithCapital());
            add(new ClassNameInvalidCharacters());
            add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
        }});
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put(className, classNode);

        // Replay
        List<Issue> issues = classNameRule.apply(
                classNodes, classNameRule.getDefaultOptions());

        // Verify - not class name violations
        Assertions.assertEquals(0, issues.size());
    }

}
