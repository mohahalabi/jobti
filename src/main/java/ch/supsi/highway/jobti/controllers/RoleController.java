package ch.supsi.highway.jobti.controllers;


import ch.supsi.highway.jobti.model.Role;
import ch.supsi.highway.jobti.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value="/roles", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Role> get() {
        return roleService.getAll();
    }

    @GetMapping(value="/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> get(@PathVariable String id) {
        Role role = roleService.findById(id);
        if (role != null){
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> post(@RequestBody Role role){
        if(role.getRoleName()==null)
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        roleService.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping(value="/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> put (@PathVariable String id, @RequestBody Role newRole ) {
        Role role = roleService.findById(id);
        if (role == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Role roleToAdd =role;
        roleToAdd.setRoleName(id);
        if (newRole.getRoleName()!=null)
            roleToAdd.setRoleName(newRole.getRoleName());
        roleService.save(roleToAdd);
        return new ResponseEntity<>(roleToAdd, HttpStatus.OK);
    }

    @DeleteMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete (@PathVariable String id ) {
        String success = "{\n" +
                " \"success\": \"true\"\n" +
                "}";
        Role role =roleService.findById(id);
        if (role == null)
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        roleService.delete(role);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}