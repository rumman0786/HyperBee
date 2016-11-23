package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.HiveDao;
import net.therap.hyperbee.domain.Hive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author azim
 * @since 11/22/16
 */
@Service
public class HiveServiceImpl implements HiveService {

    @Autowired
    private HiveDao hiveDao;

    @Transactional
    public void insertHive(Hive hive){
        hiveDao.saveHive(hive);
    }
}
