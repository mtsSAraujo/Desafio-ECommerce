package org.customer;

import db.DB;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static db.cart.productsTotal.productsTotal;

public class purchaseConfirmation {

    public static void confirmation(Connection conn){

        Statement st = null;
        PreparedStatement pst = null;
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
            try{
                pst = conn.prepareStatement("DELETE FROM kart;");

                pst.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Não foi possivel confirmar sua compra."
            + "\nVoltando ao menu anterior.");
        }

        DB.closeStatement(pst);
        DB.closeStatement(st);
    }
}
