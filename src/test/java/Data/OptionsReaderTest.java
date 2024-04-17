package Data;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OptionsReaderTest {

//    @Test
//    public void testHasCorrectOptions() {
//        // Record
//        File file = EasyMock.mock(File.class);
//        Yaml yaml = EasyMock.mock(Yaml.class);
//        List<String> rules = new ArrayList<>();
//        rules.add("VariableNameConventionRule");
//        rules.add("HollywoodPrincipleRule");
//        rules.add("ClassNameRule");
//        rules.add("PrincipleLeastKnowledgeRule");
//        rules.add("SingletonRule");
//        rules.add("DecoratorPatternRule");
//
//        OptionsReaderYAML optionsReader = new OptionsReaderYAML(file, yaml);
//
//        // Replay
//        EasyMock.replay(file, yaml);
//        Map<String, Options> options = optionsReader.readOptions();
//
//
//        // Verify
//        Assertions.assertEquals(6, options.size());
//        for (String rule : rules) {
//            Assertions.assertTrue(options.containsKey(rule));
//        }
//        EasyMock.verify(file, yaml);
//    }

    @Test
    public void testHasCorrectOptionsIntegration() {
        String filename = "src/test/resources/options1.yaml";
        List<String> rules = new ArrayList<>();
        rules.add("VariableNameConventionRule");
        rules.add("HollywoodPrincipleRule");
        rules.add("ClassNameRule");
        rules.add("PrincipleLeastKnowledgeRule");
        rules.add("SingletonRule");
        rules.add("DecoratorPatternRule");

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(filename);
        Map<String, Options> options = optionsReader.readOptions();

        Assertions.assertEquals(6, options.size());
        for (String rule : rules) {
            Assertions.assertTrue(options.containsKey(rule));
        }
    }

    @Test
    public void testHasCorrectOptionAttributesIntegration() {
        String filename = "src/test/resources/options1.yaml";
        String rule = "VariableNameConventionRule";
        List<String> attributes = new ArrayList<>();
        attributes.add("severity");
        attributes.add("variable");
        attributes.add("static");

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(filename);
        Map<String, Options> options = optionsReader.readOptions();

        Options option = options.get(rule);
        Assertions.assertEquals(3, option.attributes.size());
        for (String attribute : attributes) {
            Assertions.assertTrue(option.hasKey(attribute));
        }
    }

    @Test
    public void testHasCorrectOptionAttributeValuesIntegration() {
        String filename = "src/test/resources/options1.yaml";
        String rule = "VariableNameConventionRule";
        List<String> attributes = new ArrayList<>();
        attributes.add("severity");
        attributes.add("variable");
        attributes.add("static");
        List<String> values = new ArrayList<>();
        values.add("ERROR");
        values.add("CAMEL");
        values.add("SCREAMING_SNAKE");

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(filename);
        Map<String, Options> options = optionsReader.readOptions();

        Options option = options.get(rule);
        Assertions.assertEquals(3, option.attributes.size());
        for (int i = 0; i < attributes.size(); i++) {
            String attribute = attributes.get(i);
            String expected_value = values.get(i);
            Assertions.assertEquals(expected_value, option.get(attribute));
        }
    }
}
