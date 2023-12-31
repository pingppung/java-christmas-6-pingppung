package christmas.constants;

public enum ErrorMessage {
    INVALID_ORDER_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MAX_ORDER_LIMIT_EXCEEDED("주문은 최대 20개까지 가능합니다. 다시 입력해 주세요."),
    INVALID_DATE_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final static String PREFIX = "[ERROR] ";
    public final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }
}
