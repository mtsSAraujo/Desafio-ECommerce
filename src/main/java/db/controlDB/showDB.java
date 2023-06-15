package db.controlDB;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class showDB {

    public static String SEPARATOR = "   |   ";
    public static void showDB(Statement st){

        ResultSet rs = null;
        try{

            rs = st.executeQuery("select * from products;");

            printDB(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        DB.closeResultSet(rs);
    }

    public static void printDB(ResultSet rs){
        System.out.println("ID   |   Name   |   Price   |   Quantity");
        try {
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getInt("id")).append(SEPARATOR).append(rs.getString("product_name"))
                        .append(SEPARATOR).append(rs.getDouble("product_price")).append(SEPARATOR)
                        .append(rs.getInt("quantity"));
                System.out.println(sb);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
