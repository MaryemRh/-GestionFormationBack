package com.grokonez.jwtauthentication.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "direction")
public class Direction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String nomDirection;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
/*    @OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_responsable")
    private User responsable;*/

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "direction", orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Activite> activites ;

    public Direction() {
    }

    public Direction(int id, String nomDirection) {
        this.id = id;
        this.nomDirection = nomDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }


    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
