package Domain;

import org.junit.Test;
import Presentation.Main;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.HashMap;
import java.io.InputStream;

public class ReadInputsTest {
    @Test
    public void testGetClassStreamsOfJar() throws IOException {
        String[] testFiles = new String[] {"testJar.jar"};
        HashMap<String, InputStream> streams = Main.getClassStreams(testFiles);
        Assertions.assertEquals(3, streams.size());
        Assertions.assertTrue(streams.containsKey("C.class"));
        Assertions.assertTrue(streams.containsKey("BasicClass.class"));
        Assertions.assertTrue(streams.containsKey("BasicSingleton.class"));
    }

    @Test
    public void testGetClassStreamsOfFiles() throws IOException {
        String[] testFiles = new String[] {"target/classes/TestClasses/C.class",
                "target/classes/TestClasses/BasicClass.class", "target/classes/TestClasses/BasicSingleton.class"};
        HashMap<String, InputStream> streams = Main.getClassStreams(testFiles);
        Assertions.assertEquals(3, streams.size());
        Assertions.assertTrue(streams.containsKey("target/classes/TestClasses/C.class"));
        Assertions.assertTrue(streams.containsKey("target/classes/TestClasses/BasicClass.class"));
        Assertions.assertTrue(streams.containsKey("target/classes/TestClasses/BasicSingleton.class"));
    }

    @Test
    public void testGetClassStreamsOfBoth() throws IOException {
        String[] testFiles = new String[] {"testJar.jar", "target/classes/TestClasses/mime.class",
                "target/classes/TestClasses/symbolism.class"};
        HashMap<String, InputStream> streams = Main.getClassStreams(testFiles);
        Assertions.assertEquals(5, streams.size());
        Assertions.assertTrue(streams.containsKey("C.class"));
        Assertions.assertTrue(streams.containsKey("BasicClass.class"));
        Assertions.assertTrue(streams.containsKey("BasicSingleton.class"));
        Assertions.assertTrue(streams.containsKey("target/classes/TestClasses/mime.class"));
        Assertions.assertTrue(streams.containsKey("target/classes/TestClasses/symbolism.class"));
    }
}
