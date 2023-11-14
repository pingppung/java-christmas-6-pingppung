package christmas.enums.menuItems;

public enum Beverage implements MenuItem {
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return "Beverage";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
