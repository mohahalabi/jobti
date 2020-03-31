package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Role;
import ch.supsi.highway.jobti.model.WorkingExperience;
import ch.supsi.highway.jobti.repository.WorkingExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingExperienceService {
    @Autowired
    private WorkingExperienceRepository wERepository;

    public List<WorkingExperience> getAll(){
        return wERepository.findAll();
    }

    public WorkingExperience findById (int id){
        return wERepository.findById(id).orElse(null);
    }

    public WorkingExperience save(WorkingExperience we){
        return wERepository.save(we);

    }
}
