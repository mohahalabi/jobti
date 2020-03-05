package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.Private;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateRepository extends UserRepository<Private, Integer> {
}

