package com.main;

import db.DB;

import java.sql.*;
import java.util.Scanner;

import static org.customer.isCustomer.*;
import static org.employee.isEmployee.*;

public class main {
    public static void main(String[] args) {

        Connection conn = null;

        conn = DB.getConnection();



        isCustomerOrEmployee(conn);

        DB.closeConnection();

    }

    public static void isCustomerOrEmployee(Connection conn){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
        System.out.println("2 - Funcionário");
        System.out.println("0 - Finalizar Programa.");
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
            System.out.println("Insira uma das opções a seguir: \n1 - Cliente");
            System.out.println("2 - Funcionário");
            System.out.println("0 - Finalizar Programa.");
            option = sc.nextInt();
        }

    }
}
