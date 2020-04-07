package ch.supsi.highway.jobti.repository;



import ch.supsi.highway.jobti.model.Profession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, String> {
}
