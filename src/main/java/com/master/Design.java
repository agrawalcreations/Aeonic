package com.master;

import com.inventory.dao.MasterDAO;

import javax.faces.context.FacesContext;

/**
 * Created by raviagrawal on 20/8/16.
 */
public class Design {
    private String design;

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Design(String design) {
        this.design = design;
    }

    public Design() {
    }
}
