
<%@ page import="java.util.List" %>
<%@ page import="com.employee.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Employee> employees =
        (List<Employee>) request.getAttribute("employees");

    boolean isAdmin =
        session.getAttribute("admin") != null;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Records</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<div class="main full-main">

    <h1>Employee Directory</h1>

    <p>View and manage employee information.</p>

    <div class="toolbar">

        <a class="btn"
           href="${pageContext.request.contextPath}/employees?action=dashboard">
            Dashboard
        </a>

        <% if (isAdmin) { %>

            <a class="btn secondary"
               href="${pageContext.request.contextPath}/employees?action=addForm">
                Add Employee
            </a>

            <form action="${pageContext.request.contextPath}/logout"
                  method="post" class="inline-form">
                <button type="submit" class="danger">
                    Logout
                </button>
            </form>

        <% } else { %>

            <a class="btn secondary"
               href="${pageContext.request.contextPath}/login">
                Admin Login
            </a>

        <% } %>

    </div>

    <% String message = request.getParameter("message"); %>

    <% if ("added".equals(message)) { %>
        <div class="success">Employee added successfully!</div>
    <% } else if ("updated".equals(message)) { %>
        <div class="success">Employee updated successfully!</div>
    <% } else if ("deleted".equals(message)) { %>
        <div class="success">Employee deleted successfully!</div>
    <% } %>

    <div class="table-container">

        <table>

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Department ID</th>
                <th>Age</th>
                <th>City</th>

                <% if (isAdmin) { %>
                    <th>Actions</th>
                <% } %>
            </tr>

            <% if (employees != null && !employees.isEmpty()) { %>

                <% for (Employee emp : employees) { %>

                    <tr>

                        <td><%= emp.getId() %></td>
                        <td><%= emp.getName() %></td>
                        <td><%= emp.getSalary() %></td>
                        <td><%= emp.getDeptid() %></td>
                        <td><%= emp.getAge() %></td>
                        <td><%= emp.getCity() %></td>

                        <% if (isAdmin) { %>

                            <td>

                                <a class="edit-btn"
                                   href="${pageContext.request.contextPath}/employees?action=editForm&id=<%= emp.getId() %>">
                                    Edit
                                </a>

                                <form action="${pageContext.request.contextPath}/employees"
                                      method="post"
                                      class="inline-form"
                                      onsubmit="return confirm('Delete this employee?');">

                                    <input type="hidden"
                                           name="action"
                                           value="delete">

                                    <input type="hidden"
                                           name="id"
                                           value="<%= emp.getId() %>">

                                    <button type="submit" class="danger">
                                        Delete
                                    </button>

                                </form>

                            </td>

                        <% } %>

                    </tr>

                <% } %>

            <% } else { %>

                <tr>
                    <td colspan="<%= isAdmin ? 7 : 6 %>">
                        No employee records found.
                    </td>
                </tr>

            <% } %>

        </table>

    </div>

</div>

</body>
</html>
