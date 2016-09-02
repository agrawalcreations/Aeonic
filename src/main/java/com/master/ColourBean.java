package com.master;

import com.inventory.Inward;
import com.inventory.dao.InwardDAO;
import com.inventory.dao.OutwardDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.inventory.dao.MasterDAO;

/**
 * Created by raviagrawal on 20/8/16.
 */
@ManagedBean(name = "colourMaster")
//@SessionScoped
@ViewScoped
public class ColourBean {
    private String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    private static  ArrayList<Colour> colourList = new ArrayList<Colour>();

    public ArrayList<Colour> getColourList() {

        MasterDAO masterDao = new MasterDAO();
        colourList = (ArrayList<Colour>) masterDao.getColourMst();


        return colourList;
    }

    public String addAction() {

        Colour colourObj = new Colour(this.colour);
        FacesContext context = FacesContext.getCurrentInstance();
        Exception ex = new Exception();


        MasterDAO masterDao = new MasterDAO();
        masterDao.addColour(colourObj);

        colourList.add(colourObj);


        colour = "";
        return null;
    }

}
