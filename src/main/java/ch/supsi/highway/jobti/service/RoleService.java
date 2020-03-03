package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Role;
import ch.supsi.highway.jobti.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role findById (String id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role save(Role role){
        return roleRepository.save(role);

    }

    public void delete (Role role){
        roleRepository.delete(role);
    }

    public Optional<Role> findOne (Role role){
        return roleRepository.findOne(Example.of(role));
    }
}