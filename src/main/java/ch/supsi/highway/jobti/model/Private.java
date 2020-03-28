package ch.supsi.highway.jobti.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("private")
public class Private extends User{
    private String surname;

    private int credits;
    private int views;

    public Private() {
    }

    public Private(String name, String surname, String email, String password, Role role){
        super(name, email, password, role);
        this.surname=surname;
        this.credits=10;
        this.views=0;
    }

    public Private(String name, String surname, String email, String password, String country, String region,
                   String city, int postcode, String address, Date birthdate, String sector){
        super(name, email, password, new Role("ROLE_PRIVATE"), address, postcode, city,
                region, country, birthdate,sector);
        this.surname=surname;
        this.credits=10;
        this.views=0;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
