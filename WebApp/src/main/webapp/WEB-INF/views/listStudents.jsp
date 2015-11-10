<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Students</title>

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
    <h2 class="text-center"><spring:message code="user.title"/></h2>

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
            <th><spring:message code="table.c1"/></th>
            <th><spring:message code="table.c2"/></th>
            <th><spring:message code="table.c3"/></th>
            <th><spring:message code="table.c4"/></th>
            <th><spring:message code="table.c5"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.section}</td>
                <td>${student.group}</td>
                <td>${student.subgroup}</td>
                <td><a href="<c:url value='/students/edit/${student.id}' />">
                    <button type="button" class="btn btn-primary btn-md"><spring:message code="student.edit"/></button>
                </a>
                </td>
                <td><a href="<c:url value='/students/delete/${student.id}' />">
                    <button type="button" class="btn btn-danger btn-md"><spring:message
                            code="student.delete"/></button>
                </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <br/>
    <a href="<c:url value='students/new' />">
        <button type="button" class="btn btn-success"><spring:message code="student.new"/></button>
    </a>
</div>
</body>
</html>
