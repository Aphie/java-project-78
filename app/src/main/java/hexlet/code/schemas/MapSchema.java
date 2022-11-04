package hexlet.code.schemas;

import java.util.List;
import java.util.Map;

public class MapSchema extends BaseSchema{
    private int sizeValue;

    public MapSchema() {
        super.setSchemaIsValid(true);
    }

    public final MapSchema required() {
        super.setCheckList(List.of("isRequired"));
        return this;
    }

    public final MapSchema sizeof(int sizeValue) {
        super.setCheckList(List.of("isSizeOf"));
        this.sizeValue = sizeValue;
        return this;
    }

    @Override
    public final MapSchema toCheckIfRequired() {
        super.setSchemaIsValid(super.getDataToCheck() instanceof Map);
        return this;
    }

    public final MapSchema toCheckIfSizeOf(int sizeValue) {
        super.setSchemaIsValid(((Map) super.getDataToCheck()).keySet().size() == sizeValue);
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: super.getCheckList()) {
            if (check.equals("isSizeOf")) {
                this.toCheckIfSizeOf(this.sizeValue);
            }
        }
        return super.isSchemaIsValid();
    }


}
