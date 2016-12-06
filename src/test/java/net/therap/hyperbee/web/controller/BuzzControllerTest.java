package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author zoha
 * @since 12/4/16
 */
@RunWith(MockitoJUnitRunner.class)
public class BuzzControllerTest {

    @Mock
    BuzzDao buzzDao;

    @Mock
    BuzzService buzzService;

    @InjectMocks
    BuzzController buzzController;

    @Test
    public void testFlagBuzz() throws Exception {
        Buzz buzz = new Buzz();
        buzz.setFlagged(true);

        Mockito.when(buzzService.flagBuzz(buzz)).thenReturn(buzz);
        assertEquals(buzzService.flagBuzz(buzz), buzz);
    }
}
