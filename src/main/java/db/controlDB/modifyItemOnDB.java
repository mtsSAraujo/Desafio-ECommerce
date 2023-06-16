package db.controlDB;

import java.sql.Connection;
import java.util.Scanner;

public class modifyItemOnDB {

    public static void increaseProductOnDB(Connection conn, int id, int quantity){

    }

    public static void decreaseProductOnDB(Connection conn, int id, int quantity){

    }


    public static boolean checkIfProductExists(Connection conn, int id){

    }

    public static boolean checkIfQuantityMatchs(Connection conn, int id, int quantity){

    }


    public static void increseOrDecrease(Connection conn, int option){
        Scanner sc = new Scanner(System.in);
        boolean flagProductExist = true;
        if(option == 1){
            System.out.println("Insert the product id you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductExists(conn, id)){
                System.out.println("Insert the product quantity you want to add: ");
                int quantity = sc.nextInt();
                if(checkIfQuantityMatchs(conn, id, quantity)){
                    increaseProductOnDB(conn, id, quantity);
                }
                else{
                    System.out.println("Quantity added would reduce quantity to negative number."
                            + "\nInsert a valid value."
                    );
                }
            }
            else{
                System.out.println("Product ID doesn't exists");
            }
        }
        else if(option == 2){
            System.out.println("Insert the product id you want to modify: ");
            int id = sc.nextInt();
            if(checkIfProductExists(conn, id)){
                System.out.println("Insert the product quantity you want to decrease: ");
                int quantity = sc.nextInt();
                if(checkIfQuantityMatchs(conn, id, quantity)){
                    decreaseProductOnDB(conn, id, quantity);
                }
                else{
                    System.out.println("Quantity added would reduce quantity to negative number."
                            + "\nInsert a valid value."
                    );
                }
            }
            else{
                System.out.println("Product ID doesn't exists");
            }
        }
        else{
            System.out.println("Invalid Option!");
        }
    }

}
