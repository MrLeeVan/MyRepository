<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="/css/style.css" rel="stylesheet" /> 
<link type="text/css" href="/css/jquery.cleditor.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.8.2.js"></script>
<link rel="shortcut icon" href="/images/ico/favicon.ico" />
<title>${_res.get('Teaching.achievement')}</title>
<style type="text/css">
.header{
font-size:12px;
}
</style>
</head>
<body>
<div id="container">
<div id="primary_right">
<div class="inner">
<h3>当前位置：<a href="javascript:window.parent.location='/account'">${_res.get('admin.common.mainPage')}</a> &gt; <a href="/account/teacherReport">${_res.get('Teachers.score')}</a> &gt; ${_res.get('Teaching.achievement')}</h3>
<table border="1" class="normal tablesorter" >
<thead>
<tr align="center">
<th class="header" >${_res.get('teacher')}</th>
<th class="header" >${_res.get('student')}</th>
<th  class="header">${_res.get('Number.of.courses')}</th>
<th  class="header">${_res.get("Hearing")}(${_res.get('Each.section.provides.points')})</th>
<th  class="header">${_res.get("Spoken.language")}(${_res.get('Each.section.provides.points')})</th>
<th  class="header">${_res.get("grade.Reading")}(${_res.get('Each.section.provides.points')})</th>
<th  class="header">${_res.get("grade.writing")}(${_res.get('Each.section.provides.points')})</th>
</tr>
</thead>
<c:forEach items="${teacherReport}" var="report">
<tr class="odd" align="center">
<td>${report.teachername}</td>
<td>
<c:choose>
   <c:when test="${report.studentname!=null}">${report.studentname}</c:when>
   <c:otherwise>-</c:otherwise>
</c:choose>
</td>
<td>
<c:choose>
   <c:when test="${report.coursenum!=null}">${report.coursenum}</c:when>
   <c:otherwise>-</c:otherwise>
</c:choose>
</td>
<td>
<c:choose>
	<c:when test="${fn:contains(report.class_type, '5')}">
	<c:choose>
   <c:when test="${report.listenscore!=null}"><fmt:formatNumber value="${report.listenscore}" pattern="#.##"/></c:when>
   <c:otherwise>-</c:otherwise>
   </c:choose>
   </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</td>
<td>
<c:choose>
	<c:when test="${fn:contains(report.class_type, '6')}">
	<c:choose>
   <c:when test="${report.speakscore!=null}"><fmt:formatNumber value="${report.speakscore}" pattern="#.##"/></c:when>
   <c:otherwise>-</c:otherwise>
   </c:choose>
   </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</td>
<td>
<c:choose>
	<c:when test="${fn:contains(report.class_type, '7')}">
	<c:choose>
    <c:when test="${report.readscore!=null}"><fmt:formatNumber value="${report.readscore}" pattern="#.##"/></c:when>
    <c:otherwise>-</c:otherwise>
    </c:choose>
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</td>
<td>
<c:choose>
	<c:when test="${fn:contains(report.class_type, '8')}">
	<c:choose>
   <c:when test="${report.writescore!=null}"><fmt:formatNumber value="${report.writescore}" pattern="#.##"/></c:when>
   <c:otherwise>-</c:otherwise>
   </c:choose>
   </c:when> <c:otherwise>-</c:otherwise>
</c:choose>
</td>
</tr>
</c:forEach>
</table>
</div>
</div>
</div>
</body>
</html>