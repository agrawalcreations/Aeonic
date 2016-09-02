package com.nz.simplecrud.controller;

import com.inventory.Inward;
import com.inventory.InwardBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Created by raviagrawal on 9/8/16.
 */
@ManagedBean
public class OutwardDataExporterView implements Serializable {


    private List<Inward> outwardItems;

@ManagedProperty("#{inward}")
private InwardBean inwardBean;

@PostConstruct
public void init() {
    outwardItems = inwardBean.getOutwardItem();
        }

public List<Inward> getOutwardItems() {
        return outwardItems;
        }

public void setInwardBean(InwardBean inwardBean) {
        this.inwardBean = inwardBean;
        }

    public void postProcessXLS(Object document){
        inwardBean.postProcessXLS(document);

    }

}

