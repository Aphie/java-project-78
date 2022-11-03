package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StringSchema {
    public String dataToCheck;
    public List<String> checkList = new ArrayList<>();
    public String symbolsToCompare;
    public int lengthToCompare;
    public boolean schemaIsValid;

    public StringSchema() {
        this.schemaIsValid = true;
    }

    public StringSchema required() {
        this.checkList.add("isRequired");
        return this;
    }

    public StringSchema contains(String toCompare) {
        this.checkList.add("isContains");
        this.symbolsToCompare = toCompare;
        return this;
    }

    public StringSchema minLength(int toCompare) {
        this.checkList.add("isEqualMinLength");
        this.lengthToCompare = toCompare;
        return this;
    }

    public StringSchema toCheckIfRequired() {
        if ((this.dataToCheck == null) || this.dataToCheck.equals("")) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public StringSchema toCheckIfContains(String toCompare) {
        if (!this.dataToCheck.contains(toCompare)) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public StringSchema toCheckIfEqualLength(int toCompare) {
        if (this.dataToCheck.length()<toCompare) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public boolean isValid(String input) {
        this.dataToCheck = input;
        for (String check: this.checkList) {
            if (check.equals("isRequired")) {
                this.toCheckIfRequired();
            } else if (check.equals("isContains")) {
                this.toCheckIfContains(this.symbolsToCompare);
            } else if (check.equals("isEqualMinLength")) {
                this.toCheckIfEqualLength(this.lengthToCompare);
            } else {
                this.schemaIsValid = true;
            }
        }
        return this.schemaIsValid;
    }

}
