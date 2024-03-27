package Domain;

import Data.JavaByteCodeAdapter.ASM.ClassReaderASM;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.ClassReader;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ClassReaderASMTest {
    @Test
    public void testGetClassNode() {
        ClassReader classReader      = EasyMock.mock(ClassReader.class);
        ClassNode asmClassNode1         = EasyMock.mock(ClassNode.class);

        // Record
        classReader.accept(asmClassNode1, org.objectweb.asm.ClassReader.EXPAND_FRAMES);
        EasyMock.expectLastCall();


        // Replay
        EasyMock.replay(classReader);
        ClassReaderASM testReader = new ClassReaderASM();
        Data.JavaByteCodeAdapter.ClassNode node = testReader.makeClassNodeFromReader(classReader, asmClassNode1);

        EasyMock.verify(classReader);
        Assertions.assertNotEquals(null, node.getClass());
    }

    @Test
    public void testCreateClassNodeFromString() throws IOException {
        ClassReaderASM testReader = new ClassReaderASM();
        Data.JavaByteCodeAdapter.ClassNode node = testReader.createClassNode("target/classes/TestClasses/BasicClass.class");
        Assertions.assertEquals("TestClasses.BasicClass", node.getClassName());
    }

    @Test
    public void testCreateClassNodeFromStream() throws IOException {
        ClassReaderASM testReader = new ClassReaderASM();
        InputStream fis = Files.newInputStream(new File("target/classes/TestClasses/C.class").toPath());
        Data.JavaByteCodeAdapter.ClassNode node = testReader.createClassNode(fis);
        Assertions.assertEquals("TestClasses.C", node.getClassName());
        Assertions.assertEquals("doThing", node.getMethods().get(1).getName());
    }
}
