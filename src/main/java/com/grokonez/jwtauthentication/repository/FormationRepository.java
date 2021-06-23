package com.grokonez.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grokonez.jwtauthentication.model.*;




public interface FormationRepository  extends CrudRepository<Formation , Integer>{
	//Formation findById(int i);
	//Formation findByCodeFormation(String code);
	

     Optional<Formation> getFormationById(int id);
     List<Formation> findByStatus(StatusFormation cloturer);
//     Optional<Formation> getFormationByIdAndParticipants(int id);
//     @Query(value="SELECT p,f FROM Participant  p,Formation f where p.formation.id=f.id")
//     List<Formation> findByParticipants(Participant participant);
}
