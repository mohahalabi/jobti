package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Education;
import ch.supsi.highway.jobti.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.EditorKit;
import java.util.List;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getAll(){
        return educationRepository.findAll();
    }

    public Education findById (int id){
        return educationRepository.findById(id).orElse(null);
    }

    public Education save(Education edu){
        return educationRepository.save(edu);

    }
}
