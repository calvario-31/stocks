package stocks.model;

public class SingleData {
    private int numericValue;
    private boolean booleanValue;
    private String stringValue;
    private final InputType inputType;

    public SingleData(int numericValue) {
        this.numericValue = numericValue;
        this.inputType = InputType.NUMERIC;
    }

    public SingleData(boolean booleanValue) {
        this.booleanValue = booleanValue;
        this.inputType = InputType.CHECKBOX;
    }

    public SingleData(String stringValue) {
        this.stringValue = stringValue;
        this.inputType = InputType.LIST;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public InputType getInputType() {
        return inputType;
    }

    @Override
    public String toString() {
        return switch (inputType) {
            case LIST -> getStringValue();
            case CHECKBOX -> String.valueOf(isBooleanValue());
            case NUMERIC -> String.valueOf(getNumericValue());
        };
    }

    public enum InputType {
        NUMERIC,
        CHECKBOX,
        LIST,
    }
}
