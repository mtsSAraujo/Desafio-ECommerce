package db.controlDB;

import db.dbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static db.DB.closeStatement;

public class addOnDB {

    public static void addProductOnDB(Connection conn, int cont){

        PreparedStatement st = null;
        Scanner sc = new Scanner(System.in);


        try{

            st = conn.prepareStatement("INSERT INTO products "
                    + "(product_name, product_price, quantity) "
                    + "VALUES "
                    + "(?, ?, ?)");

            while(cont == 1) {
                System.out.println("Digite o nome do produto: ");
                String product_name = sc.next();
                st.setString(1, product_name);
                System.out.println("Digite o preço do produto: ");
                Double product_price = sc.nextDouble();
                st.setDouble(2, product_price);
                System.out.println("Digite a quantidade do produto: ");
                int quantity = sc.nextInt();
                st.setInt(3, quantity);
                System.out.println("Deseja cadastrar outro produto?"
                    + "\nDigite:\n1 - Para Sim\n2 - Para Não");
                cont = sc.nextInt();

            }
            int rowsAffected = st.executeUpdate();
            System.out.println("Feito! Produtos adicionados: " + rowsAffected);
        }
        catch (SQLException e){
            throw new dbException(e.getMessage());
        }



        closeStatement(st);
    }
}
