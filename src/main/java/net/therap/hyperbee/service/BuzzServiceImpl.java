package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public class BuzzServiceImpl implements BuzzService {

    @Autowired
    BuzzDao buzzDao;

    @Autowired
    User userDao;

    //TODO Move to CommonUtils
    public DateTime currentDate() {
        return new DateTime(System.currentTimeMillis());
    }

    @Override
    public boolean createBuzz(Buzz newBuzz) {
        newBuzz.setBuzzTime(currentDate());

        return buzzDao.create(newBuzz);
    }

    @Override
    public boolean createPinnedBuzz(Buzz newBuzz) {
        newBuzz.setBuzzTime(currentDate());
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
