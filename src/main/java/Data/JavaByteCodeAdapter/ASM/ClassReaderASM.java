package Data.JavaByteCodeAdapter.ASM;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReaderASM implements ClassReader {

    @Override
    public ClassNode createClassNode(String className) throws IOException {
        org.objectweb.asm.tree.ClassNode asmClassNode = null;
        try {
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(className);
            asmClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(asmClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
            File initialFile = new File(className);
            InputStream inputStream = new FileInputStream(initialFile);
            org.objectweb.asm.ClassReader classReader =
                    new org.objectweb.asm.ClassReader(inputStream);
            asmClassNode = new org.objectweb.asm.tree.ClassNode();
            classReader.accept(asmClassNode, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        }

        return new ClassNodeASM(asmClassNode);
    }
}
