package net.therap.hyperbee.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static net.therap.hyperbee.utils.constant.DomainConstant.DATE_TIME_FIELD;

/**
 * @author bashir
 * @author rayed
 * @author duity
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private Calendar activity_time;

    private String summary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Activity() {
        this.activity_time = new GregorianCalendar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(Calendar activity_time) {
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
