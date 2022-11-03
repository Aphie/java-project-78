package hexlet.code;

import hexlet.code.schemas.StringSchema;
import lombok.Data;

@Data
public final class Validator {

    public StringSchema string() {
        return new StringSchema();
    }
}
