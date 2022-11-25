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
    <title>aaa product</title>
    <link rel="stylesheet" href="../resources/css/url_product.css">
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
		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='product_entry'>
		<div id='header'></div>
		<div id='product_content'>
			<div id='product_div'>
				<c:if test='${userInfo.admin eq true}'>
				<div id='add_product'>상품 추가</div>
				</c:if>
				<select id="dataPerPage" value="${paging.cri.amount}">
	                 <option value="15">15개씩보기</option>
		   	         <option value="30">30개씩보기</option>
       		     <option value="45">45개씩보기</option>
       		     <option value="60">60개씩보기</option>
        		</select>
				<div id='table_div'>
				<c:forEach var="list" items="${product}">
				<table class='product_table'>
					<tr>
						<td style='position:relative'><a href="/aaa/productdetail?pno=${list.pno}">						<c:choose>
						<c:when test="${fn:length(list.tvo.fullpath) ne 0}">
							<img src='/display?fileName=${list.tvo.fullpath}' class='p_imgs'>
						</c:when>
						<c:otherwise>
							<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>
						</c:otherwise>
						</c:choose>
						<c:if test="${list.quantity eq 0}">
						<div class='sold_out' id='so_${list.pno}'>sold out</div>
						</c:if>
						</a></td>
					</tr>
					<tr>
						<td><a href="/aaa/productdetail?pno=${list.pno}">${list.pname}</a></td>
					</tr>
					<tr>
						<td>${list.price}원</td>
					</tr>
				</table>
				</c:forEach>
				</div>
			<br>
			<form action="/aaa/product" id="search_form">
				<input type="hidden" name="pageNum" value="${paging.cri.pageNum}"> <input
					type="hidden" name="amount" value="${paging.cri.amount}"> <select
					name="type">
					<option value="t">제목</option>
					<option value="c">내용</option>
					<option value="tc">제목+내용</option>
				</select> <input type="text" name="search" value="${paging.cri.search}"> <input type="submit"
					value="찾기">
			</form>
			<br> <br> <div id='paging'><a
				href="/aaa/product?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">처음으로</a>
			<c:if test="${paging.prev}">
				<a
					href="/aaa/product?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">이전</a>
			</c:if>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
				var="num">
				<a
					href="/aaa/product?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">${num}</a>
			</c:forEach>
			<c:if test="${paging.next}">
				<a
					href="/aaa/product?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">다음</a>
			</c:if>
			<a
				href="/aaa/product?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">맨끝으로</a></div>			</div>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${userId}"></iframe>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_product.js"></script>
	<script src="../resources/js/url_chat.js"></script>
</body>
</html>