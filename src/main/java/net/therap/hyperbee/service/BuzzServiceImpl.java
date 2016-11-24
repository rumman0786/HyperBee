package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
@Service
public class BuzzServiceImpl implements BuzzService {

    @Autowired
    private BuzzDao buzzDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommonUtils utils;

    @Override
    public boolean createBuzz(Buzz newBuzz) {
        System.out.println(utils.getCurrentTimeMills());
        newBuzz.getBuzzTime().setTimeInMillis(utils.getCurrentTimeMills());

        return buzzDao.save(newBuzz);
    }

    @Override
    public boolean createPinnedBuzz(Buzz newBuzz) {
        newBuzz.getBuzzTime().setTimeInMillis(utils.getCurrentTimeMills());
        newBuzz.setPinned(true);

        return buzzDao.save(newBuzz);
    }

    @Override
    public List<Buzz> retrieveAllBuzz() {
        return buzzDao.getAll();
    }

    @Override
    public Buzz retrieveBuzzById(int buzzId) {
        return buzzDao.getById(buzzId);
    }

    @Override
    public List<Buzz> retrieveBuzzByUser(String username) {
        int userId = userDao.findByUsername(username).getId();

        return buzzDao.getByUser(userId);
    }

    @Override
    public List<Buzz> retrieveBuzzByStatus(DisplayStatus displayStatus) {
        return buzzDao.getByDisplayStatus(displayStatus);
    }

    @Override
    public List<Buzz> retrieveLatestBuzz() {
        return buzzDao.getLatest(5);
    }

    @Override
    public Buzz updateBuzz(Buzz buzzToUpdate) {
        return buzzDao.modify(buzzToUpdate);
    }

    @Override
    public Buzz deleteBuzz(Buzz buzzToDelete) {
        return buzzDao.delete(buzzToDelete);
    }
}
