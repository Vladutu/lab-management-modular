<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="libs.jsp"/>
    <title>User Registration Form</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="<c:url value="/static/js/userFormCompleter.js" />"></script>

</head>

<body>
<jsp:include page="adminHeader.jsp"/>


<div class="container">
    <h2 class="text-center">User Registration Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="formUserDto" role="form">

        <spring:bind path="pnc">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="pnc">PNC: </label>
                <form:input path="pnc" class="form-control" id="pnc" onkeyup="userFormCompleter()"/>
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

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="password">Password: </label>
                <form:password path="password" class="form-control" id="password"/>
                <form:errors path="password" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="email">Email: </label>
                <form:input path="email" class="form-control" id="email"/>
                <form:errors path="email" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="userType">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="userType">Type: </label>
                <form:select path="userType" items="${formUserDto.types}" multiple="false" class="form-control"
                             id="userType"/>
                <form:errors path="userType" class="control-label"/>
            </div>
        </spring:bind>

        <form:input type="hidden" path="userState" id="userState" value="Active"/>

        <button type="submit" class="btn btn-success btn-lg">Register</button>

    </form:form>
    <br/>
    <br/>

</div>
</body>
</html>