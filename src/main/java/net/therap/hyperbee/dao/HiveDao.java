package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveDao {

    public void saveHive(Hive hive);

    public List<User> getUserListById(List<Integer> idList);

    public Hive retrieveHiveById(int id);

    public void insertUsersToHive(Hive hive, List<User> userList);

    public List<Hive> getHiveListByUserId(int userId);

    public int getHiveIdByHiveName(String name);

    public List<User> findUserNotInList(List<User> userList);
}
