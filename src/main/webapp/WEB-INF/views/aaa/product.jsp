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
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'><input type='hidden' value='${url}' id='url'><input type='hidden' value='${opt}' id='opt'><input type='hidden' value='${userId}' id='user_id'>	<div id='product_entry'>
		<div id='header'></div>
		<div id='product_content'>
			<div>
				<c:if test='${userInfo.admin eq true}'>
				<div id='add_product'>상품 추가</div>
				</c:if>
				<select id="dataPerPage">
	                 <option value="10">10개씩보기</option>
		   	         <option value="15">15개씩보기</option>
       		     <option value="20">20개씩보기</option>
        		</select>
				<table id=\"dataTableBody\">
				<tr>
					<td id="td_1">no</td>
					<td id="td_2">title</td>
					<td id="td_3">regdate</td>
				</tr>
				<c:forEach var="list" items="${product}">
					<tr>
						<td>${list.pno}</td>
						<td><a href="detail?nono=${list.pno}">${list.pname}</a></td>
						<td>${list.reg_date}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<form action="/aaa/product" id="search_form">
				<input type="hidden" name="pageNum" value="1"> <input
					type="hidden" name="amount" value="${paging.cri.amount}"> <select
					name="type">
					<option value="t">제목</option>
					<option value="c">내용</option>
					<option value="tc">제목+내용</option>
				</select> <input type="text" name="search"> <input type="submit"
					value="찾기">
			</form>
			<br> <br> <a
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
				href="/aaa/product?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}">맨끝으로</a>			</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_product.js"></script></body></html>