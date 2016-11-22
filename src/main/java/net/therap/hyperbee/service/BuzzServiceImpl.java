package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
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
    BuzzDao buzzDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CommonUtils utils;

    @Override
    public boolean createBuzz(Buzz newBuzz) {
        newBuzz.getBuzzTime().setTimeInMillis(utils.getCurrentTimeMills());

        return buzzDao.create(newBuzz);
    }

    @Override
    public boolean createPinnedBuzz(Buzz newBuzz) {
        newBuzz.getBuzzTime().setTimeInMillis(utils.getCurrentTimeMills());
        newBuzz.setPinned(true);

        return buzzDao.create(newBuzz);
    }

    @Override
    public List<Buzz> retrieveAllBuzz() {
        return buzzDao.retrieveAll();
    }

    @Override
    public Buzz retrieveBuzzById(int buzzId) {
        return buzzDao.retrieveById(buzzId);
    }

    @Override
    //TODO upon receiving UserDao
    public List<Buzz> retrieveBuzzByUser(String username) {
        //userDao.retrieveByName();
        int userId = -1;
        return buzzDao.retrieveByUser(userId);
    }

    @Override
    public List<Buzz> retrieveBuzzByStatus(DisplayStatus displayStatus) {
        return buzzDao.retrieveByDisplayStatus(displayStatus);
    }

    @Override
    public Buzz updateBuzz(Buzz buzzToUpdate) {
        return buzzDao.update(buzzToUpdate);
    }

    @Override
    public Buzz deleteBuzz(Buzz buzzToDelete) {
        return buzzDao.delete(buzzToDelete);
    }
}
