package ch.supsi.highway.jobti.model;

import org.omg.CORBA.DATA_CONVERSION;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @ManyToOne
    @NotNull
    private Role role;

    private String address;
    private String postcode;
    private String city;
    private String region;
    private String country;

    private String description;
    private Date birthdate;

    @Lob
    private byte [] image;

    public User() {
    }

    public User(String name, String email, String password,Role role) {
        this.name=name;
        this.email=email;
        this.password= password;
        this.role=role;
    }

    public User(String name, String email,  String password,Role role, String address, String postcode,String city,
                String region, String country, String description, Date birthdate, byte [] image) {
        this.name=name;
        this.email=email;
        this.password= password;
        this.role=role;
        this.address=address;
        this.postcode=postcode;
        this.city=city;
        this.region=region;
        this.country=country;
        this.description=description;
        this.birthdate=birthdate;
        this.image= image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
