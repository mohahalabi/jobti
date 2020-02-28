package ch.supsi.highway.jobti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Private {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int privateId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    public Private(String name, String surname, String email) {
        this.name = name;
        this.surname= surname;
        this.email=email;
    }

    public Private() {
    }

    public int getPrivateId() {
        return privateId;
    }

    public void setPrivateId(int privateId) {
        this.privateId = privateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
