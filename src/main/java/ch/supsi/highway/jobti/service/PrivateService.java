package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.model.Role;
import ch.supsi.highway.jobti.repository.PrivateRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class PrivateService {
    @Autowired
    private PrivateRepository pvtRepo;
    @Autowired
    private RoleService roleService;

    public List<Private> getAll(){
        return pvtRepo.findAll();
    }

    public Private findById (int id){
        return pvtRepo.findById(id).orElse(null);
    }

    public Private save(Private pvt){
        return pvtRepo.save(pvt);
    }
    public void delete (Private pvt){
        pvtRepo.delete(pvt);
    }


    @PostConstruct
    public void init() throws IOException {
        BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();


        if(roleService.getAll().size() == 0) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_COMPANY"));
            roleService.save(new Role("ROLE_PRIVATE"));
        }

//        if(getAll().size() == 0){
//            Private admin= new Private("admin","admin", "admin@jobti.ch","1234", new Role("ROLE_ADMIN")  );
//            save(admin);
//        }
    }

    public byte[] setEmptyImage() throws IOException {
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
    }
}
