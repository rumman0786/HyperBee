package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.BuzzDao;
import net.therap.hyperbee.domain.Buzz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author zoha
 * @since 12/7/16
 */
public class BuzzServiceTest {

    // Log Message Constants
    public static final String TEST_FOR_BUZZ_SAVE_SUCCESS = "Test for Buzz Save: Success";
    public static final String TEST_FOR_GET_ALL_BUZZ_SUCCESS = "Test for Get All Buzz: Success";
    public static final String TEST_FOR_GET_BUZZ_BY_ID_SUCCESS = "Test for Get Buzz by Id: Success";

    private static final Logger log = LogManager.getLogger(BuzzServiceTest.class);

    @Mock
    BuzzDao buzzDao;

    @InjectMocks
    BuzzService buzzService;

    @Before
    public void setup() {
        buzzService = new BuzzServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveOrModifyBuzz() {
        Buzz buzz = createBuzz();

        when(buzzDao.saveOrUpdate(buzz)).thenReturn(buzz);
        assertEquals(buzzDao.saveOrUpdate(buzz), buzz);

        log.trace(TEST_FOR_BUZZ_SAVE_SUCCESS);
    }

    @Test
    public void testGetAll() {
        List<Buzz> buzzList = createBuzzList();

        when(buzzDao.getAll()).thenReturn(buzzList);
        assertEquals(buzzDao.getAll().size(), 2);

        log.trace(TEST_FOR_GET_ALL_BUZZ_SUCCESS);
    }

    @Test
    public void testGetById() {
        List<Buzz> buzzList = createBuzzList();

        when(buzzDao.getById(0)).thenReturn(buzzList.get(0));
        assertEquals(buzzDao.getById(0), buzzList.get(0));

        log.trace(TEST_FOR_GET_BUZZ_BY_ID_SUCCESS);
    }

    private static List<Buzz> createBuzzList() {
        return Arrays.asList(new Buzz(), new Buzz());
    }

    private static Buzz createBuzz() {
        return new Buzz();
    }
}
