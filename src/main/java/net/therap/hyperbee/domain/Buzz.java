package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.DisplayStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static net.therap.hyperbee.utils.constant.Constant.*;

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
@NamedQueries({
        @NamedQuery(name = "buzz.getPinned", query="SELECT b FROM Buzz b WHERE b.pinned = :pinned ORDER BY b.id DESC"),
        @NamedQuery(name = "buzz.getAll", query = "FROM Buzz")
})
public class Buzz implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "buzz.message.required")
    @Column(columnDefinition = TEXT_FIELD, nullable = false)
    private String message;

    @Column(name = "buzz_time", columnDefinition = DATE_TIME_FIELD, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date buzzTime;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM, nullable = false)
    private DisplayStatus displayStatus = DisplayStatus.ACTIVE;

    @Column(columnDefinition = "INT(1)", nullable = false)
    private boolean pinned;

    @Column(columnDefinition = "INT(1)", nullable = false)
    private boolean flagged;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Buzz() {
        buzzTime = new Date();
    }

    public Buzz(String message, DisplayStatus displayStatus, boolean pinned) {
        this.message = message;
        this.buzzTime = new Date();
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

    public Date getBuzzTime() {
        return buzzTime;
    }

    public void setBuzzTime(long buzzTime) {
        this.buzzTime.setTime(buzzTime);
    }

    public DisplayStatus getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }

    public boolean isActive() {
        return displayStatus == DisplayStatus.ACTIVE;
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

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
