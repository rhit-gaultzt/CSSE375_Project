package Data;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OptionsReaderYAML {


    private final File file;
    private final Yaml yaml;


    public OptionsReaderYAML(String filename) {
        this.file = new File(filename);
        this.yaml = new Yaml();
    }

    public OptionsReaderYAML(File file,  Yaml yaml) {
        this.file = file;
        this.yaml = yaml;
    }


    public Map<String, Options> readOptions() {
        Map<String, Options> config = new HashMap<>();
        try {
            InputStream targetStream = new FileInputStream(this.file);
            Map<String, Object> data = this.yaml.load(targetStream);


            for (String ruleKey : data.keySet()) {
                if (!(data.get(ruleKey) instanceof Map))
                    throw new Error("Error Parsing Config: \"" + ruleKey
                            + "\" is not a valid rule config, must be map");

                @SuppressWarnings("unchecked")
                Map<String, Object> rule = (Map<String, Object>) data.get(ruleKey);
                List<String> keys = new ArrayList<>();
                List<String> values = new ArrayList<>();

                for (String optionKey : rule.keySet()) {
                    if (rule.get(optionKey) instanceof Map)
                        throw new Error("Error Parsing Config: \"" + ruleKey + ":"
                                + optionKey + "\" is not a valid rule"
                                + " option, must be string.");
                    if (rule.get(optionKey) instanceof List)
                        throw new Error("Error Parsing Config: \"" + ruleKey + ":"
                                + optionKey + "\" is not a valid rule"
                                + " option, must be string.");

                    keys.add(optionKey);
                    values.add(String.valueOf(rule.get(optionKey)));
                }

                config.put(ruleKey, new Options(keys, values));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Creating default config. " +
                    "Make own config.YAML to modify.");
        }


        return config;
    }


}
