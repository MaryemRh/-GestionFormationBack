package com.grokonez.jwtauthentication.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;

@Entity
public class Experience implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;
  @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;
   @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDepart ;
    private String company ;
    private String tasks ;
    private String poste ;
    //@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Experience() {
    }

    public Experience(Integer id, LocalDate dateEmbauche, LocalDate dateDepart, String company, String tasks, String poste, User user) {
        this.id = id;
        this.dateEmbauche = dateEmbauche;
        this.dateDepart = dateDepart;
        this.company = company;
        this.tasks = tasks;
        this.poste = poste;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
