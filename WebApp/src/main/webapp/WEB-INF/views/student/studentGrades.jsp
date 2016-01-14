<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <title>Home</title>
</head>


<body>
<jsp:include page="studentHeader.jsp"/>


<div class="container">

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <h2 class="text-center">My grades at ${laboratoryName}</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Value</th>
            <th>Date</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${gradeDtos}" var="grades">
        <tr>
            <td>${grades.value}</td>
            <td>${grades.date}</td>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
