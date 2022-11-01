package org.ewb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.CartVO;
import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.MemberVO;
import org.ewb.model.OrderVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;
import org.ewb.service.EWBService;
import org.ewb.model.PageVO;
import org.ewb.model.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		File login = new File(uploadFolder+"\\"+url+"\\login.jsp");
		File product = new File(uploadFolder+"\\"+url+"\\product.jsp");
		File productWrite = new File(uploadFolder+"\\"+url+"\\productwrite.jsp");
		File productDetail = new File(uploadFolder+"\\"+url+"\\productdetail.jsp");
		File modifyproduct = new File(uploadFolder+"\\"+url+"\\modifyproduct.jsp");
		File cart = new File(uploadFolder+"\\"+url+"\\cart.jsp");
		File order = new File(uploadFolder+"\\"+url+"\\order.jsp");
		File orderlist = new File(uploadFolder+"\\"+url+"\\orderlist.jsp");
		File mypage = new File(uploadFolder+"\\"+url+"\\mypage.jsp");
		File modifyprofile = new File(uploadFolder+"\\"+url+"\\modifyprofile.jsp");
		File board = new File(uploadFolder+"\\"+url+"\\board.jsp");
		try {
			if(home.createNewFile()) {
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
				System.out.println("home File created");
				FileWriter fw = new FileWriter(home);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
						"    pageEncoding=\"UTF-8\"%>\r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
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
						"		<input type='hidden' value='${userId}' id='user_id'>"+
						"		<input type='hidden' value='${url}' id='url'>"+
						"		<input type='hidden' value='${opt}' id='opt'>"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>"+
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
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
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
						"			<input type='hidden' id='signup_style'>\r\n"+
						"            <div id='sign_div'>\r\n" + 
						"                <form id='sign_form'>\r\n" + 
						"                    <table id='sign_table'>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td style='width:150px;'><span class='modi_span' id='id_span'>ID</span></td>\r\n" + 
						"                            <td style='width:650px;'><input class='modi_input input' type='text' name='id' id='id' required\r\n" + 
						"                                    data-able='f'><input type='button' value='중복확인' id='dup_check'><span\r\n" + 
						"                                    class='id check_span'></span></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td><span class='modi_span' id='pw_span'>PASSWORD</span></td>\r\n" + 
						"                            <td><input class='modi_input input' type='password' name='password' id='pw' required\r\n" + 
						"                                    data-able='f'><span class='pw check_span'></span></td>\r\n" + 
						"                        </tr>\r\n" + 
						"                        <tr>\r\n" + 
						"                            <td><span class='modi_span' id='pwc_span'>PASSWORD CHECK</span></td>\r\n" + 
						"                            <td><input class='modi_input input' type='password' id='pw_check' required><span\r\n" + 
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
						"	<div id=\"controller\">\r\n" + 
						"        <div id=\"target_name\"></div>\r\n" + 
						"        <div class=\"controllers bgcolors colors\" id=\"background-color\">배경 색</div>\r\n" + 
						"        <div class=\"controllers ftcolors colors\" id=\"color\">글자 색</div>\r\n" + 
						"        <div class=\"controllers\" id=\"font_size\">글자 크기</div>\r\n" + 
						"        <div class=\"controllers\" id=\"border\">테두리 설정</div>\r\n" + 
						"        <div class=\"controllers\" id=\"size\">크기 조절</div>\r\n" + 
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
						"    <div id=\"size_menu\" class=\"menus\">\r\n" + 
						"        <div id=\"size_width_div\">가로 : <input type=\"text\" id=\"size_width\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_height_div\">세로 : <input type=\"text\" id=\"size_height\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_change\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\" style=\"top: 0px; right: 0px\">✖</div>\r\n" + 
						"    </div>"+
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"    <script src=\"../resources/js/url_signup.js\"></script>"+
						"</body>"+
						"</html>");
				bw.close();
			}else {
				System.out.println("signup File already exists");
			}
			if(login.createNewFile()) {
				System.out.println("login File created");
				FileWriter fw = new FileWriter(login);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
						"    pageEncoding=\"UTF-8\"%>\r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
						"<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>"+url+" login</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_login.css\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
						"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"		<input type='hidden' value='${userId}' id='user_id'>"+
						"		<input type='hidden' value='${url}' id='url'>"+
						"		<input type='hidden' value='${opt}' id='opt'>"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>"+
						"    <div id=\"btns\">\r\n" + 
						"        <input type=\"button\" value=\"저장\" id=\"save\">\r\n" + 
						"        <input type=\"button\" value=\"불러오기\" id=\"load\">\r\n" +
						"    </div>\r\n" + 
						"    <div id=\"login_entry\">\r\n" + 
						"        <div id=\"header\">\r\n" + 
						"            <div>\r\n" + 
						"                <input type=\"hidden\" id=\"header_style\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_margin\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_button\">\r\n" + 
						"                <input type=\"hidden\" id=\"save_text\">\r\n" + 
						"            </div>\r\n" + 
						"        </div>\r\n" + 
						"        <div id=\"login_content\">\r\n"+
						"			<input type='hidden' id='login_style'>\r\n"+
						"            <div id=\"login_box\">\r\n" + 
						"                <table id=\"login_table\">\r\n" + 
						"                    <tr>\r\n" + 
						"                        <td>\r\n" + 
						"                            <span class=\"modi_span\" data-target=\"id\">ID</span>\r\n" + 
						"                        </td>\r\n" + 
						"                        <td>\r\n" + 
						"                            <input type=\"text\" id=\"input_id\" class=\"input\">\r\n" + 
						"                        </td>\r\n" + 
						"                    </tr>\r\n" + 
						"                    <tr>\r\n" + 
						"                        <td>\r\n" + 
						"                            <span class=\"modi_span\" data-target=\"pw\">PASSWORD</span>\r\n" + 
						"                        </td>\r\n" + 
						"                        <td>\r\n" + 
						"                            <input type=\"password\" id=\"input_pw\" class=\"input\">\r\n" + 
						"                        </td>\r\n" + 
						"                    </tr>\r\n" + 
						"                    <tr>\r\n" + 
						"                        <td colspan=\"2\" style=\"text-align:center\">\r\n" + 
						"                            <input type=\"button\" value=\"Log In\" id=\"login_btn\"><br>\r\n" +
						"                            <input type=\"button\" value=\"Sign Up\" id=\"signup_btn\">\r\n" +
						"                        </td>\r\n" + 
						"                    </tr>\r\n" + 
						"                </table>\r\n" + 
						"            </div>\r\n" + 
						"        </div>"+
						"        <div id=\"footer\"><input type=\"hidden\" id=\"footer_style\"></div>\r\n" + 
						"        \r\n" + 
						"    </div>\r\n" +
						"	<div id=\"controller\">\r\n" + 
						"        <div id=\"target_name\"></div>\r\n" + 
						"        <div class=\"controllers bgcolors colors\" id=\"background-color\">배경 색</div>\r\n" + 
						"        <div class=\"controllers ftcolors colors\" id=\"color\">글자 색</div>\r\n" + 
						"        <div class=\"controllers\" id=\"font_size\">글자 크기</div>\r\n" + 
						"        <div class=\"controllers\" id=\"border\">테두리 설정</div>\r\n" + 
						"        <div class=\"controllers\" id=\"size\">크기 조절</div>\r\n" + 
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
						"    <div id=\"size_menu\" class=\"menus\">\r\n" + 
						"        <div id=\"size_width_div\">가로 : <input type=\"text\" id=\"size_width\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_height_div\">세로 : <input type=\"text\" id=\"size_height\" size=\"5\">px</div>\r\n" + 
						"        <div id=\"size_change\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\" style=\"top: 0px; right: 0px\">✖</div>\r\n" + 
						"    </div>"+
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"    <script src=\"../resources/js/url_login.js\"></script>\r\n" + 
						"</body>"+
						"</html>");
				bw.close();
			}else {
				System.out.println("login File already exists");
			}
			if(opt.equals("shopping")) {
				if(product.createNewFile()) {
					System.out.println("product File created");
					FileWriter fw = new FileWriter(product);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
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
							"<input type='hidden' value='${opt}' id='opt'>"+
							"<input type='hidden' value='${userId}' id='user_id'>"+
							"	<div id='product_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='product_content'>\r\n"+
							"			<div id='product_div'>\r\n"+
							"				<c:if test='${userInfo.admin eq true}'>\r\n"+
							"				<div id='add_product'>상품 추가</div>\r\n"+
							"				</c:if>\r\n"+
							"				<select id=\"dataPerPage\" value=\"${paging.cri.amount}\">\r\n" + 
							"	                 <option value=\"15\">15개씩보기</option>\r\n" + 
							"		   	         <option value=\"30\">30개씩보기</option>\r\n" + 
							"       		     <option value=\"45\">45개씩보기</option>\r\n" +
							"       		     <option value=\"60\">60개씩보기</option>\r\n" +
							"        		</select>\r\n" + 
							"				<div id='table_div'>\r\n"+
							"				<c:forEach var=\"list\" items=\"${product}\">\r\n" + 
							"				<table class='product_table'>\r\n" + 
							"					<tr>\r\n" + 
							"						<td style='position:relative'><a href=\"/"+url+"/productdetail?pno=${list.pno}\">"+
							"						<c:choose>\r\n"+
							"						<c:when test=\"${fn:length(list.tvo.fullpath) ne 0}\">\r\n"+
							"							<img src='/display?fileName=${list.tvo.fullpath}' class='p_imgs'>\r\n"+
							"						</c:when>\r\n"+
							"						<c:otherwise>\r\n"+
							"							<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>\r\n"+
							"						</c:otherwise>\r\n"+
							"						</c:choose>\r\n"+
							"						<c:if test=\"${list.quantity eq 0}\">\r\n"+
							"						<div class='sold_out' id='so_${list.pno}'>sold out</div>\r\n"+
							"						</c:if>\r\n"+
							"						</a></td>\r\n" +
							"					</tr>\r\n"+
							"					<tr>\r\n"+
							"						<td><a href=\"/"+url+"/productdetail?pno=${list.pno}\">${list.pname}</a></td>\r\n" +
							"					</tr>\r\n"+
							"					<tr>\r\n"+
							"						<td>${list.price}원</td>\r\n" + 
							"					</tr>\r\n" + 
							"				</table>\r\n" + 
							"				</c:forEach>\r\n" + 
							"				</div>\r\n"+
							"			<br>\r\n" + 
							"			<form action=\"/"+url+"/product\" id=\"search_form\">\r\n" + 
							"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\"> <input\r\n" + 
							"					type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\"> <select\r\n" + 
							"					name=\"type\">\r\n" + 
							"					<option value=\"t\">제목</option>\r\n" + 
							"					<option value=\"c\">내용</option>\r\n" + 
							"					<option value=\"tc\">제목+내용</option>\r\n" + 
							"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\"> <input type=\"submit\"\r\n" + 
							"					value=\"찾기\">\r\n" + 
							"			</form>\r\n" + 
							"			<br> <br> <div id='paging'><a\r\n" + 
							"				href=\"/"+url+"/product?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
							"			<c:if test=\"${paging.prev}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/"+url+"/product?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"				var=\"num\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/"+url+"/product?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
							"			</c:forEach>\r\n" + 
							"			<c:if test=\"${paging.next}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/"+url+"/product?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<a\r\n" + 
							"				href=\"/"+url+"/product?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a></div>"+
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
							+ "pno int auto_increment primary key, "
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
							+ "fullpath varchar(300),"
							+ "foreign key(pno)"
							+ "references product_"+url+"(pno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_product_img_table);

					String create_cart_table = "create table cart_"+url+" ("
							+ "id varchar(100),"
							+ "pno int,"
							+ "b_quantity int,"
							+ "add_date datetime default now(),"
							+ "doOrder boolean default false,"
							+ "foreign key(pno)"
							+ "references product_"+url+"(pno) on update cascade on delete cascade,"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_cart_table);

					String create_payment_table = "create table payment_"+url+" ("
							+ "id varchar(100),"
							+ "payno int auto_increment primary key,"
							+ "price int,"
							+ "name varchar(100),"
							+ "address varchar(100),"
							+ "phone varchar(100),"
							+ "memo varchar(100),"
							+ "payment_date datetime default now(),"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_payment_table);

					String create_order_table = "create table order_"+url+" ("
							+ "id varchar(100),"
							+ "ono int auto_increment primary key,"
							+ "pno int,"
							+ "payno int,"
							+ "b_quantity int,"
							+ "order_date datetime default now(),"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade,"
							+ "foreign key(payno)"
							+ "references payment_"+url+"(payno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_order_table);
					
					String create_review_table = "create table review_"+url+" ("
							+ "id varchar(100),"
							+ "rno int auto_increment primary key,"
							+ "pno int,"
							+ "content longtext,"
							+ "review_date datetime default now(),"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade,"
							+ "foreign key(pno)"
							+ "references product_"+url+"(pno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_review_table);
					
					String create_review_img_table = "create table review_img_"+url+" ("
							+ "rno int,"
							+ "pno int,"
							+ "fullpath varchar(300),"
							+ "foreign key(pno)"
							+ "references product_"+url+"(pno) on update cascade on delete cascade,"
							+ "foreign key(rno)"
							+ "references review_"+url+"(rno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_review_img_table);
					
				}else {
					System.out.println("product File already exists");
				}
				if(productWrite.createNewFile()) {
					System.out.println("product write File created");
					FileWriter fw = new FileWriter(productWrite);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" product write</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_product_write.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>"+
							"<input type='hidden' value='${url}' id='url'>"+
							"<input type='hidden' value='${opt}' id='opt'>"+
							"<input type='hidden' value='${userId}' id='user_id'>"+
							"	<div id='product_write_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='product_write_content'>\r\n"+
							"			<div id=\"container\">\r\n" + 
							"				<label>상품명</label><input type='text' id='pname'>\r\n"+
							"				<label>가격</label><input type='text' id='price'>\r\n"+
							"				<label>수량</label><input type='text' id='quantity'>\r\n"+
							"				<label>섬네일</label><div contenteditable='true' id='thumbnail'></div><div id='thumb_btn'>섬네일 넣기</div><input type='file' id='thumb_file'>\r\n"+
							"        		<label>상세내용</label><div contenteditable='true' id=\"content\"></div>\r\n" +
							"				<input type='file' id='insert_img' multiple>\r\n"+
							"				<div id='insert_btn'>이미지 넣기</div>\r\n"+
							"        		<input type=\"button\" value=\"작성하기\" id=\"write_btn\">\r\n" + 
							"    		</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" +
							"    <script src=\"../resources/js/url_product_write.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("product write File already exists");
				}
				if(productDetail.createNewFile()) {
					System.out.println("product detail File created");
					FileWriter fw = new FileWriter(productDetail);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" product detail</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_product_detail.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"<input type='hidden' value='${detail.quantity}' id='product_quantity'>\r\n"+
							"<input type='hidden' value='${detail.pno}' id='product_pno'>\r\n"+
							"	<div id='product_detail_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='product_detail_content'>\r\n"+
							"			<div id='product_img'>\r\n"+
							"			<c:choose>\r\n"+
							"			<c:when test=\"${fn:length(detail.tvo.fullpath) ne 0}\">\r\n"+
							"				<img src='/display?fileName=${detail.tvo.fullpath}' class='p_imgs'>\r\n"+
							"			</c:when>\r\n"+
							"			<c:otherwise>\r\n"+
							"				<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>\r\n"+
							"			</c:otherwise>\r\n"+
							"			</c:choose>\r\n"+
							"			</div>\r\n"+
							"			<table id='product_table'>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						${detail.pname}\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						가격 : ${detail.price}\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						수량 : <input type='button' value='🔻' id='quan_down' class='quan'><input type='text' id='quan' size='2' readonly value='1' data-price='${detail.price}'><input type='button' value='🔺' id='quan_up' class='quan'>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						합계 : <span id='multi_price'></span>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						<input type='button' value='주문하기' id='order_btn'><input type='button' value='장바구니' id='cart_btn'>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</table>\r\n"+
							"			<div id='product_content'>${detail.content}</div>\r\n"+
							"			<div id='review_div'>\r\n" + 
							"				<textarea placeholder=\"리뷰\" id=\"review_text\" required></textarea><br>\r\n" + 
							"				<div contenteditable=\"false\" id=\"review_img_area\"></div><br>\r\n" + 
							"				<input type=\"button\" value=\"사진첨부\" id=\"review_img_btn\"><br>\r\n" + 
							"				<input type=\"file\" id=\"review_img_file\">\r\n" + 
							"				<input type=\"button\" value=\"작성하기\" id=\"review_btn\">\r\n" + 
							"				<div id=\"reviews\">\r\n" + 
							"					<table id=\"review_table\">\r\n" + 
							"					\r\n" + 
							"					</table>\r\n" + 
							"					<ul id=\"pagingul\">\r\n" + 
							"    				</ul>\r\n" + 
							"				</div>\r\n"+ 
							"			</div>"+
							"			<c:if test=\"${fn:contains(userInfo.admin,true)}\">\r\n"+
							"			<div id='mr'>\r\n"+
							"				<div id='modify'>수정</div>\r\n"+
							"				<div id='remove'>삭제</div>\r\n"+
							"			</div>\r\n"+
							"			</c:if>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_product_detail.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("product detail File already exists");
				}
				
				if(modifyproduct.createNewFile()) {
					System.out.println("modifyproduct write File created");
					FileWriter fw = new FileWriter(modifyproduct);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" modify product</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_modifyproduct.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>"+
							"<input type='hidden' value='${url}' id='url'>"+
							"<input type='hidden' value='${opt}' id='opt'>"+
							"<input type='hidden' value='${modify.pno}' id='pno'>"+
							"<input type='hidden' value='${userId}' id='user_id'>"+
							"	<div id='modifyproduct_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='modifyproduct_content'>\r\n"+
							"			<div id=\"container\">\r\n" + 
							"				<label>상품명</label><input type='text' id='pname' value='${modify.pname}'>\r\n"+
							"				<label>가격</label><input type='text' id='price' value='${modify.price}'>\r\n"+
							"				<label>수량</label><input type='text' id='quantity' value='${modify.quantity}'>\r\n"+
							"				<label>섬네일</label><div contenteditable='true' id='thumbnail'><img src='/display?fileName=${modify.tvo.fullpath}'></div><div id='thumb_btn'>섬네일 넣기</div><input type='file' id='thumb_file'>\r\n"+
							"        		<label>상세내용</label><div contenteditable='true' id=\"content\">${modify.content}</div>\r\n" +
							"				<input type='file' id='insert_img' multiple>\r\n"+
							"				<div id='insert_btn'>이미지 넣기</div>\r\n"+
							"        		<input type=\"button\" value=\"수정하기\" id=\"modi_btn\">\r\n" + 
							"    		</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" +
							"    <script src=\"../resources/js/url_modifyproduct.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("modifyproduct write File already exists");
				}
				
				if(cart.createNewFile()) {
					System.out.println("cart File created");
					FileWriter fw = new FileWriter(cart);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" cart</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_cart.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='cart_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='cart_content'>\r\n"+
							"			<table id='cart_table'>\r\n"+
							"			<c:forEach var=\"cart\" items=\"${cart}\">\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						<input type='checkbox' class='cart_check' data-pno='${cart.pno}' checked>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"<c:choose>"
							+ "<c:when test=\"${fn:length(cart.pvo.tvo.fullpath) ne 0}\">"
							+ "<img src='/display?fileName=${cart.pvo.tvo.fullpath}' class='p_imgs'>"
							+ "</c:when>"
							+ "<c:otherwise>"
							+ "<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>"
							+ "</c:otherwise>"
							+ "</c:choose>"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>${cart.pvo.pname}</p>\r\n"+
							"						<p>수량 : <input type='button' value='🔻'  class='quan' data-pno='${cart.pno}' data-val='down'><input type='text' value='${cart.b_quantity}' id='quan_${cart.pno}' data-price='${cart.pvo.price}' data-quantity='${cart.pvo.quantity}'><input type='button' value='🔺'  class='quan' data-pno='${cart.pno}' data-val='up'></p>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>가격 : <span id='price_${cart.pno}' class='prices'>${cart.pvo.price*cart.b_quantity}</span> 원</p>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<input type='button' class='remove_btn' data-pno='${cart.pno}' value='❌'>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</c:forEach>\r\n"+
							"				<tr>\r\n"+
							"					<td colspan='4'>\r\n"+
							"						<p><span id='f_price'></span> 원</p>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</table>\r\n"+
							"			<div id='order_all_btn'>전체 주문하기</div>\r\n"+
							"			<div id='order_selected_btn'>선택 주문하기</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_cart.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("cart File already exists");
				}

				if(order.createNewFile()) {
					System.out.println("order File created");
					FileWriter fw = new FileWriter(order);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" order</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_order.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='order_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='order_content'>\r\n"+
							"			<div id='order_des'>\r\n"+
							"				<input type='text' id='name' placeholder='이름'><br>\r\n"+
							"				<input type='text' id='address' placeholder='주소'><br>\r\n"+
							"				<input type='text' id='phone' placeholder='연락처'><br>\r\n"+
							"				<input type='text' id='memo' placeholder='메모'><br>\r\n"+
							"			</div>\r\n"+
							"			<table id='order_table'>\r\n"+
							"			<c:forEach var=\"order\" items=\"${order}\">\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"<c:choose>"
							+ "<c:when test=\"${fn:length(order.pvo.tvo.fullpath) ne 0}\">"
							+ "<img src='/display?fileName=${order.pvo.tvo.fullpath}' class='p_imgs'>"
							+ "</c:when>"
							+ "<c:otherwise>"
							+ "<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>"
							+ "</c:otherwise>"
							+ "</c:choose>"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>${order.pvo.pname}</p>\r\n"+
							"						<p>수량 : <input type='text' value='${order.b_quantity}' id='quan_${order.pno}' data-price='${order.pvo.price}'></p>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>가격 : <span id='price_${order.pno}' class='prices'>${order.pvo.price*order.b_quantity}</span> 원</p>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</c:forEach>\r\n"+
							"				<tr>\r\n"+
							"					<td colspan='4'>\r\n"+
							"						<p><span id='f_price'></span> 원</p>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</table>\r\n"+
							"			<div id='pay_btn'>결제하기</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_order.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("order File already exists");
				}
				
				if(orderlist.createNewFile()) {
					System.out.println("orderlist File created");
					FileWriter fw = new FileWriter(orderlist);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" order list</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_orderlist.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='orderlist_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='orderlist_content'>\r\n"+
							"			<div id='orderlist_div'>\r\n"+
							"				<table id='orderlist_table'>\r\n"+
							"				<c:forEach items=\"${orderlist}\" var=\"orderlist\">\r\n"+
							"					<tr id=\"payno_${orderlist.payno}\">\r\n" + 
							"						<td class=\"orderlist_td\" data-payno=\"${orderlist.payno}\">\r\n" + 
							"							${orderlist.price}원<br>\r\n" + 
							"							${orderlist.payment_date}\r\n" + 
							"						</td>\r\n" +
							"					</tr>\r\n"+
							"				</c:forEach>\r\n"+
							"				</table>\r\n"+
							"			<br> <br> <div id='paging'><a\r\n" + 
							"				href=\"/aaa/orderlist?pageNum=1&amount=${paging.cri.amount}\">처음으로</a>\r\n" + 
							"			<c:if test=\"${paging.prev}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/aaa/orderlist?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}\">이전</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"				var=\"num\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/aaa/orderlist?pageNum=${num}&amount=${paging.cri.amount}\">${num}</a>\r\n" + 
							"			</c:forEach>\r\n" + 
							"			<c:if test=\"${paging.next}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/aaa/orderlist?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}\">다음</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<a\r\n" + 
							"				href=\"/aaa/orderlist?pageNum=${paging.realEnd}&amount=${paging.cri.amount}\">맨끝으로</a></div>			</div>"+
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_orderlist.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("orderlist File already exists");
				}
				
				if(mypage.createNewFile()) {
					System.out.println("mypage File created");
					FileWriter fw = new FileWriter(mypage);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" ${userInfo.id} mypage</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_mypage.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='mypage_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='mypage_content'>\r\n"+
							"			<table id='mypage_table'>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/modifyprofile'>정보수정</a>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/cart'>장바구니</a>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/orderlist'>주문내역</a>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</table>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_mypage.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("mypage File already exists");
				}
				
				if(modifyprofile.createNewFile()) {
					System.out.println("modifyprofile File created");
					FileWriter fw = new FileWriter(modifyprofile);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" ${userInfo.id} modify profile</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_modifyprofile.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='modifyprofile_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='modifyprofile_content'>\r\n"+
							"			<div id=\"modifyprofile_div\">\r\n" + 
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>비밀번호 수정</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"password\" id=\"pw\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>비밀번호 수정 확인</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"password\" id=\"pwc\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td colspan=\"2\" class=\"td_btn\">\r\n" + 
							"								<input type=\"button\" value=\"비밀번호 수정\" id=\"pw_btn\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>\r\n" + 
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>이름 수정</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"name\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td colspan=\"2\" class=\"td_btn\">\r\n" + 
							"								<input type=\"button\" value=\"이름 수정\" id=\"name_btn\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>\r\n" + 
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>전화번호 수정</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<select id=\"f_phone\">\r\n" + 
							"									<option value=\"010\">010</option>\r\n" + 
							"									<option value=\"011\">011</option>\r\n" + 
							"									<option value=\"016\">016</option>\r\n" + 
							"									<option value=\"017\">017</option>\r\n" + 
							"									<option value=\"018\">018</option>\r\n" + 
							"									<option value=\"019\">019</option>\r\n" + 
							"								</select>\r\n" + 
							"								<input type=\"text\" id=\"phone\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td colspan=\"2\" class=\"td_btn\">\r\n" + 
							"								<input type=\"button\" value=\"번호 수정\" id=\"phone_btn\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>\r\n" + 
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>이메일 수정</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"email\">@\r\n" + 
							"								<input type=\"text\" id=\"e_address\" readonly>\r\n" + 
							"								<select id=\"e_select\">\r\n" + 
							"									<option value=\"naver.com\">naver.com</option>\r\n" + 
							"									<option value=\"google.com\">google.com</option>\r\n" + 
							"									<option value=\"dir\">직접입력</option>\r\n" + 
							"								</select>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td colspan=\"2\" class=\"td_btn\">\r\n" + 
							"								<input type=\"button\" value=\"이메일 수정\" id=\"email_btn\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>\r\n" + 
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>생년월일 수정</label>\r\n" + 
							"							</td>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"date\" id=\"birth\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td colspan=\"2\" class=\"td_btn\">\r\n" + 
							"								<input type=\"button\" value=\"생년월일 수정\" id=\"birth_btn\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>\r\n" + 
							"			</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_modifyprofile.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("modifyprofile File already exists");
				}
				
			}else if(opt.equals("community")) {
				if(board.createNewFile()) {
					System.out.println("board File created");
					FileWriter fw = new FileWriter(board);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
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
							"<input type='hidden' value='${opt}' id='opt'>"+
							"<input type='hidden' value='${userId}' id='user_id'>"+
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
				
				if(mypage.createNewFile()) {
					System.out.println("mypage File created");
					FileWriter fw = new FileWriter(mypage);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
							"    pageEncoding=\"UTF-8\"%>\r\n" + 
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" +
							"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\r\n"+
							"<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>"+url+" mypage</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_mypage.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='${url}' id='url'>\r\n"+
							"<input type='hidden' value='${opt}' id='opt'>\r\n"+
							"<input type='hidden' value='${userId}' id='user_id'>\r\n"+
							"	<div id='mypage_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='mypage_content'>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_mypage.js\"></script>"+
							"</body>"+
							"</html>");
					bw.close();

				}else {
					System.out.println("mypage File already exists");
				}
			}

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

	@RequestMapping(value = "/{url}/login", method = RequestMethod.GET)
	public void urlLogin() {

	}

	@RequestMapping(value = "/{url}/product", method = RequestMethod.GET)
	public void urlProduct(HttpSession session, Model model, CriteriaVO cri) {
		session.setAttribute("url",cri.getUrl());

		try {
			model.addAttribute("product",es.productList(cri));
			model.addAttribute("paging", new PageVO(cri, es.productMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, es.productMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{url}/productwrite", method = RequestMethod.GET)
	public void urlProductWrite() {

	}

	@RequestMapping(value = "/{url}/productdetail", method = RequestMethod.GET)
	public void urlProductDetail(ProductVO pvo, Model model) {
		model.addAttribute("detail", es.loadProductDetail(pvo));
	}
	
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	public ResponseEntity<ProductVO> productDetail(ProductVO pvo) {
		return new ResponseEntity<>(es.loadProductDetail(pvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{url}/modifyproduct", method = RequestMethod.GET)
	public void urlModifyProduct(ProductVO pvo, Model model) {
		model.addAttribute("modify", es.loadProductDetail(pvo));
	}
	
	
	@RequestMapping(value = "/{url}/cart", method = RequestMethod.GET)
	public void urlCart(CartVO cvo, Model model, HttpSession session) {
		cvo.setId((String)session.getAttribute("userId"));
		System.out.println(es.loadCart(cvo));
		model.addAttribute("cart", es.loadCart(cvo));
	}

	@RequestMapping(value = "/{url}/order", method = RequestMethod.GET)
	public void urlOrder(CartVO cvo, Model model, HttpSession session) {
		cvo.setId((String)session.getAttribute("userId"));
		cvo.setDoOrder(true);
		model.addAttribute("order", es.loadCart(cvo));
	}
	
	@RequestMapping(value = "/{url}/orderlist", method = RequestMethod.GET)
	public void urlOrderList(CriteriaVO cri, Model model, HttpSession session) {
		cri.setSearch((String)session.getAttribute("userId"));
		cri.setAmount(5);
		try {
			model.addAttribute("orderlist",es.orderlist(cri));
			model.addAttribute("paging", new PageVO(cri, es.orderlistMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, es.orderlistMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{url}/mypage", method = RequestMethod.GET)
	public void urlMyPage() {
		
	}
	
	@RequestMapping(value = "/{url}/modifyprofile", method = RequestMethod.GET)
	public void urlModifyProfile() {
		
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
			String target1 = "product_"+cvo.getUrl();
			String target2 = "product_type_"+cvo.getUrl();
			String target3 = "board_"+cvo.getUrl();
			String target4 = "board_type_"+cvo.getUrl();
			String target5 = "product_img_"+cvo.getUrl();
			String target6 = "cart_"+cvo.getUrl();
			String target7 = "payment_"+cvo.getUrl();
			String target8 = "order_"+cvo.getUrl();
			String target9 = "review_"+cvo.getUrl();
			String target10 = "review_img_"+cvo.getUrl();

			es.dropTable(target10);
			es.dropTable(target9);
			es.dropTable(target8);
			es.dropTable(target7);
			es.dropTable(target6);
			es.dropTable(target5);
			es.dropTable(target4);
			es.dropTable(target3);
			es.dropTable(target2);
			es.dropTable(target1);
			es.dropTable(target);
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
		session.setAttribute("userId", es.login(mvo).getId());
		session.setAttribute("userInfo", es.login(mvo));
		return new ResponseEntity<>(es.login(mvo),HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userInfo");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	//	public ResponseEntity<ArrayList<ProductVO>> productList(ProductVO pvo, HttpSession session) {
	//		return new ResponseEntity<>(es.productList(pvo),HttpStatus.OK);
	//	}

	@RequestMapping(value = "/writeproduct", method = RequestMethod.POST)
	public ResponseEntity<String> writeProduct(@RequestBody ProductVO pvo) {
		System.out.println(pvo);
		int result = es.writeProduct(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/modifyproduct", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyProduct(@RequestBody ProductVO pvo) {
		System.out.println(pvo);
		int result = es.modifyProduct(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/savethumbnail", method = RequestMethod.POST)
	public ResponseEntity<String> saveThumbnail(@RequestBody ThumbnailVO tvo) {
		int result = es.saveThumbnail(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/savethumbnail2", method = RequestMethod.POST)
	public ResponseEntity<String> saveThumbnail2(@RequestBody ThumbnailVO tvo) {
		int result = es.saveThumbnail2(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/modifythumbnail", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyThumbnail(@RequestBody ThumbnailVO tvo) {
		int result = es.modifyThumbnail(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@RequestBody ProductVO pvo) {
		System.out.println(pvo);
		if(pvo.getReg_date().equals("true")) {
			int result = es.deleteProduct(pvo);
			return result==1? new ResponseEntity<>("success",HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/writereview", method = RequestMethod.POST)
	public ResponseEntity<String> writeReview(@RequestBody ReviewVO rvo) {
		int result = es.writeReview(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/savereviewimg", method = RequestMethod.POST)
	public ResponseEntity<String> saveReviewImg(@RequestBody ThumbnailVO tvo) {
		int result = es.saveReviewImg(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/loadreview", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReviewVO>> loadReview(ReviewVO rvo) {
		return new ResponseEntity<>(es.loadReview(rvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletereview", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@RequestBody ReviewVO rvo) {
		int result = es.deleteReview(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/loaduserinfo", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> loadUserInfo(MemberVO mvo) {
		return new ResponseEntity<>(es.loadUserInfo(mvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modifyprofile", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyProfile(@RequestBody MemberVO mvo) {
		int result = es.modifyProfile(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
