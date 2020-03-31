package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Company;
import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.model.Role;
import ch.supsi.highway.jobti.model.WorkingExperience;
import ch.supsi.highway.jobti.repository.PrivateRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrivateService {
    @Autowired
    private PrivateRepository pvtRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private WorkingExperienceService wEService;

    public List<Private> getAll(){
        return pvtRepo.findAll();
    }

    public Private findById (String id){
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

        if(getAll().size() == 0){
            Private admin= new Private("admin","admin", "admin@jobti.ch",crypto.encode("admin"), new Role("ROLE_ADMIN"));
            save(admin);
            List<WorkingExperience> we = new ArrayList<>();
            we.add(new WorkingExperience(new Date(), new Date(), "Formazione", "Docente", "Liceo cantonale"));
            wEService.save(we.get(0));
            Private completePrivate = new Private("Luca", "Bianchi", "lucabianchi@jobti.ch", crypto.encode("privato"), new Role("ROLE_PRIVATE"),
                    "Via San Gottardo", 6600, "Locarno", "TI", "Svizzera", new Date(), "Finanza", we);
            save(completePrivate);
            Company completeCompany= new Company("Rossi", "rossi@jobti.ch", crypto.encode("azienda"),new Role("ROLE_COMPANY"), "Via ai Tigli", 6500, "Bellinzona",
                    "TI", "Svizzera", new Date(), "Costruzioni", 911234567, "SA", "www.rossi.ch", 30,123456);
            companyService.save(completeCompany);
        }
    }

    public byte[] setEmptyImage() throws IOException {
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
    }
}
