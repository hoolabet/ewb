<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>aa home</title>
    <link rel="stylesheet" href="../resources/css/url_signup.css">
    <link rel="stylesheet" href="../resources/css/url_home.css">
    <link rel="stylesheet" href="../resources/color_picker/jquery.minicolors.css">
</head>
<body>
<input type='hidden' value='${url}' id='url'><div id="btns">
        <input type="button" value="저장" id="save">
        <input type="button" value="불러오기" id="load">
    </div>
    <div id="signup_entry">
        <div id="header"></div>
        <div id='signup_content'>
            <div id='sign_div'>
                <form id='sign_form'>
                    <table id='sign_table'>
                        <tr>
                            <td style='width:150px;'><span class='modi_span' id='id_span'>ID</span></td>
                            <td style='width:650px;'><input class='modi_input' type='text' name='id' id='id' required
                                    data-able='f'><input type='button' value='중복확인' id='dup_check'><span
                                    class='id check_span'></span></td>
                        </tr>
                        <tr>
                            <td><span class='modi_span' id='pw_span'>PASSWORD</span></td>
                            <td><input class='modi_input' type='password' name='password' id='pw' required
                                    data-able='f'><span class='pw check_span'></span></td>
                        </tr>
                        <tr>
                            <td><span class='modi_span' id='pwc_span'>PASSWORD CHECK</span></td>
                            <td><input class='modi_input' type='password' id='pw_check' required><span
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../resources/color_picker/jquery.minicolors.js"></script>
    <script src="../resources/js/url_signup.js"></script></body></html>