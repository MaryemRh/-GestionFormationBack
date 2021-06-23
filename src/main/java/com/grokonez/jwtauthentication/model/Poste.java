package com.grokonez.jwtauthentication.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "poste")
public class Poste implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String nomPoste;

    @OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public Poste() {
    }

    public Poste(int id, String nomPoste) {
        this.id = id;
        this.nomPoste = nomPoste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
