package org.customer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static db.cart.showCart.showCart;
import static db.controlDB.showDB.showDB;
import static db.cart.productsTotal.productsTotal;
import static clearConsole.clearConsole.clear;

public class isCustomer {

    public static void menuCustomer(Statement st){
        System.out.println("Escolhe uma das opções a seguir:");
        System.out.println("1 - Ver lista de produtos;");
        System.out.println("2 - Adicionar produto ao carrinho;");
        System.out.println("3 - Remover produto do carrinho;");
        System.out.println("4 - Ver carrinho atual;");
        System.out.println("5 - Modificar itens do carrinho;");
        System.out.println("6 - Voltar ao menu anterior;");
        System.out.println("0 - Finalizar Programa.");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        clear();

        while(option>=0 && option<= 6) {
            switch (option) {
                case 1:
                    showDB(st);
                    break;

//            case 2:
//                addProductOnCart(id product, int quantity);
//                break;
//
//            case 3:
//                removeProductFromCart(id product, int quantity)
//                break;
//
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
//
//                break;
//
//            default:
//                System.out.println("Insira uma opção válida!");
//                break;
//
            }
            System.out.println("Escolhe uma das opções a seguir:");
            System.out.println("1 - Ver lista de produtos;");
            System.out.println("2 - Adicionar produto ao carrinho;");
            System.out.println("3 - Remover produto do carrinho;");
            System.out.println("4 - Ver carrinho atual;");
            System.out.println("5 - Modificar itens do carrinho;");
            System.out.println("6 - Voltar ao menu anterior;");
            System.out.println("0 - Finalizar Programa.");
            option = sc.nextInt();
            clear();

        }
    }
}
