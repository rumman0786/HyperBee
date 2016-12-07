package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author duity
 * @since 12/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        profileController = new ProfileController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();;
    }

    @Test
    public void update() throws Exception {
        User user = new User();

        List<Role> roleList = new ArrayList<>();

        AuthUser authUser = new AuthUser(1, "admin", roleList);

        FileInputStream file1 = new FileInputStream(new File("/home/duity/Desktop/large.png"));
        FileInputStream file2 = new FileInputStream(new File("/home/duity/Desktop/download.png"));

        MockMultipartFile firstFile = new MockMultipartFile("file", "large.png", "multipart/form-data", file1);
        MockMultipartFile secondFile = new MockMultipartFile("coverFile", "download.png", "multipart/form-data", file2);

        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/profile")
                        .file(firstFile)
                        .file(secondFile)
                        .param("designation", "developer")
                        .param("datOfBirth", "")
                        .param("skills", "coding")
                        .param("joiningDate", "")
                        .param("address","abd")
                        .param("contactNo","678")
                        .param("workHistory", "worked atr het")
                        .param("school","ddd")
                        .param("college", "gg")
                        .param("university", "mmm")
                        .param("jobExperienceYears","8")
                        .param("gender","female")
                .sessionAttr("authUser", authUser)
        )
                .andExpect(status().is(200))
                .andExpect(view().name("profile/createprofile"));
    }


}
