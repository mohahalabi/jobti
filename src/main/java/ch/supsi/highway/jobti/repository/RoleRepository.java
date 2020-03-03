package ch.supsi.highway.jobti.repository;


import ch.supsi.highway.jobti.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>
{

}