package com.grokonez.jwtauthentication.DTOS;

import java.time.LocalDate;
import java.util.Date;

import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.User;



public class SubscriptionDTO {
	
	private int id;
	private String description;
	private String name;
	private boolean approuver = false;
	
	private User users ;
	private Formation formation;
	
	 private Integer idUser;
	 private String nomUser ;
	 private String prenomUser;
	 private Integer matriculeUser;
	
	private int idFormation;
	private String codeFormation;
	private String typeFormation;
	private String themeFormation;
	private String organismeFormation;
	private String objectifFormation;
	private LocalDate DateDebutFormation;
	private LocalDate DateClotureFormation;
	

	
	public boolean isApprouver() {
		return approuver;
	}


	public void setApprouver(boolean approuver) {
		this.approuver = approuver;
	}


	public SubscriptionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public User getUsers() {
		return users;
	}


	public void setUsers(User users) {
		this.users = users;
	}


	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	public String getNomUser() {
		return nomUser;
	}


	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}


	public String getPrenomUser() {
		return prenomUser;
	}


	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}


	public Integer getMatriculeUser() {
		return matriculeUser;
	}


	public void setMatriculeUser(Integer matriculeUser) {
		this.matriculeUser = matriculeUser;
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


	public String getTypeFormation() {
		return typeFormation;
	}


	public void setTypeFormation(String typeFormation) {
		this.typeFormation = typeFormation;
	}


	public String getThemeFormation() {
		return themeFormation;
	}


	public void setThemeFormation(String themeFormation) {
		this.themeFormation = themeFormation;
	}


	public String getOrganismeFormation() {
		return organismeFormation;
	}


	public void setOrganismeFormation(String organismeFormation) {
		this.organismeFormation = organismeFormation;
	}


	public String getObjectifFormation() {
		return objectifFormation;
	}


	public void setObjectifFormation(String objectifFormation) {
		this.objectifFormation = objectifFormation;
	}


	public LocalDate getDateDebutFormation() {
		return DateDebutFormation;
	}


	public void setDateDebutFormation(LocalDate dateDebutFormation) {
		DateDebutFormation = dateDebutFormation;
	}


	public LocalDate getDateClotureFormation() {
		return DateClotureFormation;
	}


	public void setDateClotureFormation(LocalDate dateClotureFormation) {
		DateClotureFormation = dateClotureFormation;
	}



	
	
}
