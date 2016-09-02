package com.inventory;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.inventory.dao.InwardDAO;
import com.inventory.dao.OutwardDAO;
import com.master.*;
import com.nz.simplecrud.util.DateUtility;
import org.primefaces.event.RowEditEvent;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by raviagrawal on 1/8/16.
 */
@ManagedBean(name = "inward")
//@SessionScoped
@ViewScoped
 public class InwardBean {
    private static final long serialVersionUID = 1L;
    private Date date;
    private String vendor;
    private String colour;
    private String size;
    private Integer batchSize;
    private Integer noOfBatch;
    private Integer quantity;
    private String design;
    private String warehouse;
    private Map<String,String> colours;
    private Map<String,String> vendors;
    private Map<String,String> designs;
    private Map<String,String> warehouses;
    Inward order;

    @PostConstruct
    public void init() {
        //cities
/*
        colours = new HashMap<String, String>();
        colours.put("Red", "Red");
        colours.put("Blue","Blue");
        colours.put("Yellow","Yellow");
*/

        ColourBean colourBean = new ColourBean();
        List<Colour> colourList = colourBean.getColourList();

        colours = new HashMap<String,String>();
        for (int i=0;i<colourList.size();i++){
            colours.put(colourList.get(i).getColour(),colourList.get(i).getColour());
        }


        VendorBean vendorBean = new VendorBean();
        List<Vendor> vendorList = vendorBean.getVendorList();

        vendors = new HashMap<String,String>();
        for (int i=0;i<vendorList.size();i++){
            vendors.put(vendorList.get(i).getVendor(),vendorList.get(i).getVendor());
        }



/*
        vendors = new HashMap<String,String>();
        vendors.put("Rainbow cotton Mart", "Rainbow cotton Mart");
        vendors.put("India cotton Mart", "India cotton Mart");
        vendors.put("Ahmedabad cotton Mart", "Ahmedabad cotton Mart");
*/

       DesignBean designBean = new DesignBean();
                List<Design> designList = designBean.getDesignList();

        designs = new HashMap<String,String>();
        for (int i=0;i<designList.size();i++){
            designs.put(designList.get(i).getDesign(),designList.get(i).getDesign());
        }

/*
        designs = new HashMap<String,String>();
        designs.put("D1", "D1");
        designs.put("D2", "D2");
        designs.put("D3", "D3");*/



        WarehouseBean warehouseBean = new WarehouseBean();
        List<Warehouse> warehouseList = warehouseBean.getWarehouseList();

        warehouses = new HashMap<String,String>();
        for (int i=0;i<warehouseList.size();i++){
            warehouses.put(warehouseList.get(i).getWarehouse(),warehouseList.get(i).getWarehouse());
        }


/*

        warehouses = new HashMap<String,String>();
        warehouses.put("W1", "W1");
        warehouses.put("W2", "W2");
*/

    }

    public void getCalculatedQuantity() {
                if(this.getBatchSize() !=null && this.getNoOfBatch() != null){
            this.quantity = this.getBatchSize()*this.getNoOfBatch();
        }


    }

        public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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


    public Inward getOrder() {

        return order;
    }

    public void setOrder(Inward order) {
        this.order = order;
    }

    private static final ArrayList<Inward> orderList = new ArrayList<Inward>();

    public ArrayList<Inward> getOrderList() {
        return orderList;
    }

    public String addAction() {

        String actionType = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("isIn");

        System.out.println("value is "+actionType);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        // myDate is the java.util.Date in yyyy-mm-dd format
        // Converting it into String using formatter
            String strDate = sm.format(this.date);
        //Converting the String back to java.util.Date
        Date dt = null;
        try {
            dt = sm.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Inward orderitem = new Inward(strDate,this.vendor, this.design,this.colour, this.size, this.batchSize, this.noOfBatch, this.quantity, this.warehouse);
        FacesContext context = FacesContext.getCurrentInstance();
        Exception ex = new Exception();


        if(actionType.equalsIgnoreCase("in")) {
            InwardDAO inwardDao = new InwardDAO();
            inwardDao.addItem(orderitem,actionType);
            orderList.add(orderitem);
        } else {


            OutwardDAO outwardDao = new OutwardDAO();

         // check whther this item can be ordered or not.

            List<Inward> itemListAvailable = outwardDao.getItemAvailability(orderitem);
            int quantityCurrent = outwardDao.getItemAvailabilityInCurrentInv(orderitem);
            int quantityCanSell=0;
            int totalQuantityAvailable = 0;
            try {

                if((itemListAvailable!=null & !itemListAvailable.isEmpty()) || quantityCurrent>0) {
             if(itemListAvailable!=null & !itemListAvailable.isEmpty()) {
                 for(Inward inward:itemListAvailable) {
                     quantityCanSell =quantityCanSell + inward.getQuantity();
                 }
             }
                totalQuantityAvailable = quantityCanSell + quantityCurrent;


                    if(totalQuantityAvailable >= orderitem.getQuantity()) {
                        totalQuantityAvailable = totalQuantityAvailable - orderitem.getQuantity();
                        outwardDao.addItem(orderitem,actionType,itemListAvailable,totalQuantityAvailable);
                        orderList.add(orderitem);
                    } else {
                        throw ex;
                    }



            } else {
                throw ex;
            }
            } catch (Exception e){
                context.addMessage(null, new FacesMessage("Error!", "This item / quantity not availbale in inventory"));

            }

        }



        colour = "";
        size = "";
        batchSize = 0;
        noOfBatch = 0;
        quantity = 0;
        design = "";
        warehouse = "";
        vendor = "";
        return null;
    }

    public List<Inward> getInwardItem(){
        InwardDAO inwardDao = new InwardDAO();
        List<Inward> inwardItems = inwardDao.getInwardItem();
        return inwardItems;
    }



    public List<Inward> getOutwardItem(){
        OutwardDAO outwardDAO = new OutwardDAO();
        List<Inward> outwardItems = outwardDAO.getOutwardItem();
        return outwardItems;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited",((InwardBean) event.getObject()).getColour());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.remove((InwardBean) event.getObject());
    }

    public Map<String, String> getColours() {
        return colours;
    }

    public void setColours(Map<String, String> colours) {
        this.colours = colours;
    }

    public Map<String, String> getVendors() {
        return vendors;
    }

    public void setVendors(Map<String, String> vendors) {
        this.vendors = vendors;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Map<String, String> getDesigns() {
        return designs;
    }

    public void setDesigns(Map<String, String> designs) {
        this.designs = designs;
    }

    public Map<String, String> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Map<String, String> warehouses) {
        this.warehouses = warehouses;
    }



}

