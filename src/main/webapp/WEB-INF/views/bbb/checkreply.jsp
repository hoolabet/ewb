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
    <title>bbb ${userInfo.id} checkreply</title>
    <link rel="stylesheet" href="../resources/css/url_checkreply.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
	 <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap"
        rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='bbb' id='url'>
<input type='hidden' value='community' id='opt'>
<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${bbb_userId}' id='user_id'>
	<div id='checkreply_entry'>
		<div id='header'></div>
		<div id='checkreply_content'>
			<table id="checkreply_table">
				<tr>
					<td>게시물 번호</td>
					<td>내용</td>
					<td>작성일</td>
				</tr>
				<c:forEach items="${cr}" var="cr">
					<tr>
						<td><a href="/${url}/boarddetail?bno=${cw.bno}">${cr.bno}</a></td>
						<td><a href="/${url}/boarddetail?bno=${cw.bno}">${cr.content}</a></td>
						<td>${cr.reply_date}</td>
					</tr>
				</c:forEach>
			</table>
			<form action="/${url}/checkreply" id="search_form">
				<input type="hidden" name="pageNum" value="${paging.cri.pageNum}">
				<input type="hidden" name="amount" value="${paging.cri.amount}">
				<select name="type">
					<option value="c">내용</option>
				</select> <input type="text" name="search" value="${paging.cri.search}">
				<input type="submit" value="찾기">
			</form>
			<br> <br>
			<div id='paging'>
				<a
					href="/${url}/checkreply?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">처음으로</a>
				<c:if test="${paging.prev}">
					<a
						href="/${url}/checkreply?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">이전</a>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
					var="num">
					<a
						href="/${url}/checkreply?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">${num}</a>
				</c:forEach>
				<c:if test="${paging.next}">
					<a
						href="/${url}/checkreply?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">다음</a>
				</c:if>
				<a
					href="/${url}/checkreply?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">맨끝으로</a>
			</div>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${bbb_userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_checkreply.js"></script>
</body>
</html>