package com.inventory;
import com.nz.simplecrud.util.DateUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
/**
 * Created by raviagrawal on 1/8/16.
 */
public class Inward  implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String date;
    private String vendor;
    private String colour;
    private String size;
    private Integer batchSize;
    private Integer noOfBatch;
    private Integer quantity;
    private String design;
    private String warehouse;

    public Inward() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inward(String date, String vendor,String design, String colour, String size,Integer batchSize,Integer noOfBatch,Integer quantity,String warehouse) {
        this.date = date;
        this.vendor = vendor;
        this.colour = colour;
        this.size = size;
        this.batchSize = batchSize;
        this.noOfBatch = noOfBatch;
        this.quantity = quantity;
        this.design = design;
        this.warehouse = warehouse;
    }

    public String getDate() {
        return date;
    }


    public String getDay() {
        //return(DateUtility.formatDay(date));
    return(getDate());
    }

/*

    public String getSelectedColour() {
        return((getColour()));
    }
*/

    public void setDate(String date) {
        this.date = date;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getNoOfBatch() {
        return noOfBatch;
    }

    public void setNoOfBatch(Integer noOfBatch) {
        this.noOfBatch = noOfBatch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
