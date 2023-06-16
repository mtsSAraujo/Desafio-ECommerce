package db.controlDB;

import com.mysql.cj.protocol.Resultset;
import db.DB;

import java.sql.*;

public class removeFromDB {

    public static void removeProductFromDB(Connection conn, int id){

        PreparedStatement removeFromDB = null;

        try{
            removeFromDB = conn.prepareStatement("DELETE FROM products"
                    + " WHERE (id = ?)");

            removeFromDB.setInt(1, id);

            removeFromDB.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(removeFromDB);
        }

        System.out.println("Product ID: " + id + " \nRemoved successfully!");

    }

    public static void checkIfProductNotOnCart(Connection conn, int id){

        Statement checkProducts = null;
        ResultSet productsTable = null;
        Statement checkCartTable = null;
        ResultSet cartTable = null;
        boolean checkCart = false;
        boolean flagSafeToRemove = false;
        boolean cartIsEmpty = true;

        try{

            checkProducts = conn.createStatement();

            checkCartTable = conn.createStatement();

            productsTable = checkProducts.executeQuery("SELECT * FROM products");

            cartTable = checkCartTable.executeQuery("SELECT * FROM kart");

            while(cartTable.next()){
                cartIsEmpty = false;
                int fk_products_id = cartTable.getInt("fk_products");
                while(productsTable.next()){
                    if(productsTable.getInt("id") == fk_products_id){
                        System.out.println("Can't remove item, item still on cart!");
                        checkCart = true;
                        flagSafeToRemove = false;
                        break;
                    }
                    else{
                        flagSafeToRemove = true;
                    }
                }
                if(checkCart){
                    break;
                }
                if(flagSafeToRemove){
                    removeProductFromDB(conn, id);
                    break;
                }
            }
            if(cartIsEmpty){
                removeProductFromDB(conn, id);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(productsTable);
            DB.closeResultSet(cartTable);
            DB.closeStatement(checkProducts);
            DB.closeStatement(checkCartTable);
        }
    }

}
