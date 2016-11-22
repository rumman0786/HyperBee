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
public class BuzzServiceImpl {

    @Autowired
    BuzzDao buzzDao;

    @Autowired
    User userDao;

    //TODO Move to CommonUtils
    public DateTime currentDate() {
        return new DateTime(System.currentTimeMillis());
    }

    public boolean createBuzz(Buzz newBuzz) {
        newBuzz.setBuzzTime(currentDate());

        return buzzDao.create(newBuzz);
    }

    public boolean createPinnedBuzz(Buzz newBuzz) {
        newBuzz.setBuzzTime(currentDate());
        newBuzz.setPinned(true);

        return buzzDao.create(newBuzz);
    }

    public List<Buzz> retrieveAll() {
        return buzzDao.retrieveAll();
    }

    public Buzz retrieveById(int buzzId) {
        return buzzDao.retrieveById(buzzId);
    }


    //TODO upon receiving UserDao
    public List<Buzz> retrieveByUser(String username) {
        //userDao.retrieveByName();
        int userId = -1;
        return buzzDao.retrieveByUser(userId);
    }

    public List<Buzz> retrieveByDisplayStatus(DisplayStatus displayStatus) {
        return buzzDao.retrieveByDisplayStatus()
    }
}
