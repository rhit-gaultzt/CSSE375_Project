package Domain;

import Presentation.Main;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StreamHandlerTest {
    @Test
    public void testCloseStreams() throws IOException {
        InputStream sut1 = Files.newInputStream(new File("src/test/java/Presentation/MainTest.java").toPath());
        InputStream sut2 = Files.newInputStream(new File("src/main/java/Presentation/Main.java").toPath());
        List<InputStream> streams = new ArrayList<>();
        streams.add(sut1);
        streams.add(sut2);

        ClassStreamHandler.closeStreams(streams);

        Assertions.assertThrows(IOException.class, sut1::read);
        Assertions.assertThrows(IOException.class, sut2::read);
    }
}
