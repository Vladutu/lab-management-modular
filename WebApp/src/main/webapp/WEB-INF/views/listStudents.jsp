<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<a href="students?mylocale=en">English </a> | <a href="students?mylocale=ro">Romanian</a>

<h2><spring:message code="user.title"/></h2>
<table>
    <tr>
        <td><spring:message code="table.c1"/></td>
        <td><spring:message code="table.c2"/></td>
        <td><spring:message code="table.c3"/></td>
        <td><spring:message code="table.c4"/></td>
        <td><spring:message code="table.c5"/></td>
        <td></td>
        <td></td>

    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.section.name}</td>
            <td>${student.group.name}</td>
            <td>${student.subgroup.name}</td>
            <td><a href="<c:url value='/student/edit/${student.id}' />"><spring:message code="student.edit"/></a></td>
            <td><a href="<c:url value='/student/delete/${student.id}' />"><spring:message code="student.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
</body>
</html>
