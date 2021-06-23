package com.grokonez.jwtauthentication.DTOS;
import java.util.List;

public class EquipeDTO {
    private int id;
    private String nomEquipe;
    private List<UserDTO> users;
    private String responsable;
    private ActiviteDTO activte;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomEquipe() {
        return nomEquipe;
    }
    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUers(List<UserDTO> users) {
		this.users = users;
	}

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public ActiviteDTO getActivte() {
        return activte;
    }

    public void setActivte(ActiviteDTO activte) {
        this.activte = activte;
    }
}
