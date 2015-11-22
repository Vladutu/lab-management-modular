<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <jsp:include page="libs.jsp"/>
    <title>Laboratory Registration Form</title>
</head>

<body>
<jsp:include page="adminHeader.jsp"/>

<div class="container">
    <h2 class="text-center">Laboratory Registration Form</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="laboratoryDto" role="form">

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="name">Name: </label>
                <form:input path="name" class="form-control" id="name"/>
                <form:errors path="name" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="from">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="from">From: </label>
                <form:select path="from" items="${hourDtos}" multiple="false" class="form-control"
                             id="userType"/>
                <form:errors path="from" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="to">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="to">To: </label>
                <form:select path="to" items="${hourDtos}" multiple="false" class="form-control"
                             id="to"/>
                <form:errors path="to" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="day">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="day">Day: </label>
                <form:select path="day" items="${dayDtos}" multiple="false" class="form-control"
                             id="day"/>
                <form:errors path="day" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="room">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="room">Room: </label>
                <form:select path="room" items="${roomDtos}" multiple="false" class="form-control"
                             id="room"/>
                <form:errors path="room" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="section">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="section">Section: </label>
                <form:input path="section" class="form-control" id="section" value="${section}" disabled="true"/>
                <form:errors path="section" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="year">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="year">Year: </label>
                <form:input path="year" class="form-control" id="year" value="${year}" disabled="true"/>
                <form:errors path="year" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="semester">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="semester">Semester: </label>
                <form:input path="semester" class="form-control" id="semester" value="${semester}" disabled="true"/>
                <form:errors path="semester" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="group">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="group">Group: </label>
                <form:select path="group" items="${groupDtos}" multiple="false" class="form-control"
                             id="group"/>
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

        <spring:bind path="weeklyOccurrence">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="weeklyOccurrence">Weekly occurrence: </label>
                <form:select path="weeklyOccurrence" items="${weeklyOccurrenceDtos}" multiple="false"
                             class="form-control"
                             id="weeklyOccurrence"/>
                <form:errors path="weeklyOccurrence" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="professorDto.pnc">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="professorDto.pnc">Professor: </label>
                <form:select path="professorDto.pnc" items="${professorDtos}" multiple="false"
                             class="form-control"
                             id="professorDto.pnc"/>
                <form:errors path="professorDto.pnc" class="control-label"/>
            </div>
        </spring:bind>


        <button type="submit" class="btn btn-success btn-lg">Register</button>

    </form:form>
    <br/>
    <br/>

</div>
</body>
</html>