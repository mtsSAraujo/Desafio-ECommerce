package db.controlDB;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class showDB {

    public static void showDB(Statement st){

        ResultSet rs = null;
        try{

            rs = st.executeQuery("select * from products;");

            System.out.println("ID   |   Name   |   Price   |   Quantity");
            while(rs.next()){
                System.out.println(rs.getInt("id") + "   |   " + rs.getString("product_name") + "   |   " + rs.getDouble("product_price") + "   |   " + rs.getInt("quantity"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        DB.closeResultSet(rs);
    }
}
