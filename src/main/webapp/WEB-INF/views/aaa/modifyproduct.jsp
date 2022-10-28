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
    <title>aaa modify product</title>
    <link rel="stylesheet" href="../resources/css/url_modifyproduct.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'><input type='hidden' value='${url}' id='url'><input type='hidden' value='${opt}' id='opt'><input type='hidden' value='${modify.pno}' id='pno'><input type='hidden' value='${userId}' id='user_id'>	<div id='modifyproduct_entry'>
		<div id='header'></div>
		<div id='modifyproduct_content'>
			<div id="container">
				<label>상품명</label><input type='text' id='pname' value='${modify.pname}'>
				<label>가격</label><input type='text' id='price' value='${modify.price}'>
				<label>수량</label><input type='text' id='quantity' value='${modify.quantity}'>
				<label>섬네일</label><div contenteditable='true' id='thumbnail'><img src='/display?fileName=${modify.tvo.fullpath}'></div><div id='thumb_btn'>섬네일 넣기</div><input type='file' id='thumb_file'>
        		<label>상세내용</label><div contenteditable='true' id="content">${modify.content}</div>
				<input type='file' id='insert_img' multiple>
				<div id='insert_btn'>이미지 넣기</div>
        		<input type="button" value="수정하기" id="modi_btn">
    		</div>		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_modifyproduct.js"></script></body></html>