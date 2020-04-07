package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;
}
