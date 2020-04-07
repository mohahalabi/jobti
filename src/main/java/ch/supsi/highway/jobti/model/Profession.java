package ch.supsi.highway.jobti.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profession {

    @Id
    private String name;

    public Profession(){
    }

    public Profession(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
