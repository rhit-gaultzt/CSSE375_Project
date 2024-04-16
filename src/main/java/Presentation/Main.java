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
        String[] classNames = (new CLIGetClasses()).getClasses(args);
        HashMap<String, InputStream> classData = ClassStreamHandler.getClassStreams(classNames);
        ClassReader classReader = new ClassReaderASM();
        RuleHandler ruleHandler = new RuleHandler(optionsReader, classData, classReader);
        ClassStreamHandler.closeStreams(classData.values());
        return ruleHandler.applyRules();
    }
}
