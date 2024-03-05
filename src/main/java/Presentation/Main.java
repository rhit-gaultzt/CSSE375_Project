package Presentation;

import Data.DictionaryAPIAdapter;
import Data.DirectedGraphModel.Graph;
import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.OptionsReader;
import Data.OptionsReaderYAML;
import Domain.*;
import Domain.ClassNameChecks.ClassNameCheck;
import Domain.ClassNameChecks.ClassNameInvalidCharacters;
import Domain.ClassNameChecks.ClassNameInvalidWords;
import Domain.ClassNameChecks.ClassNameStartsWithCapital;
import Domain.Rules.DependencyInversionPrincipleRule;
import Domain.Rules.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Rule> rules = new ArrayList<>();
        rules.add(new DecoratorPatternRule());
        rules.add(new VariableNameConventionRule());
        rules.add(new ClassNameRule(new ArrayList<ClassNameCheck>() {{
            add(new ClassNameStartsWithCapital());
            add(new ClassNameInvalidCharacters());
            add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
        }}));
        rules.add(new HollywoodPrincipleRule(new GraphImplementationNidi()));
        rules.add(new PrincipleLeastKnowledgeRule());
        rules.add(new NoMisleadingCharacterClassRule());
        rules.add(new DependencyInversionPrincipleRule());
        rules.add(new SingletonRule());
        rules.add(new ObserverPatternRule());
        rules.add(new FlowChecker());

        OptionsReader optionsReader = new OptionsReaderYAML("./config.yaml");
        List<String> classNames = Arrays.asList(args);
        ClassReader classReader = new ClassReaderASM();
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classNames, classReader);
        Output output = new CLIOutput();

        try {
            List<Issue> issues = ruleHandler.applyRules();
            output.outputIssues(issues);
        } catch (Error error) {
            String message = error.getMessage();
            output.outputError(message);
        }
    }

}
