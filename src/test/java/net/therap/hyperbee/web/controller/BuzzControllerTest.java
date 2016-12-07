package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.BuzzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author zoha
 * @since 12/6/16
 */
@RunWith(MockitoJUnitRunner.class)
public class BuzzControllerTest {

    private static final Logger log = LogManager.getLogger(BuzzControllerTest.class);

    @Mock
    ActivityService activityService;

    @Mock
    BuzzService buzzService;

    @InjectMocks
    BuzzController buzzController;

    @Test
    public void testSaveBuzz() {
        Buzz buzz = new Buzz("Hello World!", DisplayStatus.ACTIVE, false);

        when(buzzService.saveBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.saveBuzz(buzz), buzz);

        log.debug("Test for Buzz Save: Success");
    }

    @Test
    public void testGetAllBuzz() {
        List<Buzz> buzzList = Arrays.asList(new Buzz[]{new Buzz(), new Buzz()});

        when(buzzService.getAllBuzz()).thenReturn(buzzList);
        assertEquals(2, buzzService.getAllBuzz().size());

        log.debug("Test for Get All Buzz: Success");
    }

    @Test
    public void testGetBuzzById() {
        List<Buzz> buzzList = new ArrayList<>();

        Buzz buzz1 = new Buzz("Message One", DisplayStatus.ACTIVE, false);
        buzzList.add(buzz1);

        Buzz buzz2 = new Buzz("Message Two", DisplayStatus.ACTIVE, false);
        buzzList.add(buzz2);

        when(buzzService.getBuzzById(0)).thenReturn(buzz1);
        assertEquals(buzzService.getBuzzById(0), buzz1);

        log.debug("Test for Get Buzz By ID: Success");
    }

    @Test
    public void testFlagBuzz() {
        Buzz buzz = new Buzz();
        buzz.setFlagged(true);

        when(buzzService.flagBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.flagBuzz(buzz), buzz);

        log.debug("Test for Flagging Buzz: Success");
    }

    @Test
    public void testPinBuzz() {
        Buzz buzz = new Buzz();
        buzz.setPinned(true);

        when(buzzService.pinBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.pinBuzz(buzz), buzz);

        log.debug("Test for Pinning Buzz: Success");
    }

    @Test
    public void deactivateBuzz() {
        Buzz buzz = new Buzz();
        buzz.setDisplayStatus(DisplayStatus.INACTIVE);

        when(buzzService.deactivateBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.deactivateBuzz(buzz), buzz);

        log.debug("Test for Deactivating Buzz: Success");
    }
}
