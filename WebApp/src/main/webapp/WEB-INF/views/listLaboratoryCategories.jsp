<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="libs.jsp"/>
    <title>Laboratories</title>
</head>


<body>
<jsp:include page="adminHeader.jsp"/>


<div class="container">

    <h2 class="text-center">List of laboratories</h2>


    <c:forEach items="${sectionDtos}" var="section">
        <h2>Section: ${section.name}</h2>
        <c:forEach items="${yearDtos}" var="year">
            <h4>
                <c:forEach items="${semesterDtos}" var="semester">
                    Year ${year} Semester ${semester} - <a href="/admin/laboratories/${section}/${year}/${semester}">here</a>&nbsp&nbsp&nbsp&nbsp
                </c:forEach>
            </h4>
        </c:forEach>
    </c:forEach>


    <br/>
    <br/>
</div>
</body>
</html>
