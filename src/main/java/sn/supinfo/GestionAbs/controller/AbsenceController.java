package sn.supinfo.GestionAbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.supinfo.GestionAbs.constant.Statut;
import sn.supinfo.GestionAbs.model.Absences;
import sn.supinfo.GestionAbs.service.AbsenceService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("absence", new Absences());
        return "demandeAbscenceForme";
    }

    @PostMapping("/create")
    public String createAbsence(@ModelAttribute("absence") Absences absence) {
        absenceService.createAbsence(absence);
        return "redirect:/absences/list";
    }

    @GetMapping("/list")
    public String listAbsences(Model model) {
        List<Absences> absences = absenceService.getAllAbsences();
        model.addAttribute("absences", absences);
        return "listeAbsence";
    }

    // Lire une absence par ID
    @GetMapping("/{id}")
    public ResponseEntity<Absences> getAbsenceById(@PathVariable Long id) {
        Optional<Absences> absence = absenceService.getAbsenceById(id);
        return absence.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour une absence
    @PutMapping("/update/{id}")
    public ResponseEntity<Absences> updateAbsence(@PathVariable Long id, @RequestBody Absences updatedAbsence) {
        Absences absence = absenceService.updateAbsence(id, updatedAbsence);
        if (absence != null) {
            return ResponseEntity.ok(absence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une absence par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        boolean deleted = absenceService.deleteAbsence(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/approve/{id}")
    public String approveAbsence(@PathVariable("id") Long id) {
        absenceService.updateStatut(id, Statut.Approuvé);
        return "redirect:/absences/list";
    }

    @GetMapping("/reject/{id}")
    public String rejectAbsence(@PathVariable("id") Long id) {
        absenceService.updateStatut(id, Statut.Refusé);
        return "redirect:/absences/list";
    }

    @GetMapping("/info/{id}")
    public String AttenteInfoAbsence(@PathVariable("id") Long id) {
        absenceService.updateStatut(id, Statut.Info_Suplementaire);
        return "redirect:/absences/list";
    }


}
