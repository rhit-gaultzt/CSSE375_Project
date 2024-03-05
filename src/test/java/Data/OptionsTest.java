package Data;


import Domain.Rule;
import Domain.Rules.VariableNameConventionRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class OptionsTest {

    @Test
    public void hasAttributeValid () {
        List<String> keys = new ArrayList<>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);
        Assertions.assertTrue(options.hasKey("key1"));
    }


    @Test
    public void hasAttributeInvalid () {
        List<String> keys = new ArrayList<>();
        keys.add("key1");
        keys.add("key2");
        keys.add("KEY3");
        List<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);
        Assertions.assertFalse(options.hasKey("key3"));
    }


    @Test
    public void getAttribute () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);
        Assertions.assertEquals("value2", options.get("key2"));
    }


    protected enum TestEnum1 {
        EXAMPLE1_1,
        EXAMPLE1_2,
        EXAMPLE1_3
    }

    protected enum TestEnum2 {
        EXAMPLE2_1,
        EXAMPLE2_2,
        EXAMPLE2_3
    }


    @Test
    public void getAttributeEnumValid () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("EXAMPLE1_1");
        values.add("EXAMPLE1_2");
        values.add("EXAMPLE2_3");

        Options options = new Options(keys, values);
        Assertions.assertEquals(TestEnum1.EXAMPLE1_1, options.get("key1", TestEnum1.class, ""));
        Assertions.assertEquals(TestEnum1.EXAMPLE1_2, options.get("key2", TestEnum1.class, ""));
        Assertions.assertEquals(TestEnum2.EXAMPLE2_3, options.get("key3", TestEnum2.class, ""));
    }


    @Test
    public void getAttributeEnumInvalid () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("EXAMPLE1_1");
        values.add("EXAMPLE1_2");
        values.add("EXAMPLE1_3");

        Options options = new Options(keys, values);
        Assertions.assertEquals(TestEnum1.EXAMPLE1_1, options.get("key1", TestEnum1.class, ""));
        Assertions.assertEquals(TestEnum1.EXAMPLE1_2, options.get("key2", TestEnum1.class, ""));
        Assertions.assertThrows(Error.class, () -> {
            options.get("key3", TestEnum2.class, "");
        });
    }


    @Test
    public void putAttributeNew () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);

        options.put("key4", "value4");
        Assertions.assertTrue(options.hasKey("key4"));
        Assertions.assertEquals("value4", options.get("key4"));
    }


    @Test
    public void putAttributeOverride () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);

        options.put("key3", "value4");
        Assertions.assertTrue(options.hasKey("key3"));
        Assertions.assertEquals("value4", options.get("key3"));
    }


    @Test
    public void applyDefaultNull () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);
        options.applyDefault(null);

        Assertions.assertTrue(options.hasKey("key1"));
        Assertions.assertTrue(options.hasKey("key2"));
        Assertions.assertTrue(options.hasKey("key3"));
        Assertions.assertEquals("value1", options.get("key1"));
        Assertions.assertEquals("value2", options.get("key2"));
        Assertions.assertEquals("value3", options.get("key3"));
    }


    @Test
    public void applyDefaultExists () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        keys.add("key3");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        Options options = new Options(keys, values);

        List<String> defKeys = new ArrayList<String>();
        defKeys.add("key1");
        defKeys.add("key2");
        defKeys.add("key3");
        List<String> defValues = new ArrayList<String>();
        defValues.add("value1!");
        defValues.add("value2!");
        defValues.add("value3!");

        Options defaults = new Options(defKeys, defValues);
        options.applyDefault(defaults);

        Assertions.assertTrue(options.hasKey("key1"));
        Assertions.assertTrue(options.hasKey("key2"));
        Assertions.assertTrue(options.hasKey("key3"));
        Assertions.assertEquals("value1", options.get("key1"));
        Assertions.assertEquals("value2", options.get("key2"));
        Assertions.assertEquals("value3", options.get("key3"));
    }


    @Test
    public void applyDefaultMissing () {
        List<String> keys = new ArrayList<String>();
        keys.add("key1");
        keys.add("key2");
        List<String> values = new ArrayList<String>();
        values.add("value1");
        values.add("value2");

        Options options = new Options(keys, values);

        List<String> defKeys = new ArrayList<String>();
        defKeys.add("key1");
        defKeys.add("key2");
        defKeys.add("key3");
        List<String> defValues = new ArrayList<String>();
        defValues.add("value1!");
        defValues.add("value2!");
        defValues.add("value3!");

        Options defaults = new Options(defKeys, defValues);
        options.applyDefault(defaults);

        Assertions.assertTrue(options.hasKey("key1"));
        Assertions.assertTrue(options.hasKey("key2"));
        Assertions.assertTrue(options.hasKey("key3"));
        Assertions.assertEquals("value1", options.get("key1"));
        Assertions.assertEquals("value2", options.get("key2"));
        Assertions.assertEquals("value3!", options.get("key3"));
    }



}
