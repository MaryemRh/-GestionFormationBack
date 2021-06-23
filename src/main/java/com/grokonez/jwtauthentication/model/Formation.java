package com.grokonez.jwtauthentication.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Formation")
public class Formation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String type;
    private String theme;
    private String organisme;
    private String objectif;
    private String formateur;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateDebutFormation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateClotureFormation;
    private float cout;
    private int nbrPlace;
    private StatusFormation status;
    private boolean actif;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formateur")
    private User formteur;
    @JsonBackReference
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeFormation> demandeFormation;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subscription> subscription;
    @JsonBackReference
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participant> participants;

    public Formation() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Formation(int id, String code, String type, String theme, String organisme, String objectif,
                     String formateur, LocalDate dateDebutFormation, LocalDate dateClotureFormation, float cout, int nbrPlace,
                     StatusFormation status, boolean actif, List<DemandeFormation> demandeFormation,
                     List<Subscription> subscription) {
        super();
        this.id = id;
        this.code = code;
        this.type = type;
        this.theme = theme;
        this.organisme = organisme;
        this.objectif = objectif;
        this.formateur = formateur;
        this.dateDebutFormation = dateDebutFormation;
        this.dateClotureFormation = dateClotureFormation;
        this.cout = cout;
        this.nbrPlace = nbrPlace;
        this.status = status;
        this.actif = actif;
        this.demandeFormation = demandeFormation;
        this.subscription = subscription;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }


    public LocalDate getDateDebutFormation() {
        return dateDebutFormation;
    }


    public void setDateDebutFormation(LocalDate dateDebutFormation) {
        this.dateDebutFormation = dateDebutFormation;
    }


    public LocalDate getDateClotureFormation() {
        return dateClotureFormation;
    }


    public void setDateClotureFormation(LocalDate dateClotureFormation) {
        this.dateClotureFormation = dateClotureFormation;
    }


    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

/*	public List<DemandeFormation> getDemandeFormation() {
		return demandeFormation;
	}

	public void setDemandeFormation(List<DemandeFormation> demandeFormation) {
		this.demandeFormation = demandeFormation;
	}*/

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public StatusFormation getStatus() {
      /*  LocalDate lt = LocalDate.now();
        if (dateClotureFormation.isBefore(lt)) return StatusFormation.Cloturer;*/

        return status;
    }


    public void setStatus(StatusFormation status) {
        this.status = status;
    }


    public User getFormteur() {
        return formteur;
    }

    public void setFormteur(User formteur) {
        this.formteur = formteur;
    }

    public boolean isActif() {
        return actif;
    }


    public void setActif(boolean actif) {
        this.actif = actif;
    }


    public List<DemandeFormation> getDemandeFormation() {
        return demandeFormation;
    }


    public void setDemandeFormation(List<DemandeFormation> demandeFormation) {
        this.demandeFormation = demandeFormation;
    }


    public List<Subscription> getSubscription() {
        return subscription;
    }


    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipants(Participant participants) {
        this.participants.add(participants);
    }
//
//    @Override
//    public String toString() {
//        return "Formation{" +
//                "id=" + id +
//                ", code='" + code + '\'' +
//                ", type='" + type + '\'' +
//                ", theme='" + theme + '\'' +
//                ", organisme='" + organisme + '\'' +
//                ", objectif='" + objectif + '\'' +
//                ", formateur='" + formateur + '\'' +
//                ", dateDebutFormation=" + dateDebutFormation +
//                ", dateClotureFormation=" + dateClotureFormation +
//                ", cout=" + cout +
//                ", nbrPlace=" + nbrPlace +
//                ", status=" + status +
//                ", actif=" + actif +
//                ", formteur=" + formteur +
//                ", demandeFormation=" + demandeFormation +
//                ", subscription=" + subscription +
//                ", participants=" + participants +
//                '}';
//    }
}
