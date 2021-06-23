package com.grokonez.jwtauthentication.controller;

import java.util.List;

import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.DemandeFormationDTO;
import com.grokonez.jwtauthentication.repository.DemandeFormationRepository;
import com.grokonez.jwtauthentication.repository.FormationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.DemandeFormationService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class DemandeFormationController {

    @Autowired
    DemandeFormationRepository demandeFormationRepository;
    @Autowired
    UserRepository employeeRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    DemandeFormationService demandeFormationService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/DemandeFormation")
    public List<DemandeFormationDTO> showDemandeFormationDTOS() {

        List<DemandeFormationDTO> demandeFormationDTOS = demandeFormationService.getAllDemandeFormationDTOS();
        return demandeFormationDTOS;
    }
    @GetMapping(value = "/DemandeFormationByUser")
    public List<DemandeFormationDTO> showDemandeFormationByUserDTOS(@RequestParam("user") String username, @RequestParam("assigne") String usernameAssigne) {
        User user = employeeRepository.findByUsername(username).get();
        User assigne = employeeRepository.findByUsername(usernameAssigne).get();
        List<DemandeFormationDTO> demandeFormationDTOS = demandeFormationService.getDemandeFormationDTOS(user,assigne);
        return demandeFormationDTOS;
    }



    @GetMapping(value = "/DemandeFormation/{id}")
    public DemandeFormationDTO getDemandeFormation(@PathVariable int id) {

        DemandeFormation demandeFormation = demandeFormationRepository.findById(id).get();
        if (demandeFormation != null) {
            DemandeFormationDTO demandeFormationDTO = demandeFormationService.convertToDemandeFormationDTO(demandeFormation);
            return demandeFormationDTO;
        } else return null;
    }


    @PostMapping(value = "/DemandeFormation/create")
    public void createDemandeFormation(@RequestBody DemandeFormationDTO demandeFormation) {

        DemandeFormation demFormation = new DemandeFormation();

        demFormation.setId(demandeFormation.getId());
        //emp.setStatus(employee.getStatus());
        demFormation.setPriorite(demandeFormation.getPriorite());
        demFormation.setDateEmission(demandeFormation.getDateEmission());
        demFormation.setBesoins(demandeFormation.getBesoins());
        demFormation.setTitre(demandeFormation.getTitre());
        demFormation.setDescription(demandeFormation.getDescription());
        demFormation.setTheme(demandeFormation.getTheme());
        demFormation.setActivite(demandeFormation.getActivite());
        demFormation.setEquipe(demandeFormation.getEquipe());
        demFormation.setDirection(demandeFormation.getDirection());
        demFormation.setStatus(StatusDemande.EnAttend);
        User user = employeeRepository.findByUsername(demandeFormation.getNomUser()).get();
        if (user != null) {
            demFormation.setUser(user);
        }

        demandeFormationRepository.save(demFormation);

    }

    @PutMapping(value = "/DemandeFormation/update")
    public void updateDemandeFormation(@RequestBody DemandeFormationDTO demandeFormationDTO) {

        DemandeFormation demandeFormation = new DemandeFormation();

        //demandeFormationRepository.findById(demandeFormationDTO.getId());
        demandeFormation.setId(demandeFormationDTO.getId());
        demandeFormation.setStatus(demandeFormationDTO.getStatus());
        /*emp.setPriorite(employee.getPriorite());  
        emp.setDateEmission(employee.getDateEmission());
        emp.setBesoins(employee.getBesoins());        
        emp.setTitre(employee.getTitre());            
        emp.setDescription(employee.getDescription());      
        emp.setTheme(employee.getTheme());
        emp.setActivite(employee.getActivite());       
        emp.setEquipe(employee.getEquipe());     
        emp.setDirection(employee.getDirection());
   
        Employee user   = employeeRepository.findById(employee.getIdUser()).get();
        Formation formation = formationRepository.findById(employee.getIdFormation()).get();

        if (user!=null){
            emp.setUser(user);
        }
        if (formation!=null){
            emp.setFormation(formation);
        }*/
        demandeFormationRepository.save(demandeFormation);

    }

    @DeleteMapping("/DemandeFormation/delete/{id}")
    public void deleteDemandeFormation(@PathVariable int id) {

        DemandeFormation demandeFormation = demandeFormationRepository.findById(id).get();
        if (demandeFormation != null) {
            demandeFormationRepository.delete(demandeFormation);
        }
    }

    @PostMapping(value = "/DemandeFormation/approuve")
    public void isApprouved(@RequestParam("username") String username, @RequestParam("formationName") String formationName, @RequestParam("action") String action) {
        User user = employeeRepository.findByUsername(username).get();

        DemandeFormation formation = demandeFormationRepository.findByTitre(formationName);
        if (Grade.Employee.equals(user.getGrade())) {
            if (action.equals("yes")) {
                formation.setApprouved(true);
                formation.setStatus(StatusDemande.EnAttend);
                for (User equipeUsers : user.getEquipe().getUsers()) {
                    if (Grade.ChefEquipe.equals(equipeUsers.getGrade())) {
                        formation.setAssigne(equipeUsers);
                        Notification notification = new Notification();
                        notification.setMesssage("Pouvez vous Valider la demander de Formation :"+formation.getTitre()+" de" +
                                " la part de l'utlisateur " +formation.getUser().getUsername() );
                        notification.setUser(equipeUsers);
                        notificationRepository.save(notification);

                    }
                }
            } else {
                formation.setStatus(StatusDemande.Refuser);
            }

        } else if (Grade.ChefEquipe.equals(user.getGrade())) {
            if (action.equals("yes")) {
                formation.setApprouved(true);
                formation.setStatus(StatusDemande.EnAttend);
                User Assigne = formation.getUser().getEquipe().getActivite().getUser();
                if (Grade.Manager.equals(Assigne.getGrade())) {
                    formation.setAssigne(Assigne);
                    Notification notification = new Notification();
                    notification.setMesssage("Pouvez vous Valider la demander de Formation :"+formation.getTitre()+" de" +
                            " la part de l'utlisateur " +formation.getUser().getUsername() );
                    notification.setUser(Assigne);
                    notificationRepository.save(notification);
                }
            } else {
                formation.setStatus(StatusDemande.Refuser);
            }
        } else if (Grade.Manager.equals(user.getGrade())) {
            if (action.equals("yes")) {
                formation.setApprouved(true);
                formation.setStatus(StatusDemande.EnAttend);
                User Assigne = formation.getUser().getEquipe().getActivite().getDirection().getUser();
                if (Grade.Directeur.equals(Assigne.getGrade())) {
                    formation.setAssigne(Assigne);
                    Notification notification = new Notification();
                    notification.setMesssage("Pouvez vous Valider la demander de Formation :"+formation.getTitre()+" de" +
                            " la part de l'utlisateur " +formation.getUser().getUsername() );
                    notification.setUser(Assigne);
                    notificationRepository.save(notification);

                }
            } else {
                formation.setStatus(StatusDemande.Refuser);
            }


        } else if (Grade.Directeur.equals(user.getGrade())) {
            if (action.equals("yes")) {
                formation.setApprouved(true);
                formation.setStatus(StatusDemande.Accepter);
                Notification notification = new Notification();
                notification.setMesssage("Pouvez vous Valider la demander de Formation :"+formation.getTitre()+" de" +
                        " la part de l'utlisateur " +formation.getUser().getUsername() );
               // notification.setUser(Assigne);
                notificationRepository.save(notification);

            } else {
                formation.setStatus(StatusDemande.Refuser);
            }
        }
        demandeFormationRepository.save(formation);

    }

}
