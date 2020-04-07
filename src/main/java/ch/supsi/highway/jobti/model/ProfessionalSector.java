package ch.supsi.highway.jobti.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProfessionalSector {

    @Id
    private String name;

    @OneToMany
    @NotNull
    private List<Profession> professions;

    public ProfessionalSector(){

    }
    public ProfessionalSector(String name) {
        this.name = name;
        this.professions = new ArrayList<>();
    }

    public ProfessionalSector(String name, List<Profession> professions) {
        this.name = name;
        this.professions = professions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }
}
