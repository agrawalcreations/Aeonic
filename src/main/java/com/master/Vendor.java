package com.master;

/**
 * Created by raviagrawal on 20/8/16.
 */
public class Vendor {
    private String vendor;
    private String address;
    private String phone;
    private String comment;
    private int isSupplier;
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Vendor(String vendor,String address, String phone, String comment, int isSupplier) {
        this.vendor = vendor;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.isSupplier = isSupplier;

    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsSupplier() {
        return isSupplier;
    }

    public void setIsSupplier(int isSupplier) {
        this.isSupplier = isSupplier;
    }

    public Vendor() {
    }
}
