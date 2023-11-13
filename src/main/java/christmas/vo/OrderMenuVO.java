package christmas.vo;

public class OrderMenuVO {
    private final String menuName;
    private final int quantity;

    public OrderMenuVO(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return menuName + "-" + quantity;
    }
}
