package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        super.setSchemaIsValid(true);
    }

    public final MapSchema required() {
        super.setCheckList("isRequired");
        return this;
    }

    public final MapSchema sizeof(int checkSizeValue) {
        super.setCheckList("isSizeOf");
        this.setSizeValue(checkSizeValue);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        super.setCheckList("isShape");
        this.setShapeSchema(schemas);
        return this;
    }

    @Override
    public final MapSchema toCheckIfRequired() {
        super.setSchemaIsValid(super.getDataToCheck() instanceof Map && !(super.getDataToCheck() == null));
        return this;
    }
}
