<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <link href="<c:url value="/static/css/app.css" />" rel="stylesheet">
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
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Notes for ${laboratoryName}</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Message</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noteDtos}" var="note" varStatus="counter">
                    <tr>
                        <td>${note.date}</td>
                        <td>${note.message}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>