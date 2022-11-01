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
    <title>aaa ${userInfo.id} modify profile</title>
    <link rel="stylesheet" href="../resources/css/url_modifyprofile.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${userInfo.admin}' id='admin'>
<input type='hidden' value='${url}' id='url'>
<input type='hidden' value='${opt}' id='opt'>
<input type='hidden' value='${userId}' id='user_id'>
	<div id='modifyprofile_entry'>
		<div id='header'></div>
		<div id='modifyprofile_content'>
			<div id="modifyprofile_div">
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>비밀번호 수정</label>
							</td>
							<td>
								<input type="password" id="pw">
							</td>
						</tr>
						<tr>
							<td>
								<label>비밀번호 수정 확인</label>
							</td>
							<td>
								<input type="password" id="pwc">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="td_btn">
								<input type="button" value="비밀번호 수정" id="pw_btn">
							</td>
						</tr>
					</table>
				</div>
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>이름 수정</label>
							</td>
							<td>
								<input type="text" id="name">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="td_btn">
								<input type="button" value="이름 수정" id="name_btn">
							</td>
						</tr>
					</table>
				</div>
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>전화번호 수정</label>
							</td>
							<td>
								<select id="f_phone">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select>
								<input type="text" id="phone">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="td_btn">
								<input type="button" value="번호 수정" id="phone_btn">
							</td>
						</tr>
					</table>
				</div>
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>이메일 수정</label>
							</td>
							<td>
								<input type="text" id="email">@
								<input type="text" id="e_address" readonly>
								<select id="e_select">
									<option value="naver.com">naver.com</option>
									<option value="google.com">google.com</option>
									<option value="dir">직접입력</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="td_btn">
								<input type="button" value="이메일 수정" id="email_btn">
							</td>
						</tr>
					</table>
				</div>
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>생년월일 수정</label>
							</td>
							<td>
								<input type="date" id="birth">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="td_btn">
								<input type="button" value="생년월일 수정" id="birth_btn">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id='footer'></div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_modifyprofile.js"></script></body></html>