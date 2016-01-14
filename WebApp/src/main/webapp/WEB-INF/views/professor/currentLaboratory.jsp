<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <title>Home</title>
</head>


<body>
<jsp:include page="professorHeader.jsp"/>


<div class="container">
    <h2 class="text-center">${laboratoryWithStudentsDto.laboratory.name}</h2>

    <h3 class="text-center">
        Section:${laboratoryWithStudentsDto.laboratory.section}&nbsp;&nbsp;Year:${laboratoryWithStudentsDto.laboratory.year}&nbsp;&nbsp;Group${laboratoryWithStudentsDto.laboratory.group}&nbsp;&nbsp;Subgroup:${laboratoryWithStudentsDto.laboratory.subgroup}</h3>


    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <form:form method="POST" modelAttribute="formStudentsWithGradeAndAttendanceDto" role="form">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Attendance</th>
                <th>Grade</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${laboratoryWithStudentsDto.students}" var="student" varStatus="status">
                <tr>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td><form:checkbox path="studentsWithGradeAndAttendance[${status.index}].attendance"
                                       class="form-control" id="attendance"/></td>
                    <td><form:input type="number" path="studentsWithGradeAndAttendance[${status.index}].grade"
                                    class="form-control" id="grade" min="1" max="10"/></td>
                    <td><form:input type="hidden" path="studentsWithGradeAndAttendance[${status.index}].pnc"
                                    value="${student.pnc}"/></td>
                    <td><form:input type="hidden" path="laboratoryId"
                                    value="${laboratoryWithStudentsDto.laboratory.id}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <button type="submit" class="btn btn-success btn-lg">Submit</button>
    </form:form>


</div>
</body>
</html>