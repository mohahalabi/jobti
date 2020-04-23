package ch.supsi.highway.jobti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date begin;

    @NotNull
    private Date end;

    private String schoolType;
    private String school;
    private String degree;

    public Education() {
    }

    public Education(Date begin, Date end, String schoolType, String school, String degree) {
        this.begin=begin;
        this.end=end;
        this.school=school;
        this.schoolType=schoolType;
        this.degree=degree;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFormattedBegin() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getBegin());
        String bd = calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR);
        return bd;
    }

    public String getFormattedEnd() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getEnd());
        String bd = calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR);
        return bd;
    }
}
