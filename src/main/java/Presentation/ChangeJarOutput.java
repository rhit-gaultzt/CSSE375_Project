package Presentation;

import Data.JavaByteCodeAdapter.ClassNode;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.*;

public class ChangeJarOutput {

    private String jarPath;

    public ChangeJarOutput(String jarPath) {
        this.jarPath = jarPath;
    }

    public void saveClassesAsJar(List<ClassNode> classNodes, String originalJarPath) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(this.jarPath));
        for (ClassNode classNode : classNodes) {
            org.objectweb.asm.tree.ClassNode asmClassNode = classNode.getClassNode();
            String className = asmClassNode.name.replace('.', '/') + ".class";
            byte[] classBytes = transformClassNodeToBytes(asmClassNode);
            JarEntry entry = new JarEntry(className);
            jarOutputStream.putNextEntry(entry);
            jarOutputStream.write(classBytes);
            jarOutputStream.closeEntry();
        }
        JarFile originalJar = new JarFile(originalJarPath);
        copyEntriesStartingWith(originalJar, jarOutputStream, "META-INF/");
        copyEntriesStartingWith(originalJar, jarOutputStream, "org/");
        jarOutputStream.close();
    }

    private byte[] transformClassNodeToBytes(org.objectweb.asm.tree.ClassNode classNode) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

    private void copyEntriesStartingWith(JarFile originalJar, JarOutputStream newJarOutputStream, String prefix) throws IOException {
        byte[] buffer = new byte[4096];
        Enumeration<JarEntry> entries = originalJar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().startsWith(prefix)) {
                JarEntry newEntry = new JarEntry(entry.getName());
                newJarOutputStream.putNextEntry(newEntry);

                try (InputStream inputStream = originalJar.getInputStream(entry)) {
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        newJarOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                newJarOutputStream.closeEntry();
            }
        }
    }
}
