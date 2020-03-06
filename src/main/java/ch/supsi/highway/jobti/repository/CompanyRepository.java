package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.Company;
import javax.transaction.Transactional;

@Transactional
public interface CompanyRepository extends UserBaseRepository<Company, String> {
}
