package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveDao {

    public void saveHive(Hive hive);

    public List<Hive> retrieveHive();
}
