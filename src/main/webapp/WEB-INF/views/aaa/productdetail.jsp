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
    <title>aaa product detail</title>
    <link rel="stylesheet" href="../resources/css/url_product_detail.css">
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
<input type='hidden' value='aaa' id='url'>
<input type='hidden' value='shopping' id='opt'>
<input type='hidden' value='${userId}' id='user_id'>
<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${detail.quantity}' id='product_quantity'>
<input type='hidden' value='${detail.pno}' id='product_pno'>
	<div id='product_detail_entry'>
		<div id='header'></div>
		<div id='product_detail_content'>
			<div id='product_img'>
			<c:choose>
			<c:when test="${fn:length(detail.tvo.fullpath) ne 0}">
				<img src='/display?fileName=${detail.tvo.fullpath}' class='p_imgs'>
			</c:when>
			<c:otherwise>
				<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>
			</c:otherwise>
			</c:choose>
			</div>
			<table id='product_table'>
				<tr>
					<td>
						${detail.pname}
					</td>
				</tr>
				<tr>
					<td>
						가격 : ${detail.price}
					</td>
				</tr>
				<tr>
					<td>
						수량 : <input type='button' value='🔻' id='quan_down' class='quan'><input type='text' id='quan' size='2' readonly value='1' data-price='${detail.price}'><input type='button' value='🔺' id='quan_up' class='quan'>
					</td>
				</tr>
				<tr>
					<td>
						합계 : <span id='multi_price'></span>
					</td>
				</tr>
				<tr>
					<td>
						<input type='button' value='주문하기' id='order_btn'><input type='button' value='장바구니' id='cart_btn'>
					</td>
				</tr>
			</table>
			<div id='product_content'>${detail.content}</div>
			<div id='review_div'>
				<textarea placeholder="리뷰" id="review_text" required></textarea><br>
				<div contenteditable="false" id="review_img_area"></div><br>
				<input type="button" value="사진첨부" id="review_img_btn"><br>
				<input type="file" id="review_img_file">
				<input type="button" value="작성하기" id="review_btn">
				<div id="reviews">
					<table id="review_table">
					
					</table>
					<ul id="pagingul">
    				</ul>
				</div>
			</div>			<c:if test="${fn:contains(userInfo.admin,true)}">
			<div id='mr'>
				<div id='modify'>수정</div>
				<div id='remove'>삭제</div>
			</div>
			</c:if>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_product_detail.js"></script>
</body>
</html>