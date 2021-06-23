package com.grokonez.jwtauthentication.DTOS;

import java.time.LocalDate;

public class ExperienceDTO {
	
	private Integer id;
    private LocalDate dateEmbauche;
    private LocalDate dateDepart ;
    private String company ;
    private String tasks ;
    private String poste ;
    private int userDTO;
   
    
    public ExperienceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public ExperienceDTO(Integer id, LocalDate dateEmbauche, LocalDate dateDepart, String company, String tasks,
			String poste, int userDTO) {
		super();
		this.id = id;
		this.dateEmbauche = dateEmbauche;
		this.dateDepart = dateDepart;
		this.company = company;
		this.tasks = tasks;
		this.poste = poste;
		this.userDTO = userDTO;
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
	public int getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(int userDTO) {
		this.userDTO = userDTO;
	}


    
    
    

}
