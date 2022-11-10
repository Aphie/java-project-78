package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        super.setSchemaIsValid(true);
    }

    public final StringSchema required() {
        super.setCheckList("isRequired");
        return this;
    }

    public final StringSchema contains(String toCompare) {
        super.setCheckList("isContains");
        this.setSymbolsToCompare(toCompare);
        return this;
    }

    public final StringSchema minLength(int toCompare) {
        super.setCheckList("isEqualMinLength");
        this.setLengthToCompare(toCompare);
        return this;
    }

    @Override
    public final StringSchema toCheckIfRequired() {
        super.setSchemaIsValid(((super.getDataToCheck() == null) || super.getDataToCheck().equals("")) ? false : true);
        return this;
    }


}
