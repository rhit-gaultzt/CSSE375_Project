package Data;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OptionsReaderTest {

    @Test
    public void testHasCorrectOptions() {
        // Record
        InputStream inputStream = EasyMock.mock(InputStream.class);
        Yaml yaml = EasyMock.mock(Yaml.class);

        Map<String, Object> expectedOptions = new HashMap<>();
        expectedOptions.put("VariableNameConventionRule", new HashMap<String, String>());
        expectedOptions.put("HollywoodPrincipleRule", new HashMap<String, String>());
        expectedOptions.put("ClassNameRule", new HashMap<String, String>());
        expectedOptions.put("PrincipleLeastKnowledgeRule", new HashMap<String, String>());
        expectedOptions.put("SingletonRule", new HashMap<String, String>());
        expectedOptions.put("DecoratorPatternRule", new HashMap<String, String>());

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(inputStream, yaml);

        EasyMock.expect(yaml.load(inputStream)).andReturn(expectedOptions);

        // Replay
        EasyMock.replay(inputStream, yaml);
        Map<String, Options> options = optionsReader.readOptions();

        // Verify
        Assertions.assertEquals(6, options.size());
        for (String rule : expectedOptions.keySet()) {
            Assertions.assertTrue(options.containsKey(rule));
        }
        EasyMock.verify(inputStream, yaml);
    }

    @Test
    public void testHasCorrectOptionAttributes() {
        // Record
        InputStream inputStream = EasyMock.mock(InputStream.class);
        Yaml yaml = EasyMock.mock(Yaml.class);

        Map<String, String> attributeValueMap = new HashMap<>();
        attributeValueMap.put("severity", "ERROR");
        attributeValueMap.put("variable", "CAMEL");
        attributeValueMap.put("static", "SCREAMING_SNAKE");

        Map<String, Object> expectedOptions = new HashMap<>();
        expectedOptions.put("VariableNameConventionRule", attributeValueMap);
        expectedOptions.put("HollywoodPrincipleRule", new HashMap<String, String>());

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(inputStream, yaml);

        EasyMock.expect(yaml.load(inputStream)).andReturn(expectedOptions);

        // Replay
        EasyMock.replay(inputStream, yaml);
        Map<String, Options> options = optionsReader.readOptions();

        // Verify
        Options option = options.get("VariableNameConventionRule");
        Assertions.assertEquals(3, option.attributes.size());
        for (String attribute : attributeValueMap.keySet()) {
            Assertions.assertTrue(option.hasKey(attribute));
        }
        EasyMock.verify(inputStream, yaml);
    }

    @Test
    public void testHasCorrectOptionAttributeValues() {
        // Record
        InputStream inputStream = EasyMock.mock(InputStream.class);
        Yaml yaml = EasyMock.mock(Yaml.class);

        Map<String, String> attributeValueMap = new HashMap<>();
        attributeValueMap.put("severity", "ERROR");
        attributeValueMap.put("variable", "CAMEL");
        attributeValueMap.put("static", "SCREAMING_SNAKE");

        Map<String, Object> expectedOptions = new HashMap<>();
        expectedOptions.put("VariableNameConventionRule", attributeValueMap);
        expectedOptions.put("HollywoodPrincipleRule", new HashMap<String, String>());

        OptionsReaderYAML optionsReader = new OptionsReaderYAML(inputStream, yaml);

        EasyMock.expect(yaml.load(inputStream)).andReturn(expectedOptions);

        // Replay
        EasyMock.replay(inputStream, yaml);
        Map<String, Options> options = optionsReader.readOptions();

        // Verify
        Options option = options.get("VariableNameConventionRule");
        Assertions.assertEquals(3, option.attributes.size());
        for (String attribute : attributeValueMap.keySet()) {
            String expectedValue = attributeValueMap.get(attribute);
            String actualValue = option.get(attribute);
            Assertions.assertEquals(expectedValue, actualValue);
        }
        EasyMock.verify(inputStream, yaml);
    }

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
