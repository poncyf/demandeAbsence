package sn.supinfo.GestionAbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.supinfo.GestionAbs.constant.Statut;
import sn.supinfo.GestionAbs.model.Absences;
import sn.supinfo.GestionAbs.repository.AbsenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    // Créer une nouvelle absence
    public Absences createAbsence(Absences absence) {
        return absenceRepository.save(absence);
    }

    // Lire toutes les absences
    public List<Absences> getAllAbsences() {
        return absenceRepository.findAll();
    }

    // Lire une absence par ID
    public Optional<Absences> getAbsenceById(Long id) {
        return absenceRepository.findById(id);
    }

    // Mettre à jour une absence
    public Absences updateAbsence(Long id, Absences updatedAbsence) {
        Optional<Absences> existingAbsence = absenceRepository.findById(id);
        if (existingAbsence.isPresent()) {
            Absences absence = existingAbsence.get();
            // Mettre à jour les champs nécessaires
            absence.setNom(updatedAbsence.getNom());
            absence.setPrenom(updatedAbsence.getPrenom());
            absence.setMatricule(updatedAbsence.getMatricule());
            absence.setGroupe(updatedAbsence.getGroupe());
            absence.setNiveau(updatedAbsence.getNiveau());
            absence.setFiliere(updatedAbsence.getFiliere());
            absence.setMotifDemande(updatedAbsence.getMotifDemande());
            absence.setDateDebutDemande(updatedAbsence.getDateDebutDemande());
            absence.setDateFinDemande(updatedAbsence.getDateFinDemande());
            absence.setDateDemande(updatedAbsence.getDateDemande());
            absence.setStatut(updatedAbsence.getStatut());
            return absenceRepository.save(absence);
        } else {
            return null;
        }
    }

    // Supprimer une absence par ID
    public boolean deleteAbsence(Long id) {
        Optional<Absences> existingAbsence = absenceRepository.findById(id);
        if (existingAbsence.isPresent()) {
            absenceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void updateStatut(Long id, Statut statut) {
        Absences absence = absenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Absence non trouvée"));
        absence.setStatut(statut);
        absenceRepository.save(absence);
    }
}
