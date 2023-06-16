package db.controlDB;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
