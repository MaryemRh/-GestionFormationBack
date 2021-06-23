package com.grokonez.jwtauthentication.DTOS;
import java.util.List;

public class PosteDTO {

    private int id;
    private String nomPoste;
    private List<UserDTO> users;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomPoste() {
        return nomPoste;
    }
    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

}
