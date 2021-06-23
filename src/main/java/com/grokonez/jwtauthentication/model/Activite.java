package com.grokonez.jwtauthentication.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "activite")
public class Activite implements Serializable {

    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomActivite;
    //private boolean deleted ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direction", nullable = false)
    private Direction direction;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipe> equipes;


    public Activite() {
    }

    public Activite(int id, String nomActivite) {
        this.id = id;
        this.nomActivite = nomActivite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
