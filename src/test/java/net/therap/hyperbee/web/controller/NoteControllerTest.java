package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.NoteService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.utils.constant.Url;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.NoteDateTimeValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Url.DONE_URL;
import static net.therap.hyperbee.utils.constant.Url.NOTE_SAVE_URL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private Utils utils;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        noteController = new NoteController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public void insertTest() throws Exception {

        AuthUser authUser = new AuthUser();
        authUser.setUsername("admin");
        authUser.setId(1);

        mockMvc.perform(post(NOTE_SAVE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "Mock Note Title")
                .param("description", "Mock Note Description")
                .param("dateRemindString", "")
                .sessionAttr("authUser", authUser)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("note/save"))
        ;
    }
}
