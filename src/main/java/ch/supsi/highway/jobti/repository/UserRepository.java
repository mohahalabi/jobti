package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User, Integer> extends JpaRepository <User, Integer>
{

}