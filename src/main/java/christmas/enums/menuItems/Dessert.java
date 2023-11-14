package christmas.enums.menuItems;

public enum Dessert implements MenuItem {
    CHOCO_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private final String name;
    private final int price;

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return "Dessert";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
