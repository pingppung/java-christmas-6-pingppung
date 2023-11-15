package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.services.date.DateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateValidatorTest {
    private DateValidator dateValidator;

    @BeforeEach
    void setUp() {
        dateValidator = new DateValidator();
    }

    @DisplayName("유효한 날짜 문자열을 입력했을 때 정상적으로 처리")
    @Test
    void validDateNonNumeric() {
        String validDateString = "25";
        int expectedDay = 25;

        int result = dateValidator.validateDateNonNumeric(validDateString);

        assertEquals(expectedDay, result);
    }

    @DisplayName("유효하지 않은 날짜 문자열을 입력했을 때 예외 발생")
    @Test
    void invalidDateNonNumeric() {
        String invalidDateString = "wooteco";
        assertThatThrownBy(() ->
                dateValidator.validateDateNonNumeric(invalidDateString)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    }

    @DisplayName("유효하지 않은 날짜 범위를 입력했을 때 예외 발생")
    @Test
    void invalidDateRange() {
        int invalidDay = 35;
        assertThatThrownBy(() ->
                dateValidator.validateDateRange(invalidDay)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("유효한 날짜 범위를 정상적으로 처리")
    @Test
    void validDateRange() {
        int validDay = 10;
        assertDoesNotThrow(() -> dateValidator.validateDateRange(validDay));
    }
}
