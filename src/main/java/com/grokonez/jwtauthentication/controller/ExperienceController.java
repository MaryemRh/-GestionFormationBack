package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.DTOS.ExperienceDTO;
import com.grokonez.jwtauthentication.DTOS.FormationDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.ExperienceRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.ExperienceServiceImpl;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class ExperienceController {
    @Autowired
    ExperienceServiceImpl experienceService;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    UserRepository userRepository;
    
 /*   @GetMapping(value="/")
    public Iterable<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();
    }
    @GetMapping(value="/{id}")
    public List<Experience> getAllExperiencesByUser(@PathVariable User id) {
        return experienceService.findByUser(id);
    }*/
   /* @PostMapping(value = "/")
    public Experience createExperience(@RequestBody Experience experience) {
    		Experience exp = new Experience();
    	
    	exp.setId(experience.getId());
    	exp.setCompany(experience.getCompany());
    	exp.setDateDepart(experience.getDateDepart());
    	exp.setDateEmbauche(experience.getDateEmbauche());
    	exp.setTasks(experience.getTasks());
		   
        return experienceRepository.save(experience);
    }*/
    
    @GetMapping(value="/Experiences/Users/{id}")
    public List<ExperienceDTO> getAllExperiencesByUserId(@PathVariable int id) {
    	
        
    	List<ExperienceDTO> experienceDTO = experienceService.getExperienceByUserId(id);
    	
    	
        return experienceDTO;
    }
    
  
    
  /*  @GetMapping(value = "/Experiences/{id}")
    public List<ExperienceDTO> getExperiences(@PathVariable int id) {

    	Experience experience = experienceRepository.findById(id).get();
        if (experience != null) {
        	List<ExperienceDTO> experienceDTO = experienceService.convertToExperienceDTO(experience);
            return experienceDTO;
        } else return null;
    }*/


    @PostMapping(value = "/Experiences/create")
    public Experience createExperience(@RequestBody ExperienceDTO experienceDTO) { 
    System.out.println(experienceDTO);
    	Experience experience = new Experience();
  	  User user = userRepository.findById(experienceDTO.getUserDTO()).get();

    	experience.setId(experienceDTO.getId());
    	experience.setPoste(experienceDTO.getPoste());
    	experience.setCompany(experienceDTO.getCompany());
    	experience.setDateDepart(experienceDTO.getDateDepart());
    	experience.setDateEmbauche(experienceDTO.getDateEmbauche());
    	experience.setTasks(experienceDTO.getTasks());
    	
    	  if(user !=null){
   		   experience.setUser(user);

    	  }
         

        return experienceRepository.save(experience);
    }
    
    
    @DeleteMapping("/Experiences/delete/{id}")
    public void deleteExperiences(@PathVariable int id) {
    	Experience experience = experienceRepository.findById(id).get();
        if (experience != null) {
        	experienceRepository.delete(experience);

        }
    }
    @PutMapping(value = "/Experiences/update")
    public ResponseEntity<Experience> updateExperience(@RequestBody ExperienceDTO experienceDTO) {
        System.out.println("formationDTO: " + experienceDTO.getId());
        Experience experience = new Experience();

        experience.setId(experienceDTO.getId());
        experience.setCompany(experienceDTO.getCompany());
        experience.setDateDepart(experienceDTO.getDateDepart());
        experience.setDateEmbauche(experienceDTO.getDateEmbauche());
        experience.setTasks(experienceDTO.getTasks());
        
        experienceRepository.save(experience);
        return new ResponseEntity<Experience>(
        		experience, HttpStatus.OK);
    }

    
}

