package db.cart;

import db.dbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class productsTotal {

    public static double productsTotal(Statement st){
        double product_total = 0.0;
        double total = 0.0;
        try {
            ResultSet rs = st.executeQuery("select * from kart;");

            while (rs.next()) {
                product_total = rs.getDouble("product_price") * rs.getInt("product_quantity");
                total += product_total;
            }
        }
        catch(SQLException e){
            throw new dbException(e.getMessage());
        }

        return total;
    }
}
