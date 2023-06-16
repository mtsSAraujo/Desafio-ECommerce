package db.cart;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class modifyCart {

    public static void increaseProductsOnCart(Connection conn, int id, int quantity){

    }

    public static void decreaseProductsOnCart(Connection conn, int id, int quantity){

    }

    public static ResultSet viewCartProducts(Connection conn){

        Statement statementCart = null;

        ResultSet showProductsOnCart = null;

        try{
            statementCart = conn.createStatement();
            showProductsOnCart = statementCart.executeQuery("SELECT * FROM kart");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(statementCart);
            DB.closeResultSet(showProductsOnCart);
        }

        return showProductsOnCart;
    }

    public static ResultSet viewProductsTable(Connection conn, int id){

        Statement statementProducts = null;

        ResultSet showProductsTable = null;

        try{
            statementProducts = conn.createStatement();
            String query = String.format("SELECT * FROM products where id = %s", id);
            showProductsTable = statementProducts.executeQuery(query);

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return showProductsTable;
    }

    public static boolean compareQuantityToCartIncrease(Connection conn, int addedQuantity){
        ResultSet productsTable = null;
        ResultSet cartTable = null;

        try{
            cartTable = viewCartProducts(conn);

            while(cartTable.next()){
                int productQuantityInCart = cartTable.getInt("product_quantity");
                int id_product = cartTable.getInt("fk_products");
                productsTable = viewProductsTable(conn, id_product);
                while(productsTable.next()){
                    if(productQuantityInCart + addedQuantity <= productsTable.getInt("quantity")){
                        return true;
                    }
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(productsTable);
            DB.closeResultSet(cartTable);

        }
        return false;
    }
    public static boolean compareQuantityToCartDecrease(Connection conn, int subtractedQuantity){
        ResultSet productsTable = null;
        ResultSet cartTable = null;

        try{
            cartTable = viewCartProducts(conn);

            while(cartTable.next()){
                int productQuantityInCart = cartTable.getInt("product_quantity");
                int id_product = cartTable.getInt("fk_products");
                productsTable = viewProductsTable(conn, id_product);
                while(productsTable.next()){
                    if(productQuantityInCart - subtractedQuantity <= 0){
                        return true;
                    }
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(productsTable);
            DB.closeResultSet(cartTable);

        }
        return false;
    }

    public static boolean checkIfProductsOnCart(Connection conn, int id){
        ResultSet productOnCart = null;
        try {
            productOnCart = viewCartProducts(conn);

            while(productOnCart.next()){
                if(productOnCart.getInt("id")== id){
                    return true;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(productOnCart);
        }
        return false;
    }

    public static void increaseOrDecreaseOnCart(Connection conn, int option){
        Scanner sc = new Scanner(System.in);
        if(option == 1){
            System.out.println("Insert the product ID you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductsOnCart(conn, id)){
                System.out.println("Insert the product quantity you want to add: ");
                int quantity = sc.nextInt();
                if(compareQuantityToCartIncrease(conn, quantity)){
                    increaseProductsOnCart(conn, id, quantity);
                    System.out.println("Added "+ quantity + " to the product successfully!");
                }
                else{
                    System.out.println("Quantity added would surpass stock availability.");
                }
            }
            else{
                System.out.println("Product ID not on Cart!");
            }
        }
        else if(option == 2){
            System.out.println("Insert the product ID you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductsOnCart(conn, id)){
                System.out.println("Insert the product quantity you want to decrease: ");
                int quantity = sc.nextInt();
                if(compareQuantityToCartDecrease(conn, quantity)){
                    decreaseProductsOnCart(conn, id, quantity);
                    System.out.println("Removed "+ quantity + " to the product successfully!");
                }
                else{
                    System.out.println("Quantity subtracted would reduce quantity in Cart to negative number."
                            + "\nInsert a valid value."
                    );
                }
            }
            else{
                System.out.println("Product ID not on Cart!");
            }
        }
        else{
            System.out.println("Invalid Option!");
        }
    }

}
