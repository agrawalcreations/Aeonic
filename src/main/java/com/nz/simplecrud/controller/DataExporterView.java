package com.nz.simplecrud.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.inventory.Inward;
import com.inventory.InwardBean;
/**
 * Created by raviagrawal on 9/8/16.
 */
@ManagedBean
public class DataExporterView implements Serializable {


    private List<Inward> inwardItems;

@ManagedProperty("#{inward}")
private InwardBean inwardBean;

@PostConstruct
public void init() {
    inwardItems = inwardBean.getInwardItem();

        }

public List<Inward> getInwardItems() {
        return inwardItems;
        }

public void setInwardBean(InwardBean inwardBean) {
        this.inwardBean = inwardBean;
        }

    public void postProcessXLS(Object document){
        inwardBean.postProcessXLS(document);

    }

}

