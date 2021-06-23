package com.grokonez.jwtauthentication.repository;


import java.util.List;

import com.grokonez.jwtauthentication.model.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.User;

@Repository
public interface ActiviteRepository extends CrudRepository<Activite,Integer> {

    Activite findById(int i);
    Activite findByNomActivite(String nomActivite);
    List<Activite> findActiviteByDirection(Direction direction);
    /*
    @Query( "select e from User e join e.activite a  where a.id =:idA")
 	List<User> getEmployeesByActiviteId(@Param("idA")int idA);
*/
}
