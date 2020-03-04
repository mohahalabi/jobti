package ch.supsi.highway.jobti.controllers;

import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.service.PrivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivateController {
    @Autowired
    private PrivateService pvtSrv;

    @GetMapping(value="/privates", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Private> get() {
        return pvtSrv.getAll();
    }

    @GetMapping(value="/privates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Private> get(@PathVariable int id) {
        Private pvt = pvtSrv.findById(id);
        if (pvt != null){
            return new ResponseEntity<>(pvt, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/privates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Private> post(@RequestBody Private pvt){
        if (pvt.getEmail()==null || pvt.getName()==null || pvt.getSurname()==null || pvt.getPassword()==null || pvt.getRole()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        pvtSrv.save(pvt);
        return new ResponseEntity<>(pvt, HttpStatus.CREATED);
    }

    @PutMapping(value="/privates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Private> put (@PathVariable int id, @RequestBody Private newPvt) {
        Private pvt = pvtSrv.findById(id);
        if (pvt == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        pvt.setId(id);
        pvtSrv.save(pvt);
        return new ResponseEntity<>(pvt, HttpStatus.OK);
    }

    @DeleteMapping(value = "/privates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete (@PathVariable int id ) {
        String success = "{\n" +
                " \"success\": \"true\"\n" +
                "}";
        Private pvt = pvtSrv.findById(id);
        if (pvt== null)
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        pvtSrv.delete(pvt);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
