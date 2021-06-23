package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.DTOS.EquipeDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.EquipeRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.DirectionService;
import com.grokonez.jwtauthentication.security.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.ActiviteDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import com.grokonez.jwtauthentication.security.services.ActiviteService;

import java.util.List;

@RestController
@CrossOrigin ( origins = "http://localhost:4200" , allowedHeaders = "*")
@RequestMapping("/api/auth")
public class ActiviteController {

    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    ActiviteService activiteService;
    @Autowired
    DirectionService directionService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    EquipeService equipeService;
    @GetMapping(value = "/Activites")
    public List<ActiviteDTO> showActivites(){

        List<ActiviteDTO> activiteDTOS = activiteService.getAllActivitesDTOS();
        return activiteDTOS;
    }


    @GetMapping(value = "/Activites/{id}")
    public ActiviteDTO getActivite(@PathVariable int id ){

        Activite activite = activiteRepository.findById(id);
        if(activite!=null) {
            ActiviteDTO activiteDTO = activiteService.convertToActiviteDTO(activite);
            return activiteDTO;
        }
        else return  null;
    }


    @PostMapping(value = "/Activites/create")
    public void createActivite (@RequestBody ActiviteDTO activiteDTO,@RequestParam("username") String username) {

        Activite activite = new Activite();
        activite.setNomActivite(activiteDTO.getNomActivite());
        activite.setDirection(directionRepository.
                findByNomDirection(activiteDTO.getDirection().getNomDirection()));
        User user = userRepository.findByUsername(username).get();
        user.setGrade(Grade.Manager);
        activite.setUser(user);
        activiteRepository.save(activite);

    }


    @PutMapping(value = "/Activites/update/{id}/activite")
    public void updateActivite (@PathVariable int id,@RequestBody ActiviteDTO activiteDTO,@RequestParam("username") String username) {

        Activite activite = activiteRepository.findById(id);
        activite.setNomActivite(activiteDTO.getNomActivite());
        Direction direction = directionRepository.findByNomDirection(activiteDTO.getDirectionName());
        activite.setDirection(direction);
        User user = userRepository.findByUsername(username).get();
        user.setGrade(Grade.Manager);
        activite.setUser(user);
        activiteRepository.save(activite);
    }
    @GetMapping("/Activites/EquipesByActivite")
    @ResponseBody
    public List<EquipeDTO> getActivitiesByDirectionId(@RequestParam("nomActivite") String nomActivite) {
        Activite activite = activiteRepository.findByNomActivite(nomActivite);
        List<EquipeDTO> equipeDTOS = equipeService.getEquipeByActivite(activite);
        return equipeDTOS;
    }
    /*
    @DeleteMapping("/Activites/delete/{id}")
     public void deleteActivite(@PathVariable int id) {
    	
    	Activite activite = activiteRepository.findById(id);
         if(activite != null ){
        	 activiteRepository.delete(activite);             
         }
     }

	  /*@GetMapping("/Activites/EmployeesByActiviteId/{id}")
	  @ResponseBody
	    public List<UserDTO> getEmployeesByActiviteId(@PathVariable int id) {
		  
	        List<UserDTO> employeeDTOS = activiteService.getEmployeesByActiviteId(id);
	        return employeeDTOS;
	    }
        */


}
