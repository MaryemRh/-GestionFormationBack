package com.grokonez.jwtauthentication.DTOS;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.grokonez.jwtauthentication.model.StatusFormation;


public class FormationDTO {
	
	private int id;
	private String code;
	private String type;
	private String theme;
	private String organisme;
	private String objectif;
	private String formateur;
	private LocalDate dateDebutFormation;
	private LocalDate dateClotureFormation;
	private float cout;
	private int nbrPlace;
	private StatusFormation status;
	private boolean actif ;
	
	private List<UserDTO> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getNbrPlace() {
		return nbrPlace;
	}

	public void setNbrPlace(int nbrPlace) {
		this.nbrPlace = nbrPlace;
	}

	public StatusFormation getStatus() {
		return status;
	}

	public void setStatus(StatusFormation status) {
		this.status = status;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	

}
