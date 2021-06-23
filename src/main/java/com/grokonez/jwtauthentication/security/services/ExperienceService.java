package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.model.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface  ExperienceService {
        Experience saveExperience(Experience p);
        Experience updateExperience(Experience p);
        void deleteExperience(Experience p);
        void deleteExperienceById(Integer id);
        Experience getExperience(Integer id);
        Iterable<Experience> getAllExperiences();
        List<Experience> findByUser(User user);



}
