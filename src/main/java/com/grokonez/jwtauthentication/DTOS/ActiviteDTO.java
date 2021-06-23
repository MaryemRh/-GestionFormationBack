package com.grokonez.jwtauthentication.DTOS;


import java.util.List;

public class ActiviteDTO {
    private int id;
    private String nomActivite;
    private DirectionDTO direction;
    private String responsable;
    private String directionName;
    private UserDTO userDTO;

    public ActiviteDTO( ) {
    }
    public ActiviteDTO( String nomActivite) {
		this.nomActivite = nomActivite;
	}


    public String getNomActivite() {
        return nomActivite;
    }
    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public DirectionDTO getDirection() {
        return direction;
    }

    public void setDirection(DirectionDTO direction) {
        this.direction = direction;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
