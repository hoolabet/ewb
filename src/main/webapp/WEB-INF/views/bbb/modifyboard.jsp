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
    <title>bbb modifyboard</title>
    <link rel="stylesheet" href="../resources/css/url_modifyboard.css">
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
<input type='hidden' value='bbb' id='url'>
<input type='hidden' value='community' id='opt'>
<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${bbb_userId}' id='user_id'>
	<div id='modifyboard_entry'>
		<div id='header'></div>
		<div id='modifyboard_content'>
			<input type="hidden" value="${detail.bno}" id="bno">
			<div id="entry">
				<label>제목</label>
				<input type="text" id="bname" value="${detail.bname}">
				<input type="checkbox" id="able_box" checked>
				<div id="btnss">
					<div class="btns" data-att="b">
						<b>B</b>
					</div>
					<div class="btns" data-att="i">
						<i>I</i>
					</div>
					<div class="btns" data-att="u">
						<u>U</u>
					</div>
					<div class="btns" data-att="a">L</div>
					<div class="btns" id="font_size_btn">
						<span class="material-symbols-outlined"> format_size </span>
					</div>
					<select id="font_size">
						<option value="8">8</option>
						<option value="12">12</option>
						<option value="16">16</option>
						<option value="20">20</option>
						<option value="24">24</option>
						<option value="28">28</option>
					</select>
					<div class="btns" id="font_color_btn">
						<span class="material-symbols-outlined"> format_color_text
						</span>
					</div>
					<div id="cp_div">
						<input type="text" id="cp"> <input type="button"
							value="선택" id="font_color_choice">
					</div>
					<div id="sort">
						<span class="material-symbols-outlined sort btns" data-sort="left">
							format_align_left </span> <span
							class="material-symbols-outlined sort btns" data-sort="center">
							format_align_center </span> <span
							class="material-symbols-outlined sort btns" data-sort="right">
							format_align_right </span>
					</div>
					<div class='btns' id='insert_btn'>
						<span class="material-symbols-outlined"> imagesmode </span>
					</div>
				</div>
				<div id="link_div">
					<input class="links" type="text" id="href" placeholder="링크주소"><br>
					<input class="links" type="text" id="href_text" placeholder="링크이름"><br>
					<input class="links" type="button" id="link_btn" value="링크만들기"></br>
				</div>
				<div contenteditable="true" id="content">${detail.content}</div>
			</div>
			<input type='file' id='insert_img' multiple> <input
				type="button" value="수정하기" id="write_btn">		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${bbb_userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_modifyboard.js"></script>
</body>
</html>