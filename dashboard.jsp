<%@ page contentType="text/html;charset=UTF-8" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - EMS</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<!-- SIDEBAR -->
<div class="sidebar">

    <h2>EMS <span>Admin</span></h2>

    <a class="active"
       href="${pageContext.request.contextPath}/employees?action=dashboard">
        Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/employees?action=list">
        Employees
    </a>

    <a href="${pageContext.request.contextPath}/employees?action=addForm">
        Add Employee
    </a>

    <form action="${pageContext.request.contextPath}/logout"
          method="post">

        <button type="submit" class="logout-btn">
            Logout
        </button>

    </form>

</div>

<!-- MAIN CONTENT -->
<div class="main">

    <!-- TOP HEADER -->
    <div class="dashboard-header">

        <div>
            <h1>Dashboard</h1>
            <p>Welcome to Employee Management System</p>
        </div>

        <div class="admin-info">
            Admin: <strong><%= session.getAttribute("admin") %></strong>
        </div>

    </div>

    <!-- STATISTICS -->
    <div class="dashboard-cards">

        <div class="stat-card">
            <h3>Total Employees</h3>
            <h2>${employeeCount}</h2>
            <p>Registered employees</p>
        </div>

        <div class="stat-card">
            <h3>System Status</h3>
            <h2 class="status-text">Active</h2>
            <p>Employee management system</p>
        </div>

        <div class="stat-card">
            <h3>Admin Panel</h3>
            <h2 class="status-text">Ready</h2>
            <p>Manage employee information</p>
        </div>

    </div>

    <!-- QUICK ACTIONS -->
    <div class="card">

        <h2>Employee Management</h2>

        <p>
            Manage employee records easily using the options below.
        </p>

        <div class="dashboard-actions">

            <div class="action-box">
                <h3>View Employees</h3>

                <p>
                    View all registered employee details.
                </p>

                <a class="btn"
                   href="${pageContext.request.contextPath}/employees?action=list">
                    View Employees
                </a>
            </div>

            <div class="action-box">
                <h3>Add Employee</h3>

                <p>
                    Add a new employee to the system.
                </p>

                <a class="btn secondary"
                   href="${pageContext.request.contextPath}/employees?action=addForm">
                    Add Employee
                </a>
            </div>

        </div>

    </div>

    <!-- FOOTER -->
    <div class="dashboard-footer">
        Employee Management System | Admin Dashboard
    </div>

</div>

</body>
</html>
