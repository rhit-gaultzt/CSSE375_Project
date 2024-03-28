package Domain;

import Data.JavaByteCodeAdapter.*;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.Options;
import Domain.Rules.VariableNameConventionRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VariableNameConventionTest {


    @Test
    public void applyValidFieldsDefault() {

        // Setup
        Options options    = EasyMock.mock(Options.class);
        ClassNode class1   = EasyMock.mock(ClassNode.class);
        ClassNode class2   = EasyMock.mock(ClassNode.class);
        ClassNode class3   = EasyMock.mock(ClassNode.class);
        FieldNode field1_1 = EasyMock.mock(FieldNode.class);
        FieldNode field1_2 = EasyMock.mock(FieldNode.class);
        FieldNode field1_3 = EasyMock.mock(FieldNode.class);
        FieldNode field2_1 = EasyMock.mock(FieldNode.class);
        FieldNode field2_2 = EasyMock.mock(FieldNode.class);
        FieldNode field2_3 = EasyMock.mock(FieldNode.class);
        FieldNode field3_1 = EasyMock.mock(FieldNode.class);
        FieldNode field3_2 = EasyMock.mock(FieldNode.class);
        FieldNode field3_3 = EasyMock.mock(FieldNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);
        classNodes.put("class3", class3);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.ERROR);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SCREAMING_SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.CAMEL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");
        EasyMock.expect(class3.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
            add(field1_3);
        }});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
            add(field2_3);
        }});
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
            add(field3_3);
        }});

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<>());

        EasyMock.expect(field1_1.getName()).andReturn("EXAMPLE_VAR1");
        EasyMock.expect(field1_1.isStatic()).andReturn(true);
        EasyMock.expect(field1_2.getName()).andReturn("exampleVar");
        EasyMock.expect(field1_2.isStatic()).andReturn(false);
        EasyMock.expect(field1_3.getName()).andReturn("EXAMPLE_VAR_1");
        EasyMock.expect(field1_3.isStatic()).andReturn(true);
        EasyMock.expect(field2_1.getName()).andReturn("EXAMPLE");
        EasyMock.expect(field2_1.isStatic()).andReturn(true);
        EasyMock.expect(field2_2.getName()).andReturn("example");
        EasyMock.expect(field2_2.isStatic()).andReturn(false);
        EasyMock.expect(field2_3.getName()).andReturn("EXAMPLE_3");
        EasyMock.expect(field2_3.isStatic()).andReturn(true);
        EasyMock.expect(field3_1.getName()).andReturn("EXAMPLE_1");
        EasyMock.expect(field3_1.isStatic()).andReturn(true);
        EasyMock.expect(field3_2.getName()).andReturn("example2");
        EasyMock.expect(field3_2.isStatic()).andReturn(false);
        EasyMock.expect(field3_3.getName()).andReturn("exampleVar3");
        EasyMock.expect(field3_3.isStatic()).andReturn(false);

        EasyMock.replay(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);
        Assertions.assertEquals(0, issues.size());
    }


    @Test
    public void applyInvalidFieldsDefault() {

        // Setup
        Options options    = EasyMock.mock(Options.class);
        ClassNode class1   = EasyMock.mock(ClassNode.class);
        ClassNode class2   = EasyMock.mock(ClassNode.class);
        ClassNode class3   = EasyMock.mock(ClassNode.class);
        FieldNode field1_1 = EasyMock.mock(FieldNode.class);
        FieldNode field1_2 = EasyMock.mock(FieldNode.class);
        FieldNode field1_3 = EasyMock.mock(FieldNode.class);
        FieldNode field2_1 = EasyMock.mock(FieldNode.class);
        FieldNode field2_2 = EasyMock.mock(FieldNode.class);
        FieldNode field2_3 = EasyMock.mock(FieldNode.class);
        FieldNode field3_1 = EasyMock.mock(FieldNode.class);
        FieldNode field3_2 = EasyMock.mock(FieldNode.class);
        FieldNode field3_3 = EasyMock.mock(FieldNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);
        classNodes.put("class3", class3);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.ERROR);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SCREAMING_SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.CAMEL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");
        EasyMock.expect(class3.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
            add(field1_3);
        }});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
            add(field2_3);
        }});
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
            add(field3_3);
        }});

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<>());

        EasyMock.expect(field1_1.getName()).andReturn("exampleVar");   // F
        EasyMock.expect(field1_1.isStatic()).andReturn(true);
        EasyMock.expect(field1_2.getName()).andReturn("EXAMPLE_VAR1"); // F
        EasyMock.expect(field1_2.isStatic()).andReturn(false);
        EasyMock.expect(field1_3.getName()).andReturn("EXAMPLE_VAR_1");
        EasyMock.expect(field1_3.isStatic()).andReturn(true);
        EasyMock.expect(field2_1.getName()).andReturn("EXAMPLE");
        EasyMock.expect(field2_1.isStatic()).andReturn(true);
        EasyMock.expect(field2_2.getName()).andReturn("example");
        EasyMock.expect(field2_2.isStatic()).andReturn(false);
        EasyMock.expect(field2_3.getName()).andReturn("EXAMPLE_3");
        EasyMock.expect(field2_3.isStatic()).andReturn(true);
        EasyMock.expect(field3_1.getName()).andReturn("example2");     // F
        EasyMock.expect(field3_1.isStatic()).andReturn(true);
        EasyMock.expect(field3_2.getName()).andReturn("EXAMPLE_1");    // F
        EasyMock.expect(field3_2.isStatic()).andReturn(false);
        EasyMock.expect(field3_3.getName()).andReturn("exampleVar3");
        EasyMock.expect(field3_3.isStatic()).andReturn(false);

        EasyMock.replay(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);
        Assertions.assertEquals(4, issues.size());
        Assertions.assertTrue(issues.get(0).getMessage().contains("example2"));
        Assertions.assertTrue(issues.get(1).getMessage().contains("EXAMPLE_1"));
        Assertions.assertTrue(issues.get(2).getMessage().contains("exampleVar"));
        Assertions.assertTrue(issues.get(3).getMessage().contains("EXAMPLE_VAR1"));
        Assertions.assertEquals(Severity.ERROR, issues.get(0).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(1).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(2).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(3).getSeverity());
    }


    @Test
    public void applyValidFieldsAlt() {

        // Setup
        Options options    = EasyMock.mock(Options.class);
        ClassNode class1   = EasyMock.mock(ClassNode.class);
        ClassNode class2   = EasyMock.mock(ClassNode.class);
        ClassNode class3   = EasyMock.mock(ClassNode.class);
        FieldNode field1_1 = EasyMock.mock(FieldNode.class);
        FieldNode field1_2 = EasyMock.mock(FieldNode.class);
        FieldNode field1_3 = EasyMock.mock(FieldNode.class);
        FieldNode field2_1 = EasyMock.mock(FieldNode.class);
        FieldNode field2_2 = EasyMock.mock(FieldNode.class);
        FieldNode field2_3 = EasyMock.mock(FieldNode.class);
        FieldNode field3_1 = EasyMock.mock(FieldNode.class);
        FieldNode field3_2 = EasyMock.mock(FieldNode.class);
        FieldNode field3_3 = EasyMock.mock(FieldNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);
        classNodes.put("class3", class3);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.WARNING);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(Domain.Rules.VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.PASCAL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");
        EasyMock.expect(class3.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
            add(field1_3);
        }});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
            add(field2_3);
        }});
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
            add(field3_3);
        }});

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<>());

        EasyMock.expect(field1_1.getName()).andReturn("example_var1");
        EasyMock.expect(field1_1.isStatic()).andReturn(true);
        EasyMock.expect(field1_2.getName()).andReturn("ExampleVar2");
        EasyMock.expect(field1_2.isStatic()).andReturn(false);
        EasyMock.expect(field1_3.getName()).andReturn("example_var_3");
        EasyMock.expect(field1_3.isStatic()).andReturn(true);
        EasyMock.expect(field2_1.getName()).andReturn("example");
        EasyMock.expect(field2_1.isStatic()).andReturn(true);
        EasyMock.expect(field2_2.getName()).andReturn("Example");
        EasyMock.expect(field2_2.isStatic()).andReturn(false);
        EasyMock.expect(field2_3.getName()).andReturn("Example2");
        EasyMock.expect(field2_3.isStatic()).andReturn(false);
        EasyMock.expect(field3_1.getName()).andReturn("example_var1");
        EasyMock.expect(field3_1.isStatic()).andReturn(true);
        EasyMock.expect(field3_2.getName()).andReturn("example2");
        EasyMock.expect(field3_2.isStatic()).andReturn(true);
        EasyMock.expect(field3_3.getName()).andReturn("example3");
        EasyMock.expect(field3_3.isStatic()).andReturn(true);

        EasyMock.replay(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);
        Assertions.assertEquals(0, issues.size());
    }


    @Test
    public void applyInvalidFieldsAlt() {

        // Setup
        Options options    = EasyMock.mock(Options.class);
        ClassNode class1   = EasyMock.mock(ClassNode.class);
        ClassNode class2   = EasyMock.mock(ClassNode.class);
        ClassNode class3   = EasyMock.mock(ClassNode.class);
        FieldNode field1_1 = EasyMock.mock(FieldNode.class);
        FieldNode field1_2 = EasyMock.mock(FieldNode.class);
        FieldNode field1_3 = EasyMock.mock(FieldNode.class);
        FieldNode field2_1 = EasyMock.mock(FieldNode.class);
        FieldNode field2_2 = EasyMock.mock(FieldNode.class);
        FieldNode field2_3 = EasyMock.mock(FieldNode.class);
        FieldNode field3_1 = EasyMock.mock(FieldNode.class);
        FieldNode field3_2 = EasyMock.mock(FieldNode.class);
        FieldNode field3_3 = EasyMock.mock(FieldNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);
        classNodes.put("class3", class3);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.WARNING);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.PASCAL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");
        EasyMock.expect(class3.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field1_1);
            add(field1_2);
            add(field1_3);
        }});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field2_1);
            add(field2_2);
            add(field2_3);
        }});
        EasyMock.expect(class3.getFields()).andReturn(new ArrayList<FieldNode>(){{
            add(field3_1);
            add(field3_2);
            add(field3_3);
        }});

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<>());
        EasyMock.expect(class3.getMethods()).andReturn(new ArrayList<>());

        EasyMock.expect(field1_1.getName()).andReturn("ExampleVar2"); // F
        EasyMock.expect(field1_1.isStatic()).andReturn(true);
        EasyMock.expect(field1_2.getName()).andReturn("example_var1");  // F
        EasyMock.expect(field1_2.isStatic()).andReturn(false);
        EasyMock.expect(field1_3.getName()).andReturn("example_var_3");
        EasyMock.expect(field1_3.isStatic()).andReturn(true);
        EasyMock.expect(field2_1.getName()).andReturn("Example2"); // F
        EasyMock.expect(field2_1.isStatic()).andReturn(true);
        EasyMock.expect(field2_2.getName()).andReturn("Example");
        EasyMock.expect(field2_2.isStatic()).andReturn(false);
        EasyMock.expect(field2_3.getName()).andReturn("example"); // F
        EasyMock.expect(field2_3.isStatic()).andReturn(false);
        EasyMock.expect(field3_1.getName()).andReturn("example_var1");
        EasyMock.expect(field3_1.isStatic()).andReturn(true);
        EasyMock.expect(field3_2.getName()).andReturn("example2");
        EasyMock.expect(field3_2.isStatic()).andReturn(true);
        EasyMock.expect(field3_3.getName()).andReturn("example3");
        EasyMock.expect(field3_3.isStatic()).andReturn(true);

        EasyMock.replay(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, class3, field1_1,
                field1_2, field1_3, field2_1, field2_2, field2_3, field3_1,
                field3_2, field3_3);
        Assertions.assertEquals(4, issues.size());
        Assertions.assertTrue(issues.get(0).getMessage().contains("Example2"));
        Assertions.assertTrue(issues.get(1).getMessage().contains("example"));
        Assertions.assertTrue(issues.get(2).getMessage().contains("ExampleVar2"));
        Assertions.assertTrue(issues.get(3).getMessage().contains("example_var1"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(1).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(2).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(3).getSeverity());
    }


    @Test
    public void applyValidVariablesDefault() {

        // Setup
        Options           options    = EasyMock.mock(Options.class);
        ClassNode         class1     = EasyMock.mock(ClassNode.class);
        ClassNode         class2     = EasyMock.mock(ClassNode.class);
        MethodNode        method1_1  = EasyMock.mock(MethodNode.class);
        MethodNode        method1_2  = EasyMock.mock(MethodNode.class);
        MethodNode        method2_1  = EasyMock.mock(MethodNode.class);
        LocalVariableNode field1_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_1_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_2 = EasyMock.mock(LocalVariableNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.ERROR);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SCREAMING_SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.CAMEL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){});

        EasyMock.expect(method1_1.getName()).andReturn("");
        EasyMock.expect(method1_2.getName()).andReturn("");
        EasyMock.expect(method2_1.getName()).andReturn("");

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
        }});

        EasyMock.expect(method1_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_1_1);
            add(field1_1_2);
        }});
        EasyMock.expect(method1_2.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_2_1);
            add(field1_2_2);
        }});
        EasyMock.expect(method2_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field2_1_1);
            add(field2_1_2);
        }});

        EasyMock.expect(field1_1_1.getName()).andReturn("exampleVar1");
        EasyMock.expect(field1_1_2.getName()).andReturn("example2");
        EasyMock.expect(field1_2_1.getName()).andReturn("niceExampleVarName");
        EasyMock.expect(field1_2_2.getName()).andReturn("example");
        EasyMock.expect(field2_1_1.getName()).andReturn("i");
        EasyMock.expect(field2_1_2.getName()).andReturn("nice");

        EasyMock.replay(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);
        Assertions.assertEquals(0, issues.size());
    }


    @Test
    public void applyInvalidVariablesDefault() {

        // Setup
        Options           options    = EasyMock.mock(Options.class);
        ClassNode         class1     = EasyMock.mock(ClassNode.class);
        ClassNode         class2     = EasyMock.mock(ClassNode.class);
        MethodNode        method1_1  = EasyMock.mock(MethodNode.class);
        MethodNode        method1_2  = EasyMock.mock(MethodNode.class);
        MethodNode        method2_1  = EasyMock.mock(MethodNode.class);
        LocalVariableNode field1_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_1_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_2 = EasyMock.mock(LocalVariableNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.ERROR);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SCREAMING_SNAKE);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.CAMEL);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){});

        EasyMock.expect(method1_1.getName()).andReturn("");
        EasyMock.expect(method1_2.getName()).andReturn("");
        EasyMock.expect(method2_1.getName()).andReturn("");

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
        }});

        EasyMock.expect(method1_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_1_1);
            add(field1_1_2);
        }});
        EasyMock.expect(method1_2.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_2_1);
            add(field1_2_2);
        }});
        EasyMock.expect(method2_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field2_1_1);
            add(field2_1_2);
        }});

        EasyMock.expect(field1_1_1.getName()).andReturn("exampleVar1");
        EasyMock.expect(field1_1_2.getName()).andReturn("EXAMPLE_2");
        EasyMock.expect(field1_2_1.getName()).andReturn("NiceExampleVarName");
        EasyMock.expect(field1_2_2.getName()).andReturn("Example");
        EasyMock.expect(field2_1_1.getName()).andReturn("nice_variable");
        EasyMock.expect(field2_1_2.getName()).andReturn("NICE");

        EasyMock.replay(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        Assertions.assertEquals(5, issues.size());
        Assertions.assertTrue(issues.get(0).getMessage().contains("nice_variable"));
        Assertions.assertTrue(issues.get(1).getMessage().contains("NICE"));
        Assertions.assertTrue(issues.get(2).getMessage().contains("EXAMPLE_2"));
        Assertions.assertTrue(issues.get(3).getMessage().contains("NiceExampleVarName"));
        Assertions.assertTrue(issues.get(4).getMessage().contains("Example"));
        Assertions.assertEquals(Severity.ERROR, issues.get(0).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(1).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(2).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(3).getSeverity());
        Assertions.assertEquals(Severity.ERROR, issues.get(4).getSeverity());
    }


    @Test
    public void applyValidVariablesAlt() {

        // Setup
        Options           options    = EasyMock.mock(Options.class);
        ClassNode         class1     = EasyMock.mock(ClassNode.class);
        ClassNode         class2     = EasyMock.mock(ClassNode.class);
        MethodNode        method1_1  = EasyMock.mock(MethodNode.class);
        MethodNode        method1_2  = EasyMock.mock(MethodNode.class);
        MethodNode        method2_1  = EasyMock.mock(MethodNode.class);
        LocalVariableNode field1_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_1_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_2 = EasyMock.mock(LocalVariableNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.WARNING);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.PASCAL);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SNAKE);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){});

        EasyMock.expect(method1_1.getName()).andReturn("");
        EasyMock.expect(method1_2.getName()).andReturn("");
        EasyMock.expect(method2_1.getName()).andReturn("");

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
        }});

        EasyMock.expect(method1_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_1_1);
            add(field1_1_2);
        }});
        EasyMock.expect(method1_2.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_2_1);
            add(field1_2_2);
        }});
        EasyMock.expect(method2_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field2_1_1);
            add(field2_1_2);
        }});

        EasyMock.expect(field1_1_1.getName()).andReturn("example_var_1");
        EasyMock.expect(field1_1_2.getName()).andReturn("example2");
        EasyMock.expect(field1_2_1.getName()).andReturn("nice_example_var_name");
        EasyMock.expect(field1_2_2.getName()).andReturn("example");
        EasyMock.expect(field2_1_1.getName()).andReturn("i");
        EasyMock.expect(field2_1_2.getName()).andReturn("nice");

        EasyMock.replay(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);
        Assertions.assertEquals(0, issues.size());
    }


    @Test
    public void applyInvalidVariablesAlt() {

        // Setup
        Options           options    = EasyMock.mock(Options.class);
        ClassNode         class1     = EasyMock.mock(ClassNode.class);
        ClassNode         class2     = EasyMock.mock(ClassNode.class);
        MethodNode        method1_1  = EasyMock.mock(MethodNode.class);
        MethodNode        method1_2  = EasyMock.mock(MethodNode.class);
        MethodNode        method2_1  = EasyMock.mock(MethodNode.class);
        LocalVariableNode field1_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_1_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field1_2_2 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_1 = EasyMock.mock(LocalVariableNode.class);
        LocalVariableNode field2_1_2 = EasyMock.mock(LocalVariableNode.class);

        Map<String, ClassNode> classNodes = new HashMap<>();
        classNodes.put("class1", class1);
        classNodes.put("class2", class2);

        // Record
        EasyMock.expect(options.get(EasyMock.eq("severity"), EasyMock.eq(Severity.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(Severity.WARNING);
        EasyMock.expect(options.get(EasyMock.eq("static"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.PASCAL);
        EasyMock.expect(options.get(EasyMock.eq("variable"), EasyMock.eq(VariableNameConventionRule.Format.class), EasyMock.eq("VariableNameConventionRule"))).andReturn(VariableNameConventionRule.Format.SNAKE);

        EasyMock.expect(class1.getSourceFile()).andReturn("");
        EasyMock.expect(class2.getSourceFile()).andReturn("");

        EasyMock.expect(class1.getFields()).andReturn(new ArrayList<FieldNode>(){});
        EasyMock.expect(class2.getFields()).andReturn(new ArrayList<FieldNode>(){});

        EasyMock.expect(method1_1.getName()).andReturn("");
        EasyMock.expect(method1_2.getName()).andReturn("");
        EasyMock.expect(method2_1.getName()).andReturn("");

        EasyMock.expect(class1.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method1_1);
            add(method1_2);
        }});
        EasyMock.expect(class2.getMethods()).andReturn(new ArrayList<MethodNode>(){{
            add(method2_1);
        }});

        EasyMock.expect(method1_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_1_1);
            add(field1_1_2);
        }});
        EasyMock.expect(method1_2.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field1_2_1);
            add(field1_2_2);
        }});
        EasyMock.expect(method2_1.getLocalVariables()).andReturn(new ArrayList<LocalVariableNode>(){{
            add(field2_1_1);
            add(field2_1_2);
        }});

        EasyMock.expect(field1_1_1.getName()).andReturn("example_var1");
        EasyMock.expect(field1_1_2.getName()).andReturn("EXAMPLE_2");
        EasyMock.expect(field1_2_1.getName()).andReturn("NiceExampleVarName");
        EasyMock.expect(field1_2_2.getName()).andReturn("Example");
        EasyMock.expect(field2_1_1.getName()).andReturn("nice_variable");
        EasyMock.expect(field2_1_2.getName()).andReturn("NICE");

        EasyMock.replay(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        EasyMock.verify(options, class1, class2, method1_1, method1_2,
                method2_1, field1_1_1, field1_1_2, field1_2_1, field1_2_2,
                field2_1_1, field2_1_2);

        Assertions.assertEquals(4, issues.size());
        Assertions.assertTrue(issues.get(0).getMessage().contains("NICE"));
        Assertions.assertTrue(issues.get(1).getMessage().contains("EXAMPLE_2"));
        Assertions.assertTrue(issues.get(2).getMessage().contains("NiceExampleVarName"));
        Assertions.assertTrue(issues.get(3).getMessage().contains("Example"));
        Assertions.assertEquals(Severity.WARNING, issues.get(0).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(1).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(2).getSeverity());
        Assertions.assertEquals(Severity.WARNING, issues.get(3).getSeverity());
    }


    @Test
    public void applyIntegration() throws IOException {
        // Setup
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        ClassReader classReader = new ClassReaderASM();
        String      className   = "TestClasses.VariableNameConventionDefault";
        classNodes.put(className, classReader.createClassNode(className));

        Options options = new Options(new ArrayList<>(), new ArrayList<>());
        options.put("severity", "ERROR");
        options.put("variable", "CAMEL");
        options.put("static", "SCREAMING_SNAKE");

        // Execute
        Rule rule = new VariableNameConventionRule();
        List<Issue> issues = rule.apply(classNodes, options);

        // Verify
        Assertions.assertEquals(14, issues.size());

        Assertions.assertTrue(issues.get( 0).getMessage().contains("InvalidVariable1"));
        Assertions.assertTrue(issues.get( 1).getMessage().contains("invalid_variable2"));
        Assertions.assertTrue(issues.get( 2).getMessage().contains("invalidVariable_3"));
        Assertions.assertTrue(issues.get( 3).getMessage().contains("InvalidVariable4"));
        Assertions.assertTrue(issues.get( 4).getMessage().contains("invalid_variable5"));
        Assertions.assertTrue(issues.get( 5).getMessage().contains("invalidVariable_6"));
        Assertions.assertTrue(issues.get( 6).getMessage().contains("BadExample1"));
        Assertions.assertTrue(issues.get( 7).getMessage().contains("BAD_EXAMPLE_2"));
        Assertions.assertTrue(issues.get( 8).getMessage().contains("invalid_local_variable"));
        Assertions.assertTrue(issues.get( 9).getMessage().contains("InvalidLocalVariable"));
        Assertions.assertTrue(issues.get(10).getMessage().contains("invalidLocal_Variable"));
        Assertions.assertTrue(issues.get(11).getMessage().contains("invalid_local_variable"));
        Assertions.assertTrue(issues.get(12).getMessage().contains("InvalidLocalVariable"));
        Assertions.assertTrue(issues.get(13).getMessage().contains("invalidLocal_Variable"));
    }


    @Test
    public void pascalCaseValid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "PascalCasing",
                "HelloWorld",
                "NiceRide2",
                "ThatIs2Cool4You",
                "HeyThisLinterIsCool",
                "Food",
                "Food23",
                "FoodFood23"
            };

        for (String text : texts) {
            Assertions.assertTrue(rule.isPascalCase(text));
        }
    }


    @Test
    public void pascalCaseInvalid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "camelCasing",
                "helloWorld",
                "niceRide2",
                "thatIs2Cool4You",
                "heyThisLinterIsCool",
                "snake_case",
                "hello_world",
                "nice_ride2",
                "nice_ride_2",
                "that_is_2cool4you",
                "that_is_2_cool4_you",
                "hey_this_linter_is_cool",
                "SCREAMING_SNAKE_CASE",
                "HELLO_WORLD",
                "NICE_RIDE2",
                "NICE_RIDE_2",
                "THAT_IS_2COOL4YOU",
                "THAT_IS_2_COOL4_YOU",
                "HEY_THIS_LINTER_IS_COOL"
        };

        for (String text : texts) {
            Assertions.assertFalse(rule.isPascalCase(text));
        }
    }


    @Test
    public void camelCaseValid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "camelCasing",
                "helloWorld",
                "niceRide2",
                "thatIs2Cool4You",
                "heyThisLinterIsCool",
                "food",
                "food23",
                "foodFood23"
        };

        for (String text : texts) {
            Assertions.assertTrue(rule.isCamelCase(text));
        }
    }


    @Test
    public void camelCaseInvalid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "PascalCasing",
                "HelloWorld",
                "NiceRide2",
                "ThatIs2Cool4You",
                "HeyThisLinterIsCool",
                "snake_case",
                "hello_world",
                "nice_ride2",
                "nice_ride_2",
                "that_is_2cool4you",
                "that_is_2_cool4_you",
                "hey_this_linter_is_cool",
                "SCREAMING_SNAKE_CASE",
                "HELLO_WORLD",
                "NICE_RIDE2",
                "NICE_RIDE_2",
                "THAT_IS_2COOL4YOU",
                "THAT_IS_2_COOL4_YOU",
                "HEY_THIS_LINTER_IS_COOL"
        };

        for (String text : texts) {
            Assertions.assertFalse(rule.isCamelCase(text));
        }
    }


    @Test
    public void snakeCaseValid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "snake_case",
                "hello_world",
                "nice_ride2",
                "nice_ride_2",
                "that_is_2cool4you",
                "that_is_2_cool4_you",
                "hey_this_linter_is_cool"
        };

        for (String text : texts) {
            Assertions.assertTrue(rule.isSnakeCase(text));
        }
    }


    @Test
    public void snakeCaseInvalid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "PascalCasing",
                "HelloWorld",
                "NiceRide2",
                "ThatIs2Cool4You",
                "HeyThisLinterIsCool",
                "SCREAMING_SNAKE_CASE",
                "camelCasing",
                "helloWorld",
                "niceRide2",
                "thatIs2Cool4You",
                "heyThisLinterIsCool",
                "hEllo_world",
                "HELLO_WORLD",
                "NICE_RIDE2",
                "NICE_RIDE_2",
                "THAT_IS_2COOL4YOU",
                "THAT_IS_2_COOL4_YOU",
                "HEY_THIS_LINTER_IS_COOL"
        };

        for (String text : texts) {
            Assertions.assertFalse(rule.isSnakeCase(text));
        }
    }


    @Test
    public void screamingSnakeCaseValid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "SCREAMING_SNAKE_CASE",
                "HELLO_WORLD",
                "NICE_RIDE2",
                "NICE_RIDE_2",
                "THAT_IS_2COOL4YOU",
                "THAT_IS_2_COOL4_YOU",
                "HEY_THIS_LINTER_IS_COOL"
        };

        for (String text : texts) {
            Assertions.assertTrue(rule.isScreamingSnakeCase(text));
        }
    }


    @Test
    public void screamingSnakeCaseInvalid() {
        VariableNameConventionRule rule = new VariableNameConventionRule();
        String[] texts = new String[]{
                "PascalCasing",
                "HelloWorld",
                "NiceRide2",
                "ThatIs2Cool4You",
                "HeyThisLinterIsCool",
                "camelCasing",
                "helloWorld",
                "niceRide2",
                "thatIs2Cool4You",
                "heyThisLinterIsCool",
                "snake_case",
                "hello_world",
                "nice_ride2",
                "nice_ride_2",
                "that_is_2cool4you",
                "that_is_2_cool4_you",
                "HEY_this_linter_is_cool",
                "hey_this_linter_is_cool",
        };

        for (String text : texts) {
            Assertions.assertFalse(rule.isScreamingSnakeCase(text));
        }
    }


}
