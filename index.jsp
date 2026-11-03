
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Management System</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<div class="home-container">

    <div class="home-card">

        <div class="logo">
            EMS
        </div>

        <h1>Employee Management System</h1>

        <p class="subtitle">
            Manage employee information easily and efficiently.
            A simple platform to organize your workforce.
        </p>

        <div class="home-buttons">

            <a class="btn"
               href="${pageContext.request.contextPath}/login">
                Admin Login
            </a>

            <a class="btn secondary"
               href="${pageContext.request.contextPath}/employees?action=list">
                View Employees
            </a>

        </div>

        <p class="footer-text">
            Employee Management System | Powered by Java &amp; MySQL
        </p>

    </div>

</div>

</body>
</html>

