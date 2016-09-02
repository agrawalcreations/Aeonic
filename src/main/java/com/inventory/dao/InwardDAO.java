package com.inventory.dao;

import com.inventory.Inward;
import com.nz.simplecrud.db.DataConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raviagrawal on 2/8/16.
 */
public class InwardDAO {
    public static void addItem(Inward inward, String actionType){
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            String sql = "INSERT INTO stock_in(vendor,design,date_in,colour,d_size,batch_size,no_of_batch,quantity,warehouse,isGroup,isIn,isConsolidated) " +
                    "VALUES ('"+inward.getVendor()+"','"+inward.getDesign()+"','"
                    +inward.getDate()
                    +"','"+inward.getColour()+"','"+
                    inward.getSize()+"',"+inward.getBatchSize()+","+inward.getNoOfBatch()+","+inward.getQuantity()+",'"+inward.getWarehouse()+"',1,'"+actionType+"',0)";
            stmt.executeUpdate(sql);
            con.commit();
           /* CREATE TABLE `aeonic`.`stock_in`(
            `id` INT(16) NOT NULL AUTO_INCREMENT ,
            `vendor` VARCHAR(50) NOT NULL ,
            `design` VARCHAR(50) ,
            `date_in` DATE NOT NULL ,
            `colour` VARCHAR(50) ,
            `d_size` VARCHAR(50) ,
            `batch_size` INT(10) NOT NULL ,
            `no_of_batch` INT(10) NOT NULL ,
            `quantity` INT(10) NOT NULL ,
            `warehouse` VARCHAR(10) NOT NULL ,
            `isGroup` TINYINT(1) DEFAULT '1' ,
            `isIn` enum('in','out')  ,
            `isConsolidated` TINYINT(1),
                    PRIMARY KEY (`id`)
            )*/

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DataConnect.close(con);
        }

    }


    public List<Inward> getInwardItem(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Inward> inwardItems = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            inwardItems = new ArrayList<Inward>();


            String sql = "select vendor,design,date_in,colour,d_size,batch_size,no_of_batch,quantity,warehouse,isGroup " +
                    "from stock_in where isIn='in'";
            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Inward inwardItem = null;
            while(result.next()) {
                inwardItem = new Inward();
                inwardItem.setVendor(result.getString("vendor"));
                inwardItem.setDesign(result.getString("design"));
                inwardItem.setDate(result.getString("date_in"));
                inwardItem.setColour(result.getString("colour"));
                inwardItem.setSize(result.getString("d_size"));
                inwardItem.setBatchSize(result.getInt("batch_size"));
                inwardItem.setNoOfBatch(result.getInt("no_of_batch"));
                inwardItem.setQuantity(result.getInt("quantity"));
                inwardItem.setWarehouse(result.getString("warehouse"));
                inwardItems.add(inwardItem);
            }

            //con.commit();
           /* CREATE TABLE `aeonic`.`stock_in`(
            `id` INT(16) NOT NULL AUTO_INCREMENT ,
            `vendor` VARCHAR(50) NOT NULL ,
            `design` VARCHAR(50) ,
            `date_in` DATE NOT NULL ,
            `colour` VARCHAR(50) ,
            `d_size` VARCHAR(50) ,
            `batch_size` INT(10) NOT NULL ,
            `no_of_batch` INT(10) NOT NULL ,
            `quantity` INT(10) NOT NULL ,
            `warehouse` VARCHAR(10) NOT NULL ,
            `isGroup` TINYINT(1) DEFAULT '1' ,
            `isIn` enum('in','out')  ,
            `isConsolidated` TINYINT(1),
                    PRIMARY KEY (`id`)
            )*/

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
                   } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DataConnect.close(con);
        }
    return inwardItems;
    }


}
