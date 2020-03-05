package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.Private;
import javax.transaction.Transactional;

@Transactional
public interface PrivateRepository extends UserBaseRepository<Private, String> {
}

