package com.grokonez.jwtauthentication.controller;

import java.util.List;
import java.util.Optional;

import com.grokonez.jwtauthentication.DTOS.ParticipantDTO;
import com.grokonez.jwtauthentication.model.Participant;
import com.grokonez.jwtauthentication.model.StatusFormation;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.FormationDTO;
import com.grokonez.jwtauthentication.model.Formation;
import com.grokonez.jwtauthentication.model.Message;
import com.grokonez.jwtauthentication.model.Notification;
import com.grokonez.jwtauthentication.repository.FormationRepository;
import com.grokonez.jwtauthentication.repository.MessageRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.FormationService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class FormationController {

    @Autowired
    FormationService formationService;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ParticipantRepository participantRepository;
    
    @Autowired
    MessageRepository messageRepository;

    @GetMapping(value = "/Formations")
    public List<FormationDTO> showFormations() {

        List<FormationDTO> formationDTO = formationService.getAllFormationDTOS();
        return formationDTO;
    }

    @GetMapping("/Formations/Participants/{id}")
    @ResponseBody
    public List<ParticipantDTO> getAllParticipantsByFormationId(@PathVariable int id) {
        List<ParticipantDTO> employeeDTOS = formationService.getParticipantsByFormationId(id);
        return employeeDTOS;
    }


    @GetMapping(value = "/Formations/{id}")
    public FormationDTO getFormation(@PathVariable int id) {

        Formation formation = formationRepository.findById(id).get();
        if (formation != null) {
            FormationDTO formationDTO = formationService.convertToformationDTO(formation);
            return formationDTO;
        } else return null;
    }


    @PostMapping(value = "/Formations/create")
    public void createFormation(@RequestBody FormationDTO formationDTO) {


        Formation form = new Formation();

        form.setId(formationDTO.getId());
        form.setCode(formationDTO.getCode());
        form.setType(formationDTO.getType());
        form.setTheme(formationDTO.getTheme());
        form.setOrganisme(formationDTO.getOrganisme());
        form.setObjectif(formationDTO.getObjectif());
        form.setFormateur(formationDTO.getFormateur());
        form.setDateDebutFormation(formationDTO.getDateDebutFormation());
        form.setDateClotureFormation(formationDTO.getDateClotureFormation());
        form.setCout(formationDTO.getCout());
        form.setNbrPlace(formationDTO.getNbrPlace());
        form.setStatus(formationDTO.getStatus());


   /*final List<Employee> collect = formationDTO.getEmployees()
    		.stream()
    		.map(employee -> (employeeRepository.findById(employee.getId())))
    		.collect(Collectors.toList());
    form.setEmployees(collect);
*/
        formationRepository.save(form);

    }


    @PutMapping(value = "/Formations/update")
    public ResponseEntity<Formation> updateFormation(@RequestBody FormationDTO formationDTO) {
        System.out.println("formationDTO: " + formationDTO.getId());
        Formation formation = new Formation();

        formation.setId(formationDTO.getId());
        formation.setCode(formationDTO.getCode());
        formation.setType(formationDTO.getType());
        formation.setTheme(formationDTO.getTheme());
        formation.setObjectif(formationDTO.getObjectif());
        formation.setFormateur(formationDTO.getFormateur());
        formation.setOrganisme(formationDTO.getOrganisme());
        formation.setDateDebutFormation(formationDTO.getDateDebutFormation());
        formation.setDateClotureFormation(formationDTO.getDateClotureFormation());
        formation.setCout(formationDTO.getCout());
        formation.setNbrPlace(formationDTO.getNbrPlace());
        formation.setStatus(formationDTO.getStatus());
        formation.setActif(formationDTO.isActif());
//       List<Employee> collect = formation.getEmployees().stream()
//          		.map(employee -> employeeRepository.findById(employee.getId()))
//          		.collect(Collectors.toList());
//       formation.setEmployees(collect);


        formationRepository.save(formation);
        return new ResponseEntity<Formation>(
                formation, HttpStatus.OK);
    }

    @DeleteMapping("/Formations/delete/{id}")
    public void deleteFormations(@PathVariable int id) {
        Formation formation = formationRepository.findById(id).get();
        if (formation != null) {
            formationRepository.delete(formation);

        }
    }


    @PostMapping(value = "/Formations/inscription")
    public ResponseEntity<String> inscriptionFormation(@RequestParam("participantId") String username, @RequestParam("formationId") int formationId) {

        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);
        Optional<Formation> formation = formationRepository.findById(formationId);
        User participant = null;
        Formation forma = null;
        if (user.isPresent()) {
            participant = user.get();
        }
        if (formation.isPresent()) {
            forma = formation.get();
        }
        if (forma != null && participant != null) {
            boolean isInscrit = formationService.inscriptionFormation(participant, forma);
            if (isInscrit) {
                formationRepository.save(forma);
                return new ResponseEntity<String>("L'inscription est faite avec Success", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Le participant " + participant.getUsername() + " déja exist", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<String>("L'inscription est echoué", HttpStatus.BAD_REQUEST);

    }

    @PostMapping(value = "/Formations/approve")
    public void ApprouveUserToFormation(@RequestParam("participantId") String username,
                                                          @RequestParam("formationId") int formationId) throws Exception {
        Formation formation = formationRepository.findById(formationId).get();
        formationRepository.save(formation);
        for (Participant participant : formation.getParticipants()) {
            if (participant.getUser().getUsername().equals(username)) {
                if (participant.isApproved()) {
                    participant.setApproved(false);
                } else {
                    int nb = formation.getNbrPlace();
                    if (nb == 0) {
                        throw new Exception("L'inscription est echoué, formation fermé");
                    } else {
                        formation.setNbrPlace(nb - 1);
                        System.out.print( formation.getNbrPlace());
                    }
                    participant.setApproved(true);
                }
                participantRepository.save(participant);
            }
        }
//        return new ResponseEntity<String>("L'inscription est approuvé", HttpStatus.OK);
    }

    
/*    @PostMapping(value = "/Formations/approve")
    public void ApprouveUserToFormation(@RequestParam("participantId") String username,
                                                          @RequestParam("formationId") int formationId) throws Exception {
        Formation formation = formationRepository.findById(formationId).get();
        formationRepository.save(formation);
        for (Participant participant : formation.getParticipants()) {
            if (participant.getUser().getUsername().equals(username)) {
                if (participant.isApproved()) {
                    participant.setApproved(false);
                } else {
                    int nb = formation.getNbrPlace();
                    if (nb == 0) {
                        throw new Exception("L'inscription est echoué, formation fermé");
                    } else {
                        formation.setNbrPlace(nb - 1);
                        System.out.print( formation.getNbrPlace());
                    }
                    participant.setApproved(true);
                   Message message = new Message();
                    message.setMesssage("Pouvez etes accepté à la formation :"+formation.getCode()+
                    		"qui aura lieu à la date "+formation.getDateDebutFormation()+
                            " dans "+formation.getOrganisme() );
                    
                    messageRepository.save(message);
                   
                }
                participantRepository.save(participant);
            }
        }
//        return new ResponseEntity<String>("L'inscription est approuvé", HttpStatus.OK);
    }*/

    @GetMapping("/Formations/historic")
    public Iterable<Participant> getFormationHistoric(StatusFormation status) {
        try{

            Iterable<Participant> formationDTO = participantRepository.findAll();
            return formationDTO;
        }catch(Exception e){
            throw e;
        }

    }

    @GetMapping("/Formations/historic/{participant}")
    public List<ParticipantDTO> getFormationHistoricByParticipant(@PathVariable Integer participant) {
        List<ParticipantDTO> formationDTO = formationService.findByStatusAndParticipants(participant);
        
        return formationDTO;
    }

}
