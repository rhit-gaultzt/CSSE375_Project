package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.Options;

import java.util.List;
import java.util.Map;

public interface Rule {
    Options getDefaultOptions();
    List<Issue> apply(Map<String, ClassNode> classNodes, Options options);
}
