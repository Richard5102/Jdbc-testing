package org.example.repository;

import org.example.model.Employee;
import org.example.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }



    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try ( Connection myCon = getConnection();
                Statement myStamt = myCon.createStatement();
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
        try ( Connection myCon = getConnection();
                PreparedStatement myStamt = myCon.prepareStatement("SELECT * FROM Employees WHERE ID = ?"))  {
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
    public void save(Employee employee) throws SQLException {
        String sql = "";
        if (employee.getId()!=null && employee.getId() > 0) {
            sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname =?, email =?, salary =?, curp = ? WHERE id = ?";
        } else {
           sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";
        }

        try(
                Connection myCon = getConnection();
                PreparedStatement myStamt = myCon.prepareStatement(sql)) {
            myStamt.setString(1, employee.getFirst_name());
            myStamt.setString(2, employee.getPa_surname());
            myStamt.setString(3, employee.getMa_surname());
            myStamt.setString(4, employee.getEmail());
            myStamt.setFloat(5, employee.getSalary());
            myStamt.setString(6, employee.getCurp());
            if (employee.getId()!=null && employee.getId() > 0) {
                myStamt.setInt(7, employee.getId());
            }
            myStamt.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection myCon = getConnection();
                PreparedStatement myStamt = myCon.prepareStatement("DELETE FROM employees WHERE id = ?"))
        {
            myStamt.setInt(1,id);
            myStamt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
