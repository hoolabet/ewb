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
    <title>aaa home</title>
    <link rel="stylesheet" href="../resources/css/url_signup.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${url}' id='url'><div id="btns">
        <input type="button" value="저장" id="save">
        <input type="button" value="불러오기" id="load">
    </div>
    <div id="reg_info">
    	<input type="hidden" id="reg_pw">
    </div>
    <div id="signup_entry">
        <div id="header"></div>
        <div id='signup_content'>
			<input type='hidden' id='signup_style'>
            <div id='sign_div'>
                <form id='sign_form'>
                    <table id='sign_table'>
                        <tr>
                            <td style='width:150px;'><span class='modi_span' id='id_span'>ID</span></td>
                            <td style='width:650px;'><input class='modi_input input' type='text' name='id' id='id' required
                                    data-able='f'><input type='button' value='중복확인' id='dup_check'><span
                                    class='id check_span'></span></td>
                        </tr>
                        <tr>
                            <td><span class='modi_span' id='pw_span'>PASSWORD</span></td>
                            <td><input class='modi_input input' type='password' name='password' id='pw' required
                                    data-able='f'><span class='pw check_span'></span></td>
                        </tr>
                        <tr>
                            <td><span class='modi_span' id='pwc_span'>PASSWORD CHECK</span></td>
                            <td><input class='modi_input input' type='password' id='pw_check' required><span
                                    class='pw_check check_span'></span></td>
                        </tr>
                        <tr id='before'>
                            <td><img id='sign_img'
                                    src='https://icons-for-free.com/download-icon-circle+more+plus+icon-1320183136549593898_512.png'>
                            </td>
                            <td><input type='submit' value='가입하기' id='sign_submit'></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="footer"></div>
    </div>
    <div id='sign_element'>
        <div class='sign_element' id='sign_name'>이름</div>
        <div class='sign_element' id='sign_mail'>이메일</div>
        <div class='sign_element' id='sign_phone'>전화번호</div>
        <div class='sign_element' id='sign_birth'>생년월일</div>
    </div>
    <div id='modify_span'>
        <div class='modify_span' id='ss_modify'>수정</div>
    </div>
    <div id='modify_input'>
        <div class='modify_input' id='reg'>정규식 설정
            <div class='reg' id='reg_free'>모두 허용</div>
            <div class='reg' id='reg_str'>문자</div>
            <div class='reg' id='reg_num'>숫자</div>
            <div class='reg' id='reg_spe'>특수문자</div>
        </div>
        <div class='modify_input' id='len'>글자 수 설정</div>
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
    <script src="../resources/js/url_signup.js"></script></body></html>