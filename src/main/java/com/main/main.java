package com.main;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static clearConsole.clearConsole.clear;
import static db.cart.productsTotal.productsTotal;
import static db.cart.showCart.showCart;
import static db.controlDB.showDB.showDB;
import static org.customer.isCustomer.*;

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

        isCustomerOrEmployee(st);

        DB.closeResultSet(rs);
        DB.closeStatement(st);
        DB.closeConnection();

    }

    public static void isCustomerOrEmployee(Statement st){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
        System.out.println("2 - Funcionário");
        int option = sc.nextInt();
        clear();
        while(option>0 && option <= 2) {
            switch (option) {
                case 1:
                    menuCustomer(st);
                    break;
                case 2:
                    //menuEmployee(st);
                    break;
                default:
                    System.out.println("Digite uma opção válida para execução!");
                    break;


            }
            System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
            System.out.println("2 - Funcionário");
            option = sc.nextInt();
            clear();
        }
    }
}
