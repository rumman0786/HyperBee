package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.HiveDao;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
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
    public List<User> getUserListById(List<Integer> idList) {
        List<User> userList = hiveDao.getUserListById(idList);

        return userList;
    }

    @Transactional
    public Hive insertFirstUserToHive(Hive hive, int userId) {
        List<User> userList = hive.getUserList();
        User user = userService.findById(userId);
        userList.add(user);

        hive.setUserList(userList);

        return hive;
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

    @Transactional
    public List<Hive> getHiveListByUserId(int userId) {

        return hiveDao.getHiveListByUserId(userId);
    }

    @Override
    public int getHiveIdByHiveName(String name) {
        return hiveDao.getHiveIdByHiveName(name);
    }

    @Transactional
    public List<User> getUserNotInList(int hiveId) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = hive.getUserList();

        return hiveDao.findUserNotInList(userList);
    }

    @Override
    public Hive findById(int hiveId) {

        return hiveDao.findById(hiveId);
    }

    @Override
    public List<User> getUserInList(int id) {
        Hive hive = retrieveHiveById(id);

        return hive.getUserList();
    }

    @Override
    @Transactional
    public void removeUsersFromHive(int hiveId, List<Integer> userIdList) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.removeUsersFromHive(hive, userList);
    }

    @Override
    @Transactional
    public List<Notice> getNoticeList(int id) {
        Hive hive = retrieveHiveById(id);
        List<Notice> noticeList = hive.getNoticeList();

        return hiveDao.getLastFiveNotice(noticeList, 5);
    }

    @Override
    public List<User> getUserListToRemove(int id) {
        Hive hive = retrieveHiveById(id);
        List<User> userList = hive.getUserList();
        userList.remove(userService.findById(hive.getCreatorId()));

        return userList;
    }
}
