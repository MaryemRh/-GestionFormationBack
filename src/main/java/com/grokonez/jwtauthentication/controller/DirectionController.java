package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.ActiviteDTO;
import com.grokonez.jwtauthentication.DTOS.DirectionDTO;
import com.grokonez.jwtauthentication.model.Direction;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.DirectionService;
import com.grokonez.jwtauthentication.security.services.UserDetailsServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
@RequestMapping("/api/auth")
public class DirectionController {

    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    DirectionService directionService;
    @Autowired
    UserDetailsServiceImpl employeeService;
    @Autowired
    UserRepository employeeRepository;

    @GetMapping(value = "/Directions")
    public List<DirectionDTO> showDirections(){

        List<DirectionDTO> directionDTOS = directionService.getAllDirectionsDTOS();
        return directionDTOS;
    }



    @GetMapping(value = "/Directions/{id}")
    public DirectionDTO getDirection(@PathVariable int id ){

        Direction direction = directionRepository.findById(id);
        if(direction!=null) {
            DirectionDTO directionDTO = directionService.convertToDirectionDTO(direction);
            return directionDTO;
        }
        else return  null;
    }

//
//    @GetMapping(value = "/Directions/employees")
//    public List<EmployeeDTO> showDirectionsEmployees(){
//
//        List<DirectionDTO> directionDTOS = directionService.getAllDirectionsDTOS();
//
//        List<EmployeeDTO> employeeDTOSfinal = new ArrayList<>();
//        for (DirectionDTO directionDTO : directionDTOS) {
//            EmployeeDTO responsable = directionDTO.getResponsable();
//            employeeDTOSfinal.add(responsable);
//        }
//        return employeeDTOSfinal;
//    }
    


    @PostMapping(value = "/Directions/create")
    public void createDirections (@RequestBody DirectionDTO directionDTO,@RequestParam("username") String username) {
      
        Direction direction = new Direction();
        direction.setNomDirection(directionDTO.getNomDirection());
        User user = employeeRepository.findByUsername(username).get();
        user.setGrade(Grade.Directeur);
        direction.setUser(user);
        directionRepository.save(direction);
      
    }


    @PutMapping(value = "/Directions/update/{id}")
    public void updateDirection (@PathVariable int id,@RequestBody DirectionDTO directionDTO,@RequestParam("username") String username) {
     
        Direction direction = directionRepository.findById(id);
        direction.setNomDirection(directionDTO.getNomDirection());
        User user = employeeRepository.findByUsername(username).get();
        user.setGrade(Grade.Directeur);
        direction.setUser(user);
        directionRepository.save(direction);
    }
    
    @DeleteMapping("/Directions/delete/{id}")
    public void deleteDirections(@PathVariable int id) {
    	
    	Direction direction = directionRepository.findById(id);
        if(direction != null ){
        	directionRepository.delete(direction);
            
        }
    }

    @GetMapping("/Directions/ActivitiesByDirection")
	  @ResponseBody
	    public List<ActiviteDTO> getActivitiesByDirectionId(@RequestParam("nomDirection") String nomDirection) {
		    Direction direction = directionRepository.findByNomDirection(nomDirection);
	        List<ActiviteDTO> activiteDTO = directionService.getActivitiesByDirectionId(direction);
	        return activiteDTO;
	    }


}
