package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoha
 * @since 12/6/16
 */
@Component
public class BuzzHelper {

    public static final String BUZZ_TYPE_ACTIVE = "active";
    public static final String BUZZ_TYPE_INACTIVE = "inactive";
    public static final String BUZZ_TYPE_PINNED = "pinned";
    public static final String BUZZ_TYPE_FLAGGED = "flagged";

    @Autowired
    BuzzService buzzService;

    public List<Buzz> getListByType(String type) {
        List<Buzz> buzzList = null;

        switch (type) {
            case BUZZ_TYPE_ACTIVE:
                buzzList = buzzService.getActive();
                break;

            case BUZZ_TYPE_INACTIVE:
                buzzList = buzzService.getInactive();
                break;

            case BUZZ_TYPE_PINNED:
                buzzList = buzzService.getPinned();
                break;

            case BUZZ_TYPE_FLAGGED:
                buzzList = buzzService.getFlagged();
                break;

            default:
                break;
        }

        return buzzList;
    }

    public List<Buzz> getListByTypeAndUser(String type, int userId) {
        List<Buzz> buzzList = null;

        switch (type) {
            case BUZZ_TYPE_ACTIVE:
                buzzList = buzzService.getActiveByUser(userId);
                break;

            case BUZZ_TYPE_PINNED:
                buzzList = buzzService.getPinnedByUser(userId);
                break;

            case BUZZ_TYPE_FLAGGED:
                buzzList = buzzService.getFlaggedByUser(userId);
                break;

            default:
                break;
        }

        return buzzList;
    }
}
