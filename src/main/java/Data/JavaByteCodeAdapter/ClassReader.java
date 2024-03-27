package Data.JavaByteCodeAdapter;

import java.io.IOException;
import java.io.InputStream;

public interface ClassReader {
    ClassNode createClassNode(String className) throws IOException;
    ClassNode createClassNode(InputStream classData) throws IOException;
}
