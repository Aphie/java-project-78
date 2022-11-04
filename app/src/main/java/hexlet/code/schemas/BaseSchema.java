package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

class BaseSchema {
    Object dataToCheck;
    List<String> checkList = new ArrayList<>();
    boolean schemaIsValid;

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
