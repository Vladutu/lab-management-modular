<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="../libs.jsp"/>
    <title>Student Registration Form</title>
</head>

<body>
<jsp:include page="adminHeader.jsp"/>


<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Update Student</span></div>
        <div class="uploadcontainer">

            <form:form method="POST" modelAttribute="studentDto" role="form">
                <spring:bind path="pnc">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="pnc" class="control-label">PNC: </label>
                        <form:input path="pnc" class="form-control" id="pnc" disabled="true"/>
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

                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="email" class="control-label">Email: </label>
                        <form:input path="email" class="form-control" id="email"/>
                        <form:errors path="email" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="year">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="year">Year: </label>
                        <form:select path="year" items="${formStudentDto.years}" multiple="false" class="form-control"
                                     id="year"/>
                        <form:errors path="year" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="semester">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="semester">Semester: </label>
                        <form:select path="semester" items="${formStudentDto.semesters}" multiple="false"
                                     class="form-control"
                                     id="semester"/>
                        <form:errors path="semester" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="section">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="section" class="control-label">Section: </label>
                        <form:select path="section" items="${formStudentDto.sections}" multiple="false"
                                     class="form-control"
                                     id="section"/>
                        <form:errors path="section" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="group">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="group" class="control-label">Group: </label>
                        <form:select path="group" items="${formStudentDto.groups}" multiple="false" class="form-control"
                                     id="group"/>
                        <form:errors path="group" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="subgroup">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="subgroup" class="control-label">Subgroup: </label>
                        <form:select path="subgroup" items="${formStudentDto.subgroups}" multiple="false"
                                     class="form-control"
                                     id="subgroup"/>
                        <form:errors path="subgroup" class="control-label"/>
                    </div>
                </spring:bind>


                <button type="submit" class="btn btn-success btn-lg">Update</button>


            </form:form>
            <br/>
            <br/>

        </div>
    </div>
</div>

</div>

</body>
</html>
