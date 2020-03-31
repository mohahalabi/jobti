package ch.supsi.highway.jobti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @NotNull
    private String sector;

    @NotNull
    private String jobfunction;

    @NotNull
    private String employer;

    public WorkingExperience(Date begin, Date end, String sector, String jobfunction, String employer) {
        this.begin = begin;
        this.end = end;
        this.sector = sector;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
