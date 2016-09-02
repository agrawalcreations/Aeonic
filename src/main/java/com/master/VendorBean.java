package com.master;

import com.inventory.dao.MasterDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 * Created by raviagrawal on 20/8/16.
 */
@ManagedBean(name = "vendorMaster")
//@SessionScoped
@ViewScoped
public class VendorBean {

    private String vendor;
    private String address;
    private String phone;
    private String comment;
    private int isSupplier;



    private static  ArrayList<Vendor> vendorList = new ArrayList<Vendor>();

    public ArrayList<Vendor> getVendorList() {

        MasterDAO masterDao = new MasterDAO();
        vendorList = (ArrayList<Vendor>) masterDao.getVendorMst();


        return vendorList;
    }

    public String addAction() {
            Vendor vendorObj = new Vendor(this.vendor,this.address,this.phone,this.comment,this.isSupplier);
        FacesContext context = FacesContext.getCurrentInstance();
        Exception ex = new Exception();


        MasterDAO masterDao = new MasterDAO();
        masterDao.addVendor(vendorObj);

        vendorList.add(vendorObj);


       // colour = "";
        return null;
    }

    public int getIsSupplier() {
        return isSupplier;
    }

    public void setIsSupplier(int isSupplier) {
        this.isSupplier = isSupplier;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
