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
import java.util.*;

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
    @Autowired
    private EducationService eduService;

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
                return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
            } else if(seconds%3==1){
                return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
            }
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
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
        List<WorkingExperience> we = new ArrayList<>();
        we.add(new WorkingExperience(new Date(), new Date(), sectorService.findById("Costruzioni"), professionService.findById("Muratore"),"Apprendista", "Liceo cantonale"));
        wEService.save(we.get(0));


        String [] names = {"Sofia", "Giulia", "Aurora", "Alice", "Ginevra", "Emma", "Giorgia", "Greta", "Martina", "Beatrice",
                "Luca", "Fabio", "Giulio","Pietro", "Dario", "Paolo", "Carlo", "Enrico", "Mario", "Diego" };
        String [] surnames ={"Rossi", "Ferrari", "Russo", "Bianchi", "Romano", "Gallo", "Costa", "Fontana", "Moretti", "Marino",
                "Barbieri", "Lombardi", "Giordano", "Rinaldi", "Mancini", "Martini", "Ferri", "Ferraro", "Guerra", "Carbone"};

        String  [] address = {"Via della Pace","Via Nessi"," Via ai Saleggi","Via Dogana","Via Ospedale", "Via Daro",
                "Via Zurigo","Via Trevano", "Viale Cassone", "Via Curti", "Via Soldati", "Via Odescalchi", "Via Pozzi",
                "Via alla Torre", "Via Zorzi", "Via Fontana", "Via Zurigo", "Via Motta", "Via Cantonale", "Via Monte Ceneri" };
        int [] postcodes = {6600, 6500, 6900, 6830, 6850, 6710, 6593};
        String [] cities = {"Locarno", "Bellinzona", "Lugano", "Chiasso", "Mendrisio", "Biasca", "Cadenazzo"};
        String [] lang = {"Italiano,Francese,Tedesco,Inglese", "Italiano,Tedesco,Inglese", "Inglese,Francese,Spagnolo",
                "Italiano,Inglese,Spagnolo", "Italiano,Ingelese,Francese,Tedesco,Russo"};
        for (int i = 0; i < 20; i++) {
            Private first = buildPrivate(lang[i/4], names[i],surnames[i],address[i], postcodes[i/3],cities[i/3]);
            if (i%3==0){
                first.getEducationList().add(buildEduPriv(first, "Master"));
                if (i%6==0){
                    first.getExperiences().add(buildExperience(first));
                }
            }

            save(first);
        }
    }

    public Private buildPrivate(String langs, String name, String surname, String address, int postcode, String city) throws IOException {
        BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
        Calendar c = Calendar.getInstance();
        c.set(getRand(1960,2002), getRand(1,12), getRand(1,31), 0, 0);
        Date birth = c.getTime();

        Private completePrivate = new Private(name, surname, name+surname+"@jobti.ch",
                crypto.encode("privato"), address, postcode, city,
                "TI", "Svizzera", birth, sectorService.findById("IT e Media"),
                professionService.findById("Ingegnere informatico"), new ArrayList(), langs );
        completePrivate.setImage(setEmptyImage(true));
        completePrivate.getEducationList().add(buildEdu(c.get(Calendar.YEAR)+18, "Bachelor"));
        return completePrivate;
    }

    private static int getRand(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private Education buildEdu(int init, String degree){
        Calendar c = Calendar.getInstance();
        c.set(init, Calendar.SEPTEMBER, 1, 0, 0);
        Date begin = c.getTime();
        c.set(init+3, Calendar.SEPTEMBER, 1, 0, 0);
        Date end = c.getTime();
        Education edu = new Education(begin, end, "SUP, ASP, SPF o universitaria", "SUPSI DTI", degree );
        eduService.save(edu);
        return edu;
    }

    private Education buildEduPriv(Private p, String degree){
        Calendar c = Calendar.getInstance();
        c.setTime(p.getEducationList().get(0).getEnd());
        int init = c.get(Calendar.YEAR);
        return buildEdu(init, degree);
    }

    private WorkingExperience buildExperience(Private p){
        Calendar c = Calendar.getInstance();
        c.setTime(p.getEducationList().get(1).getEnd());
        int init = c.get(Calendar.YEAR);
        c.set(init, Calendar.SEPTEMBER, 1, 0, 0);
        Date begin = c.getTime();
        c.set(init+6, Calendar.SEPTEMBER, 1, 0, 0);
        Date end = c.getTime();
        WorkingExperience we = new WorkingExperience(begin, end, sectorService.findById("IT e Media"),
                professionService.findById("Ingegnere informatico"), "Funzione", "Datore");
        wEService.save(we);
        return we;
    }
}


