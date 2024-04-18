package Domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassStreamHandler {
    public static HashMap<String, InputStream> getClassStreams(String[] filenames) throws IOException {
        HashMap<String, InputStream> classData = new HashMap<>();
        for (String filename : filenames) {
            if (filename.endsWith(".jar")) {
                handleJarClass(classData, filename);
            } else {
                InputStream input = Files.newInputStream(new File(filename).toPath());
                classData.put(filename, input);
            }
        }
        return classData;
    }

    private static void handleJarClass(HashMap<String, InputStream> classData, String filename) throws IOException {
        JarFile jar = new JarFile(filename);
        Enumeration<JarEntry> jars = jar.entries();
        while (jars.hasMoreElements()) {
            JarEntry j = jars.nextElement();
            if (j.getName().endsWith(".class") && !j.getName().contains("org")) {
                InputStream input = jar.getInputStream(j);
                classData.put(j.getName(),input);
            }
        }
    }

    public static void closeStreams(Collection<InputStream> streams) throws IOException {
        for (InputStream s : streams) {
            s.close();
        }
    }
}
