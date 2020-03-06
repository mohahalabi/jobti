package ch.supsi.highway.jobti.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("company")
public class Company extends User {
    private String sector;
    private int phoneNum;
    private String socialReason;
    private String site;
    private int employeeNum;
    private int rcNum;

    public Company() {
    }

    public Company(String name, String email,  String password,Role role){
        super(name, email, password, role);
    }

    public Company(String name, String email,  String password,Role role,  String sector, int phoneNum,
             String socialReason, String site, int employeeNum, int rcNum){
        super(name, email, password, role);
        this.sector = sector;
        this.phoneNum=phoneNum;
        this.socialReason=socialReason;
        this.site= site;
        this.employeeNum=employeeNum;
        this.rcNum=rcNum;
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
