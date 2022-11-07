package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class StringSchemaTest {
    private Validator v = new Validator();
    private StringSchema schema = v.string();
    public static final int TO_CHECK_MIN_LENGTH_FIRST = 4;
    public static final int TO_CHECK_MIN_LENGTH_SECOND = 5;

    @Test
    void basicNullAndEmptyValidTest() {
        Assertions.assertEquals(true, schema.isValid(null));
        Assertions.assertEquals(true, schema.isValid(""));
        Assertions.assertEquals(true, schema.isValid("test"));
    }

    @Test
    void basicRequiredTest() {
        Assertions.assertEquals(true, schema.isValid(""));
        Assertions.assertEquals(false, schema.required().isValid(""));
        Assertions.assertEquals(false, schema.isValid(""));
        Assertions.assertEquals(true, schema.isValid("test"));
    }

    @Test
    void basicContainsTest() {
        Assertions.assertEquals(true, schema.contains("t").isValid("test"));
        Assertions.assertEquals(true, schema.contains("te").isValid("test"));
        Assertions.assertEquals(true, schema.isValid("test"));
        Assertions.assertEquals(false, schema.isValid("what"));
    }

    @Test
    void basicLengthTest() {
        Assertions.assertEquals(false, schema.minLength(TO_CHECK_MIN_LENGTH_SECOND).isValid("test"));
        Assertions.assertEquals(true, schema.minLength(TO_CHECK_MIN_LENGTH_FIRST).isValid("test"));
        Assertions.assertEquals(true, schema.isValid("what"));
        Assertions.assertEquals(false, schema.isValid("toe"));

    }

}

class NumberSchemaTest {
    private Validator v = new Validator();
    private NumberSchema schema = v.number();

    public static final int TO_CHECK_REALISATION_ZERO = 0;
    public static final int TO_CHECK_REALISATION_FOUR = 4;
    public static final int TO_CHECK_REALISATION_FIVE = 5;
    public static final int TO_CHECK_REALISATION_TEN = 10;
    public static final int TO_CHECK_REALISATION_MINUS_TEN = -10;
    public static final int TO_CHECK_REALISATION_ELEVEN = 11;

    @Test
    void basicNullAndEmptyValidTest() {
        Assertions.assertEquals(true, schema.isValid(null));
        Assertions.assertEquals(true, schema.isValid("test"));
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_FIVE));
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_ZERO));
    }

    @Test
    void basicRequiredTest() {
        Assertions.assertEquals(false, schema.required().isValid(null));
        Assertions.assertEquals(true, schema.required().isValid(TO_CHECK_REALISATION_TEN));
        Assertions.assertEquals(false, schema.isValid("test"));
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_ZERO));
    }

    @Test
    void basicPositiveTest() {
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_ZERO));
        Assertions.assertEquals(true, schema.positive().isValid(TO_CHECK_REALISATION_TEN));
        Assertions.assertEquals(false, schema.positive().isValid(TO_CHECK_REALISATION_MINUS_TEN));
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_ZERO));
    }

    @Test
    void basicRangeTest() {
        Assertions.assertEquals(true, schema.range(TO_CHECK_REALISATION_FIVE, TO_CHECK_REALISATION_TEN)
                .isValid(TO_CHECK_REALISATION_FIVE));
        Assertions.assertEquals(true, schema.isValid(TO_CHECK_REALISATION_TEN));
        Assertions.assertEquals(false, schema.isValid(TO_CHECK_REALISATION_FOUR));
        Assertions.assertEquals(false, schema.isValid(TO_CHECK_REALISATION_ELEVEN));
    }

}

class MapSchemaTest {
    private Validator v = new Validator();
    private MapSchema schema = v.map();
    private Map<String, String> data = new HashMap<>();

    public static final int TO_CHECK_REALISATION_MINUS_TEN = -10;
    public static final int TO_CHECK_REALISATION_ELEVEN = 11;

    @Test
    void basicNullAndEmptyValidTest() {
        Assertions.assertEquals(true, schema.isValid(null));
    }

    @Test
    void basicRequiredTest() {
        data.put("key1", "value1");
        Assertions.assertEquals(false, schema.required().isValid(null));
        Assertions.assertEquals(true, schema.required().isValid(new HashMap()));
        Assertions.assertEquals(true, schema.isValid(data));
    }

    @Test
    void basicSizeOfTest() {
        data.put("key1", "value1");
        Assertions.assertEquals(false, schema.sizeof(2).isValid(data));
        data.put("key2", "value2");
        Assertions.assertEquals(true, schema.isValid(data));
    }

    @Test
    void basicShapeTest() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", TO_CHECK_REALISATION_ELEVEN);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", TO_CHECK_REALISATION_MINUS_TEN);

        Assertions.assertEquals(true, schema.isValid(human1));
        Assertions.assertEquals(true, schema.isValid(human2));
        Assertions.assertEquals(false, schema.isValid(human3));
        Assertions.assertEquals(false, schema.isValid(human4));
    }

}
