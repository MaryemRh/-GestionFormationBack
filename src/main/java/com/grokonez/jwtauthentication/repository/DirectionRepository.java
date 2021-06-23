package com.grokonez.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.Direction;
import com.grokonez.jwtauthentication.model.User;


@Repository
public interface DirectionRepository extends CrudRepository<Direction,Integer> {
    Direction findById(int id);
    Direction findByNomDirection(String nomDirection);
    List<Activite> findDirectionByActivites(int directionId);
/*    Direction findByResponsable(User responsable);
    
    @Query( "select a from Activite a join a.direction d  where d.id =:idD")
 	List<Activite> getActivitiesByDirectionId(@Param("idD")int idD);
    */

}
