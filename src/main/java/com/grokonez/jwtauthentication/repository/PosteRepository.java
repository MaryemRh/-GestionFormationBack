package com.grokonez.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Poste;
import com.grokonez.jwtauthentication.model.User;

@Repository
public interface PosteRepository extends CrudRepository<Poste , Integer> {
    Poste findById(int i);
    Poste findByNomPoste(String nomPoste);
    
    @Query( "select e from User e join e.poste a  where a.id =:idA")
 	List<User> getEmployeesByPosteId(@Param("idA")int idA);
}
