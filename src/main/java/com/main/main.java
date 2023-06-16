package com.main;

import db.DB;

import java.sql.*;
import java.util.Scanner;

import static com.main.util.fileReader.fileReader;
import static com.main.util.fileReaderTableCart.fileReaderTableCart;
import static com.main.util.fileReaderTableProducts.fileReaderTableProducts;
import static org.customer.isCustomer.*;
import static org.employee.isEmployee.*;

public class main {
    public static void main(String[] args) {

        Connection conn = null;

        conn = DB.getConnection();

        //Call Method for building the DataBase
        String queryTable = fileReaderTableProducts();

        String queryValues = fileReader();

        String queryTableCart = fileReaderTableCart();

        PreparedStatement insertTablesInDB = null;
        PreparedStatement insertTableCartInDB = null;
        PreparedStatement insertValuesInDB = null;

        try{
            insertTablesInDB = conn.prepareStatement(queryTable);

            insertTablesInDB.execute();

            insertTableCartInDB = conn.prepareStatement(queryTableCart);

            insertTableCartInDB.execute();

            insertValuesInDB = conn.prepareStatement(queryValues);

            insertValuesInDB.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(insertTablesInDB);
            DB.closeStatement(insertValuesInDB);
            DB.closeStatement(insertTableCartInDB);
        }


        isCustomerOrEmployee(conn);

        DB.closeConnection();

    }

    public static void isCustomerOrEmployee(Connection conn){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose one of the following option: \n1 - Customer;");
        System.out.println("2 - Employee;");
        System.out.println("0 - End Program.");
        int option = sc.nextInt();
        while(option>=0 && option <= 2) {
            switch (option) {
                case 1:
                    menuCustomer(conn);
                    break;

                case 2:
                    menuEmployee(conn);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Digite uma opção válida para execução!");
                    break;


            }
            if(option == 0){
                break;
            }
            System.out.println("Choose one of the following option: \n1 - Customer;");
            System.out.println("2 - Employee;");
            System.out.println("0 - End Program.");
            option = sc.nextInt();
        }

    }
}
