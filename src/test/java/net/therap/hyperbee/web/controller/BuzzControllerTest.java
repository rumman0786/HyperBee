package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.BuzzHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.BuzzValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static net.therap.hyperbee.utils.constant.Url.BUZZ_BASE_URL;
import static net.therap.hyperbee.utils.constant.Url.USER_DASHBOARD_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author zoha
 * @since 12/6/16
 */
@RunWith(MockitoJUnitRunner.class)
public class BuzzControllerTest {

    //Log Message Constants
    public static final String TEST_FOR_BUZZ_SAVE_SUCCESS = "Test for Buzz Save: Success";
    public static final String TEST_FOR_BUZZ_SAVE_ERROR_SUCCESS = "Test for Buzz Save Encountered Error: Success";
    public static final String TEST_FOR_GET_LATEST_BUZZ_SUCCESS = "Test for Get Latest Buzz: Success";
    public static final String TEST_FOR_GET_ALL_BUZZ_SUCCESS = "Test for Get All Buzz: Success";
    public static final String TEST_FOR_GET_BUZZ_BY_TYPE_SUCCESS = "Test for Get Buzz By Type: Success";
    public static final String TEST_FOR_FLAGGING_BUZZ_SUCCESS = "Test for Flagging Buzz: Success";
    public static final String TEST_FOR_PINNING_BUZZ_SUCCESS = "Test for Pinning Buzz: Success";
    public static final String TEST_FOR_DEACTIVATING_BUZZ_SUCCESS = "Test for Deactivating Buzz: Success";

    //URL Constants
    public static final String BUZZ_LIST_BY_TYPE_URL = "/buzzListByType";
    public static final String BUZZ_HISTORY_URL = "/buzzHistory";

    //Attribute Constants
    public static final String BUZZ_LIST_ATTR_NAME = "buzzList";
    public static final String PINNED_BUZZ_LIST_ATTR_NAME = "pinnedBuzzList";
    public static final String TYPE_ACTIVE = "active";
    public static final String NEW_BUZZ_ATTR_NAME = "newBuzz";

    private static final Logger log = LogManager.getLogger(BuzzControllerTest.class);

    @InjectMocks
    BuzzController buzzController;

    @Mock
    private BuzzService buzzService;

    @Mock
    private UserService userService;

    @Mock
    private BuzzHelper buzzHelper;

    @Mock
    private SessionHelper sessionHelper;

    @Mock
    private BuzzValidator buzzValidator;

    private Buzz testBuzz;

    private ModelMap model;

    private User user;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Before
    public void setup() {
        testBuzz = createBuzz();
        model = new ModelMap();
        user = new User();
    }

    @Test
    public void test_getLatestBuzz() {
        buzzController.viewLatestBuzz(model);
        assertNotEquals(model.get(BUZZ_LIST_ATTR_NAME), null);
        assertNotEquals(model.get(PINNED_BUZZ_LIST_ATTR_NAME), null);

        log.trace(TEST_FOR_GET_LATEST_BUZZ_SUCCESS);
    }

    @Test
    public void test_getBuzzByType() {
        when(sessionHelper.getAuthUserFromSession()).thenReturn(user.getAuthUser());

        assertEquals(buzzController.viewBuzzByType(TYPE_ACTIVE, model),
                BUZZ_BASE_URL + BUZZ_LIST_BY_TYPE_URL);
        assertNotEquals(model.get(BUZZ_LIST_ATTR_NAME), null);
        assertEquals(model.get("type"), "Active");

        log.trace(TEST_FOR_GET_BUZZ_BY_TYPE_SUCCESS);
    }

    @Test
    public void test_sendBuzz() {
        when(sessionHelper.getAuthUserFromSession()).thenReturn(user.getAuthUser());

        assertEquals(buzzController.sendBuzz(testBuzz, bindingResult, redirectAttributes, model),
                Utils.redirectTo(USER_DASHBOARD_URL));
        assertEquals(model.containsAttribute(NEW_BUZZ_ATTR_NAME), true);

        log.trace(TEST_FOR_BUZZ_SAVE_SUCCESS);
    }

    @Test
    public void test_sendBuzzProducesError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals(buzzController.sendBuzz(testBuzz, bindingResult, redirectAttributes, model),
                Utils.redirectTo(USER_DASHBOARD_URL));
        assertNotEquals(model.containsAttribute(NEW_BUZZ_ATTR_NAME), true);

        log.trace(TEST_FOR_BUZZ_SAVE_ERROR_SUCCESS);
    }

    @Test
    public void test_flagBuzz() {
        when(buzzService.getBuzzById(anyInt())).thenReturn(testBuzz);
        assertEquals(buzzController.flagBuzz(anyInt()), Utils.redirectTo(USER_DASHBOARD_URL));

        log.trace(TEST_FOR_FLAGGING_BUZZ_SUCCESS);
    }

    @Test
    public void test_pinBuzz() {
        when(buzzService.getBuzzById(anyInt())).thenReturn(testBuzz);
        assertEquals(buzzController.pinBuzz(anyInt()), Utils.redirectTo(USER_DASHBOARD_URL));

        log.trace(TEST_FOR_PINNING_BUZZ_SUCCESS);
    }

    @Test
    public void test_deactivateBuzz() {
        when(buzzService.getBuzzById(anyInt())).thenReturn(testBuzz);
        assertEquals(buzzController.deactivateBuzz(anyInt()), Utils.redirectTo(USER_DASHBOARD_URL));

        log.trace(TEST_FOR_DEACTIVATING_BUZZ_SUCCESS);
    }

    @Test
    public void test_getBuzzHistory() {
        assertEquals(buzzController.viewBuzzHistory(0, 20, model), BUZZ_BASE_URL + BUZZ_HISTORY_URL);
        assertEquals(model.containsAttribute("prev"), true);
        assertEquals(model.containsAttribute("next"), true);
        assertEquals(model.containsAttribute(BUZZ_LIST_ATTR_NAME), true);

        log.trace(TEST_FOR_GET_ALL_BUZZ_SUCCESS);
    }

    private static Buzz createBuzz() {
        return new Buzz();
    }
}
