package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Company extends User {
    private String sector;
    private int phoneNum;
    private String socialReason;
    private String site;
    private String description;
    private int employeeNum;
    private int rcNum;


    //TODO: create appropriate constructor

    public Company() {
    }

    public Company(String name, String email, String password, Role role ){

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
