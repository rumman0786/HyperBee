package net.therap.hyperbee.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.hyperbee.domain.constant.DomainConstant.DATE_TIME_FIELD;

/**
 * @author bashir
 * @author rayed
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    private String description;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    private Hive hive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hive getHive() {
        return hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }
}
