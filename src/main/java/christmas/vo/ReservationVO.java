package christmas.vo;

import christmas.domain.event.enums.EventDate;
import java.time.LocalDate;
import java.util.List;

public class ReservationVO {
    private final LocalDate reservationDate;
    private final List<OrderMenuVO> orderMenu;
    private final List<EligibleEventVO> eligibleEvents;

    private ReservationVO(LocalDate reservationDate, List<OrderMenuVO> orderMenu,
                          List<EligibleEventVO> eligibleEvents) {
        this.reservationDate = reservationDate;
        this.orderMenu = orderMenu;
        this.eligibleEvents = eligibleEvents;
    }

    public static ReservationVO create(int reservationDay, List<OrderMenuVO> orderMenu,
                                       List<EligibleEventVO> eligibleEvents) {
        LocalDate reservationDate = LocalDate.of(EventDate.CHRISTMAS_DAY.getYear(),
                EventDate.CHRISTMAS_DAY.getMonthValue(), reservationDay);
        return new ReservationVO(reservationDate, orderMenu, eligibleEvents);
    }


}


