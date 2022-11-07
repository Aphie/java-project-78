package hexlet.code.schemas;

import java.util.List;

public class NumberSchema extends BaseSchema {
    private int valueFrom;
    private int valueTo;

    public NumberSchema() {
        super.setSchemaIsValid(true);
    }

    public final NumberSchema required() {
        super.setCheckList("isRequired");
        return this;
    }

    public final NumberSchema positive() {
        super.setCheckList("isPositive");
        return this;
    }

    public final NumberSchema range(int checkValueFrom, int checkValueTo) {
        super.setCheckList("isRange");
        this.valueFrom = checkValueFrom;
        this.valueTo = checkValueTo;
        return this;
    }

    @Override
    public final NumberSchema toCheckIfRequired() {
        super.setSchemaIsValid((super.getDataToCheck() instanceof Integer));
        return this;
    }

    public final NumberSchema toCheckIfPositive() {
        super.setSchemaIsValid(super.getDataToCheck() == null
                || (super.getDataToCheck() instanceof Integer && (Integer) super.getDataToCheck() > 0));
        return this;
    }

    public final NumberSchema toCheckIfRange(int checkValueFrom, int checkValueTo) {
        super.setSchemaIsValid((super.getDataToCheck() instanceof Integer)
                && (((Integer) super.getDataToCheck() >= checkValueFrom)
                    && ((Integer) super.getDataToCheck() <= checkValueTo)));
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: super.getCheckList()) {
            if (check.equals("isPositive")) {
                this.toCheckIfPositive();
            } else if (check.equals("isRange")) {
                this.toCheckIfRange(this.valueFrom, this.valueTo);
            }
        }
        return super.isSchemaIsValid();
    }

}
