package com.grokonez.jwtauthentication.security.services;


import com.grokonez.jwtauthentication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grokonez.jwtauthentication.DTOS.EquipeDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.repository.EquipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;
	@Autowired
	ActiviteService activiteService;
    @Transactional
    public List<EquipeDTO> getAllEquipesDTOS() {
        return ((List<Equipe>) equipeRepository
                .findAll())
                .stream()
                .map(this::convertToEquipeDTO)
                .collect(Collectors.toList());
    }

	@Transactional
	public List<EquipeDTO> getEquipeByActivite(Activite activite) {
		return equipeRepository.findEquipeByActivite(activite)
				.stream()
				.map(this::convertToEquipeDTO)
				.collect(Collectors.toList());
	}
    @Transactional
    public EquipeDTO convertToEquipeDTO(Equipe equipe) {
        EquipeDTO equipeDTO = new EquipeDTO();

        List<User> employees = equipe.getUsers();

        final List<UserDTO> collect = employees.stream().map(employee -> {
        	UserDTO employeeDTO = employeeService.convertToUserDTO(employee);
            return employeeDTO;
        }).collect(Collectors.toList());

        equipeDTO.setUers(collect);
        equipeDTO.setId(equipe.getId());
		equipeDTO.setNomEquipe(equipe.getNomEquipe());
		equipeDTO.setActivte(activiteService.convertToActiviteDTO(equipe.getActivite()));
		UserDTO employeeDTO = null;
		for(User user : employees){
			if(Grade.ChefEquipe.equals(user.getGrade())){
				 employeeDTO = employeeService.convertToUserDTO(user);
			}
		}
		if(employeeDTO != null){
			equipeDTO.setResponsable(employeeDTO.getUsername());
		}

        return equipeDTO;
    }
    @Transactional
	public List<UserDTO> getEmployeesByEquipeId(int id){
		
		return (List<UserDTO>) equipeRepository.getEmployeesByEquipeId(id)
				.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
                
	}
    @Transactional
	  public UserDTO convertToEmployeeDTO(User employee) {
		  UserDTO employeeDTO = new UserDTO();

	        Poste poste = employee.getPoste();
	        Equipe equipe = employee.getEquipe();
	     /*   Activite activite = employee.getActivite();
	        Direction direction = activite.getDirection();
	     
	        Direction directionDeriger = employee.getDirection();
	        if (directionDeriger != null) {
	            employeeDTO.setIdDirectionDeriger(directionDeriger.getId());
	            employeeDTO.setNomDirectionDeriger(directionDeriger.getNomDirection());
	        }*/
	        employeeDTO.setId(employee.getId());
	        employeeDTO.setUsername(employee.getUsername());
	        employeeDTO.setPassword(employee.getPassword());
	        employeeDTO.setMatricule(employee.getMatricule());
	        employeeDTO.setEmail(employee.getEmail());
	        employeeDTO.setNom(employee.getNom());
	        employeeDTO.setPrenom(employee.getPrenom());
	        employeeDTO.setTelephone(employee.getTelephone());
	        employeeDTO.setDateEmbauche(employee.getDateEmbauche());
	        employeeDTO.setDateDepart(employee.getDateDepart());
	       // employeeDTO.setActif(employee.isActif());
	      //  employeeDTO.setDeleted(employee.isDeleted());
	        //employeeDTO.setRoles(employee.getRoles());
	        //employeeDTO.setRoles(collect);
	        employeeDTO.setIdPoste(poste.getId());
	        employeeDTO.setNomPoste(poste.getNomPoste());
	        employeeDTO.setIdEquipe(equipe.getId());
	        employeeDTO.setNomEquipe(equipe.getNomEquipe());
	   /*     employeeDTO.setIdActivite(activite.getId());
	        employeeDTO.setNomActivite(activite.getNomActivite());
	        employeeDTO.setIdDirection(direction.getId());
	        employeeDTO.setNomDirection(direction.getNomDirection());*/
	        
	        employeeDTO.setCapaciteAnalyse(employee.getCapaciteAnalyse());
	        employeeDTO.setMethodologie(employee.getMethodologie());
	        employeeDTO.setMaitriseStandards(employee.getMaitriseStandards());
	        employeeDTO.setCompetencesSpecifiques(employee.getCompetencesSpecifiques());
	        employeeDTO.setGestionProjets(employee.getGestionProjets());
	        employeeDTO.setOrganisationRigueurFiabilite(employee.getOrganisationRigueurFiabilite());
	        employeeDTO.setGestionRessources(employee.getGestionRessources());
	        employeeDTO.setCapacitesRedactionnelles(employee.getCapacitesRedactionnelles());
	        employeeDTO.setTotal(employee.getTotal());
	        
	        return employeeDTO;
	    }
}
