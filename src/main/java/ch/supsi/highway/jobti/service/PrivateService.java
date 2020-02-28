package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.repository.PrivateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateService {
    @Autowired
    private PrivateRepository srvRepo;

    public List<Private> getAll(){
        return srvRepo.findAll();
    }

}
