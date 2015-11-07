<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Students</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>


<body>
<h2>List of Students</h2>
<table>
    <tr>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Section</td>
        <td>Group</td>
        <td>Subgroup</td>
        <td></td>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.section.name}</td>
            <td>${student.group.name}</td>
            <td>${student.subgroup.name}</td>
        </tr>
    </c:forEach>
</table>
<br/>
</body>
</html>
