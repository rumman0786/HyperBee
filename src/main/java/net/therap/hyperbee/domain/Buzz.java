package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.hyperbee.domain.DomainConstant.DISPLAY_STATUS_ENUM;
import static net.therap.hyperbee.domain.DomainConstant.DISPLAY_STATUS_FIELD;

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

    @Column(length = 160)
    private String message;

    @Column(name = "buzz_time", nullable = false)
    private DateTime buzzTime;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM, nullable = false)
    private DisplayStatus displayStatus;

    @Column(nullable = false)
    private int pinned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public int getPinned() {
        return pinned;
    }

    public void setPinned(int pinned) {
        this.pinned = pinned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
