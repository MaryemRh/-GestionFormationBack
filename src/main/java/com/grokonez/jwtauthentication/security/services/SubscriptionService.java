package com.grokonez.jwtauthentication.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grokonez.jwtauthentication.DTOS.SubscriptionDTO;
import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Subscription;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.SubscriptionRepository;



@Service
public class SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	SubscriptionService SubscriptionService;
	
	@Transactional
    public List<SubscriptionDTO> getAllSubscriptionDTOS() {
        return ((List<Subscription>) subscriptionRepository
                .findAll())
                .stream()
                .map(this::convertToSubscriptionDTO)
                .collect(Collectors.toList());
    }
    
	@Transactional
    public SubscriptionDTO convertToSubscriptionDTO(Subscription subscription) {
    	SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

    	User employee = subscription.getUsers();
    	Formation formation = subscription.getFormation();

        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setName(subscription.getName());
        subscriptionDTO.setDescription(subscription.getDescription());
        subscriptionDTO.setApprouver(subscription.isApprouver());
        
        subscriptionDTO.setIdUser(employee.getId());
        subscriptionDTO.setNomUser(employee.getNom());
        subscriptionDTO.setPrenomUser(employee.getPrenom());
        subscriptionDTO.setMatriculeUser(employee.getMatricule());
        
        subscriptionDTO.setIdFormation(formation.getId());
        subscriptionDTO.setCodeFormation(formation.getCode());
        subscriptionDTO.setThemeFormation(formation.getTheme());
        subscriptionDTO.setTypeFormation(formation.getType());
        subscriptionDTO.setOrganismeFormation(formation.getOrganisme());
        subscriptionDTO.setObjectifFormation(formation.getObjectif());
        subscriptionDTO.setDateDebutFormation(formation.getDateDebutFormation());
        subscriptionDTO.setDateClotureFormation(formation.getDateClotureFormation());
        return subscriptionDTO;
    }
    


	
	
	
	
	
	
}
