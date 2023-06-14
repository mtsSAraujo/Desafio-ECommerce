package db.controlDB;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class showDB {

    public static void showDB(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from products;");

            System.out.println("Name   |   Price   |   Quantity");
            while(rs.next()){
                System.out.println(rs.getString("product_name") + "   |   " + rs.getDouble("product_price") + "   |   " + rs.getInt("quantity"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        DB.closeResultSet(rs);
        DB.closeStatement(st);
        DB.closeConnection();

    }
}
