package Presentation;

import Data.DictionaryAPIAdapter;
import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.OptionsReaderYAML;
import Domain.*;
import Domain.ClassNameChecks.ClassNameCheck;
import Domain.ClassNameChecks.ClassNameInvalidCharacters;
import Domain.ClassNameChecks.ClassNameInvalidWords;
import Domain.ClassNameChecks.ClassNameStartsWithCapital;
import Domain.Rules.DependencyInversionPrincipleRule;
import Domain.Rules.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        OptionsReaderYAML optionsReader = new OptionsReaderYAML("./config.yaml");
        Output output = new CLIOutput();
        Main main = new Main();

        try {
            List<Issue> issues = main.findIssues(args, optionsReader);
            output.outputIssues(issues);
        } catch (Exception error) {
            output.outputError(error.getMessage());
        }
    }

    public List<Issue> findIssues(String[] args, OptionsReaderYAML optionsReader) throws IOException {
        List<Rule> rules = setupRules();
        String[] classNames = (new CLIGetClasses()).getClasses(args);
        HashMap<String, InputStream> classData = ClassStreamHandler.getClassStreams(classNames);
        ClassReader classReader = new ClassReaderASM();
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classData, classReader);
        ClassStreamHandler.closeStreams(classData.values());
        return ruleHandler.applyRules();
    }

    public List<Rule> setupRules() {
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
