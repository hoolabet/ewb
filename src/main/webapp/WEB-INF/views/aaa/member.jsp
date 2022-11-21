<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>aaa member</title>
    <link rel="stylesheet" href="../resources/css/url_member.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
	 <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap"
        rel="stylesheet">	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='aaa' id='url'>
<input type='hidden' value='community' id='opt'>
		<input type='hidden' value='${ewbUser.id}' id='ewb_id'><input type='hidden' value='${userId}' id='user_id'>
	<div id='member_entry'>
		<div id='header'></div>
		<div id='member_content'>
			<table id="member_table">
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>이메일</td>
					<td>연락처</td>
					<td>생년월일</td>
					<td>가입일</td>
					<td></td>
				</tr>
				<c:forEach items="${member}" var="member">
					<tr>
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<td>${member.phone}</td>
						<td>${member.birth}</td>
						<td>${member.sign_date}</td>
						<td>
							<div class="delete_btn" data-id="${member.id}">❌</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<form action="/${url}/member" id="search_form">
				<input type="hidden" name="pageNum" value="${paging.cri.pageNum}">
				<input type="hidden" name="amount" value="${paging.cri.amount}">
				<select name="type">
					<option value="t">아이디</option>
					<option value="c">이름</option>
					<option value="tc">아이디+이름</option>
				</select> <input type="text" name="search" value="${paging.cri.search}">
				<input type="submit" value="찾기">
			</form>
			<br> <br>
			<div id='paging'>
				<a
					href="/${url}/member?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">처음으로</a>
				<c:if test="${paging.prev}">
					<a
						href="/${url}/member?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">이전</a>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
					var="num">
					<a
						href="/${url}/member?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">${num}</a>
				</c:forEach>
				<c:if test="${paging.next}">
					<a
						href="/${url}/member?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">다음</a>
				</c:if>
				<a
					href="/${url}/member?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">맨끝으로</a>
			</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_member.js"></script>
</body>
</html>