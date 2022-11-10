package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /** Признак валидности схемы.*/
    private boolean schemaIsValid;

    /** Параметр - строка для сравнения.*/
    private String symbolsToCompare;
    /** Параметр - количество символов в строке.*/
    private int lengthToCompare;

    /** Параметр - нижняя граница допустимого значения числа.*/
    private int valueFrom;
    /** Параметр - верхняя граница допустимого значения числа.*/
    private int valueTo;

    /** Параметр - размер Map.*/
    private int sizeValue;
    /** Параметр - схема сравнений внутренних объектов Map.*/
    private Map<String, BaseSchema> shapeSchema;

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
    protected void setCheckList(String value) {
        if (!checkList.contains(value)) {
            checkList.add(value);
        }
    }

    /**
     * Метод - проверка на вхождение подстроки в строку.
     * @param toCompare - подстрока для сравнения
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckIfContains(String toCompare) {
        this.schemaIsValid = ((String) this.dataToCheck).contains(toCompare);
        return this;
    }

    /**
     * Метод - проверка на равенство длины строки и введенного числа.
     * @param toCompare - число для сравнения
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckIfEqualLength(int toCompare) {
        this.schemaIsValid = ((String) this.dataToCheck).length() >= toCompare;
        return this;
    }

    /**
     * Метод - проверка, является ли число положительным.
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckIfPositive() {
        if (this.dataToCheck != null) {
            if (this.dataToCheck instanceof String) {
                this.schemaIsValid = true;
            } else {
                this.schemaIsValid = (Integer) this.dataToCheck > 0;
            }
        }
        return this;
    }

    /**
     * Метод - проверка на принадлежность числа к введенному диапазону.
     * @param checkValueFrom - нижняя граница допустимого диапазона
     * @param checkValueTo - верхняя граница допустимого диапазона
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckIfRange(int checkValueFrom, int checkValueTo) {
        this.schemaIsValid = ((this.dataToCheck instanceof Integer)
                && (((Integer) this.dataToCheck >= checkValueFrom)
                && ((Integer) this.dataToCheck <= checkValueTo)));
        return this;
    }

    /**
     * Метод - проверка на равенство количества пар объекта Map введенному значению.
     * @param checkSizeValue - введенное значение для сравнения с количеством пар объекта Map
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckIfSizeOf(int checkSizeValue) {
        this.schemaIsValid = (((Map) this.dataToCheck).keySet().size() == checkSizeValue);
        return this;
    }

    /**
     * Метод - проверка значений объекта Map пользовательской схеме проверок.
     * @return объект BaseSchema
     */
    public final BaseSchema toCheckInnerValues() {
        int itemValidFlag = 0;
        for (Object key: ((Map) this.dataToCheck).keySet()) {
            for (String keyToCheck : this.shapeSchema.keySet()) {
                if (key.equals(keyToCheck)) {
                    BaseSchema baseSchema = this.shapeSchema.get(keyToCheck);
                    if (!baseSchema.isValid(((Map<?, ?>) this.dataToCheck).get(key))) {
                        itemValidFlag++;
                    }
                }
            }
        }
        this.schemaIsValid = (itemValidFlag == 0);
        return this;
    }

    /**
     * Метод - базовая проверка валидности введенных данных.
     * @param input - проверяемые данные
     * @return - признак валидности данных в соответствии со списком проверок
     */
    public final boolean isValid(Object input) {
        this.schemaIsValid = true;
        this.dataToCheck = input;

        for (String check: this.checkList) {
            if (this.schemaIsValid) {
                switch (check) {
                    case "isRequired" -> {
                        this.toCheckIfRequired();
                        break;
                    }
                    case "isContains" -> {
                        this.toCheckIfContains(this.symbolsToCompare);
                        break;
                    }
                    case "isEqualMinLength" -> {
                        this.toCheckIfEqualLength(this.lengthToCompare);
                        break;
                    }
                    case "isPositive" -> {
                        this.toCheckIfPositive();
                        break;
                    }
                    case "isRange" -> {
                        this.toCheckIfRange(this.valueFrom, this.valueTo);
                        break;
                    }
                    case "isSizeOf" -> {
                        this.toCheckIfSizeOf(this.sizeValue);
                        break;
                    }
                    case "isShape" -> {
                        this.toCheckInnerValues();
                        break;
                    }
                    default -> {
                        break;
                    }
                }
            }
        }
        return this.schemaIsValid;
    }

}
