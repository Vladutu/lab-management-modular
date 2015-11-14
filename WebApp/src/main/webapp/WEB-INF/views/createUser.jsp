<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="header.jsp"/>
    <title>User Registration Form</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script>
        $(document).ready(function (e) {

            $("input[id=pnc]").keyup(function () {

                var pnc = $(this).val();
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $.ajax({
                    beforeSend: function (request) {
                        request.setRequestHeader(header, token);
                    },
                    contentType: "application/json",
                    method: "POST",
                    url: "/admin/users/new/ajax",
                    dataType: "json",
                    data: JSON.stringify(pnc),
                    success: function (data) {
                        $("#firstName").val(data.firstName);
                        $("#lastName").val(data.lastName);
                        $("#email").val(data.email);
                        $("#userType").val(data.userType);
                    }
                });

            });

        });
    </script>
</head>

<body>
<p class="text-right"><a href="users?mylocale=en">English </a> | <a href="users?mylocale=ro">Romanian</a></p>

<div class="container">
    <h2 class="text-center">User Registration Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="userDto" role="form">

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
                <form:select path="userType" items="${userTypeDtos}" multiple="false" class="form-control"
                             id="userType"/>
                <form:errors path="userType" class="control-label"/>
            </div>
        </spring:bind>

        <button type="submit" class="btn btn-success btn-lg">Register</button>

    </form:form>
    <br/>
    <br/>

    <h2 class="text-left">Go back to <a href="<c:url value='/admin/users' />">List of All Users</a></h2>
</div>
</body>
</html>