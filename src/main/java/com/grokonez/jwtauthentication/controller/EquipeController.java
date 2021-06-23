package com.grokonez.jwtauthentication.controller;


import com.grokonez.jwtauthentication.model.Grade;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.EquipeDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.Equipe;
import com.grokonez.jwtauthentication.repository.EquipeRepository;
import com.grokonez.jwtauthentication.security.services.EquipeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class EquipeController {

    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EquipeService equipeService;
    @Autowired
    ActiviteRepository activiteRepository;


    @GetMapping(value = "/Equipes")
    public List<EquipeDTO> showEquipes(){

        List<EquipeDTO> equipeDTOS = equipeService.getAllEquipesDTOS();
        return equipeDTOS;
    }


    @GetMapping(value = "/Equipes/{id}")
    public EquipeDTO getEquipe(@PathVariable int id ){

        Equipe equipe = equipeRepository.findById(id);
        if(equipe!=null) {
            EquipeDTO equipeDTO = equipeService.convertToEquipeDTO(equipe);
            return equipeDTO;
        }
        else return  null;
    }


    @PostMapping(value = "/Equipes/create")
    public void createEquipe (@RequestBody EquipeDTO equipeDTO,@RequestParam("nomActivite") String nomActivite) {

        Equipe equipe = new Equipe();
        equipe.setId(equipeDTO.getId());
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setActivite(activiteRepository.findByNomActivite(nomActivite));
        User user = userRepository.findByUsername(equipeDTO.getResponsable()).get();
      //  if(user.getGrade().equals(Grade.ChefEquipe)){
        user.setEquipe(equipe);
        user.setGrade(Grade.ChefEquipe);
     //   }

        equipeRepository.save(equipe);

    }


    @PutMapping(value = "/Equipes/update")
    public void updateEquipe (@RequestBody EquipeDTO equipeDTO,@RequestParam("nomActivite") String nomActivite,
                              @RequestParam("username") String username) {

        Equipe equipe = equipeRepository.findById(equipeDTO.getId());
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setActivite(activiteRepository.findByNomActivite(nomActivite));

        User user = userRepository.findByUsername(username).get();
        //  if(user.getGrade().equals(Grade.ChefEquipe)){
        user.setEquipe(equipe);
        user.setGrade(Grade.ChefEquipe);
        equipeRepository.save(equipe);
      
    }
    
    @DeleteMapping("/Equipes/delete/{id}")
    public void deleteEquipes(@PathVariable int id) {
    	
    	Equipe equipe = equipeRepository.findById(id);
        if(equipe != null ){
        	equipeRepository.delete(equipe);
            
        }
    }
    
    @GetMapping("/Equipes/EmployeesByEquipeId/{id}")
	  @ResponseBody
	    public List<UserDTO> getEmployeesByEquipeId(@PathVariable int id) {
		  
	        List<UserDTO> employeeDTOS = equipeService.getEmployeesByEquipeId(id);
	        return employeeDTOS;
	    }

}
