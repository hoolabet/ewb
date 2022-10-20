<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>abc product</title>
    <link rel="stylesheet" href="../resources/css/url_product.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'><input type='hidden' value='${url}' id='url'>	<div id='product_entry'>
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
   	     	<table id="dataTableBody">
    			
        		</table>
        		<ul id="pagingul">
        		</ul>
			</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_product.js"></script></body></html>