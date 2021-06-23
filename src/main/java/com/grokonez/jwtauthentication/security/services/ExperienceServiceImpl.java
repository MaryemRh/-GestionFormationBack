package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.DTOS.ExperienceDTO;
import com.grokonez.jwtauthentication.DTOS.ParticipantDTO;
import com.grokonez.jwtauthentication.model.Experience;
import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Participant;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.ExperienceRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	@Autowired
    ExperienceRepository experienceRepository;
	@Autowired
    UserDetailsServiceImpl employeeService;
	@Autowired
    UserRepository userRepository;

    @Override
    public Experience saveExperience(Experience p) {
        return experienceRepository.save(p);
    }

    @Override
    public Experience updateExperience(Experience p) {
        return experienceRepository.save(p);
    }

    @Override
    public void deleteExperience(Experience p) {
        experienceRepository.delete(p);
    }

    @Override
    public void deleteExperienceById(Integer id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public Experience getExperience(Integer id) {
        return experienceRepository.findById(id).get();
    }

    @Override
    public Iterable<Experience> getAllExperiences() {
        return  experienceRepository.findAll();
    }

    @Override
    public List<Experience> findByUser(User user) {
       return experienceRepository.findByUser(user);
    }
    
	
	
	   @Transactional
	    public ExperienceDTO convertToExperienceDTO(Experience experience) {
		   ExperienceDTO experienceDTO = new ExperienceDTO();
		   User user = experience.getUser();
		   
		   experienceDTO.setId(experience.getId());
		   experienceDTO.setCompany(experience.getCompany());
		   experienceDTO.setDateDepart(experience.getDateDepart());
		   experienceDTO.setDateEmbauche(experience.getDateEmbauche());
		   experienceDTO.setTasks(experience.getTasks());
		   if(user !=null){
			   experienceDTO.setUserDTO(experience.getUser().getId());

		   }
//		   if(user !=null){
//			   experienceDTO.setUserDTO(user.get);
//		   }
		   

	        return experienceDTO;
	    }
	   

    public List<ExperienceDTO> getAllExperienceDTOS() {
        return ((List<Experience>) experienceRepository
                .findAll())
                .stream()
                .map(this::convertToExperienceDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public List<ExperienceDTO> getExperienceByUserId(int id) {
    	User user = userRepository.getUserById(id).get();
        List<Experience> experiences = new ArrayList<>();
       /* for(Experience exp : user.getExperiences()()){
        	Experiences.add(exp.get());
        }*/
        return ( user.getExperiences())
                .stream()
                .map(this::convertToExperienceDTO)
                .collect(Collectors.toList());
    }
    



}
