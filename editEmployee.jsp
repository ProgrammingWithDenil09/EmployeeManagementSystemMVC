
<%@ page import="com.employee.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(
            request.getContextPath() + "/login"
        );
        return;
    }

    Employee employee =
        (Employee) request.getAttribute("employee");

    if (employee == null) {
        response.sendRedirect(
            request.getContextPath() + "/employees?action=list"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<div class="form-container">

    <div class="form-card">

        <h2>Update Employee</h2>

        <p>Modify the employee information.</p>

        <form action="${pageContext.request.contextPath}/employees"
              method="post">

            <input type="hidden" name="action" value="update">

            <input type="hidden" name="id"
                   value="<%= employee.getId() %>">

            <label>Employee Name</label>
            <input type="text" name="name"
                   value="<%= employee.getName() %>" required>

            <label>Salary</label>
            <input type="number" name="salary"
                   step="0.01"
                   value="<%= employee.getSalary() %>" required>

            <label>Department ID</label>
            <input type="number" name="deptid"
                   value="<%= employee.getDeptid() %>" required>

            <label>Age</label>
            <input type="number" name="age"
                   value="<%= employee.getAge() %>" required>

            <label>City</label>
            <input type="text" name="city"
                   value="<%= employee.getCity() %>" required>

            <button type="submit">Update Employee</button>

        </form>

        <a href="${pageContext.request.contextPath}/employees?action=list">
            Cancel
        </a>

    </div>

</div>

</body>
</html>
