package org.example.Main;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("---- ----- Listando todos -----------");

        Repository<Employee> repository = new EmployeeRepository();

        repository.findAll().forEach(System.out::println);

        System.out.println("*********** Buscando por id **********");
        System.out.println(repository.getByID(2));


    }
}
