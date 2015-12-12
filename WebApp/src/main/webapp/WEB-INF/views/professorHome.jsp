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

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <br><br><br><br><br><br><br><br><br><br>

    <div class="row">
        <div class="col-sm-4">
            <a href="<c:url value='/professor/currentLaboratory/' />">
                <button type="button" class="btn btn-success btn-xlarge">Current Lab</button>
            </a>
        </div>
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <a href="<c:url value='/professor/laboratories/' />">
                <button type="button" class="btn btn-success btn-xlarge">Lab list</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
