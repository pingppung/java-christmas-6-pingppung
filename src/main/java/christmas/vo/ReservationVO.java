package christmas.vo;

import christmas.dto.EventResultDTO;
import java.util.List;

public record ReservationVO(int reservationDay, List<OrderMenuVO> reservedMenu,
                            EventResultDTO eventResultDTO,
                            int totalOrderAmount, int paymentAmount) {

    public static ReservationVO create(int reservationDay, List<OrderMenuVO> reservedMenu,
                                       EventResultDTO eventResultDTO,
                                       int totalOrderAmount, int paymentAmount) {
        return new ReservationVO(reservationDay, reservedMenu, eventResultDTO,
                totalOrderAmount, paymentAmount);
    }
}
