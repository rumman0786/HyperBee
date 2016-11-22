package net.therap.hyperbee.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.hyperbee.domain.constant.DomainConstant.DATE_TIME_FIELD;

/**
 * @author bashir
 * @author rayed
 * @author duity
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime activity_time;

    private String summary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(DateTime activity_time) {
        this.activity_time = activity_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
