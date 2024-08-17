package sn.supinfo.GestionAbs.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import sn.supinfo.GestionAbs.constant.Statut;
import java.time.LocalDate;


@Entity
@Table(name = "absences")
public class Absences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String prenom;

    @Column(name = "matricule", nullable = false)
    private String matricule;

    private String groupe;
    private String niveau;
    private String filiere;
    private String motifDemande;

    private String dateDebutDemande;
    private String dateFinDemande;
    private String dateDemande;

    @PrePersist
    protected void onCreate() {
        this.dateDemande = LocalDate.now().toString(); // Définit la date actuelle avant d'enregistrer l'entité
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Statut statut = Statut.Attente;  // Initialisation par défaut

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getMotifDemande() {
        return motifDemande;
    }

    public void setMotifDemande(String motifDemande) {
        this.motifDemande = motifDemande;
    }

    public String getDateDebutDemande() {
        return dateDebutDemande;
    }

    public void setDateDebutDemande(String dateDebutDemande) {
        this.dateDebutDemande = dateDebutDemande;
    }

    public String getDateFinDemande() {
        return dateFinDemande;
    }

    public void setDateFinDemande(String dateFinDemande) {
        this.dateFinDemande = dateFinDemande;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
