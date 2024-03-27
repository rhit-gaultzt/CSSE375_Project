package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.Options;
import Data.OptionsReaderYAML;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RuleHandlerTest {


    @Test
    public void applyRulesSingleClassSingleRule() throws IOException {
        OptionsReaderYAML optionsReader  = EasyMock.mock(OptionsReaderYAML.class);
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        Map<String, Options> options = EasyMock.mock(Map.class);

        // Classes
        ClassNode classNode1         = EasyMock.mock(ClassNode.class);
        InputStream inputStream1      = EasyMock.mock(InputStream.class);
        HashMap<String, InputStream> classes    = new HashMap<>();
        classes.put("ClassName1", inputStream1);

        // Rules
        Rule    rule1                = EasyMock.mock(Rule.class);
        Options optRule1             = EasyMock.mock(Options.class);
        Options defOptRule1          = EasyMock.mock(Options.class);
        ArrayList<Rule> rules        = new ArrayList<>();
        rules.add(rule1);

        // Issues
        Issue issue1 = new Issue("I1", 23, "I2", "I2", "I3", Severity.INFO);

        // Record
        EasyMock.expect(optionsReader.readOptions()).andReturn(options);
        EasyMock.expect(classReader.createClassNode(inputStream1)).andReturn(classNode1);
        EasyMock.expect(rule1.getDefaultOptions()).andReturn(defOptRule1);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule1);
        optRule1.applyDefault(defOptRule1);
        EasyMock.expect(rule1.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue1);
        }});
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classes, classReader);
        ArrayList<Issue> issues = (ArrayList<Issue>) ruleHandler.applyRules();

        EasyMock.verify(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        Assertions.assertEquals(new ArrayList<Issue>(){{
                add(issue1);
            }}, issues);
    }


    @Test
    public void applyRulesMultipleClassSingleRule() throws IOException {
        OptionsReaderYAML optionsReader  = EasyMock.mock(OptionsReaderYAML.class);
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        Map<String, Options> options = EasyMock.mock(Map.class);

        // Classes
        ClassNode classNode1         = EasyMock.mock(ClassNode.class);
        ClassNode classNode2         = EasyMock.mock(ClassNode.class);
        ClassNode classNode3         = EasyMock.mock(ClassNode.class);
        InputStream inputStream1      = EasyMock.mock(InputStream.class);
        InputStream inputStream2      = EasyMock.mock(InputStream.class);
        InputStream inputStream3      = EasyMock.mock(InputStream.class);
        HashMap<String, InputStream> classes    = new HashMap<>();
        classes.put("ClassName1", inputStream1);
        classes.put("ClassName2", inputStream2);
        classes.put("ClassName3", inputStream3);

        // Rules
        Rule    rule1                = EasyMock.mock(Rule.class);
        Options optRule1             = EasyMock.mock(Options.class);
        Options defOptRule1          = EasyMock.mock(Options.class);
        ArrayList<Rule> rules        = new ArrayList<>();
        rules.add(rule1);

        // Issues
        Issue issue1 = new Issue("I1", 23, "I1", "I1", "I1", Severity.INFO);
        Issue issue2 = new Issue("I2", -1, "I2", "I2", "I2", Severity.INFO);
        Issue issue3 = new Issue("I3", 18, "I3", "I3", "I3", Severity.INFO);

        // Record
        EasyMock.expect(optionsReader.readOptions()).andReturn(options);
        EasyMock.expect(classReader.createClassNode(inputStream1)).andReturn(classNode1);
        EasyMock.expect(classReader.createClassNode(inputStream2)).andReturn(classNode2);
        EasyMock.expect(classReader.createClassNode(inputStream3)).andReturn(classNode3);
        EasyMock.expect(rule1.getDefaultOptions()).andReturn(defOptRule1);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule1);
        optRule1.applyDefault(defOptRule1);
        EasyMock.expect(rule1.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue1);
            add(issue2);
            add(issue3);
        }});
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(optionsReader, classReader, options, classNode1, classNode2, classNode3, rule1, defOptRule1, optRule1, inputStream1, inputStream2, inputStream3);
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classes, classReader);
        ArrayList<Issue> issues = (ArrayList<Issue>) ruleHandler.applyRules();

        EasyMock.verify(optionsReader, classReader, options, classNode1, classNode2, classNode3, rule1, defOptRule1, optRule1, inputStream1, inputStream2, inputStream3);
        Assertions.assertEquals(new ArrayList<Issue>(){{
            add(issue1);
            add(issue2);
            add(issue3);
        }}, issues);
    }


    @Test
    public void applyRulesMultipleClassMultipleRule() throws IOException {
        OptionsReaderYAML optionsReader  = EasyMock.mock(OptionsReaderYAML.class);
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        Map<String, Options> options = EasyMock.mock(Map.class);

        // Classes
        ClassNode classNode1         = EasyMock.mock(ClassNode.class);
        ClassNode classNode2         = EasyMock.mock(ClassNode.class);
        ClassNode classNode3         = EasyMock.mock(ClassNode.class);
        InputStream inputStream1      = EasyMock.mock(InputStream.class);
        InputStream inputStream2      = EasyMock.mock(InputStream.class);
        InputStream inputStream3      = EasyMock.mock(InputStream.class);
        HashMap<String, InputStream> classes    = new HashMap<>();
        classes.put("ClassName1", inputStream1);
        classes.put("ClassName2", inputStream2);
        classes.put("ClassName3", inputStream3);

        // Rules
        Rule    rule1                = EasyMock.mock(Rule.class);
        Options optRule1             = EasyMock.mock(Options.class);
        Options defOptRule1          = EasyMock.mock(Options.class);
        Rule    rule2                = EasyMock.mock(Rule.class);
        Options optRule2             = EasyMock.mock(Options.class);
        Options defOptRule2          = EasyMock.mock(Options.class);
        Rule    rule3                = EasyMock.mock(Rule.class);
        Options optRule3             = EasyMock.mock(Options.class);
        Options defOptRule3          = EasyMock.mock(Options.class);
        Rule    rule4                = EasyMock.mock(Rule.class);
        Options optRule4             = EasyMock.mock(Options.class);
        Options defOptRule4          = EasyMock.mock(Options.class);
        ArrayList<Rule> rules        = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        rules.add(rule4);

        // Issues
        Issue issue1 = new Issue("I1", 23, "I1", "I1", "I1", Severity.INFO);
        Issue issue2 = new Issue("I2", -1, "I2", "I2", "I2", Severity.INFO);
        Issue issue3 = new Issue("I3", 18, "I3", "I3", "I3", Severity.INFO);
        Issue issue4 = new Issue("I4", 93, "I4", "I4", "I4", Severity.INFO);
        Issue issue5 = new Issue("I5", -1, "I5", "I5", "I5", Severity.INFO);
        Issue issue6 = new Issue("I6", 24, "I6", "I6", "I6", Severity.INFO);

        // Record
        EasyMock.expect(optionsReader.readOptions()).andReturn(options);
        EasyMock.expect(classReader.createClassNode(inputStream1)).andReturn(classNode1);
        EasyMock.expect(classReader.createClassNode(inputStream2)).andReturn(classNode2);
        EasyMock.expect(classReader.createClassNode(inputStream3)).andReturn(classNode3);

        EasyMock.expect(rule1.getDefaultOptions()).andReturn(defOptRule1);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule1);
        optRule1.applyDefault(defOptRule1);
        EasyMock.expect(rule1.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue1);
            add(issue2);
        }});

        EasyMock.expect(rule2.getDefaultOptions()).andReturn(defOptRule2);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule2);
        optRule2.applyDefault(defOptRule2);
        EasyMock.expect(rule2.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue3);
        }});

        EasyMock.expect(rule3.getDefaultOptions()).andReturn(defOptRule3);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule3);
        optRule3.applyDefault(defOptRule3);
        EasyMock.expect(rule3.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue4);
        }});

        EasyMock.expect(rule4.getDefaultOptions()).andReturn(defOptRule4);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(optRule4);
        optRule4.applyDefault(defOptRule4);
        EasyMock.expect(rule4.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue5);
            add(issue6);
        }});
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(optionsReader, classReader, options, classNode1, classNode2, classNode3, rule1, defOptRule1, optRule1, rule2, defOptRule2, optRule2, rule3, defOptRule3, optRule3, rule4, defOptRule4, optRule4, inputStream1, inputStream2, inputStream3);
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classes, classReader);
        ArrayList<Issue> issues = (ArrayList<Issue>) ruleHandler.applyRules();

        EasyMock.verify(optionsReader, classReader, options, classNode1, classNode2, classNode3, rule1, defOptRule1, optRule1, rule2, defOptRule2, optRule2, rule3, defOptRule3, optRule3, rule4, defOptRule4, optRule4, inputStream1, inputStream2, inputStream3);
        Assertions.assertEquals(new ArrayList<Issue>(){{
            add(issue1);
            add(issue2);
            add(issue3);
            add(issue4);
            add(issue5);
            add(issue6);
        }}, issues);
    }


    @Test
    public void applyRulesOptionsNull() throws IOException {
        OptionsReaderYAML optionsReader  = EasyMock.mock(OptionsReaderYAML.class);
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        Map<String, Options> options = EasyMock.mock(Map.class);

        // Classes
        ClassNode classNode1         = EasyMock.mock(ClassNode.class);
        InputStream inputStream1      = EasyMock.mock(InputStream.class);
        HashMap<String, InputStream> classes    = new HashMap<>();
        classes.put("ClassName1", inputStream1);

        // Rules
        Rule    rule1                = EasyMock.mock(Rule.class);
        Options optRule1             = EasyMock.mock(Options.class);
        Options defOptRule1          = EasyMock.mock(Options.class);
        ArrayList<Rule> rules        = new ArrayList<>();
        rules.add(rule1);

        // Issues
        Issue issue1 = new Issue("I1", 23, "I2", "I2", "I3", Severity.INFO);

        // Record
        EasyMock.expect(optionsReader.readOptions()).andReturn(options);
        EasyMock.expect(classReader.createClassNode(inputStream1)).andReturn(classNode1);
        EasyMock.expect(rule1.getDefaultOptions()).andReturn(defOptRule1);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(null);
        defOptRule1.applyDefault(defOptRule1);
        EasyMock.expect(rule1.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue1);
        }});
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classes, classReader);
        ArrayList<Issue> issues = (ArrayList<Issue>) ruleHandler.applyRules();

        EasyMock.verify(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        Assertions.assertEquals(new ArrayList<Issue>(){{
            add(issue1);
        }}, issues);
    }


    @Test
    public void applyRulesDefaultOptionsNull() throws IOException {
        OptionsReaderYAML optionsReader  = EasyMock.mock(OptionsReaderYAML.class);
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        Map<String, Options> options = EasyMock.mock(Map.class);

        // Classes
        ClassNode classNode1         = EasyMock.mock(ClassNode.class);
        InputStream inputStream1      = EasyMock.mock(InputStream.class);
        HashMap<String, InputStream> classes    = new HashMap<>();
        classes.put("ClassName1", inputStream1);

        // Rules
        Rule    rule1                = EasyMock.mock(Rule.class);
        Options optRule1             = EasyMock.mock(Options.class);
        Options defOptRule1          = EasyMock.mock(Options.class);
        ArrayList<Rule> rules        = new ArrayList<>();
        rules.add(rule1);

        // Issues
        Issue issue1 = new Issue("I1", 23, "I2", "I2", "I3", Severity.INFO);

        // Record
        EasyMock.expect(optionsReader.readOptions()).andReturn(options);
        EasyMock.expect(classReader.createClassNode(inputStream1)).andReturn(classNode1);
        EasyMock.expect(rule1.getDefaultOptions()).andReturn(null);
        EasyMock.expect(options.get(EasyMock.anyString())).andReturn(null);
        EasyMock.expect(rule1.apply(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(new ArrayList<Issue>(){{
            add(issue1);
        }});
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classes, classReader);
        ArrayList<Issue> issues = (ArrayList<Issue>) ruleHandler.applyRules();

        EasyMock.verify(optionsReader, classReader, options, classNode1, rule1, defOptRule1, optRule1, inputStream1);
        Assertions.assertEquals(new ArrayList<Issue>(){{
            add(issue1);
        }}, issues);
    }

}
