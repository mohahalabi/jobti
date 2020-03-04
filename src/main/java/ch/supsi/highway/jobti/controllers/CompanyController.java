package ch.supsi.highway.jobti.controllers;

import ch.supsi.highway.jobti.model.Company;
import ch.supsi.highway.jobti.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompanyController {
    @Autowired
    private CompanyService cmpSrv;

    @GetMapping(value="/companies", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Company> get() {
        return cmpSrv.getAll();
    }

    @GetMapping(value="/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> get(@PathVariable int id) {
        Company cmp = cmpSrv.findById(id);
        if (cmp != null){
            return new ResponseEntity<>(cmp, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> post(@RequestBody Company cmp){
        if (cmp.getEmail()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        cmpSrv.save(cmp);
        return new ResponseEntity<>(cmp, HttpStatus.CREATED);
    }

    @PutMapping(value="/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> put (@PathVariable int id, @RequestBody Company newCmp) {
        Company cmp = cmpSrv.findById(id);
        if (cmp == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        cmp.setId(id);
        cmpSrv.save(cmp);
        return new ResponseEntity<>(cmp, HttpStatus.OK);
    }

    @DeleteMapping(value = "/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete (@PathVariable int id ) {
        String success = "{\n" +
                " \"success\": \"true\"\n" +
                "}";
        Company cmp = cmpSrv.findById(id);
        if (cmp== null)
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        cmpSrv.delete(cmp);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}