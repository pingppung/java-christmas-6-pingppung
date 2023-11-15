package christmas.services.date;

import christmas.constants.ErrorMessage;

public class DateValidator {
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;

    public int validateDateNonNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_MESSAGE.message);
        }
    }

    public void validateDateRange(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_MESSAGE.message);
        }
    }
}
