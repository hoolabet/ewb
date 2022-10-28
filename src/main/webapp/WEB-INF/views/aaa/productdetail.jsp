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
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='${url}' id='url'>
<input type='hidden' value='${opt}' id='opt'>
<input type='hidden' value='${userId}' id='user_id'>
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
			<c:if test="${fn:contains(userInfo.admin,true)}">
			<div id='mr'>
				<div id='modify'>수정</div>
				<div id='remove'>삭제</div>
			</div>
			</c:if>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_product_detail.js"></script></body></html>