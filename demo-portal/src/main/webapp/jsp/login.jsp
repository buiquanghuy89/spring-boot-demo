<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="../css/main.css" rel="stylesheet">
<html>
<head>
    <title>Spring Boot MVC Form Handling</title>
</head>

<body>
<%--<style>--%>
    <%--.login-wrapper {--%>
        <%--border: 2px solid;--%>
        <%--border-color: #c6becb;--%>
    <%--}--%>
<%--</style>--%>
<h2>Account Information</h2>
<form:form method="POST" action="login" modelAttribute="user">
    <table class="login-wrapper">
        <tr>
            <td><form:label path="userName">UserName</form:label></td>
            <td><form:input path="userName"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td colspan="2" style="align-items: center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>