package hexlet.code.schemas;

import java.util.List;

public class StringSchema extends BaseSchema {
    private String symbolsToCompare;
    private int lengthToCompare;

    public StringSchema() {
        super.setSchemaIsValid(true);
    }

    public final StringSchema required() {
        super.setCheckList(List.of("isRequired"));
        return this;
    }

    public final StringSchema contains(String toCompare) {
        super.setCheckList(List.of("isContains"));
        this.symbolsToCompare = toCompare;
        return this;
    }

    public final StringSchema minLength(int toCompare) {
        super.setCheckList(List.of("isEqualMinLength"));
        this.lengthToCompare = toCompare;
        return this;
    }

    @Override
    public final StringSchema toCheckIfRequired() {
        super.setSchemaIsValid(((super.getDataToCheck() == null) || super.getDataToCheck().equals("")) ? false : true);
        return this;
    }

    public final StringSchema toCheckIfContains(String toCompare) {
        super.setSchemaIsValid(((String) super.getDataToCheck()).contains(toCompare));
        return this;
    }

    public final StringSchema toCheckIfEqualLength(int toCompare) {
        super.setSchemaIsValid(((String) super.getDataToCheck()).length() >= toCompare);
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: super.getCheckList()) {
            if (check.equals("isContains")) {
                this.toCheckIfContains(this.symbolsToCompare);
            } else if (check.equals("isEqualMinLength")) {
                this.toCheckIfEqualLength(this.lengthToCompare);
            }
        }
        return super.isSchemaIsValid();
    }

}
