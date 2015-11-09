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

<form:form method="POST" modelAttribute="studentWrapper">
    <table>
        <tr>
            <td><label for="studentDto.firstName">First Name: </label></td>
            <td><form:input path="studentDto.firstName" id="studentDto.firstName"/></td>
        </tr>

        <tr>
            <td><label for="studentDto.lastName">Last Name: </label></td>
            <td><form:input path="studentDto.lastName" id="studentDto.lastName"/></td>
        </tr>

        <tr>
            <td><label for="studentDto.section">Section: </label></td>
            <td><form:select path="sectionDtos" items="${studentWrapper.sectionDtos}" multiple="false"/></td>
        </tr>

        <tr>
            <td><label for="studentDto.group">Group: </label></td>
            <td><form:select path="groupDtos" items="${studentWrapper.groupDtos}" multiple="false"/></td>
        </tr>

        <tr>
            <td><label for="studentDto.subgroup">Subgroup: </label></td>
            <td><form:select path="subgroupDtos" items="${studentWrapper.subgroupDtos}" multiple="false"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Register"/>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/students' />">List of All Students</a>
</body>
</html>