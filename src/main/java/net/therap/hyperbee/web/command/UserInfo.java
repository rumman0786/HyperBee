package net.therap.hyperbee.web.command;

import net.therap.hyperbee.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayed
 * @since 11/29/16 5:11 PM
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<User> userList;

    private int userId;

    public UserInfo() {
        userList = new ArrayList<>();
    }

    public UserInfo(List<User> userList) {
        this.userList = userList;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<User> getUserList() {

        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
