package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.WorkingExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingExperienceRepository extends JpaRepository<WorkingExperience, Integer> {
}
