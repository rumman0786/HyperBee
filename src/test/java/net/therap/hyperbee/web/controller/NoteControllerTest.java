package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.NoteService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoteDateTimeValidator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author bashir
 * @since 12/1/16
 */
public class NoteControllerTest {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @Mock
    private ActivityService activityService;

    @Mock
    private SessionHelper sessionHelper;

    @Mock
    private NoteHelper noteHelper;

    @Mock
    private NoteDateTimeValidator noteDateTimeValidator;

    @Mock
    private Utils utils;

    public void setUp() throws Exception{
        noteController = new NoteController();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void insertTest(){

    }
}
