package db.cart;

import db.DB;
import db.dbException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

import static db.DB.closeResultSet;
import static db.DB.closeStatement;
import static db.cart.modifyCart.viewCartProducts;

public class addProductOnCart {

    public static void addProductOnCart(Connection conn, int id, int quantity){

        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet productOnCart= null;
        Statement startProductOnCart = null;
        int productOnCartID = 0;
        try{

            st = conn.createStatement();
            rs = st.executeQuery("Select * from products;");
            if(checkIfProductAlreadyOnCart(conn, id)){
                startProductOnCart = conn.createStatement();
                productOnCart = startProductOnCart.executeQuery("SELECT * FROM kart");
                while(productOnCart.next()) {
                    if (productOnCart.getInt("fk_products") == id) {
                        productOnCartID = productOnCart.getInt("id");
                    }
                }
                pst = conn.prepareStatement("UPDATE kart "
                        + "SET product_quantity = product_quantity + ? "
                        + "WHERE (id = ?)");

                pst.setInt(1, quantity);
                pst.setInt(2, productOnCartID);
                pst.executeUpdate();

                System.out.println("Done! Since product was already on cart, we added the quantity there!");

                pst = null;
            }
            else {
                pst = conn.prepareStatement("INSERT INTO kart "
                        + "(product_name, product_price, product_quantity, fk_products) "
                        + "VALUES "
                        + "(?, ?, ?, ?)");

                String product_name = null;
                Double product_price = 0.0;
                while (rs.next()) {
                    if (rs.getInt("id") == id) {
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
        }
        catch (SQLException e){
            throw new dbException(e.getMessage());
        }

        closeResultSet(rs);
        closeStatement(pst);
        closeStatement(st);
    }

    public static boolean checkIfProductAlreadyOnCart(Connection conn, int id){

        Statement startProductOnCart = null;
        ResultSet productOnCart = null;

        try{

            startProductOnCart = conn.createStatement();
            productOnCart = startProductOnCart.executeQuery("SELECT * FROM kart");
            while(productOnCart.next()){
                if(productOnCart.getInt("fk_products") == id){
                    return true;
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        DB.closeStatement(startProductOnCart);
        DB.closeResultSet(productOnCart);
        return false;
    }
}