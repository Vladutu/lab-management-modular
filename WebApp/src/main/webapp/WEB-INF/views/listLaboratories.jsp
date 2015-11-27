<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="libs.jsp"/>
    <title>Laboratories</title>
</head>


<body>
<jsp:include page="adminHeader.jsp"/>


<div class="container">

    <a href="<c:url value='/admin/laboratories/${section}/${year}/${semester}/new/' />">
        <button type="button" class="btn btn-success">Add new laboratory</button>
    </a>

    <h2 class="text-center">List of laboratories</h2>

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
            <th>Name</th>
            <th>From</th>
            <th>To</th>
            <th>Day</th>
            <th>Room</th>
            <th>Group</th>
            <th>Subgroup</th>
            <th>Weekly occurrence</th>
            <th>Professor's name</th>

            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${laboratoryDtos}" var="laboratory">
            <tr>
                <td>${laboratory.name}</td>
                <td>${laboratory.from}</td>
                <td>${laboratory.to}</td>
                <td><spring:message code="day.${laboratory.day}"/></td>
                <td>${laboratory.room}</td>
                <td>${laboratory.group}</td>
                <td>${laboratory.subgroup}</td>
                <td>${laboratory.weeklyOccurrence}</td>

                <td>${laboratory.formProfessorDto.firstName}&nbsp${laboratory.formProfessorDto.lastName}</td>
                <td>
                    <a href="<c:url value='/admin/laboratories/${laboratory.section}/${laboratory.year}/${laboratory.semester}/edit/${laboratory.id}' />">
                        <button type="button" class="btn btn-primary btn-md">Edit</button>
                    </a>
                </td>
                <td>
                    <a href="<c:url value='/admin/laboratories/${laboratory.section}/${laboratory.year}/${laboratory.semester}/delete/${laboratory.id}' />">
                        <button type="button" class="btn btn-danger btn-md">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br/>
    <br/>
</div>
</body>
</html>
