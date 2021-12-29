package com.smi.test.model;

import java.io.Serializable;

public class Purchase implements Serializable {

    long offerId ;
    String comission;
    long amount ;

    public Purchase(long offerId, String comission, long amount) {
        this.offerId = offerId;
        this.comission = comission;
        this.amount = amount;
    }

    public Purchase() {
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getComission() {
        return comission;
    }

    public void setComission(String comission) {
        this.comission = comission;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
