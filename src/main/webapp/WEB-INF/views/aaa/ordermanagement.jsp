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
    <title>aaa ordermanagement</title>
    <link rel="stylesheet" href="../resources/css/url_ordermanagement.css">
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
<input type='hidden' value='shopping' id='opt'>
<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='ordermanagement_entry'>
		<div id='header'></div>
		<div id='ordermanagement_content'>
			<div id='ordermanagement_div'>
				<table id='ordermanagement_table'>
				<c:forEach items="${orderlist}" var="orderlist">
					<tr id="payno_${orderlist.payno}">
						<td class="orderlist_td" data-payno="${orderlist.payno}">
							${orderlist.price}원<br>
							${orderlist.payment_date}
						</td>
					</tr>
				</c:forEach>
				</table>
			<br> <br> <div id='paging'><a
				href="/${url}/ordermanagement?pageNum=1&amount=${paging.cri.amount}">처음으로</a>
			<c:if test="${paging.prev}">
				<a
					href="/${url}/ordermanagement?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}">이전</a>
			</c:if>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
				var="num">
				<a
					href="/${url}/ordermanagement?pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
			</c:forEach>
			<c:if test="${paging.next}">
				<a
					href="/${url}/ordermanagement?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">다음</a>
			</c:if>
			<a
				href="/${url}/ordermanagement?pageNum=${paging.realEnd}&amount=${paging.cri.amount}">맨끝으로</a></div>			</div>			</div>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_ordermanagement.js"></script>
</body>
</html>