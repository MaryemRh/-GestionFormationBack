package com.grokonez.jwtauthentication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grokonez.jwtauthentication.DTOS.PosteDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.Poste;
import com.grokonez.jwtauthentication.repository.PosteRepository;
import com.grokonez.jwtauthentication.security.services.PosteService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200" , allowedHeaders = "*")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class PosteController {

    @Autowired
    PosteRepository posteRepository;
    @Autowired
    PosteService posteService;


    @GetMapping(value = "/Postes")
    public List<PosteDTO> showPostes(){

        List<PosteDTO> posteDTOS = posteService.getAllPostesDTOS();
        return posteDTOS;
    }


    @GetMapping(value = "/Postes/{id}")
    public PosteDTO getPoste(@PathVariable int id ){

        Poste poste = posteRepository.findById(id);
        if(poste!=null) {
            PosteDTO posteDTO = posteService.convertToPosteDTO(poste);
            return posteDTO;
        }
        else return  null;
    }


    @PostMapping(value = "/Postes/create")
    public ResponseEntity<Void> createPoste (@RequestBody PosteDTO posteDTO) {

        if ( (posteRepository.findById(posteDTO.getId()) !=null)||(posteRepository.findByNomPoste(posteDTO.getNomPoste()) != null) ) {
            return ResponseEntity.badRequest().build();
        }
        Poste poste = new Poste();
        poste.setId(posteDTO.getId());
        poste.setNomPoste(posteDTO.getNomPoste());
        final Poste P = posteRepository.save(poste);
        if (P != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(P.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PutMapping(value = "/Postes/update")
    public ResponseEntity<Void> updatePoste (@RequestBody PosteDTO posteDTO) {
    	
    	Poste poste =new Poste();
    	poste.setId(posteDTO.getId());
    	poste.setNomPoste(posteDTO.getNomPoste());
      
        Poste P = posteRepository.save(poste);
        if (P != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @DeleteMapping("/Postes/delete/{id}")
    public void deletePostes(@PathVariable int id) {
    	Poste poste = posteRepository.findById(id);
        if(poste != null ){
        	posteRepository.delete(poste);
            
        }
    }
    
    @GetMapping("/Postes/EmployeesByPosteId/{id}")
	  @ResponseBody
	    public List<UserDTO> getEmployeesByEquipeId(@PathVariable int id) {
		  
	        List<UserDTO> employeeDTOS = posteService.getEmployeesByPosteId(id);
	        return employeeDTOS;
	    }
}
