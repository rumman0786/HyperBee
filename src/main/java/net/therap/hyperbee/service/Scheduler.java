package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author bashir
 * @since 11/26/16
 */
@Service
@EnableScheduling
public class Scheduler {

    private static final Logger log = LogManager.getLogger(Scheduler.class);

    @Autowired
    NoteDao noteDao;

    @Scheduled(cron = "0 0 0 ? * *")
    public void scheduleNoteExpireJob() {

        int noteArchiveCount = noteDao.markExpiredNoteAsInactive();
        log.debug("Note Archived:{}",noteArchiveCount);
    }
}
