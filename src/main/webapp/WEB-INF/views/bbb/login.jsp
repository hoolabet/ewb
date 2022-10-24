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
    <title>bbb login</title>
    <link rel="stylesheet" href="../resources/css/url_login.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
		<input type='hidden' value='${userId}' id='user_id'>		<input type='hidden' value='${url}' id='url'>		<input type='hidden' value='${opt}' id='opt'>		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>    <div id="btns">
        <input type="button" value="저장" id="save">
        <input type="button" value="불러오기" id="load">
    </div>
    <div id="login_entry">
        <div id="header">
            <div>
                <input type="hidden" id="header_style">
                <input type="hidden" id="save_margin">
                <input type="hidden" id="save_button">
                <input type="hidden" id="save_text">
            </div>
        </div>
        <div id="login_content">
			<input type='hidden' id='login_style'>
            <div id="login_box">
                <table id="login_table">
                    <tr>
                        <td>
                            <span class="modi_span" data-target="id">ID</span>
                        </td>
                        <td>
                            <input type="text" id="input_id" class="input">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="modi_span" data-target="pw">PASSWORD</span>
                        </td>
                        <td>
                            <input type="password" id="input_pw" class="input">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <input type="button" value="Log In" id="login_btn"><br>
                            <input type="button" value="Sign Up" id="signup_btn">
                        </td>
                    </tr>
                </table>
            </div>
        </div>        <div id="footer"><input type="hidden" id="footer_style"></div>
        
    </div>
	<div id="controller">
        <div id="target_name"></div>
        <div class="controllers bgcolors colors" id="background-color">배경 색</div>
        <div class="controllers ftcolors colors" id="color">글자 색</div>
        <div class="controllers" id="font_size">글자 크기</div>
        <div class="controllers" id="border">테두리 설정</div>
        <div class="controllers" id="size">크기 조절</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="color_picker" class="menus">
        <input type="text" id="cp" class="cp">
        <div id="color_change" class="change_btn">적용</div>
        <div class="close_btn" style="top: 0px; right: 0px">✖</div>
    </div>
    <div id="font_size_menu" class="menus">
        <div>글자 크기 : <input type="text" id="font_size_input" size="5">px</div>
        <div id="font_size_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="border_menu" class="menus">
        <div>
            위 테두리 : <input type="text" id="border_top" size="5">px
        </div>
        <div>
            아래 테두리 : <input type="text" id="border_bottom" size="5">px
        </div>
        <div>
            왼 테두리 : <input type="text" id="border_left" size="5">px
        </div>
        <div>
            오른 테두리 : <input type="text" id="border_right" size="5">px
        </div>
        <div>
            테두리 색 : <input type="text" id="border_color" class="cp">
        </div>
        <div>
            모서리 곡률 : <input type="text" id="border_radius" size="5">px
        </div>
        <div id="border_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="size_menu" class="menus">
        <div id="size_width_div">가로 : <input type="text" id="size_width" size="5">px</div>
        <div id="size_height_div">세로 : <input type="text" id="size_height" size="5">px</div>
        <div id="size_change" class="change_btn">적용</div>
        <div class="close_btn" style="top: 0px; right: 0px">✖</div>
    </div>    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_login.js"></script>
</body></html>