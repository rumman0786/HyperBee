package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.service.ReservationService;
import net.therap.hyperbee.utils.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rumman
 * @since 11/30/16
 */
@Component
public class ReservationHelper {

    private static final Logger log = LogManager.getLogger(ReservationHelper.class);

    private static final int RESERVATION_TO_DISPLAY_IN_SIDEBAR = 3;
    private static final String RESERVATION_CACHE_SUCCESS = "Reservation Cache Updated";

    @Autowired
    private ReservationService reservationService;

    public void updateReservationCache() {
        WebUtils.setSessionAttribute("cachedReservationList",
                reservationService.findLatestReservation(RESERVATION_TO_DISPLAY_IN_SIDEBAR));

        log.trace(RESERVATION_CACHE_SUCCESS);
    }
}
