package com.grokonez.jwtauthentication.DTOS;

import com.grokonez.jwtauthentication.model.ERole;

public class RoleEmployeeDTO {

	private int idRole;
	    private ERole nomRole;

	    public RoleEmployeeDTO(Integer id, ERole name) {
	        this.idRole = id;
	        this.nomRole = name;
	    }

	  

		public int getIdRole() {
	        return idRole;
	    }

	    public void setIdRole(int idRole) {
	        this.idRole = idRole;
	    }

	    public ERole getNomRole() {
	        return nomRole;
	    }

	    public void setNomRole(ERole nomRole) {
	        this.nomRole = nomRole;
	    }
}
