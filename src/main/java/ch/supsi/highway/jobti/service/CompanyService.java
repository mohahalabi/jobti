package ch.supsi.highway.jobti.service;

import ch.supsi.highway.jobti.model.Company;
import ch.supsi.highway.jobti.repository.CompanyRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository cmpRepo;

    public List<Company> getAll(){
        return cmpRepo.findAll();
    }

    public Company findById (String id){
        return cmpRepo.findById(id).orElse(null);
    }

    public Company save(Company cmp){
        return cmpRepo.save(cmp);
    }
    public void delete (Company cmp){
        cmpRepo.delete(cmp);
    }

    public byte[] setEmptyImage() throws IOException {
        return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/user.jpg"));
    }

}
