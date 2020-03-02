package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.repository.PrivateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateService {
    @Autowired
    private PrivateRepository pvtRepo;

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

}
