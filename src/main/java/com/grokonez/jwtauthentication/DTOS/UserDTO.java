package com.grokonez.jwtauthentication.DTOS;

import java.time.LocalDate;


public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private Integer matricule;
    private String email;
    private String nom;
    private String prenom;
    private String telephone;
    private String linkedin;
    private String bio;
    private LocalDate dateEmbauche;
    private LocalDate dateDepart;
//	    private boolean actif  = true ;
//	    private boolean deleted =false;
    // private Set<Role> roles ;

    private int idPoste;
    private String nomPoste;

    private int idEquipe;
    private String nomEquipe;

    private int idActivite;
    private String nomActivite;

    private int idDirection;
    private String nomDirection;

    private int idDirectionDeriger;
    private String nomDirectionDeriger;

    private Integer capaciteAnalyse;
    private Integer methodologie;
    private Integer maitriseStandards;
    private Integer competencesSpecifiques;
    private Integer gestionProjets;
    private Integer organisationRigueurFiabilite;
    private Integer gestionRessources;
    private Integer communicationLangue;
    private Integer capacitesRedactionnelles;
    private Integer total;

    private int idDemandeFormation;
    private String titreDemandeFormation;


    public UserDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDTO(Integer id, String username, String password, Integer matricule, String email, String nom,
                   String prenom, String telephone, LocalDate dateEmbauche, LocalDate dateDepart
    ) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.matricule = matricule;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.dateEmbauche = dateEmbauche;
        this.dateDepart = dateDepart;

    }

    public UserDTO(Integer id,
                   String username,
                   String password,
                   String email,
                   String nom,
                   String prenom,
                   String telephone,
                   String linkedin,
                   String bio,
                   String nomDirectionDeriger,
                   String nomPoste,
                   String nomEquipe,
                   LocalDate dateEmbauche,
                   LocalDate dateDepart,
                   int idPoste,
                   int idEquipe,
                   int idDirectionDeriger,
                   Integer matricule,
                   Integer capaciteAnalyse,
                   Integer methodologie,
                   Integer maitriseStandards,
                   Integer competencesSpecifiques,
                   Integer gestionProjets,
                   Integer organisationRigueurFiabilite,
                   Integer gestionRessources,
                   Integer communicationLangue,
                   Integer capacitesRedactionnelles,
                   Integer total) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.matricule = matricule;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.linkedin = linkedin;
        this.bio = bio;
        this.dateEmbauche = dateEmbauche;
        this.dateDepart = dateDepart;
        this.idPoste = idPoste;
        this.nomPoste = nomPoste;
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.idActivite = idActivite;
        this.nomActivite = nomActivite;
        this.idDirection = idDirection;
        this.nomDirection = nomDirection;
        this.idDirectionDeriger = idDirectionDeriger;
        this.nomDirectionDeriger = nomDirectionDeriger;
        this.capaciteAnalyse = capaciteAnalyse;
        this.methodologie = methodologie;
        this.maitriseStandards = maitriseStandards;
        this.competencesSpecifiques = competencesSpecifiques;
        this.gestionProjets = gestionProjets;
        this.organisationRigueurFiabilite = organisationRigueurFiabilite;
        this.gestionRessources = gestionRessources;
        this.communicationLangue = communicationLangue;
        this.capacitesRedactionnelles = capacitesRedactionnelles;
        this.total = total;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public int getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(int idDirection) {
        this.idDirection = idDirection;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public int getIdDirectionDeriger() {
        return idDirectionDeriger;
    }

    public void setIdDirectionDeriger(int idDirectionDeriger) {
        this.idDirectionDeriger = idDirectionDeriger;
    }

    public String getNomDirectionDeriger() {
        return nomDirectionDeriger;
    }

    public void setNomDirectionDeriger(String nomDirectionDeriger) {
        this.nomDirectionDeriger = nomDirectionDeriger;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdDemandeFormation() {
        return idDemandeFormation;
    }

    public void setIdDemandeFormation(int idDemandeFormation) {
        this.idDemandeFormation = idDemandeFormation;
    }

    public String getTitreDemandeFormation() {
        return titreDemandeFormation;
    }

    public void setTitreDemandeFormation(String titreDemandeFormation) {
        this.titreDemandeFormation = titreDemandeFormation;
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


}
