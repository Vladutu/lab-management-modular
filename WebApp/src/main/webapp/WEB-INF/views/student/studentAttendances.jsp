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


<div class="generic-container">

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">My Attendances at ${laboratoryName}</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>No.</th>
                <th>Date</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${attendanceDtos}" var="attendance" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${attendance.date}</td>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
