package christmas.vo;

import java.util.List;

public record ReservationVO(int reservationDay, List<OrderMenuVO> reservedMenu,
                            List<EligibleEventVO> eligibleEvents,
                            int totalOrderAmount, int totalDiscountAmount, int paymentAmount) {

    public static ReservationVO create(int reservationDay, List<OrderMenuVO> reservedMenu,
                                       List<EligibleEventVO> eligibleEvents,
                                       int totalOrderAmount, int totalDiscountAmount, int paymentAmount) {
        return new ReservationVO(reservationDay, reservedMenu, eligibleEvents,
                totalOrderAmount, totalDiscountAmount, paymentAmount);
    }
}
