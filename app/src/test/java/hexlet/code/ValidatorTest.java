package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringSchemaTest {
    private Validator v = new Validator();
    private StringSchema schema = v.string();
    private final int VARIABLE_TO_CHECK_MIN_LENGTH1 = 4;
    private final int VARIABLE_TO_CHECK_MIN_LENGTH2 = 5;

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
        Assertions.assertEquals(false, schema.minLength(VARIABLE_TO_CHECK_MIN_LENGTH2).isValid("test"));
        Assertions.assertEquals(true, schema.minLength(VARIABLE_TO_CHECK_MIN_LENGTH1).isValid("test"));
        Assertions.assertEquals(true, schema.isValid("what"));
        Assertions.assertEquals(false, schema.isValid("toe"));

    }

}
