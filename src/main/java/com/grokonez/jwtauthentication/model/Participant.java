package com.grokonez.jwtauthentication.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.io.Serializable;

@Entity
@Table(name = "Participant")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Participant implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonManagedReference
    public User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formation")
    @JsonManagedReference
    public Formation formation;

    private  boolean isApproved= false;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", user=" + user +
                ", formation=" + formation +
                ", isApproved=" + isApproved +
                '}';
    }
}
