package com.epam.entity;

import java.sql.Date;

public class Birth {
    private Date birthDate;
    private String birthPlace;

    public Birth(Date birthDate, String birthPlace) {
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public Birth() {
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
