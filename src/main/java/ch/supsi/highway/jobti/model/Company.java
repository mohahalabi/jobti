package ch.supsi.highway.jobti.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@DiscriminatorValue("company")
public class Company extends User {
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

    public Company(String name, String email,  String password, String address, int postcode, String city,
                   String region, String country, Date birthdate, ProfessionalSector sector,
             String socialReason, String site){
        super(name, email, password, new Role("ROLE_COMPANY"), address, postcode, city,
                 region, country, birthdate, sector);
        this.socialReason=socialReason;
        this.site= site;
    }

    public Company(String name, String email, String password, Role role, String address, int postcode, String city,
                   String region, String country, Date birthdate, ProfessionalSector sector, int phoneNum, String socialReason,
                   String site, int employeeNum, int rcNum) {
        super(name, email, password, role, address, postcode, city, region, country, birthdate, sector);
        this.phoneNum = phoneNum;
        this.socialReason = socialReason;
        this.site = site;
        this.employeeNum = employeeNum;
        this.rcNum = rcNum;
    }

    public Company(String name, String email, String password, Role role, String address, int postcode, String city, String region, String country, String description, Date birthdate, byte[] image, int phoneNum, String socialReason, String site, int employeeNum, int rcNum) {
        super(name, email, password, role, address, postcode, city, region, country, description, birthdate, image);
        this.phoneNum = phoneNum;
        this.socialReason = socialReason;
        this.site = site;
        this.employeeNum = employeeNum;
        this.rcNum = rcNum;
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

    public String getFormattedBD(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getBirthdate());
        String bd = "" + calendar.get(Calendar.YEAR);
        return bd;
    }
}
