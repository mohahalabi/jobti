package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    @NotNull

    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @ManyToOne
    @NotNull
    private Role role;

    private String address;
    private int postcode;
    private String city;
    private String region;
    private String country;

    @ManyToOne
    private ProfessionalSector sector;

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
    public User(String name, String email, String password, Role role, String address, int postcode, String city,
                String region, String country, Date birthdate, ProfessionalSector sector) {
        this.name=name;
        this.email=email;
        this.password= password;
        this.role=role;
        this.address=address;
        this.postcode=postcode;
        this.city=city;
        this.region=region;
        this.country=country;
        this.birthdate=birthdate;
        this.sector=sector;
    }


    public User(String name, String email, String password, Role role, String address, int postcode, String city,
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

    public ProfessionalSector getSector() {
        return sector;
    }

    public void setSector(ProfessionalSector sector) {
        this.sector = sector;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
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
