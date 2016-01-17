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
        <div class="panel-heading"><span class="lead">My Laboratories</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Professor</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${laboratoryDtos}" var="laboratory">
                <tr>
                    <td>${laboratory.name}</td>
                    <td>${laboratory.formProfessorDto.firstName}&nbsp;${laboratory.formProfessorDto.lastName}</td>

                    <td>
                        <a href="<c:url value='/student/${welcomeUserDto.pnc}/grades/${laboratory.name}/${laboratory.id}' />">
                            <button type="button" class="btn btn-primary btn-md">Grades</button>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value='/student/${welcomeUserDto.pnc}/attendances/${laboratory.name}/${laboratory.id}' />">
                            <button type="button" class="btn btn-primary btn-md">Attendances</button>
                        </a>
                    </td>
                    <td><a href="<c:url value='/student/laboratory/${laboratory.name}/${laboratory.id}/platform' />">
                        <button type="button" class="btn btn-primary btn-md">Platforms</button>
                    </a>
                    </td>
                    <td><a href="<c:url value='/student/laboratory/${laboratory.name}/${laboratory.id}/note' />">
                        <button type="button" class="btn btn-primary btn-md">Notes</button>
                    </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
