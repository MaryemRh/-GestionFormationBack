package com.grokonez.jwtauthentication.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.grokonez.jwtauthentication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.grokonez.jwtauthentication.DTOS.DemandeFormationDTO;
import com.grokonez.jwtauthentication.DTOS.FormationDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.message.request.LoginRequest;
import com.grokonez.jwtauthentication.message.request.SignupRequest;
import com.grokonez.jwtauthentication.message.response.JwtResponse;
import com.grokonez.jwtauthentication.message.response.MessageResponse;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import com.grokonez.jwtauthentication.repository.DemandeFormationRepository;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import com.grokonez.jwtauthentication.repository.EquipeRepository;
import com.grokonez.jwtauthentication.repository.PosteRepository;
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;
import com.grokonez.jwtauthentication.security.services.UserDetailsImpl;
import com.grokonez.jwtauthentication.security.services.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtUtils;

    @Autowired
    PosteRepository posteRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;
    @Autowired
    DemandeFormationRepository demandeFormationRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("user"+userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),

                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_MODERATOR":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/AddEmployee")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setMatricule(userDTO.getMatricule());
        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        user.setPrenom(userDTO.getPrenom());
        user.setTelephone(userDTO.getTelephone());
        user.setDateDepart(userDTO.getDateDepart());
        user.setDateEmbauche(userDTO.getDateEmbauche());
        Equipe equipe = equipeRepository.findByNomEquipe(userDTO.getNomEquipe());
        user.setEquipe(equipe);
        user.setGrade(Grade.Employee);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Employee added successfully!"));
    }

    @GetMapping("/Employees")
    @ResponseBody
    public List<UserDTO> getAllEmployeesDTOS() {
        List<UserDTO> employeeDTOS = employeeService.getAllemployeeDTOS();
        return employeeDTOS;
    }


    @GetMapping(value = "/users")
    public UserDTO getUserByUsername(@RequestParam("username") String username) {

        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            UserDTO employeeDTOS = employeeService.convertToUserDTO(user);
            return employeeDTOS;
        } else return null;
    }

    @GetMapping(value = "/userss")
    public List<UserDTO> getUsers() {

        List<UserDTO> employeeDTOS = employeeService.getAllemployeeDTOS();
        return employeeDTOS;

    }
	  /*  @GetMapping(value = "/Employees/email/{email}")
	    public UserDTO getEmployee(@PathVariable String email ){

	    	User employee = userRepository.findByEmail(email);
	        if(employee!=null) {
	        	UserDTO employeeDTOS = employeeService.convertToUserDTO(employee);
	            return employeeDTOS;
	        }
	        else return  null;
	    }*/

/*
	    @GetMapping("/Employees/status/{id}")
	    public ResponseEntity<Void> changeStatusF(@PathVariable int id) {
	    	User employee = userRepository.findById((long) id).get();
	        if(employee != null ){
	            employee.setActif(!employee.isActif());
	            final User save = userRepository.save(employee);
	            return ResponseEntity.accepted().build();
	        }
	        return ResponseEntity.notFound().build();
	    }*/

    @DeleteMapping("/Employees/delete/{id}")
    @ResponseBody
    public void deleteEmployee(@PathVariable int id) {
        User employee = userRepository.findById((Integer) id).get();
        if (employee != null) {
            // employee.setDeleted(!employee.isDeleted());
            userRepository.delete(employee);

        }
    }

    @PutMapping(value = "/Employees/update")
    public ResponseEntity<Void> updateEmployee(@RequestBody UserDTO employee) {
	    	
        System.out.println("employeeDTO: " + employee.getId());
        User user = new User();
        user.setId(employee.getId());
        user.setUsername(employee.getUsername());
        user.setPassword(employee.getPassword());
        user.setMatricule(employee.getMatricule());
        user.setEmail(employee.getEmail());
        user.setNom(employee.getNom());
        user.setPrenom(employee.getPrenom());
        user.setTelephone(employee.getTelephone());
        user.setDateDepart(LocalDate.now());
        user.setDateEmbauche(LocalDate.now());
        user.setCapaciteAnalyse(employee.getCapaciteAnalyse());
        user.setMethodologie(employee.getMethodologie());
        user.setMaitriseStandards(employee.getMaitriseStandards());
        user.setCompetencesSpecifiques(employee.getCompetencesSpecifiques());
        user.setGestionProjets(employee.getGestionProjets());
        user.setOrganisationRigueurFiabilite(employee.getOrganisationRigueurFiabilite());
        user.setGestionRessources(employee.getGestionRessources());
        user.setCommunicationLangue(employee.getCommunicationLangue());
        user.setCapacitesRedactionnelles(employee.getCapacitesRedactionnelles());
        user.setTotal(employee.getTotal());

        Poste poste = posteRepository.findById(employee.getIdPoste());
        Equipe equipe = equipeRepository.findById(employee.getIdEquipe());

        if (poste != null) {
            user.setPoste(poste);
        }
        if (equipe != null) {
            user.setEquipe(equipe);
        }

        User E = userRepository.save(user);
        if (E != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(value = "/Employees/patch")
    @ResponseStatus(HttpStatus.OK)
    public void patchEmployee(@RequestBody UserDTO employee) {


        User user = employeeService.convertToUser(employee);

        User E = userRepository.save(user);

    }


    @PutMapping(value = "Employees/register")
    public ResponseEntity<Void> registerEmployee(@RequestBody UserDTO employee) {
        User emp = userRepository.findById(employee.getId()).get();
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        if (employee.getUsername() != null) {
            emp.setUsername(employee.getUsername());
        }
        if (employee.getPassword() != null) {
            emp.setPassword(employee.getPassword());
        }
        if (employee.getMatricule() != null) {
            emp.setMatricule(employee.getMatricule());
        }
        if (employee.getEmail() != null) {
            emp.setEmail(employee.getEmail());
        }
        if (employee.getNom() != null) {
            emp.setNom(employee.getNom());
        }
        if (employee.getPrenom() != null) {
            emp.setPrenom(employee.getPrenom());
        }
        if (employee.getTelephone() != null) {
            emp.setTelephone(employee.getTelephone());
        }
        // if( employee.getRoles()    != null) {emp.setRoles(employee.getRoles());}

        emp.setDateDepart(LocalDate.now());
        // emp.setActif(true);

        User E = userRepository.save(emp);
        if (E != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(value = "/Employees/create")
    public void createEmployee(@RequestBody UserDTO employee) {


        User emp = new User();

        emp.setId(employee.getId());
        emp.setUsername(employee.getUsername());
        emp.setPassword(employee.getPassword());
        emp.setMatricule(employee.getMatricule());
        emp.setEmail(employee.getEmail());
        emp.setNom(employee.getNom());
        emp.setPrenom(employee.getPrenom());
        emp.setTelephone(employee.getTelephone());
        emp.setDateEmbauche(LocalDate.now());
        //emp.setRoles(employee.getRoles());
        //emp.setActif(false);
        //emp.setDeleted(false);

        Poste poste = posteRepository.findById(employee.getIdPoste());
        Equipe equipe = equipeRepository.findById(employee.getIdEquipe());
        Activite activite = activiteRepository.findById(employee.getIdActivite());
        //     Direction direction = directionRepository.findById(employee.getIdDirection());

        emp.setPoste(poste);
        emp.setEquipe(equipe);
        //     emp.setActivite(activite);
        //   emp.setDirection(direction);

        userRepository.save(emp);

    }

    @GetMapping("/Employees/DemandeForamationByUserId/{id}")
    @ResponseBody
    public List<DemandeFormationDTO> getDemandeFormationByUserId(@PathVariable int id) {

        List<DemandeFormationDTO> employeeDTOS = demandeFormationRepository.getDemandeFormationByUserId(id);
        return employeeDTOS;
    }


    @GetMapping(value = "/Employees/{id}")
    public UserDTO getUser(@PathVariable Integer id) {

        User user = userRepository.findById(id).get();
        if (user != null) {
            UserDTO userDTO = employeeService.convertToUserDTO(user);
            return userDTO;
        } else
            return null;
    }


}