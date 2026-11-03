package com.employee.controller;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    // Check whether admin is logged in
    private boolean isAdminLoggedIn(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        return session != null &&
                session.getAttribute("admin") != null;
    }

    // Display employee pages
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {

            // Employee list can be viewed without admin login
            showEmployees(request, response);

        } else if (action.equals("dashboard")) {

            if (!isAdminLoggedIn(request)) {
                response.sendRedirect(
                        request.getContextPath() + "/login"
                );
                return;
            }

            List<Employee> employees =
                    employeeDAO.getAllEmployees();

            request.setAttribute("employeeCount",
                    employees.size());

            request.setAttribute("employees", employees);

            request.getRequestDispatcher("/dashboard.jsp")
                    .forward(request, response);

        } else if (action.equals("addForm")) {

            if (!isAdminLoggedIn(request)) {
                response.sendRedirect(
                        request.getContextPath() + "/login"
                );
                return;
            }

            request.getRequestDispatcher("/addEmployee.jsp")
                    .forward(request, response);

        } else if (action.equals("editForm")) {

            if (!isAdminLoggedIn(request)) {
                response.sendRedirect(
                        request.getContextPath() + "/login"
                );
                return;
            }

            try {
                int id = Integer.parseInt(
                        request.getParameter("id")
                );

                Employee employee =
                        employeeDAO.getEmployeeById(id);

                if (employee == null) {
                    response.sendRedirect(
                            request.getContextPath() +
                                    "/employees?action=list"
                    );
                    return;
                }

                request.setAttribute("employee", employee);

                request.getRequestDispatcher("/editEmployee.jsp")
                        .forward(request, response);

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath() +
                                "/employees?action=list"
                );
            }

        } else {

            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list"
            );
        }
    }

    // Handle Add, Update and Delete operations
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Only an authenticated admin can modify employee records
        if (!isAdminLoggedIn(request)) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        String action = request.getParameter("action");

        try {

            if ("add".equals(action)) {

                addEmployee(request, response);

            } else if ("update".equals(action)) {

                updateEmployee(request, response);

            } else if ("delete".equals(action)) {

                deleteEmployee(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath() +
                                "/employees?action=list"
                );
            }

        } catch (NumberFormatException e) {

            request.setAttribute("error",
                    "Please enter valid numeric values.");

            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list"
            );

        } catch (Exception e) {

            log("Employee operation failed", e);

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unable to complete employee operation."
            );
        }
    }

    // Display all employees
    private void showEmployees(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        List<Employee> employees =
                employeeDAO.getAllEmployees();

        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/employees.jsp")
                .forward(request, response);
    }

    // Add employee
    private void addEmployee(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        double salary = Double.parseDouble(
                request.getParameter("salary")
        );
        int deptid = Integer.parseInt(
                request.getParameter("deptid")
        );
        int age = Integer.parseInt(
                request.getParameter("age")
        );
        String city = request.getParameter("city");

        Employee employee = new Employee();

        employee.setName(name);
        employee.setSalary(salary);
        employee.setDeptid(deptid);
        employee.setAge(age);
        employee.setCity(city);

        boolean result = employeeDAO.addEmployee(employee);

        if (result) {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list&message=added"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=addForm&error=addFailed"
            );
        }
    }

    // Update employee
    private void updateEmployee(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        String name = request.getParameter("name");

        double salary = Double.parseDouble(
                request.getParameter("salary")
        );

        int deptid = Integer.parseInt(
                request.getParameter("deptid")
        );

        int age = Integer.parseInt(
                request.getParameter("age")
        );

        String city = request.getParameter("city");

        Employee employee = new Employee();

        employee.setId(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDeptid(deptid);
        employee.setAge(age);
        employee.setCity(city);

        boolean result =
                employeeDAO.updateEmployee(employee);

        if (result) {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list&message=updated"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=editForm&id=" +
                            id + "&error=updateFailed"
            );
        }
    }

    // Delete employee
    private void deleteEmployee(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        boolean result = employeeDAO.deleteEmployee(id);

        if (result) {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list&message=deleted"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath() +
                            "/employees?action=list&error=deleteFailed"
            );
        }
    }
}
