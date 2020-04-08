package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Profession;
import ch.supsi.highway.jobti.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {

    @Autowired
    ProfessionRepository professionRepository;

    public List<Profession> getAll(){
        return professionRepository.findAll();
    }

    public void save(Profession profession){
        professionRepository.save(profession);
    }

    public Profession findById(String name){
        return professionRepository.findById(name).orElse(null);
    }

    public void saveAll (List<Profession> li){
        professionRepository.saveAll(li);
    }
}