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
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
        HashMap<String, InputStream> classData = getClassStreams(args);
        ClassReader classReader = new ClassReaderASM();
        RuleHandler ruleHandler = new RuleHandler(rules, optionsReader, classData, classReader);
        closeStreams(classData.values());
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
            add(new ClassNameRule(new ArrayList<ClassNameCheck>() {{
                add(new ClassNameStartsWithCapital());
                add(new ClassNameInvalidCharacters());
                add(new ClassNameInvalidWords(new DictionaryAPIAdapter()));
            }}));
        }};
    }

    public HashMap<String, InputStream> getClassStreams(String[] filenames) throws IOException {
        HashMap<String, InputStream> classData = new HashMap<>();
        for (String c : filenames) {
            if (c.endsWith(".jar")) {
                JarFile jar = new JarFile(c);
                Enumeration<JarEntry> jars = jar.entries();
                while (jars.hasMoreElements()) {
                    JarEntry j = jars.nextElement();
                    if (j.getName().endsWith(".class")) {
                        InputStream input = jar.getInputStream(j);
                        classData.put(j.getName(),input);
                    }
                }
            } else {
                InputStream input = Files.newInputStream(new File(c).toPath());
                classData.put(c,input);
            }
        }
        return classData;
    }

    public void closeStreams(Collection<InputStream> streams) throws IOException {
        for (InputStream s : streams) {
            s.close();
        }
    }
}
