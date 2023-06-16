package org.customer;

import db.DB;
import db.dbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static db.DB.closeStatement;
import static db.cart.addProductOnCart.addProductOnCart;
import static db.cart.removeProductFromCart.removeProductFromCart;
import static db.cart.showCart.showCart;
import static db.controlDB.showDB.showDB;
import static db.cart.productsTotal.productsTotal;
import static org.customer.purchaseConfirmation.confirmation;

public class isCustomer {

    public static void menuCustomer(Connection conn){

        Statement st = null;
        try{
            st = conn.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Choose one of the following options:");
        System.out.println("1 - Products list;");
        System.out.println("2 - Add product on Cart;");
        System.out.println("3 - Remove product from Cart;");
        System.out.println("4 - List of products on Cart;");
        System.out.println("5 - Modify Cart items;");
        System.out.println("6 - Confirm Purchase;");
        System.out.println("7 - Previous menu;");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        int cont = 1;
        while(option>=0 && option<= 6) {
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
                    showDB(st);
                    System.out.println("Insert the product ID that will be added: ");
                    int productAddedID = sc.nextInt();
                    System.out.println("Insert the product quantity to be added: ");
                    int productAddedQuantity = sc.nextInt();
                    ResultSet seeIfProductOnDB = null;
                    boolean controlProductAdded = false;
                    try{
                        seeIfProductOnDB = st.executeQuery("SELECT * FROM products");

                        while(seeIfProductOnDB.next()){
                            if (productAddedID == seeIfProductOnDB.getInt("id")){
                                controlProductAdded = false;
                                if (productAddedQuantity  <= seeIfProductOnDB.getInt("quantity")) {
                                    addProductOnCart(conn, cont, productAddedID, productAddedQuantity);
                                    break;
                                }
                                else{
                                    System.out.println("Product quantity higher then the available on Stock.");
                                    break;
                                }
                            }
                            else{
                                controlProductAdded = true;
                            }
                        }
                        if (controlProductAdded){
                            System.out.println("Product ID Not Found.");
                        }
                    }
                    catch(SQLException e){
                        e.printStackTrace();
                    }
                    finally {
                        DB.closeResultSet(seeIfProductOnDB);
                    }
                    break;

                case 3:
                    showCart(st);
                    System.out.println("Insert the ID of a product to be removed: ");
                    int idRemoved = sc.nextInt();
                    removeProductFromCart(conn, idRemoved);
                    break;

                case 4:
                    showCart(st);
                    System.out.println("Subtotal: R$" + productsTotal(st));
                    break;

//            case 5:
//                showCart();
//                System.out.println("Deseja adicionar ou remover algum produto?");
//                System.out.println("1 - Sim; \n2 - Não");
//                int modifyKart = sc.nextInt();
//                if(modifyKart == 1){
//                    System.out.println("Digite o id do produto e a quantidade a ser alterada, no formato (id quantidade):");
//                }

                case 6:
                    confirmation(conn);
                    break;

                case 7:
                    break;
//
//                break;
//
//            default:
//                System.out.println("Insira uma opção válida!");
//                break;
//
            }
            if(option == 7){
                break;
            }
            System.out.println("Choose one of the following options:");
            System.out.println("1 - Products list;");
            System.out.println("2 - Add product on Cart;");
            System.out.println("3 - Remove product from Cart;");
            System.out.println("4 - List of products on Cart;");
            System.out.println("5 - Modify Cart items;");
            System.out.println("6 - Confirm Purchase;");
            System.out.println("7 - Previous menu;");
            option = sc.nextInt();

        }

        closeStatement(st);
    }
}
