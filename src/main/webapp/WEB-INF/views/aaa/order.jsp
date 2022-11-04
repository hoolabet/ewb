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
    <title>aaa order</title>
    <link rel="stylesheet" href="../resources/css/url_order.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
	 <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap"
        rel="stylesheet"></head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='aaa' id='url'>
<input type='hidden' value='shopping' id='opt'>
		<input type='hidden' value='${ewbUser.id}' id='ewb_id'><input type='hidden' value='${userId}' id='user_id'>
	<div id='order_entry'>
		<div id='header'></div>
		<div id='order_content'>
			<div id='order_des'>
				<select id="des_select">
					<option value="new">새로운 배송지</option>
				</select><br>
				<input type='text' id='label' placeholder='배송지 이름'><br>
				<input type='text' id='name' placeholder='이름'><br>
				<input type='text' id='address' placeholder='주소'><br>
				<input type='text' id='phone' placeholder='연락처'><br>
				<input type='text' id='memo' placeholder='메모'><br>
			</div>
			<table id='order_table'>
			<c:forEach var="order" items="${order}">
				<tr>
					<td>
<c:choose><c:when test="${fn:length(order.pvo.tvo.fullpath) ne 0}"><img src='/display?fileName=${order.pvo.tvo.fullpath}' class='p_imgs'></c:when><c:otherwise><img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'></c:otherwise></c:choose>					</td>
					<td>
						<p>${order.pvo.pname}</p>
						<p>수량 : <input type='text' value='${order.b_quantity}' id='quan_${order.pno}' data-price='${order.pvo.price}'></p>
					</td>
					<td>
						<p>가격 : <span id='price_${order.pno}' class='prices'>${order.pvo.price*order.b_quantity}</span> 원</p>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan='4'>
						<p><span id='f_price'></span> 원</p>
					</td>
				</tr>
			</table>
			<div id='pay_btn'>결제하기</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_order.js"></script></body></html>