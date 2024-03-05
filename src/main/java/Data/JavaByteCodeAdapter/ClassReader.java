package Data.JavaByteCodeAdapter;

import java.io.IOException;

public interface ClassReader {
    ClassNode createClassNode(String className) throws IOException;
}
