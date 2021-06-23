package com.grokonez.jwtauthentication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "demandeFormation")
public class DemandeFormation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private StatusDemande status;
	private int priorite;
	private Date dateEmission;
	private String besoins;
	private String titre;
	private String description;
	private String theme;
	private String activite;
	private String equipe;
	private String direction;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User assigne;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_formation")
	@JsonManagedReference
	private Formation formation;
	Boolean isApprouved = false;
	Boolean isLastGrade = false;

	public DemandeFormation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeFormation(int id, StatusDemande status, int priorite, Date dateEmission, String besoins, String titre,
			String description, String theme, String activite, String equipe, String direction) {
		super();
		this.id = id;
		this.status = status;
		this.priorite = priorite;
		this.dateEmission = dateEmission;
		this.besoins = besoins;
		this.titre = titre;
		this.description = description;
		this.theme = theme;
		this.activite = activite;
		this.equipe = equipe;
		this.direction = direction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusDemande getStatus() {
		return status;
	}

	public void setStatus(StatusDemande status) {
		this.status = status;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public Date getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}

	public String getBesoins() {
		return besoins;
	}

	public void setBesoins(String besoins) {
		this.besoins = besoins;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public Boolean getApprouved() {
		return isApprouved;
	}

	public void setApprouved(Boolean approuved) {
		isApprouved = approuved;
	}

	public Boolean getLastGrade() {
		return isLastGrade;
	}

	public void setLastGrade(Boolean lastGrade) {
		isLastGrade = lastGrade;
	}

	public User getAssigne() {
		return assigne;
	}

	public void setAssigne(User assigne) {
		this.assigne = assigne;
	}
}
