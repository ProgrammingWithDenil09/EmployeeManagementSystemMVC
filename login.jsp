
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

<div class="login-container">

    <div class="login-card">

        <h2>Admin Login</h2>

        <p>Sign in to manage employee records.</p>

        <% if (request.getAttribute("error") != null) { %>

            <div class="error">
                <%= request.getAttribute("error") %>
            </div>

        <% } %>

        <form action="${pageContext.request.contextPath}/login"
              method="post">

            <label>Username</label>

            <input type="text"
                   name="username"
                   placeholder="Enter username"
                   required>

            <label>Password</label>

            <input type="password"
                   name="password"
                   placeholder="Enter password"
                   required>

            <button type="submit">Login</button>

        </form>

        <a href="${pageContext.request.contextPath}/index.jsp">
            Back to Home
        </a>

    </div>

</div>

</body>
</html>
