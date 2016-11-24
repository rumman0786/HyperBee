package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.HiveDao;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
@Service
public class HiveServiceImpl implements HiveService {

    @Autowired
    private HiveDao hiveDao;

    @Autowired
    private UserService userService;

    @Transactional
    public void insertHive(Hive hive) {
        hiveDao.saveHive(hive);
    }

    @Transactional
    public List<Hive> retrieveHive() {

        return hiveDao.retrieveHive();
    }

    @Transactional
    public List<User> getUserList() {
        List<User> dishList = hiveDao.getUserList();

        return dishList;
    }

    @Transactional
    public List<User> getUserListById(List<Integer> idList) {
        List<User> userList = hiveDao.getUserListById(idList);

        return userList;
    }

    @Transactional
    public void addUserToHive(List<Integer> userIdList) {

    }

    @Override
    public Hive retrieveHiveById(int id) {

        return hiveDao.retrieveHiveById(id);
    }

    @Override
    @Transactional
    public void insertUsersToHive(int hiveId, List<Integer> userIdList) {

        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.insertUsersToHive(hive, userList);

    }

    @Override
    @Transactional
    public List<Hive> retrieveHiveByUserId(int userId) {

        User user = userService.findById(userId);

        return user.getHiveList();
    }

    @Transactional
    public List<Hive> getHiveListByUserId(int userId) {

        return hiveDao.getHiveListByUserId(userId);
    }

}
