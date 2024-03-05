package Data;

import Domain.Rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Options {


    public HashMap<String, String> attributes = new HashMap<>();


    public Options (List<String> keys, List<String> values) {
        for (int index = 0; index < keys.size(); index++) {
            put(keys.get(index), values.get(index));
        }
    }


    public boolean hasKey(String key) {
        return attributes.containsKey(key);
    }


    public String get(String key) {
        return attributes.get(key);
    }


    public <E extends Enum<E>> E get(String key, Class<E> attributes, String caller) {
        try {
            return Enum.valueOf(attributes, this.get(key));
        } catch (NullPointerException error) {
            throw new Error("Config Error: Property "
                    + attributes.getSimpleName() + " for " + caller
                    + " does not exist");
        } catch (IllegalArgumentException error) {
            throw new Error(
                    String.format("Config Error (%s): \"%s\" is an invalid "
                                    + "attribute option for the property \"%s\"."
                                    + "\n\tValid Options: %s",
                            caller, this.get(key),
                            key, Arrays.toString(attributes.getEnumConstants()))
            );
        }
    }


    public void put(String key, String value) {
        attributes.put(key, value);
    }


    public void applyDefault(Options defaults) {
        if (defaults == null) return;
        for (String key : defaults.attributes.keySet()) {
            if (!this.hasKey(key)) {
                this.put(key, defaults.get(key));
            }
        }
    }


}
