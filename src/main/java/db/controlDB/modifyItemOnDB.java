package db.controlDB;

import db.DB;

import java.sql.*;
import java.util.Scanner;

public class modifyItemOnDB {

    public static void increaseProductOnDB(Connection conn, int id, int quantity){

        PreparedStatement increaseOnDB = null;
        try{
            increaseOnDB = conn.prepareStatement("UPDATE products "
                    + "SET quantity = quantity + ? "
                    + "WHERE (id = ?)");

            increaseOnDB.setInt(1, quantity);
            increaseOnDB.setInt(2, id);
            increaseOnDB.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(increaseOnDB);
        }

    }

    public static void decreaseProductOnDB(Connection conn, int id, int quantity){

        PreparedStatement decreaseOnDB = null;
        try{
            decreaseOnDB = conn.prepareStatement("UPDATE products "
                    + "SET quantity = quantity - ? "
                    + "WHERE (id = ?)");

            decreaseOnDB.setInt(1, quantity);
            decreaseOnDB.setInt(2, id);
            decreaseOnDB.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(decreaseOnDB);
        }

    }


    public static boolean checkIfProductExists(Connection conn, int id){

        Statement startResultSet = null;
        ResultSet productsList = null;

        try{
            startResultSet = conn.createStatement();
            productsList = startResultSet.executeQuery("SELECT * FROM products");

            while(productsList.next()){
                if(productsList.getInt("id") == id){
                    return true;
                }
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(startResultSet);
            DB.closeResultSet(productsList);
        }
        return false;
    }

    public static boolean checkIfQuantityMatchsIncrease(Connection conn, int id, int quantity){

        Statement startResultSet = null;
        ResultSet productsList = null;

        try{

            startResultSet = conn.createStatement();
            String query = String.format("SELECT * FROM products where id = %s", id);
            productsList = startResultSet.executeQuery(query);

            while(productsList.next()){
                if((productsList.getInt("quantity") + quantity) >= 0){
                    return true;
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(startResultSet);
            DB.closeResultSet(productsList);
        }

        return false;
    }
    public static boolean checkIfQuantityMatchsDecrease(Connection conn, int id, int quantity){

        Statement startResultSet = null;
        ResultSet productsList = null;

        try{

            startResultSet = conn.createStatement();
            String query = String.format("SELECT * FROM products where id = %s", id);
            productsList = startResultSet.executeQuery(query);

            while(productsList.next()){
                if((productsList.getInt("quantity") - quantity) >= 0) {
                    return true;
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(startResultSet);
            DB.closeResultSet(productsList);
        }

        return false;
    }


    public static void increseOrDecrease(Connection conn, int option){
        Scanner sc = new Scanner(System.in);
        if(option == 1){
            System.out.println("Insert the product ID you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductExists(conn, id)){
                System.out.println("Insert the product quantity you want to add: ");
                int quantity = sc.nextInt();
                if(checkIfQuantityMatchsIncrease(conn, id, quantity)){
                    increaseProductOnDB(conn, id, quantity);
                    System.out.println("Added "+ quantity + " to the product successfully!");
                }
                else{
                    System.out.println("Quantity added would reduce quantity to negative number."
                            + "\nInsert a valid value."
                    );
                }
            }
            else{
                System.out.println("Product ID doesn't exists");
            }
        }
        else if(option == 2){
            System.out.println("Insert the product ID you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductExists(conn, id)){
                System.out.println("Insert the product quantity you want to decrease: ");
                int quantity = sc.nextInt();
                if(checkIfQuantityMatchsDecrease(conn, id, quantity)){
                    decreaseProductOnDB(conn, id, quantity);
                    System.out.println("Removed "+ quantity + " to the product successfully!");
                }
                else{
                    System.out.println("Quantity added would reduce quantity to negative number."
                            + "\nInsert a valid value."
                    );
                }
            }
            else{
                System.out.println("Product ID doesn't exists");
            }
        }
        else{
            System.out.println("Invalid Option!");
        }
    }

}
