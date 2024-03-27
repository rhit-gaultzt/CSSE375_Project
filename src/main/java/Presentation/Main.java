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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

        OptionsReaderYAML optionsReader = new OptionsReaderYAML("./config.yaml");
        HashMap<String, InputStream> classData = getClassStreams(args);

        ClassReader classReader = new ClassReaderASM();
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classData, classReader);
        Output output = new CLIOutput();

        try {
            List<Issue> issues = ruleHandler.applyRules();
            output.outputIssues(issues);
        } catch (Error error) {
            String message = error.getMessage();
            output.outputError(message);
        }
    }

    public static HashMap<String, InputStream> getClassStreams(String[] filenames) throws IOException {
        HashMap<String, InputStream> classData = new HashMap<>();
        for (String c : filenames) {
            if (c.endsWith(".jar")) {
                try (JarFile jar = new JarFile(c)) {
                    Enumeration<JarEntry> jars = jar.entries();
                    while (jars.hasMoreElements()) {
                        JarEntry j = jars.nextElement();
                        if (j.getName().endsWith(".class")) {
                            InputStream input = jar.getInputStream(j);
                            classData.put(j.getName(),input);
                        }
                    }
                }
            } else {
                InputStream input = Files.newInputStream(new File(c).toPath());
                classData.put(c,input);
            }
        }
        return classData;
    }
}
