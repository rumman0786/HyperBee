package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.hyperbee.domain.constant.DomainConstant.*;

/**
 * @author bashir
 * @author zoha
 * @author rayed
 * @author duity
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "buzz")
public class Buzz implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = TEXT_FIELD, nullable = false)
    private String message;

    @Column(name = "buzz_time", columnDefinition = DATE_TIME_FIELD, nullable = false)
    private DateTime buzzTime;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM, nullable = false)
    private DisplayStatus displayStatus;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private boolean pinned;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Buzz(String message, DateTime buzzTime, DisplayStatus displayStatus, boolean pinned) {
        this.message = message;
        this.buzzTime = buzzTime;
        this.displayStatus = displayStatus;
        this.pinned = pinned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getBuzzTime() {
        return buzzTime;
    }

    public void setBuzzTime(DateTime buzzTime) {
        this.buzzTime = buzzTime;
    }

    public DisplayStatus getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
