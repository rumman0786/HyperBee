package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static net.therap.hyperbee.utils.constant.DomainConstant.*;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @author zoha
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
    private Calendar dateCreated;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private Calendar dateExpired;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "noticeList")
    private List<Hive> hiveList;

    public Notice() {
        this.dateCreated = new GregorianCalendar();
        this.dateExpired = new GregorianCalendar();
    }

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

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateExpired(Calendar dateExpired) {
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
