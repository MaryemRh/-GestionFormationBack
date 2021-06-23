package com.grokonez.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.DTOS.DemandeFormationDTO;
import com.grokonez.jwtauthentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	User findByEmail(String email);
	Optional<User> findByUsernameIgnoreCase(String username);
	
	 Optional<User> getUserById(Integer id);

	 
//	 @Query( "select u from DemandeFormation u where u.id_user =:id")
//	 	List<DemandeFormationDTO> getDemandeFormationByUserId(@Param("id") int id);
	 
	 
	 
}