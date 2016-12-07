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

    @Autowired
    private ActivityService activityService;

    @Override
    @Transactional
    public void saveHive(Hive hive) {
        hiveDao.saveHive(hive);
        activityService.archive("Created New Hive");
    }

    public List<User> getUserListById(List<Integer> idList) {
        List<User> userList = hiveDao.getUserListById(idList);

        return userList;
    }

    @Override
    @Transactional
    public Hive saveFirstUserToHive(Hive hive, int userId) {
        List<User> userList = hive.getUserList();
        User user = userService.findById(userId);
        userList.add(user);
        hive.setUserList(userList);
        activityService.archive("Added Hive creator to user List");

        return hive;
    }

    @Override
    public Hive retrieveHiveById(int id) {
        return hiveDao.retrieveHiveById(id);
    }

    @Override
    @Transactional
    public void saveUsersToHive(int hiveId, List<Integer> userIdList) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.addUsersToHive(hive, userList);
        activityService.archive("Saved User to Hive");
    }

    @Override
    public Hive getHiveByHiveName(String name) {
        return hiveDao.getHiveByHiveName(name);
    }

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
    @Transactional
    public void removeUsersFromHive(int hiveId, List<Integer> userIdList) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.removeUsersFromHive(hive, userList);
        activityService.archive("Removed user from Hive");
    }

    @Override
    public List<User> getUserListToRemove(int id) {
        Hive hive = retrieveHiveById(id);
        List<User> userList = hive.getUserList();
        userList.remove(userService.findById(hive.getCreator().getId()));

        return userList;
    }

    @Override
    public List<Notice> getLatestNotice(List<Notice> noticeList) {

        if (noticeList.isEmpty()) {
            return noticeList;
        }
        activityService.archive("View Latest Notice");

        return hiveDao.getLastNNotice(noticeList,5);
    }

    @Override
    public List<Hive> getAllHive() {
        return hiveDao.findAll();
    }

    @Override
    public boolean alreadyExist(Hive hive) {
        return hiveDao.alreadyExist(hive);
    }
}
