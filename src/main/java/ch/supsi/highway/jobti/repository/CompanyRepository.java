package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends UserRepository<Company, Integer> {
}
