package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Hive;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveService {

    public void insertHive(Hive hive);

//    public List<Hive> retrieveHive();

//    public List<User> getUserListById(List<Integer> idList);

//    public void insertFirstUserToHive(int userId);

    public Hive retrieveHiveById(int id);

    public void insertUsersToHive(int hiveId, List<Integer> userIdList);

//    public List<Hive> retrieveHiveByUserId(int userId);

    public Hive insertFirstUserToHive(Hive hive, int userId);

    public List<Hive> getHiveListByUserId(int userId);

    public int getHiveIdByHiveName(String name);
}
