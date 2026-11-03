
package com.employee.dao;

import com.employee.model.Employee;
import com.employee.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ==========================================
    // 1. ADMIN LOGIN VALIDATION
    // ==========================================

    public boolean validateAdmin(String username, String password) {

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // 2. ADD EMPLOYEE
    // ==========================================

    public boolean addEmployee(Employee employee) {

        String sql = "INSERT INTO employee " +
                "(name, salary, deptid, age, city) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());
            ps.setInt(3, employee.getDeptid());
            ps.setInt(4, employee.getAge());
            ps.setString(5, employee.getCity());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // 3. GET ALL EMPLOYEES
    // ==========================================

    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employee ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDeptid(rs.getInt("deptid"));
                employee.setAge(rs.getInt("age"));
                employee.setCity(rs.getString("city"));

                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


    // ==========================================
    // 4. GET EMPLOYEE BY ID
    // ==========================================

    public Employee getEmployeeById(int id) {

        Employee employee = null;

        String sql = "SELECT * FROM employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    employee = new Employee();

                    employee.setId(rs.getInt("id"));
                    employee.setName(rs.getString("name"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setDeptid(rs.getInt("deptid"));
                    employee.setAge(rs.getInt("age"));
                    employee.setCity(rs.getString("city"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }


    // ==========================================
    // 5. UPDATE EMPLOYEE
    // ==========================================

    public boolean updateEmployee(Employee employee) {

        String sql = "UPDATE employee SET " +
                "name = ?, salary = ?, deptid = ?, " +
                "age = ?, city = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());
            ps.setInt(3, employee.getDeptid());
            ps.setInt(4, employee.getAge());
            ps.setString(5, employee.getCity());
            ps.setInt(6, employee.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // 6. DELETE EMPLOYEE
    // ==========================================

    public boolean deleteEmployee(int id) {

        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // 7. GET TOTAL EMPLOYEE COUNT
    // ==========================================

    public int getEmployeeCount() {

        String sql = "SELECT COUNT(*) FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

