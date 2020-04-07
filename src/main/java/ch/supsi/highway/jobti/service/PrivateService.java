package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.JobtiApplication;
import ch.supsi.highway.jobti.model.*;
import ch.supsi.highway.jobti.repository.PrivateRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
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
    @Autowired
    private ProfessionalSectorService sectorService;
    @Autowired
    private ProfessionService professionService;

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

        //creazione profesioni e settori
        if(sectorService.getAll().size() == 0){
            populateDBprofessions("/static/db/professions.txt");
        }

        //creazione role
        if(roleService.getAll().size() == 0) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_COMPANY"));
            roleService.save(new Role("ROLE_PRIVATE"));
        }

        if(getAll().size() == 0){
            List<WorkingExperience> we = new ArrayList<>();
            we.add(new WorkingExperience(new Date(), new Date(), sectorService.getOne("Costruzioni"), professionService.getOne("Muratore"),"Apprendista", "Liceo cantonale"));
            wEService.save(we.get(0));

            Private admin= new Private("admin","admin", "admin@jobti.ch",crypto.encode("admin"), roleService.findById("ROLE_ADMIN"));
            save(admin);

            Private completePrivate = new Private("Luca", "Bianchi", "lucabianchi@jobti.ch", crypto.encode("privato"),
                    "Via San Gottardo", 6600, "Locarno", "TI", "Svizzera", new Date(), sectorService.getOne("Costruzioni"), professionService.getOne("Muratore"), we );
            save(completePrivate);
            Company completeCompany= new Company("Rossi", "rossi@jobti.ch", crypto.encode("azienda"),new Role("ROLE_COMPANY"), "Via ai Tigli", 6500, "Bellinzona",
                    "TI", "Svizzera", new Date(), sectorService.getOne("Costruzioni"), 911234567, "SA", "www.rossi.ch", 30,123456);
            companyService.save(completeCompany);
        }
    }

    public byte[] setEmptyImage() throws IOException {
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
    }

    public void populateDBprofessions(String fileName) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ProfessionalSector s= null;
        List<Profession> ps = null;
        while(reader.ready()) {
            String line = reader.readLine();

            if (line.startsWith("- ") && ps!=null){
                ps.add(new Profession(line.substring(2)));
            } else {
                if(s!=null && ps != null){
                    professionService.saveAll(ps);
                    s.setProfessions(ps);
                    sectorService.save(s);
                }
                s= new ProfessionalSector(line);
                ps = new ArrayList<>();
            }
        }
        if(s!=null && ps!=null){
            professionService.saveAll(ps);
            s.setProfessions(ps);
            sectorService.save(s);
        }
    }
}
