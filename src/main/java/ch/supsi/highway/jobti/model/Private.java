package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Private extends User{

    @NotNull
    private String surname;

    private int credits;
    private int views;

    public Private(String name, String email, String password, Role role){
        super(name, email, password, role);
        this.credits=10;
        this.views=0;
    }

    public Private(String name, String surname, String email, String password, Role role){
        super(name, email, password, role);
        this.surname=surname;
        this.credits=10;
        this.views=0;
    }


    public Private() {
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
