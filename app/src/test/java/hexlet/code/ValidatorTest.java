package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void basicNullAndEmptyValidTest() {
        Assertions.assertEquals(true, schema.isValid(null));
        Assertions.assertEquals(true, schema.isValid("test"));
        Assertions.assertEquals(true, schema.isValid(5));
        Assertions.assertEquals(true, schema.isValid(0));
    }

    @Test
    void basicRequiredTest() {
        Assertions.assertEquals(false, schema.required().isValid(null));
        Assertions.assertEquals(true, schema.required().isValid(10));
        Assertions.assertEquals(false, schema.isValid("test"));
        Assertions.assertEquals(true, schema.isValid(0));
    }

    @Test
    void basicPositiveTest() {
        Assertions.assertEquals(true, schema.positive().isValid(10));
        Assertions.assertEquals(false, schema.isValid(-10));
    }

    @Test
    void basicRangeTest() {
        Assertions.assertEquals(true, schema.range(5, 10).isValid(5));
        Assertions.assertEquals(true, schema.isValid(10));
        Assertions.assertEquals(false, schema.isValid(4));
        Assertions.assertEquals(false, schema.isValid(11));
    }
}
