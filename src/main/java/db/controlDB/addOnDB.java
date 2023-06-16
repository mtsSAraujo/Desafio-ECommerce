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
                System.out.println("Insert product name: ");
                String product_name = sc.next();
                st.setString(1, product_name);
                System.out.println("Insert product price: ");
                Double product_price = sc.nextDouble();
                st.setDouble(2, product_price);
                System.out.println("Insert the product quantity: ");
                int quantity = sc.nextInt();
                st.setInt(3, quantity);
                System.out.println("Do you wish to add another product?"
                    + "\n1 - Yes \n2 - No");
                cont = sc.nextInt();

            }
            int rowsAffected = st.executeUpdate();
            System.out.println("Done! Products added: " + rowsAffected);
        }
        catch (SQLException e){
            throw new dbException(e.getMessage());
        }

        closeStatement(st);
    }
}
