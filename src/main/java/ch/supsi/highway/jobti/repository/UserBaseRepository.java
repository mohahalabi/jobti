package ch.supsi.highway.jobti.repository;

import ch.supsi.highway.jobti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User, String> extends JpaRepository<T, String> {
}

