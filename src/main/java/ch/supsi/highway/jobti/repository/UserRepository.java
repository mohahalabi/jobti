package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.User;
import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository <User, String>
{

}