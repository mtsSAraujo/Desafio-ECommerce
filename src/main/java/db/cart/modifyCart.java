package db.cart;

import db.DB;

import java.sql.*;
import java.util.Scanner;

public class modifyCart {

    public static void increaseProductsOnCart(Connection conn, int id, int quantity){

        PreparedStatement increaseOnCart = null;
        try{
            increaseOnCart = conn.prepareStatement("UPDATE kart "
                    + "SET product_quantity = product_quantity + ? "
                    + "WHERE (id = ?)");

            increaseOnCart.setInt(1, quantity);
            increaseOnCart.setInt(2, id);
            increaseOnCart.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(increaseOnCart);
        }
    }

    public static void decreaseProductsOnCart(Connection conn, int id, int quantity){

        PreparedStatement decreaseOnCart = null;
        ResultSet productsOnFinalCart = null;

        try{
            decreaseOnCart = conn.prepareStatement("UPDATE kart "
                    + "SET product_quantity = product_quantity - ? "
                    + "WHERE (id = ?)");

            decreaseOnCart.setInt(1, quantity);
            decreaseOnCart.setInt(2, id);
            decreaseOnCart.executeUpdate();

            decreaseOnCart = null;

            productsOnFinalCart = viewCartProducts(conn, id);
            while(productsOnFinalCart.next()){
                if(productsOnFinalCart.getInt("product_quantity") == 0){
                    decreaseOnCart = conn.prepareStatement("DELETE FROM kart "
                            + "WHERE (id = ?)");

                    decreaseOnCart.setInt(1, id);
                    decreaseOnCart.executeUpdate();
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(decreaseOnCart);
            DB.closeResultSet(productsOnFinalCart);
        }
    }

    public static ResultSet viewCartProducts(Connection conn, int id){

        Statement statementCart = null;

        ResultSet showProductsOnCart = null;

        try{
            statementCart = conn.createStatement();
            String query = String.format("SELECT * FROM kart where id = %s", id);
            showProductsOnCart = statementCart.executeQuery(query);

        }
        catch(SQLException e){
            e.printStackTrace();
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

    public static boolean compareQuantityToCartIncrease(Connection conn,int id, int addedQuantity){
        ResultSet productsTable = null;
        ResultSet cartTable = null;

        try{
            cartTable = viewCartProducts(conn, id);

            while(cartTable.next()){
                int productQuantityInCart = cartTable.getInt("product_quantity");
                int id_product = cartTable.getInt("fk_products");
                productsTable = viewProductsTable(conn, id_product);
                while(productsTable.next()) {
                    if (productQuantityInCart + addedQuantity <= productsTable.getInt("quantity")) {
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
    public static boolean compareQuantityToCartDecrease(Connection conn,int id, int subtractedQuantity){
        ResultSet cartTable = null;

        try{
            cartTable = viewCartProducts(conn, id);

            while(cartTable.next()){
                int productQuantityInCart = cartTable.getInt("product_quantity");
                if(productQuantityInCart - subtractedQuantity >= 0){
                    return true;
                }


            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(cartTable);

        }
        return false;
    }

    public static boolean checkIfProductsOnCart(Connection conn, int id){
        ResultSet productOnCart = null;
        try {
            productOnCart = viewCartProducts(conn, id);

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
                if(compareQuantityToCartIncrease(conn,id, quantity)){
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
                if(compareQuantityToCartDecrease(conn, id, quantity)){
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
