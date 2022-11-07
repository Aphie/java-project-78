package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int sizeValue;
    private Map<String, BaseSchema> shapeSchema;

    public MapSchema() {
        super.setSchemaIsValid(true);
    }

    public final MapSchema required() {
        super.setCheckList("isRequired");
        return this;
    }

    public final MapSchema sizeof(int checkSizeValue) {
        super.setCheckList("isSizeOf");
        this.sizeValue = checkSizeValue;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        super.setCheckList("isShape");
        this.shapeSchema = schemas;
        return this;

    }

    @Override
    public final MapSchema toCheckIfRequired() {
        super.setSchemaIsValid(super.getDataToCheck() instanceof Map && !(super.getDataToCheck() == null));
        return this;
    }

    public final MapSchema toCheckIfSizeOf(int checkSizeValue) {
        super.setSchemaIsValid(((Map) super.getDataToCheck()).keySet().size() == checkSizeValue);
        return this;
    }

    public final MapSchema toCheckInnerValues() {
        int itemValidFlag = 0;
        for (Object key: ((Map) super.getDataToCheck()).keySet()) {
            for (String keyToCheck : this.shapeSchema.keySet()) {
                if (key.equals(keyToCheck)) {
                    BaseSchema baseSchema = this.shapeSchema.get(keyToCheck);
                    if (!baseSchema.isValid(((Map<?, ?>) super.getDataToCheck()).get(key))) {
                        itemValidFlag++;
                    }
                }
            }
        }
        super.setSchemaIsValid(itemValidFlag == 0);
        return this;
    }

    @Override
    public final boolean isValid(Object input) {
        super.isValid(input);
        for (String check: super.getCheckList()) {
            if (check.equals("isSizeOf")) {
                this.toCheckIfSizeOf(this.sizeValue);
            } else if (check.equals("isShape")) {
                this.toCheckInnerValues();
            }
        }
        return super.isSchemaIsValid();
    }


}
