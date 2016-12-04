package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveService {

    void saveHive(Hive hive);

    Hive retrieveHiveById(int id);

    void saveUsersToHive(int hiveId, List<Integer> userIdList);

    Hive saveFirstUserToHive(Hive hive, int userId);

    Hive getHiveByHiveName(String name);

    List<User> getUserNotInList(int hiveId);

    Hive findById(int hiveId);

    void removeUsersFromHive(int hiveId, List<Integer> userIdList);

    List<User> getUserListToRemove(int id);

    List<Notice> getLatestNotice(List<Notice> noticeList);

    List<Hive> getAllHive();
}
