package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.BuzzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    //Log Message Constants
    public static final String TEST_FOR_BUZZ_SAVE_SUCCESS = "Test for Buzz Save: Success";
    public static final String TEST_FOR_GET_ALL_BUZZ_SUCCESS = "Test for Get All Buzz: Success";
    public static final String TEST_FOR_GET_BUZZ_BY_ID_SUCCESS = "Test for Get Buzz By ID: Success";
    public static final String TEST_FOR_FLAGGING_BUZZ_SUCCESS = "Test for Flagging Buzz: Success";
    public static final String TEST_FOR_PINNING_BUZZ_SUCCESS = "Test for Pinning Buzz: Success";
    public static final String TEST_FOR_DEACTIVATING_BUZZ_SUCCESS = "Test for Deactivating Buzz: Success";

    private static final Logger log = LogManager.getLogger(BuzzControllerTest.class);

    @Mock
    ActivityService activityService;

    @Mock
    BuzzService buzzService;

    @InjectMocks
    BuzzController buzzController;

    @Test
    public void testSaveBuzz() {
        Buzz buzz = createBuzz();

        when(buzzService.saveBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.saveBuzz(buzz), buzz);

        log.debug(TEST_FOR_BUZZ_SAVE_SUCCESS);
    }

    @Test
    public void testGetAllBuzz() {
        List<Buzz> buzzList = createBuzzList();

        when(buzzService.getAllBuzz()).thenReturn(buzzList);
        assertEquals(2, buzzService.getAllBuzz().size());

        log.debug(TEST_FOR_GET_ALL_BUZZ_SUCCESS);
    }

    @Test
    public void testGetBuzzById() {
        List<Buzz> buzzList = createBuzzList();

        when(buzzService.getBuzzById(0)).thenReturn(buzzList.get(0));
        assertEquals(buzzService.getBuzzById(0), buzzList.get(0));

        log.debug(TEST_FOR_GET_BUZZ_BY_ID_SUCCESS);
    }

    @Test
    public void testFlagBuzz() {
        Buzz buzz = createBuzz();

        when(buzzService.flagBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.flagBuzz(buzz), buzz);

        log.debug(TEST_FOR_FLAGGING_BUZZ_SUCCESS);
    }

    @Test
    public void testPinBuzz() {
        Buzz buzz = createBuzz();

        when(buzzService.pinBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.pinBuzz(buzz), buzz);

        log.debug(TEST_FOR_PINNING_BUZZ_SUCCESS);
    }

    @Test
    public void deactivateBuzz() {
        Buzz buzz = createBuzz();

        when(buzzService.deactivateBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.deactivateBuzz(buzz), buzz);

        log.debug(TEST_FOR_DEACTIVATING_BUZZ_SUCCESS);
    }

    private static Buzz createBuzz() {
        return new Buzz();
    }

    private static List<Buzz> createBuzzList() {
        return Arrays.asList(createBuzz(), createBuzz());
    }
}
