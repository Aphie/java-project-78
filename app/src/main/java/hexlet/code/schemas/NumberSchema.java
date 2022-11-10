package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

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
        this.setValueFrom(checkValueFrom);
        this.setValueTo(checkValueTo);
        return this;
    }

    @Override
    public final NumberSchema toCheckIfRequired() {
        super.setSchemaIsValid((super.getDataToCheck() instanceof Integer && !(super.getDataToCheck() == null)));
        return this;
    }

}
