package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.Options;
import Data.OptionsReaderYAML;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class RuleHandler {
    private final List<Rule> rules;
    private final Map<String, ClassNode> classes;
    private final Map<String, Options> options;


    public RuleHandler(List<Rule> rules, OptionsReaderYAML optionsReader,
                       HashMap<String, InputStream> classData, ClassReader classReader) throws IOException {
        this.classes = new HashMap<>();
        this.rules   = rules;
        this.options = optionsReader.readOptions();
        for(String className: classData.keySet()) {
            this.classes.put(className, classReader.createClassNode(classData.get(className)));
        }
    }


    public List<Issue> applyRules() {
        List<Issue> issues = new ArrayList<>();
        for (Rule rule : this.rules) {
            issues.addAll(rule.apply(classes, this.getOptions(rule)));
        }
        return issues;
    }


    private Options getOptions(Rule rule) {
        String ruleName  = rule.getClass().getSimpleName();
        Options defaults = rule.getDefaultOptions();
        Options options  = this.options.get(ruleName);
        if (defaults == null)
            defaults = new Options(new ArrayList<>(), new ArrayList<>());
        if (options == null)
            options = defaults;
        options.applyDefault(defaults);
        return options;
    }


}
