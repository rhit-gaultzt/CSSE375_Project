package Presentation;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;
import Data.OptionsReaderYAML;
import Domain.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
    private boolean createsOutputJar = false;

    public static void main(String[] args) {
        OptionsReaderYAML optionsReader = new OptionsReaderYAML("./config.yaml");
        Output output = new CLIOutput();
        String jarOutputPath = "./output.jar";
        ChangeJarOutput jarOutput = new ChangeJarOutput(jarOutputPath);
        Main main = new Main();

        try {
            List<Issue> issues = main.findIssues(args, optionsReader, jarOutput,
                    new CLIGetClasses(), new ClassReaderASM(), new ClassStreamHandler());
            output.outputIssues(issues);
            if (main.createsOutputJar) {
                output.outputJarLocation(jarOutputPath);
            }
        } catch (Exception error) {
            output.outputError(error.getMessage());
        }
    }

    public List<Issue> findIssues(String[] args, OptionsReaderYAML optionsReader, ChangeJarOutput jarOutput,
                                  CLIGetClasses cliClasses, ClassReader classReader, ClassStreamHandler streamHandler) throws IOException {
        String[] classNames = cliClasses.getClasses(args);
        HashMap<String, InputStream> classData = streamHandler.getClassStreams(classNames);
        RuleHandler ruleHandler = new RuleHandler(optionsReader, classData, classReader);
        ClassStreamHandler.closeStreams(classData.values());
        List<Issue> issues = ruleHandler.applyRules();
        handleChangeRules(ruleHandler, jarOutput, classNames);
        return issues;
    }

    public void handleChangeRules(RuleHandler ruleHandler, ChangeJarOutput changeJarOutput,
                                  String[] classNames) throws IOException {
        if (classNames.length == 1 && classNames[0].endsWith(".jar")) {
            this.createsOutputJar = true;
            List<ClassNode> classNodes = ruleHandler.applyChangeRules();
            changeJarOutput.saveClassesAsJar(classNodes, classNames[0]);
        }

    }
}
