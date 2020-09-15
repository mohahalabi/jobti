package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.*;
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

        //creazione profesioni (se non ci sono profssioni non ci sono anche i settori
        if(sectorService.getAll().size() == 0){
            //Agricoltira e ambiente
            professionService.save(new Profession("Acquacoltore"));
            professionService.save(new Profession("Agricoltore"));
            professionService.save(new Profession("Astronomo"));
            professionService.save(new Profession("Biologo"));
            professionService.save(new Profession("Casaro"));
            professionService.save(new Profession("Ecologo"));
            professionService.save(new Profession("Enologo"));
            professionService.save(new Profession("Fornaio"));
            professionService.save(new Profession("Giardiniere"));
            professionService.save(new Profession("Meteorologo"));
            professionService.save(new Profession("Olivicoltore"));
            professionService.save(new Profession("Perito agrario"));
            professionService.save(new Profession("Pescatore"));
            professionService.save(new Profession("Sismologo"));
            professionService.save(new Profession("Vinificatore"));
            professionService.save(new Profession("Zoologo"));

            List<Profession> agriAmbi = new ArrayList<>();
            agriAmbi.add(professionService.getOne("Acquacoltore"));
            agriAmbi.add(professionService.getOne("Agricoltore"));
            agriAmbi.add(professionService.getOne("Astronomo"));
            agriAmbi.add(professionService.getOne("Biologo"));
            agriAmbi.add(professionService.getOne("Casaro"));
            agriAmbi.add(professionService.getOne("Ecologo"));
            agriAmbi.add(professionService.getOne("Enologo"));
            agriAmbi.add(professionService.getOne("Fornaio"));
            agriAmbi.add(professionService.getOne("Giardiniere"));
            agriAmbi.add(professionService.getOne("Meteorologo"));
            agriAmbi.add(professionService.getOne("Olivicoltore"));
            agriAmbi.add(professionService.getOne("Perito agrario"));
            agriAmbi.add(professionService.getOne("Pescatore"));
            agriAmbi.add(professionService.getOne("Sismologo"));
            agriAmbi.add(professionService.getOne("Vinificatore"));
            agriAmbi.add(professionService.getOne("Zoologo"));

            sectorService.save(new ProfessionalSector("Agricoltura e ambiente", agriAmbi));

            //Costruzioni Edilizia
            professionService.save(new Profession("Agente immobilire"));
            professionService.save(new Profession("Architetto"));
            professionService.save(new Profession("Architetto di interni"));
            professionService.save(new Profession("Capocantiere"));
            professionService.save(new Profession("Elettricista"));
            professionService.save(new Profession("Falegname"));
            professionService.save(new Profession("Fisico"));
            professionService.save(new Profession("Geometra"));
            professionService.save(new Profession("Idraulico"));
            professionService.save(new Profession("Ingegnere del rischio geologico"));
            professionService.save(new Profession("Ingegnere edile"));
            professionService.save(new Profession("Ingegnere minerario"));
            professionService.save(new Profession("Ingegnere petrolifero"));
            professionService.save(new Profession("Installatore impianti"));
            professionService.save(new Profession("Muratore"));
            professionService.save(new Profession("Tappezziere"));
            professionService.save(new Profession("Urbanista"));

            List<Profession> edilizia = new ArrayList<>();
            edilizia.add(professionService.getOne("Agente immobiliare"));
            edilizia.add(professionService.getOne("Architetto"));
            edilizia.add(professionService.getOne("Architetto di interni"));
            edilizia.add(professionService.getOne("Capocantiere"));
            edilizia.add(professionService.getOne("Elettricista"));
            edilizia.add(professionService.getOne("Falegname"));
            edilizia.add(professionService.getOne("Fisico"));
            edilizia.add(professionService.getOne("Geometra"));
            edilizia.add(professionService.getOne("Idraulico"));
            edilizia.add(professionService.getOne("Ingegnere del rischio geologico"));
            edilizia.add(professionService.getOne("Ingegnere edile"));
            edilizia.add(professionService.getOne("Ingegnere minerario"));
            edilizia.add(professionService.getOne("Ingegnere petrolifero"));
            edilizia.add(professionService.getOne("Installatore impianti"));
            edilizia.add(professionService.getOne("Muratore"));
            edilizia.add(professionService.getOne("Tappezziere"));
            edilizia.add(professionService.getOne("Urbanista"));

            sectorService.save(new ProfessionalSector("Costruzioni e Edilizia", edilizia));
        }


        //creazione role
        if(roleService.getAll().size() == 0) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_COMPANY"));
            roleService.save(new Role("ROLE_PRIVATE"));
        }

//        if(getAll().size() == 0){
//            List<WorkingExperience> we = new ArrayList<>();
//            we.add(new WorkingExperience(new Date(), new Date(), sectorService.getOne("Costruzioni e Edilizia"), professionService.getOne("Muratore"),"Apprendista", "Liceo cantonale"));
//            wEService.save(we.get(0));
//
//            Private admin= new Private("admin","admin", "admin@jobti.ch",crypto.encode("admin"), new Role("ROLE_ADMIN"),"Via San Gottardo", 6600, "Locarno", "TI", "Svizzera", new Date(), sectorService.getOne("Costruzioni e Edilizia"), professionService.getOne("Muratore"), we );
//            save(admin);
//
//            Private completePrivate = new Private("Luca", "Bianchi", "lucabianchi@jobti.ch", crypto.encode("privato"), new Role("ROLE_PRIVATE"),
//                    "Via San Gottardo", 6600, "Locarno", "TI", "Svizzera", new Date(), sectorService.getOne("Costruzioni e Edilizia"), professionService.getOne("Muratore"), we );
//            save(completePrivate);
//            Company completeCompany= new Company("Rossi", "rossi@jobti.ch", crypto.encode("azienda"),new Role("ROLE_COMPANY"), "Via ai Tigli", 6500, "Bellinzona",
//                    "TI", "Svizzera", new Date(), sectorService.getOne("Costruzioni e Edilizia"), 911234567, "SA", "www.rossi.ch", 30,123456);
//            companyService.save(completeCompany);
//        }
    }

    public byte[] setEmptyImage() throws IOException {
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
    }
}
