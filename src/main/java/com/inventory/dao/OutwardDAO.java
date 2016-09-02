package com.inventory.dao;

import com.inventory.Inward;
import com.nz.simplecrud.db.DataConnect;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raviagrawal on 2/8/16.
 */
public class OutwardDAO {

    public List<Inward> getItemAvailability(Inward inward) {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Inward> inwardItems = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            inwardItems = new ArrayList<Inward>();


            String sql = "select id, quantity " +
                    "from stock_in where isIn='in' and design ='"+ inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"' and isConsolidated= 0";

            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Inward inwardItem = null;
            while(result.next()) {
                inwardItem = new Inward();
                inwardItem.setId(result.getInt("id"));
                inwardItem.setQuantity(result.getInt("quantity"));
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

    public int getItemAvailabilityInCurrentInv(Inward inward) {
        Connection con = null;
        PreparedStatement stmt = null;
        //List<Inward> inwardItems = null;
        ResultSet result = null;
        int quantityResult = 0;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            //inwardItems = new ArrayList<Inward>();


            String sql = "select quantity " +
                    "from current_inventory where design ='"+ inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";

            stmt = con.prepareStatement(sql);
            result=stmt.executeQuery();
            Inward inwardItem = null;
            while(result.next()) {
                inwardItem = new Inward();
                inwardItem.setQuantity(result.getInt("quantity"));
          //      inwardItems.add(inwardItem);
            }
            if(inwardItem!=null) {

                quantityResult = inwardItem.getQuantity();

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
        return quantityResult;


    }

    public static void addItem(Inward inward, String actionType, List<Inward> itemListAvailable,int totalQuantityAvailable){
        Connection con = null;
        Statement stmt = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtUpdate = null;
        PreparedStatement pstmtUpdateStock = null;

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


                /*con = DataConnect.getConnection();
                con.setAutoCommit(false);
                */
                //stmt = con.createStatement();


/*            String sqlSelect = "select vendor,design,date_in,colour,d_size,batch_size,no_of_batch,quantity,warehouse,isGroup " +
                    "from stock_in where isIn='out' and design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";
*/

            // ----------------------------    UpdateCurrentInventory method starts ------

            String sqlSelect = "select * " +
                        "from current_inventory where design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";

                String sqlInsert = "INSERT INTO current_inventory(design,colour,d_size,batch_size,no_of_batch,quantity,warehouse) " +
                        "VALUES ('"+inward.getDesign()+"','"
                        +inward.getColour()+"','"+
                        inward.getSize()+"',"+inward.getBatchSize()+","+inward.getNoOfBatch()+","+totalQuantityAvailable+",'"+inward.getWarehouse()+"')";

                String sqlUpdate =  "Update current_inventory set quantity ="+totalQuantityAvailable+" where design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";


            List<Integer> idList = new ArrayList<Integer>();
/*
int id[]=new int[10000000];
            for(int i=0;i<itemListAvailable.size();i++) {
                id[i]=itemListAvailable.get(i).getId();
            }
*/
            for(Inward items : itemListAvailable) {

                idList.add(items.getId());
            }


            String sqlUpdateStock =  "Update stock_in set isConsolidated=1 where design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'and id =?" ;




            pstmtSelect = con.prepareStatement(sqlSelect);

                //PreparedStatement pstmt2 = con.prepareStatement(sql2);
                ResultSet rsSelect = pstmtSelect.executeQuery();
                if (rsSelect.next()) {
                    pstmtUpdate = con.prepareStatement(sqlUpdate);
                    pstmtUpdate.executeUpdate();
                    pstmtUpdateStock = con.prepareStatement(sqlUpdateStock);

                   for(int i=0;i<itemListAvailable.size();i++) {
                        pstmtUpdateStock.setInt(1,itemListAvailable.get(i).getId());
                        pstmtUpdateStock.executeUpdate();

                        //idList.add(items.getId());
                    }



                    System.out.println("The Item already in current Inventory");
                    rsSelect.close();
                }
                else
                {
                    pstmtInsert = con.prepareStatement(sqlInsert);

                    pstmtInsert.executeUpdate();

                    pstmtUpdateStock = con.prepareStatement(sqlUpdateStock);

                    for(int i=0;i<itemListAvailable.size();i++) {
                        pstmtUpdateStock.setInt(1,itemListAvailable.get(i).getId());
                        pstmtUpdateStock.executeUpdate();

                        //idList.add(items.getId());
                    }



                    //con.commit();
                }
            con.commit();

    /*
    CREATE TABLE `current_inventory` (
  `id` INT(16) NOT NULL AUTO_INCREMENT,
  `design` VARCHAR(50) DEFAULT NULL,
  `colour` VARCHAR(50) DEFAULT NULL,
  `d_size` VARCHAR(50) DEFAULT NULL,
  `batch_size` INT(10) NOT NULL,
  `no_of_batch` INT(10) NOT NULL,
  `quantity` INT(10) NOT NULL,
  `warehouse` VARCHAR(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;
     */

                        }

            // ----------------------------    UpdateCurrentInventory method ends ------



//            con.commit();

  //          updateCurrentInventory(inward);
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
            `isIn` enum('in','out')  ,
            `isConsolidated` TINYINT(1),
                    PRIMARY KEY (`id`)
            )*/

         catch (SQLException ex) {
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
        if(pstmtInsert != null) {
            try {
                pstmtInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            if(pstmtUpdate != null) {
                try {
                    pstmtUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstmtUpdateStock != null) {
                try {
                    pstmtUpdateStock.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        if(pstmtSelect != null) {
            try {
                pstmtSelect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

            DataConnect.close(con);



    }
    }


    public List<Inward> getOutwardItem(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Inward> inwardItems = null;
        ResultSet result = null;
        try {
            con = DataConnect.getConnection();
            //con.setAutoCommit(false);
            inwardItems = new ArrayList<Inward>();


            String sql = "select vendor,design,date_in,colour,d_size,batch_size,no_of_batch,quantity,warehouse,isGroup " +
                    "from stock_in where isIn='out'";
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



    public static void updateCurrentInventory(Inward inward){
        Connection con = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtInsert = null;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            //stmt = con.createStatement();


/*            String sqlSelect = "select vendor,design,date_in,colour,d_size,batch_size,no_of_batch,quantity,warehouse,isGroup " +
                    "from stock_in where isIn='out' and design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";
*/
            String sqlSelect = "select * " +
                    "from current_inventory where design ='"+inward.getDesign()+"' and colour ='"+inward.getColour()+"' and d_size ='"+inward.getSize()+"' and batch_size ="+inward.getBatchSize()+" and warehouse= '"+inward.getWarehouse()+"'";

            String sqlInsert = "INSERT INTO current_inventory(design,colour,d_size,batch_size,no_of_batch,quantity,warehouse) " +
                    "VALUES ('"+inward.getDesign()+"','"
                    +inward.getColour()+"','"+
                    inward.getSize()+"',"+inward.getBatchSize()+","+inward.getNoOfBatch()+","+inward.getQuantity()+",'"+inward.getWarehouse()+"')";

             pstmtSelect = con.prepareStatement(sqlSelect);



            //PreparedStatement pstmt2 = con.prepareStatement(sql2);
                ResultSet rsSelect = pstmtSelect.executeQuery();
                if (rsSelect.next()) {
                    System.out.println("The Item already in current Inventory");
                    rsSelect.close();
                }
                else
                {
                    pstmtInsert = con.prepareStatement(sqlInsert);

                    pstmtInsert.executeUpdate();
                    con.commit();
                }





    /*
    CREATE TABLE `current_inventory` (
  `id` INT(16) NOT NULL AUTO_INCREMENT,
  `design` VARCHAR(50) DEFAULT NULL,
  `colour` VARCHAR(50) DEFAULT NULL,
  `d_size` VARCHAR(50) DEFAULT NULL,
  `batch_size` INT(10) NOT NULL,
  `no_of_batch` INT(10) NOT NULL,
  `quantity` INT(10) NOT NULL,
  `warehouse` VARCHAR(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;
     */

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if(pstmtInsert != null) {
                try {
                    pstmtInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstmtSelect != null) {
                try {
                    pstmtSelect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DataConnect.close(con);
        }

    }


}
