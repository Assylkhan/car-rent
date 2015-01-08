package com.epam.entity;

public class Client extends User {
    private String email;
    private PassportInfo passportInfo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PassportInfo getPassportInfo() {
        return passportInfo;
    }

    public void setPassportInfo(PassportInfo passportInfo) {
        this.passportInfo = passportInfo;
    }
}