package com.grokonez.jwtauthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.DTOS.SubscriptionDTO;
import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Subscription;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.FormationRepository;
import com.grokonez.jwtauthentication.repository.SubscriptionRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.SubscriptionService;



@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class SubscriptionController {
	
	@Autowired
    SubscriptionRepository subscriptionRepository;
	@Autowired
    UserRepository employeeRepository;
	@Autowired
    FormationRepository formationRepository;
	@Autowired
    SubscriptionService subscriptionService;
	
	
    @GetMapping("/Subscription")
    @ResponseBody
    public List<SubscriptionDTO> getAllSubscriptionDTOS() {
        List<SubscriptionDTO> subscriptionDTOS = subscriptionService.getAllSubscriptionDTOS();
        return subscriptionDTOS;
    }



    @PostMapping(value = "/Subscription/create")
    public void createEmployee (@RequestBody SubscriptionDTO subscription) {

    	Subscription sub = new Subscription();

    	sub.setId(subscription.getId());
    	sub.setName(subscription.getName());
    	sub.setDescription(subscription.getDescription());
    	sub.setApprouver(subscription.isApprouver());
    	
       

        User emp = employeeRepository.findById(subscription.getIdUser()).get();
        Formation fmr = formationRepository.findById(subscription.getIdFormation()).get();

        sub.setUsers(emp);
        sub.setFormation(fmr);

    
        subscriptionRepository.save(sub);
      
    }
    
    @PutMapping(value = "/Subscription/update")
    public ResponseEntity<Void> updateSubscription(@RequestBody SubscriptionDTO subscription) {
    	Subscription sub = subscriptionRepository.findById(subscription.getId()).get();
        
    	//sub.setId(subscription.getId());
    	sub.setName(subscription.getName());
    	sub.setDescription(subscription.getDescription());
    	sub.setApprouver(subscription.isApprouver());
    	
    	User user   = employeeRepository.findById(subscription.getIdUser()).get();
        Formation formation = formationRepository.findById(subscription.getIdFormation()).get();

        if (user!=null){
            sub.setUsers(user);
        }
        if (formation!=null){
            sub.setFormation(formation);
        }
        Subscription S = subscriptionRepository.save(sub);
        if (S != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
