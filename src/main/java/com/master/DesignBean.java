package com.master;

import com.inventory.dao.MasterDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 * Created by raviagrawal on 20/8/16.
 */
@ManagedBean(name = "designMaster")
//@SessionScoped
@ViewScoped
public class DesignBean {
    private String design;

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    private static ArrayList<Design> designList = new ArrayList<Design>();

    public ArrayList<Design> getDesignList() {

        MasterDAO masterDao = new MasterDAO();
        designList = (ArrayList<Design>) masterDao.getDesignMst();

        return designList;
    }

    public String addAction() {

        Design designObj = new Design(this.design);
        FacesContext context = FacesContext.getCurrentInstance();
        Exception ex = new Exception();


        MasterDAO masterDao = new MasterDAO();
        masterDao.addDesign(designObj);

        designList.add(designObj);


        design = "";
        return null;
    }
}
