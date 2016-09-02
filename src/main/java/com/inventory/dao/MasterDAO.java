package com.inventory.dao;

import com.inventory.Inward;
import com.master.Colour;
import com.master.Design;
import com.master.Vendor;
import com.master.Warehouse;
import com.nz.simplecrud.db.DataConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raviagrawal on 20/8/16.
 */
public class MasterDAO {
    public static void addColour(Colour colour){
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            String sql = "INSERT INTO colour_mst(colour) " +
                    "VALUES ('"+colour.getColour()+"')";
            stmt.executeUpdate(sql);
            con.commit();

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

    public static void addWarehouse(Warehouse warehouse){
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            String sql = "INSERT INTO warehouse_mst(warehouse) " +
                    "VALUES ('"+warehouse.getWarehouse()+"')";
            stmt.executeUpdate(sql);
            con.commit();

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


    public static void addDesign(Design design){
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            String sql = "INSERT INTO design_mst(design) " +
                    "VALUES ('"+design.getDesign()+"')";
            stmt.executeUpdate(sql);
            con.commit();

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


    public List<Colour> getColourMst(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Colour> colourObjs = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            colourObjs = new ArrayList<Colour>();


            String sql = "select colour " +
                    "from colour_mst where isActive=1";
            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Colour colourObj = null;
            while(result.next()) {
                colourObj = new Colour();
                colourObj.setColour(result.getString("colour"));
                colourObjs.add(colourObj);
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
        return colourObjs;
    }



    public List<Design> getDesignMst(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Design> designObjs = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            designObjs = new ArrayList<Design>();


            String sql = "select design " +
                    "from design_mst where isActive=1";
            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Design designObj = null;
            while(result.next()) {
                designObj = new Design();
                designObj.setDesign(result.getString("design"));
                designObjs.add(designObj);
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
        return designObjs;
    }




    public List<Warehouse> getWarehouseMst(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Warehouse> warehouseObjs = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            warehouseObjs = new ArrayList<Warehouse>();


            String sql = "select warehouse " +
                    "from warehouse_mst where isActive=1";
            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Warehouse warehouseObj = null;
            while(result.next()) {
                warehouseObj = new Warehouse();
                warehouseObj.setWarehouse(result.getString("warehouse"));
                warehouseObjs.add(warehouseObj);
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
        return warehouseObjs;
    }

    public static void addVendor(Vendor vendor){
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();

            String sql = "INSERT INTO vendor_mst(vendor,address,phone,comment,isSupplier) " +
                    "VALUES ('"+vendor.getVendor()+"','"+vendor.getAddress()+"','"+vendor.getPhone()+"','"+vendor.getComment()+"','1)";
            stmt.executeUpdate(sql);
            con.commit();

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


    public List<Vendor> getVendorMst(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Vendor> vendorObjs = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            vendorObjs = new ArrayList<Vendor>();


            String sql = "select vendor " +
                    "from vendor_mst where isActive=1 and isSupplier=1";
            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Vendor vendorObj = null;
            while(result.next()) {
                vendorObj = new Vendor();
                vendorObj.setVendor(result.getString("vendor"));
                vendorObjs.add(vendorObj);
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
        return vendorObjs;
    }


}
