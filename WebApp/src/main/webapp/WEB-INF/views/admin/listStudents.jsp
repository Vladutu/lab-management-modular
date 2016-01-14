<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <title>Students</title>
</head>


<body>
<jsp:include page="adminHeader.jsp"/>


<div class="container">
    <a href="<c:url value='/admin/students/new' />">
        <button type="button" class="btn btn-success"><spring:message code="student.new"/></button>
    </a>

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
            <th><spring:message code="table.c0"/></th>
            <th><spring:message code="table.c1"/></th>
            <th><spring:message code="table.c2"/></th>
            <th><spring:message code="table.c3"/></th>
            <th><spring:message code="table.c4"/></th>
            <th><spring:message code="table.c5"/></th>
            <th><spring:message code="table.c6"/></th>
            <th><spring:message code="table.c7"/></th>
            <th><spring:message code="table.c8"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.pnc}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.email}</td>
                <td>${student.year}</td>
                <td>${student.semester}</td>
                <td>${student.section}</td>
                <td>${student.group}</td>
                <td>${student.subgroup}</td>
                <td><a href="<c:url value='/admin/students/edit/${student.pnc}' />">
                    <button type="button" class="btn btn-primary btn-md"><spring:message code="student.edit"/></button>
                </a>
                </td>
                <td><a href="<c:url value='/admin/students/delete/${student.pnc}' />">
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
</div>
</body>
</html>
