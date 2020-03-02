package ch.supsi.highway.jobti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private int foundationDate;
    private String sector;
    private int phoneNum;
    private String socialReason;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private String site;
    private String description;
    private int employeeNum;
    private int rcNum;

    public Company(String name, String email, int foundationDate, String sector, int phoneNum, String socialReason, String address,
                   String postcode, String city, String country, String site, String description, int employeeNum, int rcNum){
        this.name=name;
        this.email=email;
        this.foundationDate=foundationDate;
        this.sector=sector;
        this.phoneNum=phoneNum;
        this.socialReason=socialReason;
        this.address=address;
        this.postcode=postcode;
        this.city=city;
        this.country=country;
        this.site=site;
        this.description=description;
        this.employeeNum=employeeNum;
        this.rcNum=rcNum;
    }

    public Company(String name, String email, String sector, String socialReason, String address,
                   String postcode, String city, String country){
        this.name=name;
        this.email=email;
        this.sector=sector;
        this.socialReason=socialReason;
        this.address=address;
        this.postcode=postcode;
        this.city=city;
        this.country=country;
    }

    public Company() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public int getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(int foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public int getRcNum() {
        return rcNum;
    }

    public void setRcNum(int rcNum) {
        this.rcNum = rcNum;
    }


}
