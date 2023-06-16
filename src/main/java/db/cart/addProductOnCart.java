package db.cart;

import db.dbException;

import java.sql.*;
import java.util.Scanner;

import static db.DB.closeResultSet;
import static db.DB.closeStatement;

public class addProductOnCart {

    public static void addProductOnCart(Connection conn, int cont, int id, int quantity){

        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);

        try{

            st = conn.createStatement();
            rs = st.executeQuery("Select * from products;");
            pst = conn.prepareStatement("INSERT INTO kart "
                    + "(product_name, product_price, product_quantity, fk_products) "
                    + "VALUES "
                    + "(?, ?, ?, ?)");

            String product_name = null;
            Double product_price = 0.0;
            while(rs.next()) {
                if(rs.getInt("id") == id){
                    product_name = rs.getString("product_name");
                    product_price = rs.getDouble("product_price");
                }
            }
            pst.setString(1, product_name);
            pst.setDouble(2, product_price);
            pst.setInt(3, quantity);
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Done! Products added: " + rowsAffected);
        }
        catch (SQLException e){
            throw new dbException(e.getMessage());
        }

        closeResultSet(rs);
        closeStatement(pst);
        closeStatement(st);
    }
}