package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseSchema {
    private Object dataToCheck;
    private List<String> checkList = new ArrayList<>();
    private boolean schemaIsValid;

    public BaseSchema toCheckIfRequired() {
        return this;
    }

    public void setCheckList(String value) {
        if (!checkList.contains(value)) {
            checkList.add(value);
        }
    }

    public boolean isValid(Object input) {
        this.schemaIsValid = true;
        this.dataToCheck = input;
        if (this.checkList.contains("isRequired") && this.schemaIsValid == true) {
                this.toCheckIfRequired();
            }
        return this.schemaIsValid;
    }

}
