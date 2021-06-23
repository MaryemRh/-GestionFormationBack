package com.grokonez.jwtauthentication.DTOS;



import java.util.List;

import com.grokonez.jwtauthentication.model.ERole;
import com.grokonez.jwtauthentication.model.User;

public class RoleDTO {
	
		private Integer id;
		private ERole name;
		private List<User> user;
		
	   public RoleDTO(Integer id, ERole name) {
		   this.id = id;
	        this.name = name;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public ERole getName() {
			return name;
		}
		public void setName(ERole name) {
			this.name = name;
		}

		public List<User> getUser() {
			return user;
		}

		public void setUser(List<User> user) {
			this.user = user;
		}
	    
	    

}
