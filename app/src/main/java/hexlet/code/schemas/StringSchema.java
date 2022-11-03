package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StringSchema {
    private String dataToCheck;
    private List<String> checkList = new ArrayList<>();
    private String symbolsToCompare;
    private int lengthToCompare;
    private boolean schemaIsValid;

    public StringSchema() {
        this.schemaIsValid = true;
    }

    public final StringSchema required() {
        this.checkList.add("isRequired");
        return this;
    }

    public final StringSchema contains(String toCompare) {
        this.checkList.add("isContains");
        this.symbolsToCompare = toCompare;
        return this;
    }

    public final StringSchema minLength(int toCompare) {
        this.checkList.add("isEqualMinLength");
        this.lengthToCompare = toCompare;
        return this;
    }

    public final StringSchema toCheckIfRequired() {
        if ((this.dataToCheck == null) || this.dataToCheck.equals("")) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public final StringSchema toCheckIfContains(String toCompare) {
        if (!this.dataToCheck.contains(toCompare)) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public final StringSchema toCheckIfEqualLength(int toCompare) {
        if (this.dataToCheck.length() < toCompare) {
            this.schemaIsValid = false;
        } else {
            this.schemaIsValid = true;
        }
        return this;
    }

    public final boolean isValid(String input) {
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
