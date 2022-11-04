package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class BaseSchema {
    protected Object dataToCheck;
    protected List<String> checkList = new ArrayList<>();
    protected boolean schemaIsValid;

    public BaseSchema toCheckIfRequired() {
        return this;
    }

    public boolean isValid(Object input) {
        this.dataToCheck = input;
        for (String check: this.checkList) {
            if (check.equals("isRequired")) {
                this.toCheckIfRequired();
            }
        }
        return this.schemaIsValid;
    }
}
