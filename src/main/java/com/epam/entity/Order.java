package com.epam.entity;

import java.util.Date;

public class Order extends BaseEntity {
    private PassportInfo passportInfo;
    private Date rentTerm;

    public Date getRentTerm() {
        return rentTerm;
    }

    public void setRentTerm(Date rentTerm) {
        this.rentTerm = rentTerm;
    }

    public PassportInfo getPassportInfo() {
        return passportInfo;
    }

    public void setPassportInfo(PassportInfo passportInfo) {
        this.passportInfo = passportInfo;
    }

}
