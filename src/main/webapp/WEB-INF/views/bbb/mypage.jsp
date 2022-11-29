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
    <title>bbb ${userInfo.id} mypage</title>
    <link rel="stylesheet" href="../resources/css/url_mypage.css">
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
	<div id='mypage_entry'>
		<div id='header'></div>
		<div id='mypage_content'>
			<table id='mypage_table'>
				<tr>
					<td>
						<a href='/${url}/modifyprofile'>정보수정</a>
					</td>
					<td>
						<a href='/${url}/checkwrite'>작성 글 보기</a>
					</td>
					<td>
						<a href='/${url}/checkreply'>작성 댓글 보기</a>
					</td>
				</tr>
			</table>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${bbb_userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_mypage.js"></script>
</body>
</html>