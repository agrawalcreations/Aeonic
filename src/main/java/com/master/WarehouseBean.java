package com.master;

import com.inventory.dao.MasterDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 * Created by raviagrawal on 20/8/16.
 */
@ManagedBean(name = "warehouseMaster")
//@SessionScoped
@ViewScoped
public class WarehouseBean {
    private String warehouse;

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    private static  ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();

    public ArrayList<Warehouse> getWarehouseList() {
        MasterDAO masterDao = new MasterDAO();
        warehouseList = (ArrayList<Warehouse>) masterDao.getWarehouseMst();


        return warehouseList;
    }

    public String addAction() {

        Warehouse warehouseObj = new Warehouse(this.warehouse);
        FacesContext context = FacesContext.getCurrentInstance();
        Exception ex = new Exception();


        MasterDAO masterDao = new MasterDAO();
        masterDao.addWarehouse(warehouseObj);

        warehouseList.add(warehouseObj);


        warehouse = "";
        return null;
    }

}
