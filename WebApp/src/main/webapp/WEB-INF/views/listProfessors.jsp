<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="libs.jsp"/>
    <title>Professors</title>
</head>


<body>
<jsp:include page="adminHeader.jsp"/>


<div class="container">
    <a href="<c:url value='/admin/professors/new' />">
        <button type="button" class="btn btn-success">Add new professor</button>
    </a>

    <h2 class="text-center">List of professors</h2>

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
            <th>Pnc</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Position</th>
            <th>Office</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${professors}" var="professor">
            <tr>
                <td>${professor.pnc}</td>
                <td>${professor.firstName}</td>
                <td>${professor.lastName}</td>
                <td>${professor.email}</td>
                <td>${professor.position}</td>
                <td>${professor.office}</td>
                <td><a href="<c:url value='/admin/professors/edit/${professor.pnc}' />">
                    <button type="button" class="btn btn-primary btn-md">Edit</button>
                </a>
                </td>
                <td><a href="<c:url value='/admin/professors/delete/${professor.pnc}' />">
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
