package net.therap.hyperbee.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author bashir
 * @since 11/21/16
 */
@Entity
@Table(name = "hive")
public class Hive implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    private String name;

    private String description;

    private String imagePath;

    @ManyToMany
    @JoinTable(
            name = "user_hive",
            joinColumns = @JoinColumn(name = "hive_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false)
    )
    private List<User> userList;

    @ManyToMany
    @JoinTable(
            name = "notice_hive",
            joinColumns = @JoinColumn(name = "hive_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "notice_id", nullable = false)
    )
    private List<Notice> noticeList;

    @OneToMany(mappedBy = "hive")
    private List<Post> postList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Notice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
