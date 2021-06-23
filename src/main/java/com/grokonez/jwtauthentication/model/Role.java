package com.grokonez.jwtauthentication.model;



import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
  
    	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Enumerated(EnumType.STRING)
		@Column(length = 20)
		private ERole name;
		
		@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY )
		private List<User> users;
		
		public Role() {
		
		}
		
		public Role(ERole name) {
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

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name=" + name +
				", users=" + users +
				'}';
	}
}