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
		<input type='hidden' value='${userId}' id='user_id'>
		<input type='hidden' value='aaa' id='url'>
		<input type='hidden' value='community' id='opt'>
		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>
    <div id="btns">
        <input type="button" value="저장" id="save">
        <input type="button" value="불러오기" id="load">
    </div>
    <div id="main_entry">
        <div id="header">
            <div>
                <input type="hidden" id="header_style">
                <input type="hidden" id="save_margin">
                <input type="hidden" id="save_button">
                <input type="hidden" id="save_text">
				 <input type="hidden" id="save_font">
            </div>
        </div>
        <div id="main_content"></div>
        <div id="footer"><input type="hidden" id="footer_style"></div>
        
    </div>
    <div id="controller">
        <div id="target_name"></div>
        <div class="controllers bgcolors colors" id="background-color">배경 색</div>
        <div class="controllers ftcolors colors" id="color">글자 색</div>
        <div class="controllers" id="font_size">글자 크기</div>
        <div class="controllers" id="border">테두리 설정</div>
        <div class="controllers" id="margin">공백 설정</div>
        <div class="controllers" id="size">크기 조절</div>
        <div class="controllers" id="place">위치 조절</div>
        <div class="controllers" id="center">가운데 정렬</div>
        <div class="controllers" id="create_element">요소 추가</div>
        <div class="controllers" id="direction">방향 정렬</div>
        <div class="controllers" id="inner_margin">내부 공백</div>
        <div class="controllers" id="position">포지션 설정</div>
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
    <div id="margin_menu" class="menus">
        <div>
            위 공백 : <input type="text" id="margin_top" size="5">px
        </div>
        <div>
            아래 공백 : <input type="text" id="margin_bottom" size="5">px
        </div>
        <div>
            왼 공백 : <input type="text" id="margin_left" size="5">px
        </div>
        <div>
            오른 공백 : <input type="text" id="margin_right" size="5">px
        </div>
        <div id="margin_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="size_menu" class="menus">
        <div id="size_width_div">가로 : <input type="text" id="size_width" size="5">px</div>
        <div id="size_height_div">세로 : <input type="text" id="size_height" size="5">px</div>
        <div id="size_change" class="change_btn">적용</div>
        <div class="close_btn" style="top: 0px; right: 0px">✖</div>
    </div>
    <div id="place_menu" class="menus">
        <div>가로 : <input type="text" id="place_left" size="5">px</div>
        <div>세로 : <input type="text" id="place_top" size="5">px</div>
        <div id="place_change" class="change_btn">적용</div>
        <div class="close_btn" style="top: 0px; right: 0px">✖</div>
    </div>
    <div id="center_menu" class="menus">
        <div class="center" data-center="x">가로 중앙</div>
        <div class="center" data-center="y">세로 중앙</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="create_element_menu" class="menus">
        <div class="elements" id="create_home">홈</div>
        <div class="elements" id="create_login">로그인</div>
        <div class="elements" id="create_ul">목록</div>
        <div class="elements" id="create_editable">텍스트 상자</div>
    </div>
    <div id="direction_menu" class="menus">
            <div><input type="radio" class="list_direction" name="d" value="inline-block"><label>가로</label></div>
            <div><input type="radio" class="list_direction" name="d" value="list-item"><label>세로</label></div>
    </div>
    <div id="inner_margin_menu" class="menus">
        <div>
            위 공백 : <input type="text" id="inner_margin_top" size="5">px
        </div>
        <div>
            아래 공백 : <input type="text" id="inner_margin_bottom" size="5">px
        </div>
        <div>
            왼 공백 : <input type="text" id="inner_margin_left" size="5">px
        </div>
        <div>
            오른 공백 : <input type="text" id="inner_margin_right" size="5">px
        </div>
        <div id="inner_margin_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="position_menu" class="menus">
        <div class='position' data-position='default'>
            Default
        </div>
        <div class='position' data-position='fixed'>
            Fixed
        </div>
        <div class='position' data-position='sticky'>
            Sticky
        </div>
    </div>
    <div id="body_controller_btn">
		<span class="material-symbols-outlined" style="font-size: 40px;">
			public
		</span>
	</div>
    <div id="body_controller">
        <div id="margin_controller_btn" class="controllers">전체 영역 공백</div>
        <div id="button_controller_btn" class="controllers input_controller_btn" data-type="button">버튼</div>
        <div id="text_controller_btn" class="controllers input_controller_btn" data-type="text">입력창</div>
        <div id="font_controller_btn" class="controllers">글꼴</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="margin_controller">
        <table>
            <tr>
                <td>위측 공백</td>
                <td><input type="text" id="body_top" size="5">px</td>
            </tr>
            <tr>
                <td>아래측 공백</td>
                <td><input type="text" id="body_bottom" size="5">px</td>
            </tr>
            <tr>
                <td>좌측 공백</td>
                <td><input type="text" id="body_left" size="5">px</td>
            </tr>
            <tr>
                <td>우측 공백</td>
                <td><input type="text" id="body_right" size="5">px</td>
            </tr>
        </table>
        <div id="entry_margin_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
    <div id="input_controller">
        <table>
            <tr>
                <td>글자 크기</td>
                <td><input type="text" id="input_ftsize" size="5">px</td>
            </tr>
            <tr>
                <td>안쪽 여백</td>
                <td><input type="text" id="input_padding" size="5">px</td>
            </tr>
            <tr>
                <td>테두리 크기</td>
                <td><input type="text" id="input_bdwidth"size="5">px</td>
            </tr>
            <tr>
                <td>테두리 색</td>
                <td><input type="text" id="input_bdcolor" class="cp" size="5"></td>
            </tr>
            <tr>
                <td>배경 색</td>
                <td><input type="text" id="input_bgcolor" class="cp" size="5"></td>
            </tr>
            <tr>
                <td>글자 색</td>
                <td><input type="text" id="input_ftcolor" class="cp" size="5"></td>
            </tr>
        </table>
        
        <div id="entry_input_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>
	 <div id="font_controller">
        <table>
            <tr>
                <td>글꼴 선택</td>
                <td>
					<select id="font_select">
        				<option value="normal">기본</option>
        				<option id="jua">jua</option>
        				<option id="bs">bs</option>
        				<option id="go">go</option>
        				<option id="jo">jo</option>
        				<option id="pen">pen</option>
        				<option id="noto">noto</option>
        				<option id="poor">poor</option>
    				</select>
				</td>
            </tr>
			 <tr>
            	<td>글씨 크기</td>
            	<td><input type="text" id="font_size_select"></td>
            </tr>
            <tr>
                <td>미리보기</td>
                <td>
                	<div id="font_preview" contenteditable="true" style="width: 60px;height:60px;border:1px solid black;">hi</div>
                </td>
            </tr>
        </table>
        <div id="entry_font_apply" class="change_btn">적용</div>
        <div class="close_btn">✖</div>
    </div>    <input type='file' id='upload_input' style='display:none'>
	<div id="chat_btn">💬</div>
	<iframe id="if"	width="400" height="500" src="http://localhost:8080/chat?chat_url=${url}&id=${userId}"></iframe>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/color_picker/jquery.minicolors.js"></script>
	<script src="../resources/js/url_home.js"></script>
	<script src="../resources/js/url_chat.js"></script>
</body>
</html>