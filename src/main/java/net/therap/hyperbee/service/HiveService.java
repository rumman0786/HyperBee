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

    public Hive retrieveHiveById(int id);

    public void insertUsersToHive(int hiveId, List<Integer> userIdList);

    public Hive insertFirstUserToHive(Hive hive, int userId);

    public List<Hive> getHiveListByUserId(int userId);

    public int getHiveIdByHiveName(String name);

    public List<User> getUserNotInList(int hiveId);

    public Hive findById(int hiveId);

    public List<User> getUserInList(int id);
}
