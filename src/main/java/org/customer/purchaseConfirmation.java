package org.customer;

import db.DB;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static db.cart.productsTotal.productsTotal;

public  class purchaseConfirmation {

    public static void confirmation(Connection conn){

        Statement st = null;
        try{
            st = conn.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        double total = productsTotal(st);
        System.out.println("O valor final da compra foi: \n"
                + total + "\n Deseja confirmar o pedido?"
                + "\n1 - Para Sim; \n2 - Para Não.");
        int confirmation = sc.nextInt();

        if(confirmation == 1){
            System.out.println("Compra confirmada!");
            updateDB(conn);
            emptyCart(conn);
        }
        else{
            System.out.println("Não foi possivel confirmar sua compra."
            + "\nVoltando ao menu anterior.");
        }

        DB.closeStatement(st);
    }

    public static void emptyCart(Connection conn){

        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement("DELETE FROM kart;");

            pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(pst);
        }
    }

    public static void updateDB(Connection conn){

        ResultSet rsCart = null;
        PreparedStatement n_pst = null;
        Statement st = null;
        ResultSet rsDB = null;

        try{
            st = conn.createStatement();
            rsCart = st.executeQuery("SELECT * FROM kart");
            n_pst = conn.prepareStatement("UPDATE products "
                    + "SET quantity = ? "
                    + "WHERE (id = ?);");

            while(rsCart.next()){
                int id = rsCart.getInt("fk_products");

                rsDB = productsInCart(conn, id);

                int quantityDB = rsDB.getInt("quantity");
                int quantityCart = rsCart.getInt("product_quantity");
                int quantity = quantityDB - quantityCart;
                n_pst.setInt(1, quantity);
                n_pst.setInt(2, id);
                n_pst.executeUpdate();

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rsCart);
            DB.closeResultSet(rsDB);
            DB.closeStatement(n_pst);
            DB.closeStatement(st);
        }
    }

    public static ResultSet productsInCart(Connection conn, int id){

        ResultSet rs = null;
        Statement st = null;

        try{
            st = conn.createStatement();
            String query = String.format("SELECT * FROM products where id = %s", id);
            rs = st.executeQuery(query);
            rs.next();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return rs;

    }

}
