package com.grokonez.jwtauthentication.security.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.grokonez.jwtauthentication.DTOS.ParticipantDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.ParticipantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grokonez.jwtauthentication.DTOS.FormationDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.repository.FormationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;


@Service
public class FormationService {
@Autowired
    ModelMapper modelMapper;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Transactional
    public List<ParticipantDTO> getParticipantsByFormationId(int id) {
        Formation formation = formationRepository.getFormationById(id).get();
        List<User> users = new ArrayList<>();
        for(Participant participant : formation.getParticipants()){
            users.add(participant.getUser());
        }
        return ( formation.getParticipants())
                .stream()
                .map(this::convertToParticipantDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FormationDTO> getAllFormationDTOS() {

        return ((List<Formation>) formationRepository.findAll())
                .stream()
                .map(this::convertToformationDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ParticipantDTO> findByStatusAndParticipants(Integer participant) {

        return ((List<Participant>) participantRepository.findByParticipant( participant))
                .stream()
                .map(this::convertToParticipantDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FormationDTO> getAllHistoric(StatusFormation cloturer) {

        return ((List<Formation>) formationRepository.findByStatus(StatusFormation.Cloturer))
                .stream()
                .map(this::convertToformationDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public FormationDTO convertToformationDTO(Formation formation) {

        FormationDTO formationDTO = new FormationDTO();

        formationDTO.setId(formation.getId());
        formationDTO.setCode(formation.getCode());
        formationDTO.setType(formation.getType());
        formationDTO.setTheme(formation.getTheme());
        formationDTO.setOrganisme(formation.getOrganisme());
        formationDTO.setObjectif(formation.getObjectif());
        formationDTO.setFormateur(formation.getFormateur());
        formationDTO.setDateDebutFormation(formation.getDateDebutFormation());
        formationDTO.setDateClotureFormation(formation.getDateClotureFormation());
        formationDTO.setCout(formation.getCout());
        formationDTO.setNbrPlace(formation.getNbrPlace());
        if(formation !=null){
        	formationDTO.setStatus(formation.getStatus());
        }
        
        System.out.println("Formation : " + formation.isActif());
        formationDTO.setActif(formation.isActif());
      //  formationDTO.setUsers(collect);
        return formationDTO;
    }

    @Transactional
    public UserDTO convertToEmployeeDTO(User employee) {
        UserDTO employeeDTO = new UserDTO();

        Poste poste = employee.getPoste();
        Equipe equipe = employee.getEquipe();
 
		if (poste != null) {
			employeeDTO.setIdPoste(poste.getId());
			employeeDTO.setNomPoste(poste.getNomPoste());
		}
		if (equipe != null) {
			employeeDTO.setIdEquipe(equipe.getId());
			employeeDTO.setNomEquipe(equipe.getNomEquipe());
		}
		;

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



    @Transactional
    public Boolean inscriptionFormation(User user, Formation formation) {
        Participant part = new Participant();
        part.setFormation(formation);
        part.setUser(user);
        part.setApproved(false);
        if (formation.getParticipants() != null && formation.getParticipants().isEmpty()) {
            formation.addParticipants(part);
            return true;

        } else {
            for (Participant parts : formation.getParticipants()) {

                if (!parts.getUser().getUsername().equals(user.getUsername()) || !parts.getFormation().equals(formation)) {
                    formation.addParticipants(parts);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
//todo refactoring to participantService
    @Transactional
    public ParticipantDTO convertToParticipantDTO(Participant participant) {

//        ParticipantDTO participantDTO = new ParticipantDTO();
        ParticipantDTO participantDTO=modelMapper.map(participant,ParticipantDTO.class);
//        FormationDTO formationDTO = new FormationDTO();
//        UserDTO userDTO = new UserDTO( );
//        participantDTO.setId(participant.getId());
//        participantDTO.setApproved(participant.isApproved());
//        participantDTO.setUser(userDTO);
//        participantDTO.setFormation(formationDTO);
//        participantDTO.setFormation(formationDTO);
        return participantDTO;
    }
}
