package db.cart;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class removeProductFromCart {

    public static void removeProductFromCart(Connection conn, int id){

        PreparedStatement deleteProduct = null;

        try{
            deleteProduct = conn.prepareStatement("DELETE FROM kart"
                    + " WHERE (id = ?)");

            deleteProduct.setInt(1,id);

            deleteProduct.executeUpdate();
            System.out.println("Product removed successfully!");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(deleteProduct);
        }

    }
}
