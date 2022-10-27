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
    <title>aaa cart</title>
    <link rel="stylesheet" href="../resources/css/url_cart.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='${url}' id='url'>
<input type='hidden' value='${opt}' id='opt'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='cart_entry'>
		<div id='header'></div>
		<div id='cart_content'>
			<table id='cart_table'>
			<c:forEach var="cart" items="${cart}">
				<tr>
					<td>
						<input type='checkbox' class='cart_check' data-pno='${cart.pno}' checked>
					</td>
					<td>
<c:choose><c:when test="${fn:length(cart.pvo.tvo.fullpath) ne 0}"><img src='/display?fileName=${cart.pvo.tvo.fullpath}' class='p_imgs'></c:when><c:otherwise><img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'></c:otherwise></c:choose>					</td>
					<td>
						<p>${cart.pvo.pname}</p>
						<p>수량 : <input type='button' value='🔻'  class='quan' data-pno='${cart.pno}' data-val='down'><input type='text' value='${cart.b_quantity}' id='quan_${cart.pno}' data-price='${cart.pvo.price}' data-quantity='${cart.pvo.quantity}'><input type='button' value='🔺'  class='quan' data-pno='${cart.pno}' data-val='up'></p>
					</td>
					<td>
						<p>가격 : <span id='price_${cart.pno}' class='prices'>${cart.pvo.price*cart.b_quantity}</span> 원</p>
					</td>
					<td>
						<input type='button' class='remove_btn' data-pno='${cart.pno}' value='❌'>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan='4'>
						<p><span id='f_price'></span> 원</p>
					</td>
				</tr>
			</table>
			<div id='order_all_btn'>전체 주문하기</div>
			<div id='order_selected_btn'>선택 주문하기</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_cart.js"></script></body></html>