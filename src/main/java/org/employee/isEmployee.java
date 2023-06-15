package org.employee;

import db.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static db.controlDB.addOnDB.addProductOnDB;
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

        System.out.println("Escolhe uma das opções a seguir:");
        System.out.println("1 - Ver lista de produtos;");
        System.out.println("2 - Adicionar produto ao banco de dados;");
        System.out.println("3 - Remover produto do banco de dados;");
        System.out.println("4 - Modificar itens do banco de dados;");
        System.out.println("5 - Voltar ao menu anterior;");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        System.out.println("\n\n\n");

        while(option>0 && option <=5) {
            switch (option) {
                case 1:
                    String sair = "1";
                    while(sair.equals("1")) {
                        showDB(st);
                        System.out.println("Deseja voltar ao menu anterior? (Digite qualquer coisa)");
                        sair = sc.next();
                        if(sair.equals(1)){
                            sair = "2";
                        }
                    }
                    break;

                case 2:
                    int cont = 1;
                    addProductOnDB(conn, cont);
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;


                default:
                    System.out.println("Opção digitada inválida!");
                    break;
            }
            if(option == 5){
                break;
            }
            System.out.println("Escolhe uma das opções a seguir:");
            System.out.println("1 - Ver lista de produtos;");
            System.out.println("2 - Adicionar produto ao banco de dados;");
            System.out.println("3 - Remover produto do banco de dados;");
            System.out.println("4 - Modificar itens do banco de dados;");
            System.out.println("5 - Voltar ao menu anterior;");
            option = sc.nextInt();
            System.out.println("\n\n\n");
        }

        DB.closeStatement(st);
    }
}
