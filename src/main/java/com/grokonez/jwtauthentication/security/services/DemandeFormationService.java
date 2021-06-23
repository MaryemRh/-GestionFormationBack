package com.grokonez.jwtauthentication.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grokonez.jwtauthentication.DTOS.DemandeFormationDTO;
import com.grokonez.jwtauthentication.model.DemandeFormation;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.DemandeFormationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;



@Service
public class DemandeFormationService {
	
	@Autowired
	DemandeFormationRepository demandeFormationRepository;
	@Autowired
	UserDetailsServiceImpl employeeService;
//	@Autowired
//	UserRepository userRepository;
	
	   public List<DemandeFormationDTO> getAllDemandeFormationDTOS() {
	        return ((List<DemandeFormation>) demandeFormationRepository
	                .findAll())
	                .stream()
	                .map(this::convertToDemandeFormationDTO)
	                .collect(Collectors.toList());
	    }
	public List<DemandeFormationDTO> getDemandeFormationDTOS(User user ,User assigne ) {
		List<DemandeFormation> demandeFormationList = new ArrayList<>();
		List<DemandeFormation> demandeFormationsByUsers = demandeFormationRepository.findByUser(user);
		demandeFormationList.addAll(demandeFormationsByUsers);
		List<DemandeFormation> demandeFormationsByAssigne = demandeFormationRepository.findByAssigne(assigne);
		demandeFormationList.addAll(demandeFormationsByAssigne);

		return demandeFormationList
				.stream()
				.map(this::convertToDemandeFormationDTO)
				.collect(Collectors.toList());
	}
	   
	   public List<DemandeFormationDTO> getDemandeForamationByUserId(int id){
			
				return (List<DemandeFormationDTO>) demandeFormationRepository.getDemandeFormationByUserId(id)
						.stream()
		              //  .map(this::convertToDemandeFormationDTO)
		                .collect(Collectors.toList());
		                
			}
	   
	    public DemandeFormationDTO convertToDemandeFormationDTO(DemandeFormation demandeFormation) {
	    	DemandeFormationDTO demandeFormationDTO = new DemandeFormationDTO();

	    	User employee = demandeFormation.getUser();
	    	User assigne = demandeFormation.getAssigne();
	    	demandeFormationDTO.setId(demandeFormation.getId());
	    	demandeFormationDTO.setStatus(demandeFormation.getStatus());
	    	demandeFormationDTO.setPriorite(demandeFormation.getPriorite());
	    	demandeFormationDTO.setDateEmission(demandeFormation.getDateEmission());
	    	demandeFormationDTO.setBesoins(demandeFormation.getBesoins());
	       
	    	demandeFormationDTO.setTitre(demandeFormation.getTitre());
	    	demandeFormationDTO.setDescription(demandeFormation.getDescription());
	    	demandeFormationDTO.setTheme(demandeFormation.getTheme());
	    	demandeFormationDTO.setActivite(demandeFormation.getActivite());
	    	demandeFormationDTO.setEquipe(demandeFormation.getEquipe());
	    	demandeFormationDTO.setDirection(demandeFormation.getDirection());
	    	demandeFormationDTO.setIdUser(employee.getId());
	    	demandeFormationDTO.setNomUser(employee.getNom());
			demandeFormationDTO.setPrenomUser(employee.getPrenom());
			demandeFormationDTO.setUsername(employee.getUsername());
			if(assigne !=null){
				demandeFormationDTO.setAssigneUsername(assigne.getUsername());
			}
			demandeFormationDTO.setMatriculeUser(employee.getMatricule());
			demandeFormationDTO.setApproved(demandeFormation.getApprouved());
	        return demandeFormationDTO;
	    }
	    
	 
		



}
