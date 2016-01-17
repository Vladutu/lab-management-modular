<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <link href="<c:url value="/static/css/app.css" />" rel="stylesheet">
    <title>Home</title>
</head>

<body>
<jsp:include page="professorHeader.jsp"/>

<div class="generic-container">

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
                ${errorMessage}
        </div>
    </c:if>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Documents for ${laboratoryName}</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>File Name</th>
                    <th>Type</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${documentDtos}" var="doc" varStatus="counter">
                    <tr>
                        <td>${counter.index + 1}</td>
                        <td>${doc.name}</td>
                        <td>${doc.type}</td>
                        <td>
                            <a href="<c:url value='/professor/laboratories/${laboratoryId}/${laboratoryName}/platform/documents/${doc.id}' />"
                               class="btn btn-danger custom-width">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel-heading"><span class="lead">Upload New Document</span></div>
        <div class="uploadcontainer">
            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

                <div class="row">
                    <div class="form-group col-md-12">
                        <spring:bind path="file">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-md-3 control-lable" for="file">Upload a document </label>

                                <div class="col-md-7">
                                    <form:input type="file" path="file" class="form-control input-sm" id="file"/>
                                    <form:errors path="file" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="Upload" class="btn btn-primary btn-sm">
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>
</body>
</html>