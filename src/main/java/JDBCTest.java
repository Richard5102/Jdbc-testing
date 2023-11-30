import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) {
        Connection myCon = null;
        PreparedStatement myStant = null;
        ResultSet myRes = null;

        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "5102");
            System.out.print("Genial nos conectamos ");

            String sql = "INSERT INTO employees (first_name, pa_surname) VALUES (?, ?)";
            myStant = myCon.prepareStatement(sql);
            myStant.setString(1, "Johana");
            myStant.setString(2, "Dorantes");

            int rowsAffected = myStant.executeUpdate();


            while (myRes.next()) {
                System.out.println(myRes.getString(
                        "first_name"
                ));
            }
        } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Algo salio mal :(");
        }

    }
}
