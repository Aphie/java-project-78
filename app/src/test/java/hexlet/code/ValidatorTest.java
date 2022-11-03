package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringSchemaTest {
    public Validator v = new Validator();
    StringSchema schema = v.string();

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
        Assertions.assertEquals(false, schema.minLength(5).isValid("test"));
        Assertions.assertEquals(true, schema.minLength(4).isValid("test"));
        Assertions.assertEquals(true, schema.isValid("what"));
        Assertions.assertEquals(false, schema.isValid("toe"));

    }

}
