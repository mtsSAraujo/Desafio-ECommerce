package db.cart;

import db.dbException;

import java.sql.*;
import java.util.Scanner;

import static db.DB.closeResultSet;
import static db.DB.closeStatement;

public class addProductOnCart {

    public static void addProductOnCart(Connection conn, int cont){

        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);

        try{

            st = conn.createStatement();
            rs = st.executeQuery("Select * from products;");
            pst = conn.prepareStatement("INSERT INTO kart "
                    + "(product_name, product_price, product_quantity, fk_products) "
                    + "VALUES "
                    + "(?, ?, ?, ?)");

            String product_name = "0";
            Double product_price = 0.0;
            int product_quantity = 0;
            System.out.println("Digite o ID do produto a ser adicionado: ");
            int id = sc.nextInt();
            while(rs.next()) {
                if(rs.getInt("id") == id){
                    product_name = rs.getString("product_name");
                    product_price = rs.getDouble("product_price");
                    product_quantity = rs.getInt("quantity");
                }
            }
            if(product_name.equals("0")){
                System.out.println("Id digitado inválido!");
            }
            pst.setString(1, product_name);
            pst.setDouble(2, product_price);
            System.out.println("Digite a quantidade do produto: ");
            int quantity = sc.nextInt();
            if(quantity > product_quantity){
                System.out.println("Quantidade maior que a disponivel em estoque."
                        +   "\nNão é possivel adicionar essa quantidade ao carrinho.");
            }
            pst.setInt(3, quantity);
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Feito! Produtos adicionados: " + rowsAffected);
        }
        catch (SQLException e){
            throw new dbException(e.getMessage());
        }

        closeResultSet(rs);
        closeStatement(pst);
        closeStatement(st);
    }
}
