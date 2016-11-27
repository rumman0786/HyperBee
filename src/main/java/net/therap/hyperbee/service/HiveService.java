package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveService {

    public void insertHive(Hive hive);

    public List<Hive> retrieveHive();

    public List<User> getUserListById(List<Integer> idList);

    public void addUserToHive(List<Integer> userIdList);

    public Hive retrieveHiveById(int id);

    public void insertUsersToHive(int hiveId, List<Integer> userIdList);

    public List<Hive> retrieveHiveByUserId(int userId);

    public List<Hive> getHiveListByUserId(int userId);
}
