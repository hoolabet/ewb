<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${url} ${opt} management</title>
<link rel="stylesheet" href="../resources/css/management.css">
</head>
<body>
	<input type="hidden" value="${url}" id="url">
	<input type="hidden" value="${opt}" id="opt">
	<div id="to_main">main</div>
	<div id="management_entry">
		<div id="status">
			<div id="url_label">${url}</div>
			<div class="status" data-href="home">메인	</div>
			<div class="status" data-href="signup">회원가입</div>
			<c:choose>
				<c:when test="${opt eq 'shopping'}">
					<div class="status" data-href="product">상품 게시판</div>
				</c:when>
				<c:otherwise>
					<div class="status" data-href="board">게시판</div>
				</c:otherwise>
			</c:choose>
			<div id="delete">❌</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/management.js"></script>
</body>
</html>