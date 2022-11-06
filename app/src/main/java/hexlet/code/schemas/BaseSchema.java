package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseSchema {
    private Object dataToCheck;
    private List<String> checkList = new ArrayList<>();
    private boolean schemaIsValid;

    BaseSchema toCheckIfRequired() {
        return this;
    }

    boolean isValid(Object input) {
        this.dataToCheck = input;
        for (String check: this.checkList) {
            if (check.equals("isRequired")) {
                this.toCheckIfRequired();
            }
        }
        return this.schemaIsValid;
    }
}
