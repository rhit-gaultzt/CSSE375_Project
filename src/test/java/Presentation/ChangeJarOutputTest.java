package Presentation;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import Data.JavaByteCodeAdapter.ClassNode;
import Domain.ClassStreamHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class ChangeJarOutputTest {

    @Test
    public void testBasicChangeJarOutput() throws IOException {
        // Record
        ChangeJarOutput changeJarOutput = new ChangeJarOutput("testOutput.jar");
        HashMap<String, InputStream> streams =  ClassStreamHandler.getClassStreams(new String[]{"./testJar.jar"});
        ClassReaderASM classReaderASM = new ClassReaderASM();
        List<ClassNode> classNodes = new ArrayList<>();
        for (InputStream stream  : streams.values()) {
            classNodes.add(classReaderASM.createClassNode(stream));
        }

        // Replay
        changeJarOutput.saveClassesAsJar(classNodes, "./testJar.jar");

        // Verify
        JarFile output = new JarFile("testOutput.jar");
        Assertions.assertTrue(output != null);
    }

    @Test
    public void testComplexChangeJarOutput() throws IOException {
        // Record
        ChangeJarOutput changeJarOutput = new ChangeJarOutput("testOutput.jar");
        HashMap<String, InputStream> streams =  ClassStreamHandler.getClassStreams(new String[]{"./funTestJar.jar"});
        ClassReaderASM classReaderASM = new ClassReaderASM();
        List<ClassNode> classNodes = new ArrayList<>();
        for (InputStream stream  : streams.values()) {
            classNodes.add(classReaderASM.createClassNode(stream));
        }

        // Replay
        changeJarOutput.saveClassesAsJar(classNodes, "./funTestJar.jar");

        // Verify
        JarFile output = new JarFile("testOutput.jar");
        Assertions.assertTrue(output != null);
        Assertions.assertTrue(output.getEntry("Adder.class") != null);
        Assertions.assertTrue(output.getEntry("Magic.class") != null);
        Assertions.assertTrue(output.getEntry("Main.class") != null);
    }
}
