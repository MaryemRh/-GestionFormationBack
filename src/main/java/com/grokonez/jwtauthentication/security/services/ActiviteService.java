package com.grokonez.jwtauthentication.security.services;


import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grokonez.jwtauthentication.DTOS.ActiviteDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActiviteService {

    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;
    @Autowired
    DirectionService directionService;
    
	/*public List<UserDTO> getEmployeesByActiviteId(int id){
		
		return (List<UserDTO>) activiteRepository.getEmployeesByActiviteId(id)
				.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
                
	}*/

    public List<ActiviteDTO> getAllActivitesDTOS() {
        return ((List<Activite>) activiteRepository
                .findAll())
                .stream()
                .map(this::convertToActiviteDTO)
                .collect(Collectors.toList());
    }

    public ActiviteDTO convertToActiviteDTO(Activite activite) {
        ActiviteDTO activiteDTO = new ActiviteDTO(activite.getNomActivite());
        Direction direction = activite.getDirection();
        activiteDTO.setId(activite.getId());
        activiteDTO.setDirection(directionService.convertToDirectionDTO(directionRepository
                .findByNomDirection(direction.getNomDirection())));
                for(Equipe equipe :activite.getEquipes()){
                    for (User user : equipe.getUsers()){
                        if(Grade.Manager.equals(user.getGrade())){
                            activiteDTO.setResponsable(user.getUsername());
                        }
                    }
                }
        activiteDTO.setUserDTO(convertToUserDTO(activite.getUser()));
        return activiteDTO;
    }
    
    public UserDTO convertToUserDTO(User employee) {
    	UserDTO employeeDTO = new UserDTO();
        if(employee != null){

        Poste poste = employee.getPoste();
        Equipe equipe = employee.getEquipe();

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
        //employeeDTO.setActif(employee.isActif());
       // employeeDTO.setDeleted(employee.isDeleted());
      //  employeeDTO.setRoles(collect);
            if(poste!= null) {
                employeeDTO.setIdPoste(poste.getId());
                employeeDTO.setNomPoste(poste.getNomPoste());
            }
            if(equipe != null){
                employeeDTO.setIdEquipe(equipe.getId());
                employeeDTO.setNomEquipe(equipe.getNomEquipe());
            }

        employeeDTO.setCapaciteAnalyse(employee.getCapaciteAnalyse());
        employeeDTO.setMethodologie(employee.getMethodologie());
        employeeDTO.setMaitriseStandards(employee.getMaitriseStandards());
        employeeDTO.setCompetencesSpecifiques(employee.getCompetencesSpecifiques());
        employeeDTO.setGestionProjets(employee.getGestionProjets());
        employeeDTO.setOrganisationRigueurFiabilite(employee.getOrganisationRigueurFiabilite());
        employeeDTO.setGestionRessources(employee.getGestionRessources());
        employeeDTO.setCapacitesRedactionnelles(employee.getCapacitesRedactionnelles());
        employeeDTO.setTotal(employee.getTotal());

        }
        return employeeDTO;
    }

}
