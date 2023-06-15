package db.cart;

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
            System.out.println("Produto removido com sucesso!");

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}
