package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.utils.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rumman
 * @since 11/29/16
 */
@Component
public class NoticeHelper {

    private static final Logger log = LogManager.getLogger(NoticeHelper.class);

    private static final int NOTICE_TO_DISPLAY_IN_SIDEBAR = 3;
    private static final int GROUP_ALL_ID = 1;
    private static final String NOTICE_CACHE_SUCCESS = "Notice Cache Updated";

    @Autowired
    private NoticeService noticeService;

    public void updateNoticeCache() {
        WebUtils.setSessionAttribute("cachedNoticeList",
                noticeService.getNoticeListByHiveId(GROUP_ALL_ID, NOTICE_TO_DISPLAY_IN_SIDEBAR));

        log.trace(NOTICE_CACHE_SUCCESS);
    }
}
