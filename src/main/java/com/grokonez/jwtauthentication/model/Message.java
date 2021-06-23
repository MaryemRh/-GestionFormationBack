package com.grokonez.jwtauthentication.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message implements Serializable{

	/**
    *
    */
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String messsage;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_user")
   private User user;
   
   
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(int id, String messsage, User user) {
		super();
		this.id = id;
		this.messsage = messsage;
		this.user = user;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
   
   
   
}
