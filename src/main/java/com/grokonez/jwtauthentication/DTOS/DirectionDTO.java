package com.grokonez.jwtauthentication.DTOS;
import java.util.List;


public class DirectionDTO {

    private int id;
    private String nomDirection;
    private UserDTO userDTO;
  
	public DirectionDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DirectionDTO( String nomDirection) {
        this.nomDirection = nomDirection;
    }



    public String getNomDirection() {
        return nomDirection;
    }
    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
