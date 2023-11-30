package org.example.repository;

import org.example.model.Employee;
import org.example.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection()  throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Statement myStamt = getConnection().createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * from Employees")) {
            while (myRes.next()) {
               Employee e = createEmployee(myRes);
               employees.add(e);
            }
        }
        return employees;
    }



    @Override
    public Employee getByID(Integer id) throws SQLException {
        Employee employee = null;
        try (PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM Employees WHERE ID = ?"))  {
            myStamt.setInt(1, id);
            try(ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }

    private  Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setFirst_name((myRes.getString("first_name")));
        e.setPa_surname((myRes.getString("pa_surname")));
        e.setMa_surname((myRes.getString("ma_surname")));
        e.setEmail((myRes.getString("email")));
        e.setSalary((myRes.getFloat("salary")));
        return e;
    }
}
