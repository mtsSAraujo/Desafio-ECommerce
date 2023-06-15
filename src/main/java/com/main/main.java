package com.main;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.cart.showCart.showCart;
import static db.controlDB.showDB.showDB;

public class main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DB.getConnection();

            st = conn.createStatement();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        showCart(st);
        showDB(st);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
//        System.out.println("2 - Funcionário");
//        int option = sc.nextInt();
//        clear();
//        switch (option){
//                case 1:
//                    //isCustomer();
//                    break;
//                case 2:
//                    //isEmployee();
//                    break;
//                default:
//                    System.out.println("Digite uma opção válida para execução!");
//                    break;
//
//
//        }
        DB.closeResultSet(rs);
        DB.closeStatement(st);
        DB.closeConnection();

    }
}
