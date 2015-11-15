<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="libs.jsp"/>
    <title>Users</title>
</head>


<body>
<jsp:include page="adminHeader.jsp"/>

<p class="text-right"><a href="users?mylocale=en">English </a> | <a href="users?mylocale=ro">Romanian</a></p>

<div class="container">
    <a href="<c:url value='/admin/users/new' />">
        <button type="button" class="btn btn-success">Add new user</button>
    </a>

    <h2 class="text-center">List of users</h2>

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
            <th>Type</th>
            <th>State</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.pnc}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.userType}</td>
                <td>${user.userState}</td>
                <td><a href="<c:url value='/admin/users/edit/${user.pnc}' />">
                    <button type="button" class="btn btn-primary btn-md">Edit</button>
                </a>
                </td>
                <td><a href="<c:url value='/admin/users/delete/${user.pnc}' />">
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
