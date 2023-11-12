package christmas.enums;

public enum ChristmasDate {
    YEAR(2023),
    MONTH(12),
    DAY(25);
    private final int value;

    ChristmasDate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
