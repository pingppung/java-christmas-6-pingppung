package christmas.dto;

public class EventParamsDTO {
    private final int dday;
    private final int dessertCount;
    private final int mainDishCount;

    public EventParamsDTO(int dday, int dessertCount, int mainDishCount) {
        this.dday = dday;
        this.dessertCount = dessertCount;
        this.mainDishCount = mainDishCount;
    }

    public int getDday() {
        return dday;
    }

    public int getDessertCount() {
        return dessertCount;
    }

    public int getMainDishCount() {
        return mainDishCount;
    }
}