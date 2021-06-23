package com.grokonez.jwtauthentication.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "subscription")
public class Subscription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String name;
	private boolean approuver;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
	private User users ;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formation")
    private Formation formation;

	
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Subscription(int id, String description, String name, boolean approuver) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.approuver = approuver;
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

	public boolean isApprouver() {
		return approuver;
	}

	public void setApprouver(boolean approuver) {
		this.approuver = approuver;
	}
	
	

}
