<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="libs.jsp"/>
    <title>Home</title>
</head>


<body>
<jsp:include page="professorHeader.jsp"/>


<div class="container">
    <h2 class="text-center">${professorLaboratoryDto.name}</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Attendance</th>
            <th>Grade</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${professorLaboratoryDto.students}" var="student">
            <tr>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td><input type="checkbox" name="attendance" value="attendance"></td>
                <td><input type="number" name="grade" min="1" max="10"></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>