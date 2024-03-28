package Domain;

import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.JavaByteCodeAdapter.InsnList;
import Data.JavaByteCodeAdapter.MethodNode;
import Data.Options;
import Domain.Rules.HollywoodPrincipleRule;
import Domain.Rules.PrincipleLeastKnowledgeRule;
import Domain.Rules.SwitchRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SwitchRuleTest {

    @Test
    public void switchRuleNoClasses() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void switchRule1ClassNoMethods() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.BasicClass", classReader.createClassNode("TestClasses.BasicClass"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void switchRuleBasicMethod() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.C", classReader.createClassNode("TestClasses.C"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void switchRuleNormalIfs() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.NormalIfClass", classReader.createClassNode("TestClasses.NormalIfClass"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(0, issues.size());
    }

    @Test
    public void switchRuleBasicSwitch() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.SwitchStatementClass", classReader.createClassNode("TestClasses.SwitchStatementClass"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals("TestClasses.SwitchStatementClass", issues.get(0).classValue);
        Assertions.assertEquals("method doSwitch has a switch statement or is comparing the same variable to many values",
                                issues.get(0).message);
    }

    @Test
    public void switchRuleManyIfsOnSameVariable() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.SwitchByIfsClass", classReader.createClassNode("TestClasses.SwitchByIfsClass"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(1, issues.size());
        Assertions.assertEquals("TestClasses.SwitchByIfsClass", issues.get(0).classValue);
        Assertions.assertEquals("method doSwitchIf has a switch statement or is comparing the same variable to many values",
                issues.get(0).message);
    }

    @Test
    public void switchRuleManyIfsAndSwitches() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        classNodes.put("TestClasses.ManySwitchesClass", classReader.createClassNode("TestClasses.ManySwitchesClass"));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "INFO");

        // Execute
        Rule rule = new SwitchRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(4, issues.size());
        Assertions.assertEquals("TestClasses.ManySwitchesClass", issues.get(0).classValue);
        Assertions.assertEquals("method doSwitch has a switch statement or is comparing the same variable to many values",
                issues.get(0).message);

        Assertions.assertEquals("TestClasses.ManySwitchesClass", issues.get(1).classValue);
        Assertions.assertEquals("method doSwitchIf has a switch statement or is comparing the same variable to many values",
                issues.get(1).message);

        Assertions.assertEquals("TestClasses.ManySwitchesClass", issues.get(2).classValue);
        Assertions.assertEquals("method doSwitchBoth has a switch statement or is comparing the same variable to many values",
                issues.get(2).message);

        Assertions.assertEquals("TestClasses.ManySwitchesClass", issues.get(3).classValue);
        Assertions.assertEquals("method doSwitchBoth has a switch statement or is comparing the same variable to many values",
                issues.get(3).message);
    }



}
