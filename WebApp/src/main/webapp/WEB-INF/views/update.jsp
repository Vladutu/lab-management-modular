<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Registration Form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Registration Form</h2>

<form:form method="POST" modelAttribute="studentDto">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="firstName">First Name: </label></td>
            <td><form:input path="firstName" id="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="lastName">Last Name: </label></td>
            <td><form:input path="lastName" id="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="section">Section: </label></td>
            <td><form:input path="section" id="section"/></td>
            <td><form:errors path="section" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="group">Group: </label></td>
            <td><form:input path="group" id="group"/></td>
            <td><form:errors path="group" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="subgroup">Subgroup: </label></td>
            <td><form:input path="subgroup" id="subgroup"/></td>
            <td><form:errors path="subgroup" cssClass="error"/></td>
        </tr>


        <tr>
            <td colspan="3">
                <input type="submit" value="Update"/>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value="/students" />">List of All Students</a>
</body>
</html>
