package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        StringSchema schema = v.string();

        schema.isValid(""); // true
        schema.isValid(null);// true

        schema.required();

        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true
        schema.isValid(null); // false
        schema.isValid(""); // false

        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); //false

        schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")

        schema.contains("what").minLength(40).isValid("what does the fox say");
    }
}
