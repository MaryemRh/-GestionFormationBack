package com.grokonez.jwtauthentication.repository;


import java.util.List;

import com.grokonez.jwtauthentication.model.Activite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Equipe;
import com.grokonez.jwtauthentication.model.User;

@Repository
public interface EquipeRepository extends CrudRepository<Equipe,Integer> {

    Equipe findById(int i);
    Equipe findByNomEquipe(String nomEquipe);
    List<Equipe> findEquipeByActivite(Activite activite);
    
    @Query( "select e from User e join e.equipe a  where a.id =:idA")
 	List<User> getEmployeesByEquipeId(@Param("idA")int idA);
}
