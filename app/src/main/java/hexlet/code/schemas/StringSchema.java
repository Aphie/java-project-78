package hexlet.code.schemas;

public class StringSchema extends BaseSchema{
    private String symbolsToCompare;
    private int lengthToCompare;

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

    @Override
    public final StringSchema toCheckIfRequired() {
        this.schemaIsValid = ((this.dataToCheck == null) || this.dataToCheck.equals("")) ? false : true;
        return this;
    }

    public final StringSchema toCheckIfContains(String toCompare) {
        this.schemaIsValid = ((String)this.dataToCheck).contains(toCompare);
        return this;
    }

    public final StringSchema toCheckIfEqualLength(int toCompare) {
        this.schemaIsValid = ((String)this.dataToCheck).length() >= toCompare;
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: this.checkList) {
            if (check.equals("isContains")) {
                this.toCheckIfContains(this.symbolsToCompare);
            } else if (check.equals("isEqualMinLength")) {
                this.toCheckIfEqualLength(this.lengthToCompare);
            }
        }
        return this.schemaIsValid;
    }

}
