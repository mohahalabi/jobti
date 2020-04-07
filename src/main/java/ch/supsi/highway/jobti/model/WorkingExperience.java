package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class WorkingExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date begin;

    @NotNull
    private Date end;

    @ManyToOne
//    @NotNull
    private ProfessionalSector sector;

    @ManyToOne
//    @NotNull
    private Profession profession;

    @NotNull
    private String jobfunction;

    @NotNull
    private String employer;

    public WorkingExperience(Date begin, Date end, ProfessionalSector sector, Profession profession,String jobfunction, String employer) {
        this.begin = begin;
        this.end = end;
        this.sector = sector;
        this.profession = profession;
        this.jobfunction = jobfunction;
        this.employer = employer;
    }


    public WorkingExperience() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ProfessionalSector getSector() {
        return sector;
    }

    public void setSector(ProfessionalSector sector) {
        this.sector = sector;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getJobfunction() {
        return jobfunction;
    }

    public void setJobfunction(String function) {
        this.jobfunction = function;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
