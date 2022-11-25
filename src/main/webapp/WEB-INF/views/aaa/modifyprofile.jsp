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
	<div id='modifyprofile_entry'>
		<div id="reg_info"></div>
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
								<input type="password" id="pw"><span style="position:absolute" class="pw"></span>
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
				<div class="modifyprofile_div">
					<table>
						<tr>
							<td>
								<label>배송지 설정</label>
							</td>
						</tr>
						<tr>
							<td>
								<select id="des_select">
									<option value="new">새로운 배송지</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="des_label" placeholder="배송지 이름" required>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="des_name" placeholder="이름" required>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="des_address" placeholder="배송지 주소" required>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="des_phone" placeholder="연락처" required>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="des_memo" placeholder="메모">
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" id="des_add_btn" value="추가">
								<input type="button" id="des_modify_btn" value="수정">
								<input type="button" id="des_remove_btn" value="삭제">
							</td>
						</tr>
					</table>
				</div>
				<div>
					<input type="button" id="user_delete_btn" value="계정 삭제">
				</div>
			</div>
		</div>
		<div id='footer'></div>
	</div>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${userId}"></iframe>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_chat.js"></script>
    <script src="../resources/js/url_modifyprofile.js"></script>
</body>
</html>