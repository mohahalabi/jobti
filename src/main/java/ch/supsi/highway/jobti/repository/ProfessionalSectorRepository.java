package ch.supsi.highway.jobti.repository;


import ch.supsi.highway.jobti.model.ProfessionalSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalSectorRepository extends JpaRepository<ProfessionalSector, String> {
}
