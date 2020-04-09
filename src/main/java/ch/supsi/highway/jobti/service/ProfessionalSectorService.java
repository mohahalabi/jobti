package ch.supsi.highway.jobti.service;


import ch.supsi.highway.jobti.model.ProfessionalSector;
import ch.supsi.highway.jobti.repository.ProfessionalSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalSectorService {
    @Autowired
    ProfessionalSectorRepository sectorRepository;

    public List<ProfessionalSector> getAll(){
        return sectorRepository.findAll();
    }

    public void save(ProfessionalSector professionalSector){
        sectorRepository.save(professionalSector);
    }

    public ProfessionalSector findById(String name){
        return sectorRepository.findById(name).orElse(null);
    }

}
