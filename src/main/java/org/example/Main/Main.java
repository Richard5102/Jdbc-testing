package org.example.Main;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {


        try(Connection myConn = DatabaseConnection.getInstance())  {
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("Insertando empleado");

            repository.delete(8);
            repository.findAll().forEach(System.out::println);

        }
    }
}
