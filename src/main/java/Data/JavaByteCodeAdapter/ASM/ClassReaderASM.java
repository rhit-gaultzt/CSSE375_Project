package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ClassReaderASM implements ClassReader {

    @Override
    public ClassNode createClassNode(String className) throws IOException {
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(className);
            return makeClassNodeFromReader(classReader, new org.objectweb.asm.tree.ClassNode());
        } catch (IOException e) {
            File initialFile = new File(className);
            InputStream inputStream = Files.newInputStream(initialFile.toPath());
            org.objectweb.asm.ClassReader classFileReader =
                    new org.objectweb.asm.ClassReader(inputStream);
            return makeClassNodeFromReader(classFileReader, new org.objectweb.asm.tree.ClassNode());
        }
    }

    @Override
    public ClassNode createClassNode(InputStream classData) throws IOException {
        org.objectweb.asm.ClassReader classReader =
                new org.objectweb.asm.ClassReader(classData);
        return makeClassNodeFromReader(classReader, new org.objectweb.asm.tree.ClassNode());
    }

    public ClassNode makeClassNodeFromReader(org.objectweb.asm.ClassReader reader, org.objectweb.asm.tree.ClassNode asmClassNode) {
        reader.accept(asmClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        return new ClassNodeASM(asmClassNode);
    }
}
