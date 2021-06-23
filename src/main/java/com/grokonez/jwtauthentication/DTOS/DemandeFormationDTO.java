package com.grokonez.jwtauthentication.DTOS;

import java.util.Date;

import com.grokonez.jwtauthentication.model.StatusDemande;


public class DemandeFormationDTO {

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
	
	private Integer idUser;
	private String nomUser;
	private String prenomUser;
	private String username;
	private  String assigneUsername;

	private Integer matriculeUser;
	
	private int idFormation;
	private String codeFormation;
	private Boolean isApproved;
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
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer long1) {
		this.idUser = long1;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public Integer getMatriculeUser() {
		return matriculeUser;
	}
	public void setMatriculeUser(Integer matriculeUser) {
		this.matriculeUser = matriculeUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getCodeFormation() {
		return codeFormation;
	}
	public void setCodeFormation(String codeFormation) {
		this.codeFormation = codeFormation;
	}

	public Boolean getApproved() {
		return isApproved;
	}

	public void setApproved(Boolean approved) {
		isApproved = approved;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAssigneUsername() {
		return assigneUsername;
	}

	public void setAssigneUsername(String assigneUsername) {
		this.assigneUsername = assigneUsername;
	}
}
