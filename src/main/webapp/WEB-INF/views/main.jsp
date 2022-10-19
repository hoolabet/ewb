<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome, ${ewbUser.id}</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<input type="hidden" value="${ewbUser.id}" id="id">
	<div id="logout">Log Out</div>
	<div id="management">
		<div id="create_new_page" class="management_btn">create new page</div>
		<div id="edit_page" class="management_btn">edit page</div>
	</div>
	<div id="new_page_option">
		<div id="shopping" class="new_page_option">쇼핑몰</div>
		<div id="community" class="new_page_option">커뮤니티</div>
	</div>
	<div id="edit_page_option">
		<c:choose>
		<c:when test="${fn:length(editPage) ne 0}">
		<c:forEach var="page" items="${editPage}">
			<div data-url="${page.url}" data-opt="${page.opt}" class="edit_page">${page.url}</div>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<div>
				없음
			</div>
		</c:otherwise>
		</c:choose>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/main.js"></script>
</body>
</html>