<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="header.jsp"/>
    <title>Student Registration Form</title>
</head>

<body>
<p class="text-right"><a href="students?mylocale=en">English </a> | <a href="students?mylocale=ro">Romanian</a></p>

<div class="container">
    <h2 class="text-center">Student Update Form</h2>

    <form:form method="POST" modelAttribute="studentDto" role="form">
        <spring:bind path="pnc">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="pnc" class="control-label">PNC: </label>
                <form:input path="pnc" class="form-control" id="pnc"/>
                <form:errors path="pnc" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="firstName" class="control-label">First Name: </label>
                <form:input path="firstName" class="form-control" id="firstName"/>
                <form:errors path="firstName" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="lastName" class="control-label">Last Name: </label>
                <form:input path="lastName" class="form-control" id="lastName"/>
                <form:errors path="lastName" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="section">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="section" class="control-label">Section: </label>
                <form:input path="section" class="form-control" id="section"/>
                <form:errors path="section" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="group">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="group" class="control-label">Group: </label>
                <form:input path="group" class="form-control" id="group"/>
                <form:errors path="group" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="subgroup">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="subgroup" class="control-label">Subgroup: </label>
                <form:input path="subgroup" class="form-control" id="subgroup"/>
                <form:errors path="subgroup" class="control-label"/>
            </div>
        </spring:bind>


        <button type="submit" class="btn btn-success btn-lg">Update</button>


    </form:form>
    <br/>
    <br/>

    <h2 class="text-left">Go back to <a href="<c:url value='/admin/students' />">List of All Students</a></h2>

</div>

</body>
</html>
