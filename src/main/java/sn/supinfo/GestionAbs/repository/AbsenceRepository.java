package sn.supinfo.GestionAbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sn.supinfo.GestionAbs.model.Absences;

public interface AbsenceRepository extends JpaRepository<Absences, Long> {
}