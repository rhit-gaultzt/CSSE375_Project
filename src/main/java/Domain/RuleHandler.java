package Domain;

import Data.DictionaryAPIAdapter;
import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.Options;
import Data.OptionsReaderYAML;
import Domain.ClassNameChecks.ClassNameCheck;
import Domain.ClassNameChecks.ClassNameInvalidCharacters;
import Domain.ClassNameChecks.ClassNameInvalidWords;
import Domain.ClassNameChecks.ClassNameStartsWithCapital;
import Domain.Rules.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class RuleHandler {
    private final List<Rule> rules;
    private final Map<String, ClassNode> classes;
    private final Map<String, Options> options;

    public RuleHandler(OptionsReaderYAML optionsReader, HashMap<String,
                       InputStream> classData, ClassReader classReader) throws IOException {
        this.classes = new HashMap<>();
        this.rules   = getDefaultRules();
        this.options = optionsReader.readOptions();
        for(String className: classData.keySet()) {
            this.classes.put(className, classReader.createClassNode(classData.get(className)));
        }
    }

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

    public static List<Rule> getDefaultRules() {
        return new ArrayList<Rule>() {{
            add(new DecoratorPatternRule());
            add(new VariableNameConventionRule());
            add(new HollywoodPrincipleRule(new GraphImplementationNidi()));
            add(new PrincipleLeastKnowledgeRule());
            add(new NoMisleadingCharacterClassRule());
            add(new DependencyInversionPrincipleRule());
            add(new SingletonRule());
            add(new ObserverPatternRule());
            add(new FlowChecker());
            add(new SwitchRule());
            add(new ClassNameRule(new ArrayList<ClassNameCheck>() {{
                add(new ClassNameStartsWithCapital());
                add(new ClassNameInvalidCharacters());
                add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
            }}));
        }};
    }


}
