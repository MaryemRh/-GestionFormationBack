package com.grokonez.jwtauthentication.repository;

import java.util.List;

import com.grokonez.jwtauthentication.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.DTOS.DemandeFormationDTO;
import com.grokonez.jwtauthentication.model.DemandeFormation;



@Repository
public interface DemandeFormationRepository extends CrudRepository<DemandeFormation,Integer>{
	DemandeFormation findByTitre(String titre);
//	
//	 @Query( "UPDATE DemandeFormation\r\n" + 
//	 		"	SET status=status\r\n" + 
//	 		"	WHERE id:=id")
//		List<DemandeFormation> updateByStatus(@Param("id")int id,@Param("status")String status);
	
	 @Query( "select d from DemandeFormation d where id_user =:id")
	 	List<DemandeFormationDTO> getDemandeFormationByUserId(@Param("id") int id);

	List<DemandeFormation> findByUser(User user);
	List<DemandeFormation> findByAssigne(User user);

}
