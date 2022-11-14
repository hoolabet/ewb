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
    <title>aaa boarddetail</title>
    <link rel="stylesheet" href="../resources/css/url_boarddetail.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
	 <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap"
        rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='aaa' id='url'>
<input type='hidden' value='community' id='opt'>
<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='boarddetail_entry'>
		<div id='header'></div>
		<div id='boarddetail_content'>
			<input type="hidden" value="${detail.bno}" id="bno">
			<input type="hidden" value="${detail.id}" id="b_id">
			<div id="entry">
				<div id="rm">
					<input type="button" value="수정" id="modi_btn">
					<input type="button" value="삭제" id="remo_btn">
				</div>
				<label>제목</label>
				<p id="bname">${detail.bname}</p>
				<label>내용</label>
				<p id="content">${detail.content}</p>
			</div>
			<div id="reply">
				<textarea rows="3" cols="60" id="reply_content"></textarea>
				<input type="button" value="reply" id="reply_btn">
				<table id="reply_table">

				</table>
				<ul id="pagingul">
				</ul>
			</div>		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_boarddetail.js"></script>
</body>
</html>