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
    private String[] classNames = {};
    private HashMap<String, InputStream> classData = new HashMap<>();

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

        this.handleGetClassData(args, cliClasses, streamHandler);
        RuleHandler ruleHandler = new RuleHandler(optionsReader, this.classData, classReader);
        ClassStreamHandler.closeStreams(classData.values());
        List<Issue> issues = ruleHandler.applyRules();
        handleChangeRules(ruleHandler, jarOutput, classNames);
        return issues;
    }

    public void handleGetClassData(String[] args, CLIGetClasses cliClasses, ClassStreamHandler streamHandler) {
        boolean hasValidClasses = false;
        this.classNames = cliClasses.getClasses(args, true);

        while (!hasValidClasses) {
            try {
                this.classData = streamHandler.getClassStreams(classNames);
                hasValidClasses = true;
            } catch (IOException e) {
                cliClasses.displayInvalidClass(e);
                this.classNames = cliClasses.getClasses(new String[] {}, false);
            }
        }
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
