package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static net.therap.hyperbee.domain.constant.DomainConstant.*;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    private String title;

    private String description;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime dateCreated;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime dateExpired;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "noticeList")
    private List<Hive> hiveList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(DateTime dateExpired) {
        this.dateExpired = dateExpired;
    }

    public DisplayStatus getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hive> getHiveList() {
        return hiveList;
    }

    public void setHiveList(List<Hive> hiveList) {
        this.hiveList = hiveList;
    }
}
