package org.example.Main;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {


        try(Connection myConn = DatabaseConnection.getInstance())  {
            if (myConn.getAutoCommit()) {
                myConn.setAutoCommit(false);
            }

            try {
                Repository<Employee> repository = new EmployeeRepository(myConn);

                System.out.println( "---------- Insertar un nuevo cliente ----------");
                Employee employee = new Employee();
             /*   employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname(("Villa"));
                employee.setEmail("ame@example.com");
                employee.setSalary(30000F);
                employee.setCurp("AMER123RTC456AQW12");
                repository.save(employee);*/

                myConn.commit();
                employee.setFirst_name("David");
                employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname(("Villa"));
                employee.setEmail("ame@example.com");
                employee.setSalary(30000F);
                employee.setCurp("AMER123RTC456AQW12");

                repository.save(employee);
            } catch (SQLException e) {
                myConn.rollback();
                e.printStackTrace();
            }
        }

    }
}
