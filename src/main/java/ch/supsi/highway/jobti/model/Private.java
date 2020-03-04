package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Private extends User{

    @NotNull
    private String surname;

    private int credits;
    private int views;

    //TODO: create appropriate constructor
    public Private(String name, String surname, String email, String password, Role role){
        this.surname = surname;
        this.credits=10;
        this.views=0;
    }


    public Private() {
    }


}
