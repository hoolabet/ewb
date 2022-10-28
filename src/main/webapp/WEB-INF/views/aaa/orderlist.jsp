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
    <title>aaa order list</title>
    <link rel="stylesheet" href="../resources/css/url_orderlist.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='${url}' id='url'>
<input type='hidden' value='${opt}' id='opt'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='orderlist_entry'>
		<div id='header'></div>
		<div id='orderlist_content'>
			<div id='orderlist_div'>
				<table id='orderlist_table'>
				<c:forEach items="${orderlist}" var="orderlist">
					<tr>
						<td>
							${orderlist.price}
							<input type='hidden' value='${orderlist.payno}'>
						</td>
						<td>
							${orderlist.name}
						</td>
						<td>
							${orderlist.address}
						</td>
						<td>
							${orderlist.phone}
						</td>
							<c:if test="${fn:length(orderlist.phone) ne 0}">
						<td>
							${orderlist.phone}
						</td>
							</c:if>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_orderlist.js"></script></body></html>