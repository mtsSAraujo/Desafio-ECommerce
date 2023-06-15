package org.customer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static db.cart.showCart.showCart;
import static db.controlDB.showDB.showDB;

public class isCustomer {

    public void menuCustomer(Statement st, ResultSet rs){
        System.out.println("Escolhe uma das opções a seguir:");
        System.out.println("1 - Ver lista de produtos;");
        System.out.println("2 - Adicionar produto ao carrinho;");
        System.out.println("3 - Remover produto do carrinho;");
        System.out.println("4 - Ver carrinho atual;");
        System.out.println("5 - Modificar itens do carrinho;");
        System.out.println("6 - Voltar ao menu anterior;");
        System.out.println("0 - Finalizar Programa.");
        Scanner sc = new Scanner(System.in);
        clearConsole.clearConsole.clear();
        int option = sc.nextInt();

        switch(option){
            case 1:
                showDB(st);
                break;

//            case 2:
//                addProductOnKart(id product, int quantity);
//                break;
//
//            case 3:
//                removeProductFromKart(id product, int quantity)
//                break;
//
            case 4:
                showCart(st);
                //productsTotal();
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
    }
}
