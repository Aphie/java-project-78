package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String symbolsToCompare;
    private int lengthToCompare;

    public StringSchema() {
        super.setSchemaIsValid(true);
    }

    public final StringSchema required() {
        super.setCheckList("isRequired");
        return this;
    }

    public final StringSchema contains(String toCompare) {
        super.setCheckList("isContains");
        this.symbolsToCompare = toCompare;
        return this;
    }

    public final StringSchema minLength(int toCompare) {
        super.setCheckList("isEqualMinLength");
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
        if (super.getCheckList().contains("isContains") && super.isSchemaIsValid() == true) {
            this.toCheckIfContains(this.symbolsToCompare);
        } else if (super.getCheckList().contains("isEqualMinLength") && super.isSchemaIsValid() == true) {
            this.toCheckIfEqualLength(this.lengthToCompare);
        }
        return super.isSchemaIsValid();
    }

}
