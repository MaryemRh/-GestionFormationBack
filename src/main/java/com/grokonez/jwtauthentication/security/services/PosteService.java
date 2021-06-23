package com.grokonez.jwtauthentication.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grokonez.jwtauthentication.DTOS.PosteDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.Direction;
import com.grokonez.jwtauthentication.model.Equipe;
import com.grokonez.jwtauthentication.model.Poste;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.PosteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosteService {

    @Autowired
    PosteRepository posteRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;

    public List<PosteDTO> getAllPostesDTOS() {
        return ((List<Poste>) posteRepository
                .findAll())
                .stream()
                .map(this::convertToPosteDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public PosteDTO convertToPosteDTO(Poste poste) {
        PosteDTO posteDTO = new PosteDTO();

        List<User> employees = poste.getUsers();

        final List<UserDTO> collect = employees.stream().map(employee -> {
        	UserDTO employeeDTO = employeeService.convertToUserDTO(employee);
            return employeeDTO;
        }).collect(Collectors.toList());

        posteDTO.setUsers(collect);
        posteDTO.setId(poste.getId());
        posteDTO.setNomPoste(poste.getNomPoste());

        return posteDTO;
    }
    @Transactional
	public List<UserDTO> getEmployeesByPosteId(int id){
		
		return (List<UserDTO>) posteRepository.getEmployeesByPosteId(id)
				.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
                
	}
    @Transactional
	  public UserDTO convertToEmployeeDTO(User employee) {
		  UserDTO employeeDTO = new UserDTO();

	        Poste poste = employee.getPoste();
	        Equipe equipe = employee.getEquipe();
	  /*      Activite activite = employee.getActivite();
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
	        //   employeeDTO.setActif(employee.isActif());
	        //   employeeDTO.setDeleted(employee.isDeleted());
	        //employeeDTO.setRoles(employee.getRoles());
	        //employeeDTO.setRoles(collect);
	        employeeDTO.setIdPoste(poste.getId());
	        employeeDTO.setNomPoste(poste.getNomPoste());
	        employeeDTO.setIdEquipe(equipe.getId());
	        employeeDTO.setNomEquipe(equipe.getNomEquipe());
	    /*    employeeDTO.setIdActivite(activite.getId());
	        employeeDTO.setNomActivite(activite.getNomActivite());
	        employeeDTO.setIdDirection(direction.getId());
	        employeeDTO.setNomDirection(direction.getNomDirection());
	        */
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
