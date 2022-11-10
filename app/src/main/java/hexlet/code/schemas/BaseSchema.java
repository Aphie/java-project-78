package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс базовой схемы со свойствами <b>dataToCheck</b>, <b>checkList</b> и <b>schemaIsValid</b>.
 * @author s.andreyuk or Aphie
 * @version 1.0
 */
@Data
public class BaseSchema {
    /** Поле данных для проверки.*/
    private Object dataToCheck;

    /** Список проверок.*/
    private List<String> checkList = new ArrayList<>();

    /** Признак валидности системы.*/
    private boolean schemaIsValid;

    /**
     * Метод - базовая проверка на обязательность.
     * @return - возвращает базовую схему в неизменном виде
     */
    public BaseSchema toCheckIfRequired() {
        return this;
    }

    /**
     * Метод - сеттер добавления проверок в список проверок.
     * @param value - добавляемая проверка
     */
    public void setCheckList(String value) {
        if (!checkList.contains(value)) {
            checkList.add(value);
        }
    }

    /**
     * Метод - базовая проверка валидности введенных данных.
     * @param input - проверяемые данные
     * @return - признак валидности данных в соответствии со списком проверок
     */
    public boolean isValid(Object input) {
        this.schemaIsValid = true;
        this.dataToCheck = input;
        if (this.checkList.contains("isRequired") && this.schemaIsValid) {
            this.toCheckIfRequired();
        }
        return this.schemaIsValid;
    }

}
