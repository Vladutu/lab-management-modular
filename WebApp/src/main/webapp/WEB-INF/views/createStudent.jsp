<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="libs.jsp"/>
    <title>Student Registration Form</title>
</head>

<body>
<jsp:include page="adminHeader.jsp"/>

<p class="text-right"><a href="students?mylocale=en">English </a> | <a href="students?mylocale=ro">Romanian</a></p>

<div class="container">
    <h2 class="text-center">Student Registration Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="studentDto" role="form">

        <spring:bind path="pnc">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="pnc">PNC: </label>
                <form:input path="pnc" class="form-control" id="pnc"/>
                <form:errors path="pnc" class="control-label"/>
            </div>
        </spring:bind>


        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="firstName">First Name: </label>
                <form:input path="firstName" class="form-control" id="firstName"/>
                <form:errors path="firstName" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="lastName">Last Name: </label>
                <form:input path="lastName" class="form-control" id="lastName"/>
                <form:errors path="lastName" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="section">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="section">Section: </label>
                <form:select path="section" items="${sectionDtos}" multiple="false" class="form-control"
                             id="section"/>
                <form:errors path="section" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="group">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="group">Group: </label>
                <form:select path="group" items="${groupDtos}" multiple="false" class="form-control" id="group"/>
                <form:errors path="group" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="subgroup">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="subgroup">Subgroup: </label>
                <form:select path="subgroup" items="${subgroupDtos}" multiple="false" class="form-control"
                             id="subgroup"/>
                <form:errors path="subgroup" class="control-label"/>
            </div>
        </spring:bind>

        <button type="submit" class="btn btn-success btn-lg">Register</button>

    </form:form>
    <br/>
    <br/>

    <h2 class="text-left">Go back to <a href="<c:url value='/admin/students' />">List of All Students</a></h2>
</div>
</body>
</html>