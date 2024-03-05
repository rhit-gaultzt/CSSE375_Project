package Data;

import java.io.FileNotFoundException;
import java.util.Map;

public interface OptionsReader {

    Map<String, Options> readOptions() throws FileNotFoundException;

}
