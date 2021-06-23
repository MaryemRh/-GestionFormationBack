package com.grokonez.jwtauthentication.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Size( max = 50)
    private String nom;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 100)
    private String password;
    
    private Integer matricule;
    private String prenom;
    private String telephone;
    private String linkedin;
    private String bio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateEmbauche;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateDepart ;
    //private boolean actif ;
   	private Integer capaciteAnalyse ;
   	private Integer methodologie;
   	private Integer maitriseStandards ;
   	private Integer competencesSpecifiques ;
   	private Integer gestionProjets ;
   	private Integer organisationRigueurFiabilite ;
   	private Integer gestionRessources ;
   	private Integer communicationLangue ;
   	private Integer capacitesRedactionnelles ;
   	private Integer total ;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Grade grade;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "userid"), 
    	inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Notification> notifications = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	//@JsonBackReference
	private List<Participant> participants; 

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST,  fetch = FetchType.LAZY)
	//@JsonManagedReference
	private List<Experience> experiences;

    @ManyToOne
    @JoinColumn(name = "id_poste")
	//@JsonBackReference
    private Poste poste;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
	//@JsonBackReference
    private Equipe equipe;


	//@JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeFormation> demandeFormation;

    
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Subscription subscription;
    
    public User() {}

    public User(String nom, String username, String email, String password) {
        this.nom = nom;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username2, String email2, String encode) {
		// TODO Auto-generated constructor stub
    	 
         this.username = username2;
         this.email = email2;
         this.password = encode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Integer getCapaciteAnalyse() {
		return capaciteAnalyse;
	}

	public void setCapaciteAnalyse(Integer capaciteAnalyse) {
		this.capaciteAnalyse = capaciteAnalyse;
	}

	public Integer getMethodologie() {
		return methodologie;
	}

	public void setMethodologie(Integer methodologie) {
		this.methodologie = methodologie;
	}

	public Integer getMaitriseStandards() {
		return maitriseStandards;
	}

	public void setMaitriseStandards(Integer maitriseStandards) {
		this.maitriseStandards = maitriseStandards;
	}

	public Integer getCompetencesSpecifiques() {
		return competencesSpecifiques;
	}

	public void setCompetencesSpecifiques(Integer competencesSpecifiques) {
		this.competencesSpecifiques = competencesSpecifiques;
	}

	public Integer getGestionProjets() {
		return gestionProjets;
	}

	public void setGestionProjets(Integer gestionProjets) {
		this.gestionProjets = gestionProjets;
	}

	public Integer getOrganisationRigueurFiabilite() {
		return organisationRigueurFiabilite;
	}

	public void setOrganisationRigueurFiabilite(Integer organisationRigueurFiabilite) {
		this.organisationRigueurFiabilite = organisationRigueurFiabilite;
	}

	public Integer getGestionRessources() {
		return gestionRessources;
	}

	public void setGestionRessources(Integer gestionRessources) {
		this.gestionRessources = gestionRessources;
	}

	public Integer getCommunicationLangue() {
		return communicationLangue;
	}

	public void setCommunicationLangue(Integer communicationLangue) {
		this.communicationLangue = communicationLangue;
	}

	public Integer getCapacitesRedactionnelles() {
		return capacitesRedactionnelles;
	}

	public void setCapacitesRedactionnelles(Integer capacitesRedactionnelles) {
		this.capacitesRedactionnelles = capacitesRedactionnelles;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<DemandeFormation> getDemandeFormation() {
		return demandeFormation;
	}

	public void setDemandeFormation(List<DemandeFormation> demandeFormation) {
		this.demandeFormation = demandeFormation;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}


	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	public void addNotifications(Notification notifications) {
		this.notifications.add(notifications);
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}