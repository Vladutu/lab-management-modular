<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <title>Home</title>
</head>

<script src="<c:url value="/static/js/attributeName.js" />"></script>

<body>
<jsp:include page="professorHeader.jsp"/>


<div class="container">

    <h2 class="text-center">List of laboratories</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form action="/professor/laboratory">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>From</th>
                <th>To</th>
                <th>Day</th>
                <th>Room</th>
                <th>Section</th>
                <th>Group</th>
                <th>Subgroup</th>
                <th>Weekly occurrence</th>
                <th>Date</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${laboratoryDtos}" var="laboratory" varStatus="status">
                <tr>
                    <input type="hidden" value="${laboratory.id}" id="labId${status.index}">
                    <td>${laboratory.name}</td>
                    <td>${laboratory.from}</td>
                    <td>${laboratory.to}</td>
                    <td><spring:message code="day.${laboratory.day}"/></td>
                    <td>${laboratory.room}</td>
                    <td>${laboratory.section}</td>
                    <td>${laboratory.group}</td>
                    <td>${laboratory.subgroup}</td>
                    <td>${laboratory.weeklyOccurrence}</td>
                    <td><input type="date" id="date${status.index}"></td>
                    <td>
                        <button type="submit" class="btn btn-primary btn-md" id="submit${status.index}"
                                onclick="setAttributeName(${status.index})">
                            View
                        </button>
                    </td>
                    <td>
                        <a href="<c:url value='/professor/laboratories/${laboratory.id}/${laboratory.name}/platform' />">
                            <button type="button" class="btn btn-primary btn-md">Platform</button>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value='/professor/laboratories/${laboratory.id}/${laboratory.name}/note' />">
                            <button type="button" class="btn btn-primary btn-md">Notes</button>
                        </a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>