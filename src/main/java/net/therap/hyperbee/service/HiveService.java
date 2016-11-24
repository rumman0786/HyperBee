package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Hive;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveService {

    public void insertHive(Hive hive);

    public List<Hive> retrieveHive();
}
