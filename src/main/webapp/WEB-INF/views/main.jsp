<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome, ${ewbUser.id}</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<input type="hidden" value="${ewbUser.id}" id="id">
	<div id="management">
		<div id="create_new_page" class="management_btn">create new page</div>
		<div id="edit_page" class="management_btn">edit page</div>
	</div>
	<div id="new_page_option">
		<div id="shopping" class="new_page_option">쇼핑몰</div>
		<div id="community" class="new_page_option">커뮤니티</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/main.js"></script>
</body>
</html>