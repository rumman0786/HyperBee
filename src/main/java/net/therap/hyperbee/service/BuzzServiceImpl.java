package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
@Service
public class BuzzServiceImpl implements BuzzService {

    public static final int LATEST_RANGE = 15;
    public static final int PINNED_RANGE = 5;

    @Autowired
    private BuzzDao buzzDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Utils utils;

    @Override
    @Transactional
    public Buzz saveBuzz(Buzz newBuzz) {
        newBuzz.setBuzzTime(utils.getCurrentTimeMills());

        return buzzDao.saveOrUpdate(newBuzz);
    }

    @Override
    public List<Buzz> getAllBuzz() {
        return buzzDao.getAll();
    }

    @Override
    public Buzz getBuzzById(int buzzId) {
        return buzzDao.getById(buzzId);
    }

    @Override
    public int getActiveCountByUser(int userId) {
        return buzzDao.getActiveCountByUser(userDao.findById(userId));
    }

    @Override
    public int getPinnedCountByUser(int userId) {
        return buzzDao.getPinnedCountByUser(userDao.findById(userId));
    }

    @Override
    public int getFlaggedCountByUser(int userId) {
        return buzzDao.getFlaggedCountByUser(userDao.findById(userId));
    }

    @Override
    public int getActiveCount() {
        return buzzDao.getActiveCount();
    }

    @Override
    public int getInactiveCount() {
        return buzzDao.getInactiveCount();
    }

    @Override
    public int getPinnedCount() {
        return buzzDao.getPinnedCount();
    }

    @Override
    public int getFlaggedCount() {
        return buzzDao.getFlaggedCount();
    }

    @Override
    public List<Buzz> getBuzzByStatus(DisplayStatus displayStatus) {
        return buzzDao.getByDisplayStatus(displayStatus);
    }

    @Override
    public List<Buzz> getLatestBuzz() {
        return buzzDao.getLatest(LATEST_RANGE);
    }

    @Override
    public List<Buzz> getLatestPinnedBuzz() {
        return buzzDao.getLatestPinnedBuzz(PINNED_RANGE);
    }

    @Override
    @Transactional
    public Buzz flagBuzz(Buzz buzzToFlag) {
        buzzToFlag.setFlagged(!buzzToFlag.isFlagged());

        return buzzDao.saveOrUpdate(buzzToFlag);
    }

    @Override
    @Transactional
    public Buzz deactivateBuzz(Buzz buzzToDeactivate) {
        buzzToDeactivate.setDisplayStatus(DisplayStatus.INACTIVE);
        buzzToDeactivate.setFlagged(false);
        buzzToDeactivate.setPinned(false);

        return buzzDao.saveOrUpdate(buzzToDeactivate);
    }

    @Override
    @Transactional
    public Buzz pinBuzz(Buzz buzzToPin) {
        buzzToPin.setPinned(!buzzToPin.isFlagged());

        return buzzDao.saveOrUpdate(buzzToPin);
    }
}
