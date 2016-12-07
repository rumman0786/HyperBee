package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.HiveDao;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.Post;
import net.therap.hyperbee.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author azim
 * @since 12/6/16
 */
public class HiveServiceTest {

    @InjectMocks
    private HiveService hiveService;

    @Mock
    private HiveDao hiveDao;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        hiveService = new HiveServiceImpl();
        MockitoAnnotations.initMocks(this);
        when(hiveDao.retrieveHiveById(1)).thenReturn(createDummyHive());
    }

    @Test
    public void retrieveHiveByIdTest(){
        assertEquals(1, hiveDao.retrieveHiveById(1).getUserList().size());
    }

    private Hive createDummyHive() {
        Hive hive = new Hive();
        hive.setName("Test Hive");
        hive.setDescription("Test Hive For Mock Test");
        hive.setImagePath("TestHiveCover.png");

        User user = new User();
        user.setId(1);
        hive.setCreator(user);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        hive.setUserList(userList);

        Post post = new Post();
        post.setId(1);
        List<Post> postList = new ArrayList<>();
        postList.add(post);
        hive.setPostList(postList);

        Notice notice = new Notice();
        notice.setId(1);
        List<Notice> noticeList = new ArrayList<>();
        noticeList.add(notice);
        hive.setNoticeList(noticeList);

        return hive;
    }


}
