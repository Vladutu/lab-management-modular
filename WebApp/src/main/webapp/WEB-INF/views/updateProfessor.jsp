<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="libs.jsp"/>
    <title>Professor Update Form</title>
</head>

<body>
<jsp:include page="adminHeader.jsp"/>

<p class="text-right"><a href="professors?mylocale=en">English </a> | <a href="professors?mylocale=ro">Romanian</a></p>

<div class="container">
    <h2 class="text-center">Professor Update Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="professorDto" role="form">

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

        <spring:bind path="position">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="position">Position: </label>
                <form:input path="position" class="form-control" id="position"/>
                <form:errors path="position" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="office">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="office">Office: </label>
                <form:input path="office" class="form-control" id="office"/>
                <form:errors path="office" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="email">Email: </label>
                <form:input path="email" class="form-control" id="email"/>
                <form:errors path="email" class="control-label"/>
            </div>
        </spring:bind>

        <button type="submit" class="btn btn-success btn-lg">Update</button>

    </form:form>
    <br/>
    <br/>

    <h2 class="text-left">Go back to <a href="<c:url value='/admin/professors' />">List of All Professors</a></h2>
</div>
</body>
</html>