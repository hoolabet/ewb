<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EWB</title>
	<link rel="stylesheet" href="../resources/css/home.css">
</head>
<body>
	<input type="hidden" value="${ewbUser.id}" id="id">
	<div id="container">
		<div id="container_title">EWB</div>
		<div id="begin">START</div>
		<div id="login">
			<table>
				<tr>
					<td>ID</td>  
					<td><input type="text" id="login_id" class="login_input_text" size="15"></td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="password" id="login_pw" class="login_input_text" size="15"></td>
				</tr>
				<tr>
					<td colspan="2" id="login_btn_td"><br><span class="login_btn" id="login_btn">LOG IN</span><span class="login_btn" id="signup_open_btn">SIGN UP</span></td>
				</tr>
			</table>
		</div>
		<div id="signup">
			<table>
				<tr>
					<td>ID</td>  
					<td><input type="text" id="signup_id" class="signup_input_text" size="15"></td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="password" id="signup_pw" class="signup_input_text" size="15"></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" id="signup_name" class="signup_input_text" size="15"></td>
				</tr>
				<tr>
					<td colspan="2" id="signup_btn_td"><br><span id="signup_btn">SIGN UP</span></td>
				</tr>
			</table>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/home.js"></script>
</body>
</html>
