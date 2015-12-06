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
    <br><br><br><br><br><br><br><br><br><br>

    <div class="row">
        <div class="col-sm-4">
            <button type="button" class="btn btn-success btn-xlarge">${laboratoryDto.name}</button>
        </div>
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-success btn-xlarge">${laboratoryDto.day}</button>
        </div>
    </div>
</div>
</body>
</html>