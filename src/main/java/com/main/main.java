package com.main;

import db.DB;

import java.sql.Connection;

import static db.cart.showCart.showCart;

public class main {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        //showDB();
        showCart();
        DB.closeConnection();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
//        System.out.println("2 - Funcionário");
//        int option = sc.nextInt();
//        clear();
//        switch (option){
//                case 1:
//                    //isCustomer();
//                    break;
//                case 2:
//                    //isEmployee();
//                    break;
//                default:
//                    System.out.println("Digite uma opção válida para execução!");
//                    break;
//
//
//        }

    }
}
