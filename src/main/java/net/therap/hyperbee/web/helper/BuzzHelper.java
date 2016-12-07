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

    @Autowired
    BuzzService buzzService;

    public List<Buzz> getListByType(String type) {
        List<Buzz> buzzList = null;

        switch (type) {
            case "active":
                buzzList = buzzService.getActive();
                break;

            case "inactive":
                buzzList = buzzService.getInactive();
                break;

            case "pinned":
                buzzList = buzzService.getPinned();
                break;

            case "flagged":
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
            case "active":
                buzzList = buzzService.getActiveByUser(userId);
                break;

            case "pinned":
                buzzList = buzzService.getPinnedByUser(userId);
                break;

            case "flagged":
                buzzList = buzzService.getFlaggedByUser(userId);
                break;

            default:
                break;
        }

        return buzzList;
    }
}
