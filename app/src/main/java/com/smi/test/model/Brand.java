package com.smi.test.model;

import android.media.Image;

import java.io.Serializable;

public class Brand implements Serializable {
    private long offerId ;
    private String displayName ;
    private String pic;

    public Brand(String displayName, String pic, long offerId) {
        this.offerId= offerId ;
        this.displayName = displayName;
        this.pic = pic ;
    }

    public Brand()

    {}

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public  String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }
}
