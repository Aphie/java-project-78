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
        if (checkList.size() != 0) {
            for (int i = 0; i < checkList.size(); i++) {
                if (!this.checkList.get(i).equals(value)) {
                    this.checkList.add(value);
                }
            }
        } else {
            this.checkList.add(value);
        }
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
