package org.ewb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.ContentVO;
import org.ewb.model.MemberVO;
import org.ewb.model.ProductVO;
import org.ewb.service.EWBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EWBController {
	@Autowired
	EWBService es;

	@RequestMapping(value = "/ewblogin", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> ewbLogin(MemberVO mvo, HttpSession session) {
		session.setAttribute("ewbUser", es.ewbLogin(mvo));
		return new ResponseEntity<>(es.ewbLogin(mvo),HttpStatus.OK);
	}

	@RequestMapping(value = "/ewbsignup", method = RequestMethod.POST)
	public ResponseEntity<String> ewbSignUp(@RequestBody MemberVO mvo) {
		int result = es.ewbSignUp(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main(HttpSession session) {
		try {
			MemberVO mvo = new MemberVO();
			mvo = (MemberVO)session.getAttribute("ewbUser");
			session.setAttribute("editPage", es.getPage(mvo.getId()));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping(value = "/newpage", method = RequestMethod.GET)
	public void newPage(String id,String url, String opt, HttpSession session) {
		session.setAttribute("url", url);
		session.setAttribute("opt", opt);
		System.out.println(opt);
		//		String uploadFolder = "C:\\Users\\master\\Desktop\\sp\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		String uploadFolder = "D:\\01-STUDY\\e\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		File uploadPath = new File(uploadFolder, url);
		if(!uploadPath.exists()) {
			System.out.println(url+" Folder created");
			uploadPath.mkdirs();
		}else {
			System.out.println(url+" Folder already exists");
		}
		File home = new File(uploadFolder+"\\"+url+"\\home.jsp");
		File signup = new File(uploadFolder+"\\"+url+"\\signup.jsp");
		File product = new File(uploadFolder+"\\"+url+"\\product.jsp");
		File board = new File(uploadFolder+"\\"+url+"\\board.jsp");
		try {
			if(home.createNewFile()) {
				System.out.println("home File created");
				FileWriter fw = new FileWriter(home);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
						"    pageEncoding=\"UTF-8\"%>\r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
						"<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>"+url+" home</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"		<input type='hidden' value='${url}' id='url'>"+
						"    <div id=\"btns\">\r\n" + 
						"        <input type=\"button\" value=\"저장\" id=\"save\">\r\n" + 
						"        <input type=\"button\" value=\"불러오기\" id=\"load\">\r\n" +
						"    </div>\r\n" + 
						"    <div id=\"main_entry\">\r\n" + 
						"        <div id=\"header\">\r\n" + 
						"            <div>\r\n" + 
						"                <input type=\"hidden\" id=\"header_style\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_margin\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_button\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_text\">\r\n" + 
						"            </div>\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"main_content\"></div>\r\n" + 
						"        <div id=\"footer\"><input type=\"hidden\" id=\"footer_style\"></div>\r\n" + 
						"        \r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"controller\">\r\n" + 
						"        <div id=\"target_name\"></div>\r\n" + 
						"        <div class=\"controllers bgcolors colors\" id=\"background-color\">배경 색</div>\r\n" + 
						"        <div class=\"controllers ftcolors colors\" id=\"color\">글자 색</div>\r\n" + 
						"        <div class=\"controllers\" id=\"font_size\">글자 크기</div>\r\n" + 
						"        <div class=\"controllers\" id=\"border\">테두리 설정</div>\r\n" + 
						"        <div class=\"controllers\" id=\"margin\">공백 설정</div>\r\n" + 
						"        <div class=\"controllers\" id=\"size\">크기 조절</div>\r\n" + 
						"        <div class=\"controllers\" id=\"place\">위치 조절</div>\r\n" + 
						"        <div class=\"controllers\" id=\"center\">가운데 정렬</div>\r\n" + 
						"        <div class=\"controllers\" id=\"create_element\">요소 추가</div>\r\n" + 
						"        <div class=\"controllers\" id=\"direction\">방향 정렬</div>\r\n" + 
						"        <div class=\"controllers\" id=\"inner_margin\">내부 공백</div>\r\n" + 
						"        <div class=\"controllers\" id=\"position\">포지션 설정</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"color_picker\" class=\"menus\">\r\n" + 
						"        <input type=\"text\" id=\"cp\" class=\"cp\">\r\n" + 
						"        <div id=\"color_change\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\" style=\"top: 0px; right: 0px\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"font_size_menu\" class=\"menus\">\r\n" + 
						"        <div>글자 크기 : <input type=\"text\" id=\"font_size_input\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"font_size_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"border_menu\" class=\"menus\">\r\n" + 
						"        <div>\r\n" + 
						"            위 테두리 : <input type=\"text\" id=\"border_top\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            아래 테두리 : <input type=\"text\" id=\"border_bottom\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            왼 테두리 : <input type=\"text\" id=\"border_left\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            오른 테두리 : <input type=\"text\" id=\"border_right\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            테두리 색 : <input type=\"text\" id=\"border_color\" class=\"cp\">\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            모서리 곡률 : <input type=\"text\" id=\"border_radius\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"border_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"margin_menu\" class=\"menus\">\r\n" + 
						"        <div>\r\n" + 
						"            위 공백 : <input type=\"text\" id=\"margin_top\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            아래 공백 : <input type=\"text\" id=\"margin_bottom\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            왼 공백 : <input type=\"text\" id=\"margin_left\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            오른 공백 : <input type=\"text\" id=\"margin_right\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"margin_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"size_menu\" class=\"menus\">\r\n" + 
						"        <div id=\"size_width_div\">가로 : <input type=\"text\" id=\"size_width\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_height_div\">세로 : <input type=\"text\" id=\"size_height\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_change\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\" style=\"top: 0px; right: 0px\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"place_menu\" class=\"menus\">\r\n" + 
						"        <div>가로 : <input type=\"text\" id=\"place_left\" size=\"5\">px</div>\r\n" + 
						"        <div>세로 : <input type=\"text\" id=\"place_top\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"place_change\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\" style=\"top: 0px; right: 0px\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"center_menu\" class=\"menus\">\r\n" + 
						"        <div class=\"center\" data-center=\"x\">가로 중앙</div>\r\n" + 
						"        <div class=\"center\" data-center=\"y\">세로 중앙</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"create_element_menu\" class=\"menus\">\r\n" + 
						"        <div class=\"elements\" id=\"create_home\">홈</div>\r\n" + 
						"        <div class=\"elements\" id=\"create_login\">로그인</div>\r\n" + 
						"        <div class=\"elements\" id=\"create_ul\">목록</div>\r\n" + 
						"        <div class=\"elements\" id=\"create_editable\">텍스트 상자</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"direction_menu\" class=\"menus\">\r\n" + 
						"            <div><input type=\"radio\" class=\"list_direction\" name=\"d\" value=\"inline-block\"><label>가로</label></div>\r\n" + 
						"            <div><input type=\"radio\" class=\"list_direction\" name=\"d\" value=\"list-item\"><label>세로</label></div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"inner_margin_menu\" class=\"menus\">\r\n" + 
						"        <div>\r\n" + 
						"            위 공백 : <input type=\"text\" id=\"inner_margin_top\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            아래 공백 : <input type=\"text\" id=\"inner_margin_bottom\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            왼 공백 : <input type=\"text\" id=\"inner_margin_left\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div>\r\n" + 
						"            오른 공백 : <input type=\"text\" id=\"inner_margin_right\" size=\"5\">px\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"inner_margin_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"position_menu\" class=\"menus\">\r\n" + 
						"        <div class='position' data-position='default'>\r\n" + 
						"            Default\r\n" + 
						"        </div>\r\n" + 
						"        <div class='position' data-position='fixed'>\r\n" + 
						"            Fixed\r\n" + 
						"        </div>\r\n" + 
						"        <div class='position' data-position='sticky'>\r\n" + 
						"            Sticky\r\n" + 
						"        </div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"body_controller_btn\"></div>\r\n" + 
						"    <div id=\"body_controller\">\r\n" + 
						"        <div id=\"margin_controller_btn\" class=\"controllers\">전체 영역 공백</div>\r\n" + 
						"        <div id=\"button_controller_btn\" class=\"controllers input_controller_btn\" data-type=\"button\">버튼</div>\r\n" + 
						"        <div id=\"text_controller_btn\" class=\"controllers input_controller_btn\" data-type=\"text\">입력창</div>\r\n" + 
						"        <div id=\"font_controller_btn\" class=\"controllers\">글꼴</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"margin_controller\">\r\n" + 
						"        <table>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>위측 공백</td>\r\n" + 
						"                <td><input type=\"text\" id=\"body_top\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>아래측 공백</td>\r\n" + 
						"                <td><input type=\"text\" id=\"body_bottom\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>좌측 공백</td>\r\n" + 
						"                <td><input type=\"text\" id=\"body_left\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>우측 공백</td>\r\n" + 
						"                <td><input type=\"text\" id=\"body_right\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"        </table>\r\n" + 
						"        <div id=\"entry_margin_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"input_controller\">\r\n" + 
						"        <table>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>글자 크기</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_ftsize\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>안쪽 여백</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_padding\" size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>테두리 크기</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_bdwidth\"size=\"5\">px</td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>테두리 색</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_bdcolor\" class=\"cp\" size=\"5\"></td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>배경 색</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_bgcolor\" class=\"cp\" size=\"5\"></td>\r\n" + 
						"            </tr>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>글자 색</td>\r\n" + 
						"                <td><input type=\"text\" id=\"input_ftcolor\" class=\"cp\" size=\"5\"></td>\r\n" + 
						"            </tr>\r\n" + 
						"        </table>\r\n" + 
						"        \r\n" + 
						"        <div id=\"entry_input_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>\r\n" + 
						"    <input type='file' id='upload_input' style='display:none'>\r\n" + 
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"    <script src=\"../resources/js/url_home.js\"></script>\r\n" + 
						"</body>"+
						"</html>");
				bw.close();
			}else {
				System.out.println("home File already exists");
			}
			if(signup.createNewFile()) {
				System.out.println("signup File created");
				FileWriter fw = new FileWriter(signup);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
						"    pageEncoding=\"UTF-8\"%>\r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
						"<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>"+url+" home</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_signup.css\">\r\n" +
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
						"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"<input type='hidden' value='${url}' id='url'>"+
						"<div id=\"btns\">\r\n" + 
						"        <input type=\"button\" value=\"저장\" id=\"save\">\r\n" + 
						"        <input type=\"button\" value=\"불러오기\" id=\"load\">\r\n" + 
						"    </div>\r\n" + 
						"    <div id=\"signup_entry\">\r\n" + 
						"        <div id=\"header\"></div>\r\n" + 
						"        <div id='signup_content'>\r\n" + 
						"            <div id='sign_div'>\r\n" + 
						"                <form id='sign_form'>\r\n" + 
						"                    <table id='sign_table'>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td style='width:150px;'><span class='modi_span' id='id_span'>ID</span></td>\r\n" + 
						"                            <td style='width:650px;'><input class='modi_input' type='text' name='id' id='id' required\r\n" + 
						"                                    data-able='f'><input type='button' value='중복확인' id='dup_check'><span\r\n" + 
						"                                    class='id check_span'></span></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td><span class='modi_span' id='pw_span'>PASSWORD</span></td>\r\n" + 
						"                            <td><input class='modi_input' type='password' name='password' id='pw' required\r\n" + 
						"                                    data-able='f'><span class='pw check_span'></span></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td><span class='modi_span' id='pwc_span'>PASSWORD CHECK</span></td>\r\n" + 
						"                            <td><input class='modi_input' type='password' id='pw_check' required><span\r\n" + 
						"                                    class='pw_check check_span'></span></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                        <tr id='before'>\r\n" + 
						"                            <td><img id='sign_img'\r\n" + 
						"                                    src='https://icons-for-free.com/download-icon-circle+more+plus+icon-1320183136549593898_512.png'>\r\n" + 
						"                            </td>\r\n" + 
						"                            <td><input type='submit' value='가입하기' id='sign_submit'></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                    </table>\r\n" + 
						"                </form>\r\n" + 
						"            </div>\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"footer\"></div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id='sign_element'>\r\n" + 
						"        <div class='sign_element' id='sign_name'>이름</div>\r\n" + 
						"        <div class='sign_element' id='sign_mail'>이메일</div>\r\n" + 
						"        <div class='sign_element' id='sign_phone'>전화번호</div>\r\n" + 
						"        <div class='sign_element' id='sign_birth'>생년월일</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id='modify_span'>\r\n" + 
						"        <div class='modify_span' id='ss_modify'>수정</div>\r\n" + 
						"    </div>\r\n" + 
						"    <div id='modify_input'>\r\n" + 
						"        <div class='modify_input' id='reg'>정규식 설정\r\n" + 
						"            <div class='reg' id='reg_free'>모두 허용</div>\r\n" + 
						"            <div class='reg' id='reg_str'>문자</div>\r\n" + 
						"            <div class='reg' id='reg_num'>숫자</div>\r\n" + 
						"            <div class='reg' id='reg_spe'>특수문자</div>\r\n" + 
						"        </div>\r\n" + 
						"        <div class='modify_input' id='len'>글자 수 설정</div>\r\n" + 
						"    </div>\r\n" + 
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"    <script src=\"../resources/js/url_signup.js\"></script>"+
						"</body>"+
						"</html>");
				bw.close();
			}else {
				System.out.println("signup File already exists");
			}
			if(opt.equals("shopping")) {
				System.out.println("hahaha");
				if(product.createNewFile()) {
					System.out.println("product File created");
					FileWriter fw = new FileWriter(product);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" product</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_product.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>"+
							"<input type='hidden' value='${url}' id='url'>"+
							"	<div id='product_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='product_content'>\r\n"+
							"			<div>\r\n"+
							"				<select id=\"dataPerPage\">\r\n" + 
							"	                 <option value=\"10\">10개씩보기</option>\r\n" + 
							"		   	         <option value=\"15\">15개씩보기</option>\r\n" + 
							"       		     <option value=\"20\">20개씩보기</option>\r\n" + 
							"        		</select>\r\n" + 
							"   	     	<table id=\"dataTableBody\">\r\n" + 
							"    			\r\n" + 
							"        		</table>\r\n" + 
							"        		<ul id=\"pagingul\">\r\n" + 
							"        		</ul>\r\n"+
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_product.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();
					String create_product_table = "create table product_"+url+" ("
						+ "pno int primary key, "
						+ "pname varchar(100) not null,"
						+ "price int not null,"
						+ "content longtext,"
						+ "quantity int default 1,"
						+ "s_quantity int default 0,"
						+ "reg_date datetime default now(),"
						+ "type int"
						+ ")";
					es.createTable(create_product_table);
					
					String create_product_type_table = "create table product_type_"+url+" ("
						+ "type int primary key,"
						+ "tname varchar(100) not null"
						+ ")";
					es.createTable(create_product_type_table);
					
					String create_product_img_table = "create table product_img_"+url+" ("
							+ "pno int,"
							+ "fullpath varchar(300)"
							+ ")";
						es.createTable(create_product_img_table);
				}else {
					System.out.println("product File already exists");
				}
			}else if(opt.equals("community")) {
				if(board.createNewFile()) {
					System.out.println("board File created");
					FileWriter fw = new FileWriter(board);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" board</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_board.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>"+
							"<input type='hidden' value='${url}' id='url'>"+
							"	<div id='board_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='board_content'></div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_board.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();
					String create_board_table = "create table board_"+url+" ("
						+ "bno int primary key, "
						+ "bname varchar(100) not null,"
						+ "content longtext,"
						+ "reg_date datetime default now(),"
						+ "type int"
						+ ")";
					es.createTable(create_board_table);
						
					String create_board_type_table = "create table board_type_"+url+" ("
						+ "type int primary key,"
						+ "tname varchar(100) not null"
						+ ")";
					es.createTable(create_board_type_table);
				}else {
					System.out.println("board File already exists");
				}
			}
			String create_member_table = "create table member_"+url+" ("
					+ "id varchar(100) primary key, "
					+ "pw varchar(100) not null,"
					+ "name varchar(100),"
					+ "email varchar(100),"
					+ "phone varchar(100),"
					+ "birth varchar(100),"
					+ "sign_date datetime default now(),"
					+ "admin boolean default false"
					+ ")";
			es.createTable(create_member_table);
			MemberVO mvo = new MemberVO();
			mvo.setId(url);
			mvo.setPw(url);
			es.createFirstAccount(mvo);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/loadpage", method = RequestMethod.GET)
	public void loadPage(HttpSession session, String url, String opt) {
		session.setAttribute("url",url);
		session.setAttribute("opt",opt);
	}

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public void management(HttpSession session) {
		String url = (String)session.getAttribute("url");
	}

	@RequestMapping(value = "/{url}/home", method = RequestMethod.GET)
	public void urlHome() {

	}

	@RequestMapping(value = "/{url}/signup", method = RequestMethod.GET)
	public void urlSignUp() {

	}
	
	@RequestMapping(value = "/{url}/product", method = RequestMethod.GET)
	public void urlProduct() {

	}
	
	@RequestMapping(value = "/{url}/board", method = RequestMethod.GET)
	public void urlBoard() {

	}

	@RequestMapping(value = "/savecontent", method = RequestMethod.POST)
	public ResponseEntity<String> saveContent(@RequestBody ContentVO cvo){
		int result = es.saveContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/modifycontent", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyContent(@RequestBody ContentVO cvo){
		int result = es.modifyContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/deletecontent", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteContent(@RequestBody ContentVO cvo){
		//String uploadFolder = "C:\\Users\\master\\Desktop\\sp\\test\\src\\main\\webapp\\WEB-INF\\views";
		String uploadFolder = "D:\\01-STUDY\\e\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		File file = new File(uploadFolder+"\\"+cvo.getUrl());
		if( file.exists() ){ 

			if(file.isDirectory()){ 

				File[] files = file.listFiles();

				for( int i=0; i<files.length; i++){
					if( files[i].delete() ){
						System.out.println(files[i].getName()+" jsp삭제성공");
					}else{
						System.out.println(files[i].getName()+" jsp삭제실패");
					}
				}

			}
			if(file.delete()){
				System.out.println("jsp파일삭제 성공");
			}else{
				System.out.println("jsp파일삭제 실패");
			}

		}else{
			System.out.println("jsp파일이 존재하지 않습니다.");
		}
		try {
			String target = "member_"+cvo.getUrl();
			es.dropTable(target);
			String target1 = "product_"+cvo.getUrl();
			String target2 = "product_type_"+cvo.getUrl();
			String target3 = "board_"+cvo.getUrl();
			String target4 = "board_type_"+cvo.getUrl();
			es.dropTable(target1);
			es.dropTable(target2);
			es.dropTable(target3);
			es.dropTable(target4);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		int result = es.deleteContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/loadcontent", method = RequestMethod.GET)
	public ResponseEntity<ContentVO> loadContent(ContentVO cvo){

		return new ResponseEntity<>(es.loadContent(cvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dupcheck", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> dupCheck(MemberVO mvo, HttpSession session) {
		String url = (String)session.getAttribute("url");
		mvo.setUrl(url);
		return new ResponseEntity<>(es.dupCheck(mvo),HttpStatus.OK);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody MemberVO mvo) {
		
		int result = es.signUp(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> login(MemberVO mvo, HttpSession session) {
		String url = (String)session.getAttribute("url");
		mvo.setSign_date(url);
		session.setAttribute("userInfo", es.login(mvo));
		return new ResponseEntity<>(es.login(mvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ProductVO>> productList(ProductVO pvo) {
		return new ResponseEntity<>(es.productList(pvo),HttpStatus.OK);
	}
}
