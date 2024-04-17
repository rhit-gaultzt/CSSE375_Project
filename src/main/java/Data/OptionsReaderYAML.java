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


    private final InputStream inputStream;
    private final Yaml yaml;


    public OptionsReaderYAML(String filename) {
        InputStream stream;
        this.yaml = new Yaml();
        try {
            stream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            stream = null;
            System.out.println("Using default config. " +
                    "Make own config.YAML to modify.");
        }

        this.inputStream = stream;
    }

    public OptionsReaderYAML(InputStream inputStream, Yaml yaml) {
        this.inputStream = inputStream;
        this.yaml = yaml;
    }


    public Map<String, Options> readOptions() {
        Map<String, Options> config = new HashMap<>();

        if (this.inputStream == null) {
            return config;
        }

        Map<String, Object> data = this.yaml.load(inputStream);

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


        return config;
    }


}
