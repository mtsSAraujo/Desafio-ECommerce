package db.cart;

import db.dbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class productsTotal {

    public static double productsTotal(Statement st){
        double total = 0;
        try {
            ResultSet rs = st.executeQuery("select * from kart;");

            while (rs.next()) {
                total += rs.getDouble("product_price");
            }
        }
        catch(SQLException e){
            throw new dbException(e.getMessage());
        }

        return total;
    }
}
