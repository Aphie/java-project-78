package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private int valueFrom;
    private int valueTo;

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

    public final NumberSchema range(int checkValueFrom, int checkValueTo) {
        this.checkList.add("isRange");
        this.valueFrom = checkValueFrom;
        this.valueTo = checkValueTo;
        return this;
    }

    @Override
    public final NumberSchema toCheckIfRequired() {
        this.schemaIsValid = ((this.dataToCheck instanceof Integer));
        return this;
    }

    public final NumberSchema toCheckIfPositive() {
        this.schemaIsValid = ((Integer) this.dataToCheck > 0);
        return this;
    }

    public final NumberSchema toCheckIfRange(int checkValueFrom, int checkValueTo) {
        this.schemaIsValid = ((Integer) this.dataToCheck >= checkValueFrom)
                && ((Integer) this.dataToCheck <= checkValueTo);
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: this.checkList) {
            if (check.equals("isPositive")) {
                this.toCheckIfPositive();
            } else if (check.equals("isRange")) {
                this.toCheckIfRange(this.valueFrom, this.valueTo);
            }
        }
        return this.schemaIsValid;
    }

}
