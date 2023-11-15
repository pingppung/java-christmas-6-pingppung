package christmas.view;

public class OutputView {
    private static final String MONEY_UNIT = "원";
    private static final String ITEM_QUANTITY_FORMAT = "%s %d개";

    public void printFormattedMessage(String output) {
        System.out.println(output);
    }

    public void displayReservationResult(String preview) {
        System.out.println(preview);
    }

}
