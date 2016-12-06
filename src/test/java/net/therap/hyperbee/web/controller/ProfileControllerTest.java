package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author duity
 * @since 12/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        profileController = new ProfileController();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void update() throws Exception {
        User user=new User();

        when(userService.findByUsername(any(String.class))).thenReturn(user);

        this.mockMvc.perform(get("/profile/stalk/admin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("User", user))
                .andExpect(model().attribute("profile", user.getProfile()));
    }


}
