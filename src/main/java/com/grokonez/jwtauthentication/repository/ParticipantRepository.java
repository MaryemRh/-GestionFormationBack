package com.grokonez.jwtauthentication.repository;


import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Participant;
import com.grokonez.jwtauthentication.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {


//    @Query(value = "SELECT p,f FROM Participant p,Formation f WHERE p.formation.id=f.id and p.user.id=1")
//    List<Participant> findByFormation(Formation participant);
    @Query( "select p from Participant p  where p.user.id =:idA And  p.formation.status=0 ")
    List<Participant> findByParticipant(@Param("idA")int idA);
}
