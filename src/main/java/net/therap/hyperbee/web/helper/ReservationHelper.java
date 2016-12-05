package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rumman
 * @since 11/30/16
 */
@Component
public class ReservationHelper {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);
    private static final int RESERVATION_TO_DISPLAY_IN_SIDEBAR = 3;

    @Autowired
    private ReservationService reservationService;

    public void persistInSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("cachedReservationList", reservationService.findLatestReservation(RESERVATION_TO_DISPLAY_IN_SIDEBAR));
    }
}
