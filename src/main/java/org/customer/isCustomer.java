package org.customer;

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

        System.out.println("Escolhe uma das opções a seguir:");
        System.out.println("1 - Ver lista de produtos;");
        System.out.println("2 - Adicionar produto ao carrinho;");
        System.out.println("3 - Remover produto do carrinho;");
        System.out.println("4 - Ver carrinho atual;");
        System.out.println("5 - Modificar itens do carrinho;");
        System.out.println("6 - Confirmar compra;");
        System.out.println("7 - Voltar ao menu anterior;");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        int cont = 1;
        while(option>=0 && option<= 6) {
            String sair = "1";
            switch (option) {

                case 1:
                    while(sair.equals("1")) {
                        showDB(st);
                        System.out.println("Digite qualquer tecla para voltar ao menu anterior:");
                        sair = sc.next();
                        if(sair.equals("1")){
                            sair = "2";
                        }
                    }
                    break;

                case 2:
                    showDB(st);
                    addProductOnCart(conn, cont);
                    break;

                case 3:
                    showCart(st);
                    System.out.println("Digite o id do produto a ser removido: ");
                    int idRemovido = sc.nextInt();
                    removeProductFromCart(conn, idRemovido);
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
            System.out.println("Escolhe uma das opções a seguir:");
            System.out.println("1 - Ver lista de produtos;");
            System.out.println("2 - Adicionar produto ao carrinho;");
            System.out.println("3 - Remover produto do carrinho;");
            System.out.println("4 - Ver carrinho atual;");
            System.out.println("5 - Modificar itens do carrinho;");
            System.out.println("6 - Confirmar compra;");
            System.out.println("7 - Voltar ao menu anterior;");
            option = sc.nextInt();

        }

        closeStatement(st);
    }
}
