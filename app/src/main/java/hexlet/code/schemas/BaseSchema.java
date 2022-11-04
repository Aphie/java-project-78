package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class BaseSchema {
    private Object dataToCheck;
    private List<String> checkList = new ArrayList<>();
    private boolean schemaIsValid;

    protected BaseSchema toCheckIfRequired() {
        return this;
    }

    protected boolean isValid(Object input) {
        this.dataToCheck = input;
        for (String check: this.checkList) {
            if (check.equals("isRequired")) {
                this.toCheckIfRequired();
            }
        }
        return this.schemaIsValid;
    }
}
