package ch.supsi.highway.jobti.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("private")
public class Private extends User{
    private String surname;

    private int credits;
    private int views;

    @OneToMany
    private List<WorkingExperience> experiences;

    @ManyToOne
    private Profession profession;

    @OneToMany
    private List<Education> educationList;


    public Private() {
    }

    public Private(String name, String surname, String email, String password, Role role){
        super(name, email, password, role);
        this.surname=surname;
        this.credits=10;
        this.views=0;
        this.experiences=new ArrayList<>();
        this.educationList= new ArrayList<>();
    }


    public Private(String name, String surname, String email, String password, String country, String region,
                   String city, int postcode, String address, Date birthdate, ProfessionalSector sector, Profession profession){
        super(name, email, password, new Role("ROLE_PRIVATE"), address, postcode, city,
                region, country, birthdate,sector);
        this.surname=surname;
        this.credits=10;
        this.views=0;
        this.experiences= new ArrayList<>();
        this.profession = profession;
        this.educationList= new ArrayList<>();

    }

    public Private(String name, String surname, String email, String password, String address, int postcode,
                   String city, String region, String country, Date birthdate, ProfessionalSector sector,Profession profession, List exp) {
        super(name, email, password, new Role("ROLE_PRIVATE"), address, postcode, city, region, country, birthdate, sector );
        this.surname=surname;
        this.credits=10;
        this.views=0;
        this.experiences= exp;
        this.profession= profession;
        this.educationList= new ArrayList<>();

    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<WorkingExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkingExperience> experiences) {
        this.experiences = experiences;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }
}
