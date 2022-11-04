package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private int fromValue;
    private int toValue;

    public NumberSchema() {
        this.schemaIsValid = true;
    }

    public final NumberSchema required() {
        this.checkList.add("isRequired");
        return this;
    }

    public final NumberSchema positive() {
        this.checkList.add("isPositive");
        return this;
    }

    public final NumberSchema range(int fromValue, int toValue) {
        this.checkList.add("isRange");
        this.fromValue = fromValue;
        this.toValue = toValue;
        return this;
    }

    @Override
    public final NumberSchema toCheckIfRequired() {
        this.schemaIsValid = ((this.dataToCheck instanceof Integer));
        return this;
    }

    public final NumberSchema toCheckIfPositive() {
        this.schemaIsValid = ((Integer)this.dataToCheck > 0);
        return this;
    }

    public final NumberSchema toCheckIfRange(int fromValue, int toValue) {
        this.schemaIsValid = ((Integer)this.dataToCheck >= fromValue) && ((Integer)this.dataToCheck <= toValue);
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: this.checkList) {
            if (check.equals("isPositive")) {
                this.toCheckIfPositive();
            } else if (check.equals("isRange")) {
                this.toCheckIfRange(this.fromValue, this.toValue);
            }
        }
        return this.schemaIsValid;
    }

}
