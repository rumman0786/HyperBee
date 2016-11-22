package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.NotePriority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static net.therap.hyperbee.utils.constant.DomainConstant.*;

/**
 * @author bashir
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@Table(name = "note")
public class Note implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    private String type;

    private String title;

    private String description;

    @Column(name = "date_created", columnDefinition = DATE_TIME_FIELD)
    private Calendar dateCreated;

    @Column(name = "date_remind", columnDefinition = DATE_TIME_FIELD)
    private Calendar dateRemind;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = PRIORITY_ENUM)
    private NotePriority priority;

    @Enumerated
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {
        this.dateCreated = new GregorianCalendar();
        this.dateRemind = new GregorianCalendar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateRemind() {
        return dateRemind;
    }

    public void setDateRemind(Calendar dateRemind) {
        this.dateRemind = dateRemind;
    }

    public NotePriority getPriority() {
        return priority;
    }

    public void setPriority(NotePriority priority) {
        this.priority = priority;
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
}
