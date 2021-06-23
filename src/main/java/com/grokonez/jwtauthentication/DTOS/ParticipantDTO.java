package com.grokonez.jwtauthentication.DTOS;

import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


public class ParticipantDTO {

	 Integer id;

	 UserDTO user;
	 FormationDTO formation;

	private  boolean isApproved= false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	

	public FormationDTO getFormation() {
		return formation;
	}

	public void setFormation(FormationDTO formation) {
		this.formation = formation;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		isApproved = approved;
	}

	@Override
	public String toString() {
		return "ParticipantDTO{" +
				"id=" + id +
				", user=" + user +
				", formation=" + formation +
				", isApproved=" + isApproved +
				'}';
	}
}
