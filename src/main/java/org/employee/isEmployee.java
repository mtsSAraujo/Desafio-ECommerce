package org.employee;

import db.DB;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static db.controlDB.addOnDB.addProductOnDB;
import static db.controlDB.removeFromDB.removeProductFromDB;
import static db.controlDB.showDB.showDB;

public class isEmployee {

    public static void menuEmployee(Connection conn){

        Statement st = null;
        try{
            st = conn.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Choose one of the following options:");
        System.out.println("1 - Products list;");
        System.out.println("2 - Add product on Data Base;");
        System.out.println("3 - Remove product from Data Base;");
        System.out.println("4 - Modify products from Data Base;");
        System.out.println("5 - Previous menu;");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        System.out.println("\n\n\n");

        while(option>0 && option <=5) {
            String exit = "1";
            switch (option) {
                case 1:
                    while(exit.equals("1")) {
                        showDB(st);
                        System.out.println("Press any button to go back to the previous menu:");
                        exit = sc.next();
                        if(exit.equals("1")){
                            exit = "2";
                        }
                    }
                    break;

                case 2:
                    int cont = 1;
                    addProductOnDB(conn, cont);
                    break;

                case 3:
                    showDB(st);
                    System.out.println("Insert the ID of the product you wish to remove: ");
                    int removedProductID = sc.nextInt();
                    ResultSet checkIfProductExistsInDB = null;
                    boolean flagCheckIfProductExistsInDB = true;
                    try {
                        checkIfProductExistsInDB = st.executeQuery("SELECT id FROM products");

                        while(checkIfProductExistsInDB.next()){
                            if(removedProductID == checkIfProductExistsInDB.getInt("id")){
                                flagCheckIfProductExistsInDB = false;
                                removeProductFromDB(conn, removedProductID);
                                break;
                            }
                            else{
                                flagCheckIfProductExistsInDB = true;
                            }
                        }
                        if(flagCheckIfProductExistsInDB){
                            System.out.println("Product is not in DB.");
                        }
                    }
                    catch(SQLException e){
                        e.printStackTrace();
                    }
                    finally {
                        DB.closeResultSet(checkIfProductExistsInDB);
                    }
                    break;

                case 4:
                    break;

                case 5:
                    break;


                default:
                    System.out.println("Invalid Option!");
                    break;
            }
            if(option == 5){
                break;
            }
            System.out.println("Choose one of the following options:");
            System.out.println("1 - Products list;");
            System.out.println("2 - Add product on Data Base;");
            System.out.println("3 - Remove product from Data Base;");
            System.out.println("4 - Modify products from Data Base;");
            System.out.println("5 - Previous menu;");
            option = sc.nextInt();
            System.out.println("\n\n\n");
        }

        DB.closeStatement(st);
    }
}
