package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends CrudRepository<Experience,Integer> {
    List<Experience> findByUser(User user);
    
    Optional<Experience> getExperienceById(int id);

}
