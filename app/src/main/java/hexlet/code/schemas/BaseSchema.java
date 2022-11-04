package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

class BaseSchema {
    public Object dataToCheck;
    public List<String> checkList = new ArrayList<>();
    public boolean schemaIsValid;

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
