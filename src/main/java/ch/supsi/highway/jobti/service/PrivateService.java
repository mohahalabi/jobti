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
import java.util.Calendar;
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


            Private admin= new Private("admin","admin", "admin@jobti.ch",crypto.encode("admin"), roleService.findById("ROLE_ADMIN"));
            admin.setImage(setEmptyImage(true));
            save(admin);

            populatePrivates();

            Company completeCompany= new Company("Rossi", "rossi@jobti.ch", crypto.encode("azienda"),new Role("ROLE_COMPANY"), "Via ai Tigli", 6500, "Bellinzona",
                    "TI", "Svizzera", new Date(), sectorService.findById("Costruzioni"), 911234567, "SA", "www.rossi.ch", 30,123456);
            completeCompany.setImage(setEmptyImage(false));
            companyService.save(completeCompany);
        }
    }

    public byte[] setEmptyImage(Boolean isPrivate) throws IOException {
        int seconds = Calendar.getInstance().get(Calendar.SECOND);
        if(isPrivate){
            if(seconds%3==0){
                return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user-avatar.jpg"));
            } else if(seconds%3==1){
                return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user-avatar-blue.jpg"));
            }
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user-avatar-green.jpg"));
        }
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/company-avatar.jpg"));

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

    public void populatePrivates() throws IOException {
        BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
        Calendar c = Calendar.getInstance();
        c.set(1980, Calendar.FEBRUARY, 12, 0, 0);
        Date birth = c.getTime();
        List<WorkingExperience> we = new ArrayList<>();
        we.add(new WorkingExperience(new Date(), new Date(), sectorService.findById("Costruzioni"), professionService.findById("Muratore"),"Apprendista", "Liceo cantonale"));
        wEService.save(we.get(0));
        String langs= "Italiano,Francese,Russo";
        Private completePrivate = new Private("Luca", "Bianchi", "lucabianchi@jobti.ch",
                crypto.encode("privato"), "Via San Gottardo", 6600, "Locarno",
                "TI", "Svizzera", birth, sectorService.findById("Costruzioni"),
                professionService.findById("Muratore"), we, langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1990, Calendar.MARCH, 12, 0, 0);
        birth = c.getTime();
        langs= "Italiano,Tedesco";
        completePrivate = new Private("Marco", "Di Gioia", "marco@jobti.ch",
                crypto.encode("privato"), "Via San Giovanni", 6700, "Faido",
                "TI", "Svizzera", birth, sectorService.findById("Costruzioni"),
                professionService.findById("Architetto"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1980, Calendar.MARCH, 12, 0, 0);
        birth = c.getTime();
        langs= "Italiano,Ingelese";
        completePrivate = new Private("Mario", "Bernasconi", "berna@jobti.ch",
                crypto.encode("privato"), "Via Cantonale ", 6900, "Lugano",
                "TI", "Svizzera", birth, sectorService.findById("Sanità"),
                professionService.findById("Farmacista"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1989, Calendar.JANUARY, 10, 0, 0);
        birth = c.getTime();
        langs= "Ingelese,Tedesco";
        completePrivate = new Private("Livia", "Benvenuti", "livia@jobti.ch",
                crypto.encode("privato"), "Via Principale ", 6542, "Giumaglio",
                "TI", "Svizzera", birth, sectorService.findById("IT e Media"),
                professionService.findById("App Developer"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1985, Calendar.OCTOBER, 10, 0, 0);
        birth = c.getTime();
        langs= "Italiano,Francese,Ingelese,Tedesco";
        completePrivate = new Private("Celio", "Fanucci", "celio@jobti.ch",
                crypto.encode("privato"), "Bahnhofstrasse ", 2855, "Glovelier",
                "JU", "Svizzera", birth, sectorService.findById("Turismo e Ristorazione"),
                professionService.findById("Bartender"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1992, Calendar.DECEMBER, 10, 0, 0);
        birth = c.getTime();
        langs= "Italiano,Francese,Tedesco,Russso";
        completePrivate = new Private("Carola", "Ferri", "caro@jobti.ch",
                crypto.encode("privato"), "Brumakerstrasse", 8038, "Zürich",
                "ZH", "Svizzera", birth, sectorService.findById("Sanità"),
                professionService.findById("Pediatra"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1972, Calendar.AUGUST, 10, 0, 0);
        birth = c.getTime();
        langs= "Russso";
        completePrivate = new Private("Lina", "Napolitani", "lina@jobti.ch",
                crypto.encode("privato"), "Via gabbietta", 1337, "Valorb",
                "GE", "Svizzera", birth, sectorService.findById("Sanità"),
                professionService.findById("Omeopata"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);

        c.set(1993, Calendar.SEPTEMBER, 10, 0, 0);
        birth = c.getTime();
        langs= "Tedesco,Francese";
        completePrivate = new Private("Valerio", "Lorenzo", "vale@jobti.ch",
                crypto.encode("privato"), "Via Crucis", 6853, "Origlio",
                "TI", "Svizzera", birth, sectorService.findById("Costruzioni"),
                professionService.findById("Ingegnere minerario"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        save(completePrivate);
    }
}
