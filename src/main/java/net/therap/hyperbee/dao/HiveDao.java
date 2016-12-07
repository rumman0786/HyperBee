package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveDao {

    void saveHive(Hive hive);

    List<User> getUserListById(List<Integer> idList);

    Hive retrieveHiveById(int id);

    void addUsersToHive(Hive hive, List<User> userList);

    Hive getHiveByHiveName(String name);

    List<User> findUserNotInList(List<User> userList);

    Hive findById(int hiveId);

    void removeUsersFromHive(Hive hive, List<User> userList);

    List<Notice> getLastFiveNotice(List<Notice> noticeList, int range);

    List<Hive> findAll();

    boolean alreadyExist(Hive hive);
}
