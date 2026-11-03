
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(
            request.getContextPath() + "/login"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<div class="form-container">

    <div class="form-card">

        <h2>Add New Employee</h2>

        <p>Enter the employee details below.</p>

        <form action="${pageContext.request.contextPath}/employees"
              method="post">

            <input type="hidden" name="action" value="add">

            <label>Employee Name</label>
            <input type="text" name="name"
                   placeholder="Enter employee name" required>

            <label>Salary</label>
            <input type="number" name="salary"
                   min="0" step="0.01"
                   placeholder="Enter salary" required>

            <label>Department ID</label>
            <input type="number" name="deptid"
                   placeholder="Enter department ID" required>

            <label>Age</label>
            <input type="number" name="age"
                   min="18" max="100"
                   placeholder="Enter age" required>

            <label>City</label>
            <input type="text" name="city"
                   placeholder="Enter city" required>

            <button type="submit">Save Employee</button>

        </form>

        <a href="${pageContext.request.contextPath}/employees?action=list">
            Back to Employees
        </a>

    </div>

</div>

</body>
</html>
