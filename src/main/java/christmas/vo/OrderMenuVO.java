package christmas.vo;

public record OrderMenuVO(String menuName, int quantity) {

    @Override
    public String toString() {
        return menuName + " " + quantity + "개";
    }
}