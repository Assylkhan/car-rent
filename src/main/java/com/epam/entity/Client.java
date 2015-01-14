package com.epam.entity;

import java.math.BigDecimal;

public class Client extends User {
    private BigDecimal bill;

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }
}
