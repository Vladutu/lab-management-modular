<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Registration Form</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
            integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>

<body>
<p class="text-right"><a href="students?mylocale=en">English </a> | <a href="students?mylocale=ro">Romanian</a></p>

<div class="container">
    <h2 class="text-center">Registration Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="studentDto" role="form">

        <div class="form-group">
            <label for="firstName">First Name: </label>
            <form:input path="firstName" class="form-control" id="firstName"/>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name: </label>
            <form:input path="lastName" class="form-control" id="lastName"/>
        </div>

        <div class="form-group">
            <label for="section">Section: </label>
            <form:select path="section" items="${sectionDtos}" multiple="false" class="form-control" id="section"/>
        </div>

        <div class="form-group">
            <label for="group">Group: </label>
            <form:select path="group" items="${groupDtos}" multiple="false" class="form-control" id="group"/>
        </div>

        <div class="form-group">
            <label for="subgroup">Subgroup: </label>
            <form:select path="subgroup" items="${subgroupDtos}" multiple="false" class="form-control" id="subgroup"/>
        </div>

        <button type="submit" class="btn btn-success btn-lg">Register</button>

    </form:form>
    <br/>
    <br/>

    <h2 class="text-left">Go back to <a href="<c:url value='/students' />">List of All Students</a></h2>
</div>
</body>
</html>