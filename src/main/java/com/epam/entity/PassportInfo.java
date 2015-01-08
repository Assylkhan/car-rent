package com.epam.entity;

import java.sql.Date;

public class PassportInfo extends BaseEntity{
    private String surname;
    private String name;
    private String citizenship;
    private String gender;
    private Birth birth;
    private String issuedAgencyName;
    private Date issuedDate;
    private Date validity;

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getIssuedAgencyName() {
        return issuedAgencyName;
    }

    public void setIssuedAgencyName(String issuedAgencyName) {
        this.issuedAgencyName = issuedAgencyName;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
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
}
