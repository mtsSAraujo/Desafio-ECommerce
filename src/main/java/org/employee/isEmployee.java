package org.employee;

import java.util.Scanner;

public class isEmployee {

    public void menuEmployee(){
        System.out.println("Escolhe uma das opções a seguir:");
        System.out.println("1 - Ver lista de produtos;");
        System.out.println("2 - Adicionar produto ao banco de dados;");
        System.out.println("3 - Remover produto do banco de dados;");
        System.out.println("4 - Modificar itens do banco de dados;");
        System.out.println("5 - Voltar ao menu anterior;");
        System.out.println("0 - Finalizar Programa.");
        Scanner sc = new Scanner(System.in);
        clearConsole.clearConsole.clear();
        int option = sc.nextInt();
        while(option>=0 && option <=5) {
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opção digitada inválida!");
                    break;
            }
            System.out.println("Escolhe uma das opções a seguir:");
            System.out.println("1 - Ver lista de produtos;");
            System.out.println("2 - Adicionar produto ao banco de dados;");
            System.out.println("3 - Remover produto do banco de dados;");
            System.out.println("4 - Modificar itens do banco de dados;");
            System.out.println("5 - Voltar ao menu anterior;");
            System.out.println("0 - Finalizar Programa.");
            option = sc.nextInt();
        }
    }
}
