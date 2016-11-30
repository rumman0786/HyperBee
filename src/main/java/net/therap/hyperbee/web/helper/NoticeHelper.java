package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author rumman
 * @since 11/29/16
 */
@Component
public class NoticeHelper {

    @Autowired
    private NoticeService noticeService;

    private static final int NOTICE_TO_DISPLAY_IN_SIDEBAR = 5;

    public void persistInSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("cachedNoticeList", noticeService.findLatestNotices(NOTICE_TO_DISPLAY_IN_SIDEBAR));
    }

}
