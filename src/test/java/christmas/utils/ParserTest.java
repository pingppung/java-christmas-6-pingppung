package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.vo.OrderMenuVO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @DisplayName("주문 정보 파싱 - 유효한 입력")
    @Test
    public void testParserOrder_ValidInput() {
        String input = "초코케이크-2, 레드와인-3, 시저샐러드-1";
        List<OrderMenuVO> result = parser.parserOrder(input);
        assertEquals(3, result.size());
    }

    @DisplayName("주문 정보 파싱 - 잘못된 입력")
    @Test
    public void testParserOrder_InvalidInput() {
        String invalidInput = "배-2, 고-abc, 파-1";
        assertThatThrownBy(() ->
                parser.parserOrder(invalidInput)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
