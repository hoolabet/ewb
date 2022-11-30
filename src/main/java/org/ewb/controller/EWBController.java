package org.ewb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ewb.model.ChatVO;
import org.ewb.model.ContentVO;
import org.ewb.model.MemberVO;
import org.ewb.service.EWBService;
import org.ewb.service.HandlerService;
import org.ewb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EWBController {
	@Autowired
	EWBService es;
	
	@Autowired
	HandlerService hs;
	
	@Autowired
	UserService us;
	
	// ewb 서비스 로그인
	@RequestMapping(value = "/ewblogin", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> ewbLogin(MemberVO mvo, HttpSession session) {
		session.setAttribute("ewbUser", es.ewbLogin(mvo));
		return new ResponseEntity<>(es.ewbLogin(mvo),HttpStatus.OK);
	}
	// ewb 서비스 회원가입
	@RequestMapping(value = "/ewbsignup", method = RequestMethod.POST)
	public ResponseEntity<String> ewbSignUp(@RequestBody MemberVO mvo) {
		int result = es.ewbSignUp(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// ewb 메인화면
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main(HttpSession session,Model model) {
		try {
			MemberVO mvo = new MemberVO();
			mvo = (MemberVO)session.getAttribute("ewbUser");
			model.addAttribute("editPage", es.getPage(mvo.getId()));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 새로운 페이지 생성 id: ewb 아이디 , url: 설정한 주소 이름 , opt: 쇼핑몰 or 커뮤니티  
	@RequestMapping(value = "/newpage", method = RequestMethod.GET)
	public void newPage(String id,String url, String opt, HttpSession session) {
		session.setAttribute("url", url);
		session.setAttribute("opt", opt);
		System.out.println(opt);
		// 파일 생성 경로
		//		String uploadFolder = "C:\\Users\\master\\Desktop\\sp\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		String uploadFolder = "D:\\01-STUDY\\workspace\\ewb\\src\\main\\webapp\\WEB-INF\\views";
//		String uploadFolder = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ewb\\WEB-INF\\views";
		// 파일 생성 경로에 url 이름의 폴더 생성
		File uploadPath = new File(uploadFolder, url);
		if(!uploadPath.exists()) {
			System.out.println(url+" Folder created");
			uploadPath.mkdirs();
		}else {
			System.out.println(url+" Folder already exists");
		}
		// 파일명 선언
		File home = new File(uploadFolder+"\\"+url+"\\home.jsp");
		File member = new File(uploadFolder+"\\"+url+"\\member.jsp");
		File signup = new File(uploadFolder+"\\"+url+"\\signup.jsp");
		File login = new File(uploadFolder+"\\"+url+"\\login.jsp");
		File product = new File(uploadFolder+"\\"+url+"\\product.jsp");
		File productWrite = new File(uploadFolder+"\\"+url+"\\productwrite.jsp");
		File productDetail = new File(uploadFolder+"\\"+url+"\\productdetail.jsp");
		File modifyproduct = new File(uploadFolder+"\\"+url+"\\modifyproduct.jsp");
		File cart = new File(uploadFolder+"\\"+url+"\\cart.jsp");
		File order = new File(uploadFolder+"\\"+url+"\\order.jsp");
		File orderlist = new File(uploadFolder+"\\"+url+"\\orderlist.jsp");
		File ordermanagement = new File(uploadFolder+"\\"+url+"\\ordermanagement.jsp");
		File mypage = new File(uploadFolder+"\\"+url+"\\mypage.jsp");
		File modifyprofile = new File(uploadFolder+"\\"+url+"\\modifyprofile.jsp");
		File board = new File(uploadFolder+"\\"+url+"\\board.jsp");
		File boardwrite = new File(uploadFolder+"\\"+url+"\\boardwrite.jsp");
		File boarddetail = new File(uploadFolder+"\\"+url+"\\boarddetail.jsp");
		File modifyboard = new File(uploadFolder+"\\"+url+"\\modifyboard.jsp");
		File checkwrite = new File(uploadFolder+"\\"+url+"\\checkwrite.jsp");
		File checkreply = new File(uploadFolder+"\\"+url+"\\checkreply.jsp");
		File boardmanagement = new File(uploadFolder+"\\"+url+"\\boardmanagement.jsp");
		try {
			// 선언한 파일명을 만드는데 성공하면 true
			if(home.createNewFile()) {
				// table 생성
				String create_member_table = "create table member_"+url+" ("
						+ "id varchar(100) primary key, "
						+ "pw varchar(100) not null,"
						+ "name varchar(100),"
						+ "email varchar(100),"
						+ "phone varchar(100),"
						+ "birth varchar(100),"
						+ "sign_date datetime default now(),"
						+ "status int default 0,"
						+ "admin boolean default false"
						+ ")";
				es.createTable(create_member_table);
				
				String create_chat_table = "create table chat_"+url+" ("
						+ "id varchar(100), "
						+ "content longtext, "
						+ "chat_date datetime default now()"
						+ ")";
				es.createTable(create_chat_table);
				
				MemberVO mvo = new MemberVO();
				mvo.setId(url);
				mvo.setPw(url);
				// 초기 운영자 id 생성
				es.createFirstAccount(mvo);
				System.out.println("home File created");
				// 생성된 파일에 내용 쓰기
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
						"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link\r\n" + 
						"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
						"        rel=\"stylesheet\">\r\n"+
						"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
						"</head>\r\n" + 
						"<body>\r\n" + 
						"		<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
						"		<input type='hidden' value='"+url+"' id='url'>\r\n"+
						"		<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
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
						"				 <input type=\"hidden\" id=\"save_font\">\r\n"+
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
						"    <div id=\"body_controller_btn\">\r\n"+
						"		<span class=\"material-symbols-outlined\" style=\"font-size: 40px;\">\r\n" + 
						"			public\r\n" + 
						"		</span>\r\n"+
						"	</div>\r\n" + 
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
						"	 <div id=\"font_controller\">\r\n" + 
						"        <table>\r\n" + 
						"            <tr>\r\n" + 
						"                <td>글꼴 선택</td>\r\n" + 
						"                <td>\r\n" + 
						"					<select id=\"font_select\">\r\n" + 
						"        				<option value=\"normal\">기본</option>\r\n" + 
						"        				<option id=\"jua\">jua</option>\r\n" + 
						"        				<option id=\"bs\">bs</option>\r\n" + 
						"        				<option id=\"go\">go</option>\r\n" + 
						"        				<option id=\"jo\">jo</option>\r\n" + 
						"        				<option id=\"pen\">pen</option>\r\n" + 
						"        				<option id=\"noto\">noto</option>\r\n" + 
						"        				<option id=\"poor\">poor</option>\r\n" + 
						"    				</select>\r\n" + 
						"				</td>\r\n" + 
						"            </tr>\r\n" + 
						"			 <tr>\r\n" + 
						"            	<td>글씨 크기</td>\r\n" + 
						"            	<td><input type=\"text\" id=\"font_size_select\"></td>\r\n" + 
						"            </tr>\r\n"+
						"            <tr>\r\n" + 
						"                <td>미리보기</td>\r\n" + 
						"                <td>\r\n" + 
						"                	<div id=\"font_preview\" contenteditable=\"true\" style=\"width: 60px;height:60px;border:1px solid black;\">hi</div>\r\n" + 
						"                </td>\r\n" + 
						"            </tr>\r\n" + 
						"        </table>\r\n" + 
						"        <div id=\"entry_font_apply\" class=\"change_btn\">적용</div>\r\n" + 
						"        <div class=\"close_btn\">✖</div>\r\n" + 
						"    </div>"+
						"    <input type='file' id='upload_input' style='display:none'>\r\n" + 
						"	<div id=\"chat_btn\">💬</div>\r\n" + 
						"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
						"	<script\r\n" + 
						"		src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"	<script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"	<script src=\"../resources/js/url_home.js\"></script>\r\n" + 
						"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
						"</body>\r\n"+
						"</html>");
				bw.close();
			}else {
				System.out.println("home File already exists");
			}
			
			if(member.createNewFile()) {
				System.out.println("member File created");
				FileWriter fw = new FileWriter(member);
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
						"    <title>"+url+" member</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_member.css\">\r\n" +
						"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
						"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
						"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link\r\n" + 
						"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
						"        rel=\"stylesheet\">"+
						"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
						"</head>\r\n" + 
						"<body>\r\n" + 
						"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
						"<input type='hidden' value='"+url+"' id='url'>\r\n"+
						"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>"+
						"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
						"	<div id='member_entry'>\r\n"+
						"		<div id='header'></div>\r\n"+
						"		<div id='member_content'>\r\n"+
						"			<table id=\"member_table\">\r\n" + 
						"				<tr>\r\n" + 
						"					<td>아이디</td>\r\n" + 
						"					<td>이름</td>\r\n" + 
						"					<td>이메일</td>\r\n" + 
						"					<td>연락처</td>\r\n" + 
						"					<td>생년월일</td>\r\n" + 
						"					<td>가입일</td>\r\n" + 
						"					<td></td>\r\n" + 
						"				</tr>\r\n" + 
						"				<c:forEach items=\"${member}\" var=\"member\">\r\n" + 
						"					<tr>\r\n" + 
						"						<td>${member.id}</td>\r\n" + 
						"						<td>${member.name}</td>\r\n" + 
						"						<td>${member.email}</td>\r\n" + 
						"						<td>${member.phone}</td>\r\n" + 
						"						<td>${member.birth}</td>\r\n" + 
						"						<td>${member.sign_date}</td>\r\n" + 
						"						<td>\r\n" + 
						"							<div class=\"delete_btn\" data-id=\"${member.id}\">❌</div>\r\n" + 
						"						</td>\r\n" + 
						"					</tr>\r\n" + 
						"				</c:forEach>\r\n" + 
						"			</table>\r\n" + 
						"			<form action=\"/${url}/member\" id=\"search_form\">\r\n" + 
						"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\">\r\n" + 
						"				<input type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\">\r\n" + 
						"				<select name=\"type\">\r\n" + 
						"					<option value=\"t\">아이디</option>\r\n" + 
						"					<option value=\"c\">이름</option>\r\n" + 
						"					<option value=\"tc\">아이디+이름</option>\r\n" + 
						"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\">\r\n" + 
						"				<input type=\"submit\" value=\"찾기\">\r\n" + 
						"			</form>\r\n" + 
						"			<br> <br>\r\n" + 
						"			<div id='paging'>\r\n" + 
						"				<a\r\n" + 
						"					href=\"/${url}/member?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
						"				<c:if test=\"${paging.prev}\">\r\n" + 
						"					<a\r\n" + 
						"						href=\"/${url}/member?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
						"				</c:if>\r\n" + 
						"				<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
						"					var=\"num\">\r\n" + 
						"					<a\r\n" + 
						"						href=\"/${url}/member?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
						"				</c:forEach>\r\n" + 
						"				<c:if test=\"${paging.next}\">\r\n" + 
						"					<a\r\n" + 
						"						href=\"/${url}/member?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
						"				</c:if>\r\n" + 
						"				<a\r\n" + 
						"					href=\"/${url}/member?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a>\r\n"+
						"			</div>\r\n"+
						"		</div>\r\n"+
						"		<div id='footer'></div>\r\n"+
						"	</div>\r\n"+
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
						"    <script src=\"../resources/js/url_member.js\"></script>\r\n"+
						"</body>\r\n"+
						"</html>");
				bw.close();

			}else {
				System.out.println("member File already exists");
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
						"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link\r\n" + 
						"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
						"        rel=\"stylesheet\">\r\n"+
						"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
						"</head>\r\n" + 
						"<body>\r\n" + 
						"<input type='hidden' value='"+url+"' id='url'>\r\n"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
						"<div id=\"btns\">\r\n" + 
						"        <input type=\"button\" value=\"저장\" id=\"save\">\r\n" + 
						"        <input type=\"button\" value=\"불러오기\" id=\"load\">\r\n" + 
						"    </div>\r\n" + 
						"	 <div id=\"reg_info\">\r\n" + 
						"    	<input type=\"hidden\" id=\"reg_pw\">\r\n" + 
						"    </div>\r\n"+
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
						"    <script src=\"../resources/js/url_signup.js\"></script>\r\n"+
						"</body>\r\n"+
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
						"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link\r\n" + 
						"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
						"        rel=\"stylesheet\">\r\n"+
						"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
						"</head>\r\n" + 
						"<body>\r\n" + 
						"		<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
						"		<input type='hidden' value='"+url+"' id='url'>\r\n"+
						"		<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
						"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
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
						"</body>\r\n"+
						"</html>");
				bw.close();
			}else {
				System.out.println("login File already exists");
			}
			// 페이지 생성 시 쇼핑몰 옵션 선택
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
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
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
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"	<script\r\n" + 
							"		src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"	<script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"    <script src=\"../resources/js/url_product.js\"></script>\r\n"+
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"</body>\r\n"+
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
					
					String create_destination_table = "create table destination_"+url+" ("
							+ "id varchar(100),"
							+ "label varchar(100) unique,"
							+ "name varchar(100),"
							+ "address varchar(100),"
							+ "phone varchar(100),"
							+ "memo varchar(100),"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_destination_table);
					
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
							" 	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"<link rel=\"stylesheet\"\r\n" + 
							"	href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"		<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='product_write_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='product_write_content'>\r\n"+
							"			<div id=\"container\">\r\n" + 
							"				<label>상품명</label><input type='text' id='pname'>\r\n"+
							"				<label>가격</label><input type='text' id='price'>\r\n"+
							"				<label>수량</label><input type='text' id='quantity'>\r\n"+
							"				<label>섬네일</label><div contenteditable='true' id='thumbnail'></div><div id='thumb_btn'>섬네일 넣기</div><input type='file' id='thumb_file'>\r\n"+
							"        		<label>상세내용</label><div id=\"entry\">\r\n" + 
							"					<input type=\"checkbox\" id=\"able_box\" checked>\r\n" + 
							"					<div id=\"btnss\">\r\n" + 
							"						<div class=\"btns\" data-att=\"b\">\r\n" + 
							"							<b>B</b>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"i\">\r\n" + 
							"							<i>I</i>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"u\">\r\n" + 
							"							<u>U</u>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"a\">L</div>\r\n" + 
							"						<div class=\"btns\" id=\"font_size_btn\">\r\n" + 
							"							<span class=\"material-symbols-outlined\"> format_size </span>\r\n"+
							"						</div>\r\n" + 
							" 						<select\r\n" + 
							"							id=\"font_size\">\r\n" + 
							"							<option value=\"8\">8</option>\r\n" + 
							"							<option value=\"12\">12</option>\r\n" + 
							"							<option value=\"16\">16</option>\r\n" + 
							"							<option value=\"20\">20</option>\r\n" + 
							"							<option value=\"24\">24</option>\r\n" + 
							"							<option value=\"28\">28</option>\r\n" + 
							"						</select>\r\n" + 
							"						<div class=\"btns\" id=\"font_color_btn\">\r\n" + 
							"							<span class=\"material-symbols-outlined\"> format_color_text\r\n" + 
							"							</span>\r\n" + 
							"						</div>\r\n" + 
							"						<div id=\"cp_div\">\r\n" + 
							"							<input type=\"text\" id=\"cp\"> <input type=\"button\"\r\n" + 
							"								value=\"선택\" id=\"font_color_choice\">\r\n" + 
							"						</div>\r\n" + 
							"						<div id=\"sort\">\r\n" + 
							"							<span class=\"material-symbols-outlined sort btns\"\r\n" + 
							"								data-sort=\"left\"> format_align_left </span> <span\r\n" + 
							"								class=\"material-symbols-outlined sort btns\" data-sort=\"center\">\r\n" + 
							"								format_align_center </span> <span\r\n" + 
							"								class=\"material-symbols-outlined sort btns\" data-sort=\"right\">\r\n" + 
							"								format_align_right </span>\r\n" + 
							"						</div>\r\n" + 
							"						<div class='btns' id='insert_btn'>\r\n"+
							"							<span class=\"material-symbols-outlined\">\r\n" + 
							"								imagesmode\r\n" + 
							"							</span>\r\n"+
							"						</div>\r\n"+
							"					</div>\r\n" + 
							"					<div id=\"link_div\">\r\n" + 
							"						<input class=\"links\" type=\"text\" id=\"href\" placeholder=\"링크주소\"><br>\r\n" + 
							"						<input class=\"links\" type=\"text\" id=\"href_text\" placeholder=\"링크이름\"><br>\r\n" + 
							"						<input class=\"links\" type=\"button\" id=\"link_btn\" value=\"링크만들기\"></br>\r\n" + 
							"					</div>\r\n" + 
							"					<div contenteditable=\"true\" id=\"content\"></div>\r\n" + 
							"				</div>\r\n" +
							"				<input type='file' id='insert_img' multiple>\r\n"+
							"        		<input type=\"button\" value=\"작성하기\" id=\"write_btn\">\r\n" + 
							"    		</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" +
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_product_write.js\"></script>\r\n"+
							"</body>\r\n"+
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
							" 	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
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
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_product_detail.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_product_write.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\"\r\n" + 
							"		href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${modify.pno}' id='pno'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"<input type='hidden' value='${modify.tvo.fullpath}' id='thumbpath'>\r\n"+
							"	<div id='modifyproduct_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='modifyproduct_content'>\r\n"+
							"			<div id=\"container\">\r\n" + 
							"				<label>상품명</label><input type='text' id='pname' value='${modify.pname}'>\r\n"+
							"				<label>가격</label><input type='text' id='price' value='${modify.price}'>\r\n"+
							"				<label>수량</label><input type='text' id='quantity' value='${modify.quantity}'>\r\n"+
							"				<label>섬네일</label><div contenteditable='true' id='thumbnail'><img src='/display?fileName=${modify.tvo.fullpath}'></div><div id='thumb_btn'>섬네일 넣기</div><input type='file' id='thumb_file'>\r\n"+
							"        		<label>상세내용</label><div id=\"entry\">\r\n" + 
							"					<input type=\"checkbox\" id=\"able_box\" checked>\r\n" + 
							"					<div id=\"btnss\">\r\n" + 
							"						<div class=\"btns\" data-att=\"b\">\r\n" + 
							"							<b>B</b>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"i\">\r\n" + 
							"							<i>I</i>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"u\">\r\n" + 
							"							<u>U</u>\r\n" + 
							"						</div>\r\n" + 
							"						<div class=\"btns\" data-att=\"a\">L</div>\r\n" + 
							"						<div class=\"btns\" id=\"font_size_btn\">\r\n" + 
							"							<span class=\"material-symbols-outlined\"> format_size </span>\r\n"+
							"						</div>\r\n" + 
							" 						<select\r\n" + 
							"							id=\"font_size\">\r\n" + 
							"							<option value=\"8\">8</option>\r\n" + 
							"							<option value=\"12\">12</option>\r\n" + 
							"							<option value=\"16\">16</option>\r\n" + 
							"							<option value=\"20\">20</option>\r\n" + 
							"							<option value=\"24\">24</option>\r\n" + 
							"							<option value=\"28\">28</option>\r\n" + 
							"						</select>\r\n" + 
							"						<div class=\"btns\" id=\"font_color_btn\">\r\n" + 
							"							<span class=\"material-symbols-outlined\"> format_color_text\r\n" + 
							"							</span>\r\n" + 
							"						</div>\r\n" + 
							"						<div id=\"cp_div\">\r\n" + 
							"							<input type=\"text\" id=\"cp\"> <input type=\"button\"\r\n" + 
							"								value=\"선택\" id=\"font_color_choice\">\r\n" + 
							"						</div>\r\n" + 
							"						<div id=\"sort\">\r\n" + 
							"							<span class=\"material-symbols-outlined sort btns\"\r\n" + 
							"								data-sort=\"left\"> format_align_left </span> <span\r\n" + 
							"								class=\"material-symbols-outlined sort btns\" data-sort=\"center\">\r\n" + 
							"								format_align_center </span> <span\r\n" + 
							"								class=\"material-symbols-outlined sort btns\" data-sort=\"right\">\r\n" + 
							"								format_align_right </span>\r\n" + 
							"						</div>\r\n" + 
							"						<div class='btns' id='insert_btn'>\r\n"+
							"							<span class=\"material-symbols-outlined\">\r\n" + 
							"								imagesmode\r\n" + 
							"							</span>\r\n"+
							"						</div>\r\n"+
							"					</div>\r\n" + 
							"					<div id=\"link_div\">\r\n" + 
							"						<input class=\"links\" type=\"text\" id=\"href\" placeholder=\"링크주소\"><br>\r\n" + 
							"						<input class=\"links\" type=\"text\" id=\"href_text\" placeholder=\"링크이름\"><br>\r\n" + 
							"						<input class=\"links\" type=\"button\" id=\"link_btn\" value=\"링크만들기\"></br>\r\n" + 
							"					</div>\r\n" + 
							"					<div contenteditable=\"true\" id=\"content\">${modify.content}</div>\r\n" + 
							"				</div>\r\n" +
							"				<input type='file' id='insert_img' multiple>\r\n"+
							"        		<input type=\"button\" value=\"수정하기\" id=\"modi_btn\">\r\n" + 
							"    		</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" +
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_modifyproduct.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
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
							"					<c:choose>\r\n"+
							"					<c:when test=\"${fn:length(cart.pvo.tvo.fullpath) ne 0}\">\r\n"+
							"						<img src='/display?fileName=${cart.pvo.tvo.fullpath}' class='p_imgs'>\r\n"+
							"					</c:when>\r\n"+
							"					<c:otherwise>\r\n"+
							"						<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>\r\n"+
							"					</c:otherwise>\r\n"+
							"					</c:choose>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>${cart.pvo.pname}</p>\r\n"+
							"						<p>수량 : <input type='button' value='🔻'  class='quan' data-pno='${cart.pno}' data-val='down'><input type='text' value='${cart.b_quantity}' id='quan_${cart.pno}' data-price='${cart.pvo.price}' data-quantity='${cart.pvo.quantity}' readonly><input type='button' value='🔺'  class='quan' data-pno='${cart.pno}' data-val='up'></p>\r\n"+
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
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_cart.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='order_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='order_content'>\r\n"+
							"			<div id='order_des'>\r\n"+
							"				<select id=\"des_select\">\r\n" + 
							"					<option value=\"new\">새로운 배송지</option>\r\n" + 
							"				</select><br>\r\n" + 
							"				<input type='text' id='label' placeholder='배송지 이름'><br>\r\n" + 
							"				<input type='text' id='name' placeholder='이름'><br>\r\n" + 
							"				<input type='text' id='address' placeholder='주소'><br>\r\n" + 
							"				<input type='text' id='phone' placeholder='연락처'><br>\r\n" + 
							"				<input type='text' id='memo' placeholder='메모'><br>\r\n"+
							"			</div>\r\n"+
							"			<table id='order_table'>\r\n"+
							"			<c:forEach var=\"order\" items=\"${order}\">\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"					<c:choose>\r\n"+
							"					<c:when test=\"${fn:length(order.pvo.tvo.fullpath) ne 0}\">\r\n"+
							"						<img src='/display?fileName=${order.pvo.tvo.fullpath}' class='p_imgs'>\r\n"+
							"					</c:when>\r\n"+
							"					<c:otherwise>\r\n"+
							"						<img src='https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png' class='p_imgs'>\r\n"+
							"					</c:otherwise>\r\n"+
							"					</c:choose>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<p>${order.pvo.pname}</p>\r\n"+
							"						<p>수량 : <input type='text' value='${order.b_quantity}' id='quan_${order.pno}' data-price='${order.pvo.price}' readonly></p>\r\n"+
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
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_order.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='orderlist_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='orderlist_content'>\r\n"+
							"			<div id='orderlist_div'>\r\n"+
							"				<table id='orderlist_table'>\r\n"+
							"				<c:forEach items=\"${orderlist}\" var=\"orderlist\">\r\n"+
							"					<tr id=\"payno_${orderlist.payno}\">\r\n" + 
							"						<td class=\"orderlist_td\" data-payno=\"${orderlist.payno}\">\r\n" + 
							"							결제 아이디 : ${orderlist.id }<br>\r\n" + 
							"							총 결제 금액 : ${orderlist.price}원<br>\r\n" + 
							"							받는 사람 : ${orderlist.name}<br>\r\n" + 
							"							배송주소 : ${orderlist.address}<br>\r\n" + 
							"							메모 : ${orderlist.memo}<br>\r\n" + 
							"							결제완료 시간 : ${orderlist.payment_date}" + 
							"						</td>\r\n" +
							"					</tr>\r\n"+
							"				</c:forEach>\r\n"+
							"				</table>\r\n"+
							"			<br> <br> <div id='paging'><a\r\n" + 
							"				href=\"/${url}/orderlist?pageNum=1&amount=${paging.cri.amount}\">처음으로</a>\r\n" + 
							"			<c:if test=\"${paging.prev}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/orderlist?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}\">이전</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"				var=\"num\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/orderlist?pageNum=${num}&amount=${paging.cri.amount}\">${num}</a>\r\n" + 
							"			</c:forEach>\r\n" + 
							"			<c:if test=\"${paging.next}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/orderlist?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}\">다음</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<a\r\n" + 
							"				href=\"/${url}/orderlist?pageNum=${paging.realEnd}&amount=${paging.cri.amount}\">맨끝으로</a></div>			</div>"+
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_orderlist.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("orderlist File already exists");
				}
				
				if(ordermanagement.createNewFile()) {
					System.out.println("ordermanagement File created");
					FileWriter fw = new FileWriter(ordermanagement);
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
							"    <title>"+url+" ordermanagement</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_ordermanagement.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='ordermanagement_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='ordermanagement_content'>\r\n"+
							"			<div id='ordermanagement_div'>\r\n"+
							"				<table id='ordermanagement_table'>\r\n"+
							"				<c:forEach items=\"${orderlist}\" var=\"orderlist\">\r\n"+
							"					<tr id=\"payno_${orderlist.payno}\">\r\n" + 
							"						<td class=\"orderlist_td\" data-payno=\"${orderlist.payno}\">\r\n" + 
							"							결제 아이디 : ${orderlist.id }<br>\r\n" + 
							"							총 결제 금액 : ${orderlist.price}원<br>\r\n" + 
							"							받는 사람 : ${orderlist.name}<br>\r\n" + 
							"							배송주소 : ${orderlist.address}<br>\r\n" + 
							"							메모 : ${orderlist.memo}<br>\r\n" + 
							"							결제완료 시간 : ${orderlist.payment_date}\r\n" + 
							"						</td>\r\n" +
							"					</tr>\r\n"+
							"				</c:forEach>\r\n"+
							"				</table>\r\n"+
							"			<br> <br> <div id='paging'><a\r\n" + 
							"				href=\"/${url}/ordermanagement?pageNum=1&amount=${paging.cri.amount}\">처음으로</a>\r\n" + 
							"			<c:if test=\"${paging.prev}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/ordermanagement?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}\">이전</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"				var=\"num\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/ordermanagement?pageNum=${num}&amount=${paging.cri.amount}\">${num}</a>\r\n" + 
							"			</c:forEach>\r\n" + 
							"			<c:if test=\"${paging.next}\">\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/ordermanagement?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}\">다음</a>\r\n" + 
							"			</c:if>\r\n" + 
							"			<a\r\n" + 
							"				href=\"/${url}/ordermanagement?pageNum=${paging.realEnd}&amount=${paging.cri.amount}\">맨끝으로</a></div>			</div>"+
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_ordermanagement.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("ordermanagement File already exists");
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
							"    <title>"+url+" ${"+url+"_userId} mypage</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_mypage.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
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
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_mypage.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"    <title>"+url+" ${"+url+"_userId} modify profile</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_modifyprofile.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='modifyprofile_entry'>\r\n"+
							"		<div id=\"reg_info\"></div>\r\n"+
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
							"								<input type=\"password\" id=\"pw\"><span style=\"position:absolute\" class=\"pw\"></span>\r\n" + 
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
							"				<div class=\"modifyprofile_div\">\r\n" + 
							"					<table>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<label>배송지 설정</label>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<select id=\"des_select\">\r\n" + 
							"									<option value=\"new\">새로운 배송지</option>\r\n" + 
							"								</select>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"des_label\" placeholder=\"배송지 이름\" required>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"des_name\" placeholder=\"이름\" required>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"des_address\" placeholder=\"배송지 주소\" required>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"des_phone\" placeholder=\"연락처\" required>\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"text\" id=\"des_memo\" placeholder=\"메모\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"						<tr>\r\n" + 
							"							<td>\r\n" + 
							"								<input type=\"button\" id=\"des_add_btn\" value=\"추가\">\r\n" + 
							"								<input type=\"button\" id=\"des_modify_btn\" value=\"수정\">\r\n" + 
							"								<input type=\"button\" id=\"des_remove_btn\" value=\"삭제\">\r\n" + 
							"							</td>\r\n" + 
							"						</tr>\r\n" + 
							"					</table>\r\n" + 
							"				</div>"+
							"			</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_modifyprofile.js\"></script>\r\n"+
							"</body>\r\n"+
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
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='board_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='board_content'>\r\n"+
							"			<div id=\"board_write\">작성하기</div>\r\n"+
							"			<table id=\"board_table\">\r\n" + 
							"				<tr>\r\n" + 
							"					<td>아이디</td>\r\n" + 
							"					<td>제목</td>\r\n" + 
							"					<td>조회 수</td>\r\n" + 
							"					<td>추천 수</td>\r\n" + 
							"					<td>작성일</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<c:forEach items=\"${boardlist}\" var=\"boardlist\">\r\n" + 
							"					<tr>\r\n" + 
							"						<td>${boardlist.id}</td>\r\n" + 
							"						<td><a href=\"/${url}/boarddetail?bno=${boardlist.bno}\">${boardlist.bname}</a></td>\r\n" + 
							"						<td>${boardlist.cnt}</td>\r\n" + 
							"						<td>${boardlist.like_}</td>\r\n" + 
							"						<td>${boardlist.reg_date}</td>\r\n" + 
							"					</tr>\r\n" + 
							"				</c:forEach>\r\n" + 
							"			</table>\r\n" + 
							"			<form action=\"/${url}/board\" id=\"search_form\">\r\n" + 
							"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\">\r\n" + 
							"				<input type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\">\r\n" + 
							"				<select name=\"type\">\r\n" + 
							"					<option value=\"t\">아이디</option>\r\n" + 
							"					<option value=\"c\">이름</option>\r\n" + 
							"					<option value=\"tc\">아이디+이름</option>\r\n" + 
							"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\">\r\n" + 
							"				<input type=\"submit\" value=\"찾기\">\r\n" + 
							"			</form>\r\n" + 
							"			<br> <br>\r\n" + 
							"			<div id='paging'>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/board?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
							"				<c:if test=\"${paging.prev}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/board?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"					var=\"num\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/board?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
							"				</c:forEach>\r\n" + 
							"				<c:if test=\"${paging.next}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/board?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/board?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a>\r\n" + 
							"			</div>"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_board.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();
					String create_board_table = "create table board_"+url+" ("
							+ "bno int auto_increment primary key, "
							+ "id varchar(100) not null,"
							+ "bname varchar(100) not null,"
							+ "content longtext,"
							+ "reg_date datetime default now(),"
							+ "type int,"
							+ "cnt int default 0,"
							+ "like_ int default 0,"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_board_table);

					String create_board_type_table = "create table board_type_"+url+" ("
							+ "type int primary key,"
							+ "tname varchar(100) not null"
							+ ")";
					es.createTable(create_board_type_table);
					
					String create_reply_table = "create table reply_"+url+" ("
							+ "rno int auto_increment primary key, "
							+ "bno int, "
							+ "id varchar(100) not null,"
							+ "content longtext,"
							+ "reply_date datetime default now(),"
							+ "type int,"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade,"
							+ "foreign key(bno)"
							+ "references board_"+url+"(bno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_reply_table);
					
					String create_rereply_table = "create table rereply_"+url+" ("
							+ "rrno int auto_increment primary key, "
							+ "rno int, "
							+ "id varchar(100) not null,"
							+ "content longtext,"
							+ "rereply_date datetime default now(),"
							+ "type int,"
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade,"
							+ "foreign key(rno)"
							+ "references reply_"+url+"(rno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_rereply_table);
					
					String create_like_table = "create table like_"+url+" ("
							+ "bno int, "
							+ "id varchar(100), "
							+ "foreign key(id)"
							+ "references member_"+url+"(id) on update cascade on delete cascade,"
							+ "foreign key(bno)"
							+ "references board_"+url+"(bno) on update cascade on delete cascade"
							+ ")";
					es.createTable(create_like_table);
				}else {
					System.out.println("board File already exists");
				}
				
				if(boardwrite.createNewFile()) {
					System.out.println("boardwrite File created");
					FileWriter fw = new FileWriter(boardwrite);
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
							"    <title>"+url+" boardwrite</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_boardwrite.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"<link rel=\"stylesheet\"\r\n" + 
							"	href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='boardwrite_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='boardwrite_content'>\r\n"+
							"			<div id=\"entry\">\r\n" + 
							"				<label>제목</label>\r\n" + 
							"				<input type=\"text\" id=\"bname\">\r\n" + 
							"				<input type=\"checkbox\" id=\"able_box\" checked>\r\n" + 
							"				<div id=\"btnss\">\r\n" + 
							"					<div class=\"btns\" data-att=\"b\">\r\n" + 
							"						<b>B</b>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"i\">\r\n" + 
							"						<i>I</i>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"u\">\r\n" + 
							"						<u>U</u>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"a\">L</div>\r\n" + 
							"					<div class=\"btns\" id=\"font_size_btn\">\r\n" + 
							"						<span class=\"material-symbols-outlined\"> format_size </span>\r\n" + 
							"					</div>\r\n" + 
							"					<select id=\"font_size\">\r\n" + 
							"						<option value=\"8\">8</option>\r\n" + 
							"						<option value=\"12\">12</option>\r\n" + 
							"						<option value=\"16\">16</option>\r\n" + 
							"						<option value=\"20\">20</option>\r\n" + 
							"						<option value=\"24\">24</option>\r\n" + 
							"						<option value=\"28\">28</option>\r\n" + 
							"					</select>\r\n" + 
							"					<div class=\"btns\" id=\"font_color_btn\">\r\n" + 
							"						<span class=\"material-symbols-outlined\"> format_color_text\r\n" + 
							"						</span>\r\n" + 
							"					</div>\r\n" + 
							"					<div id=\"cp_div\">\r\n" + 
							"						<input type=\"text\" id=\"cp\"> <input type=\"button\"\r\n" + 
							"							value=\"선택\" id=\"font_color_choice\">\r\n" + 
							"					</div>\r\n" + 
							"					<div id=\"sort\">\r\n" + 
							"						<span class=\"material-symbols-outlined sort btns\" data-sort=\"left\">\r\n" + 
							"							format_align_left </span> <span\r\n" + 
							"							class=\"material-symbols-outlined sort btns\" data-sort=\"center\">\r\n" + 
							"							format_align_center </span> <span\r\n" + 
							"							class=\"material-symbols-outlined sort btns\" data-sort=\"right\">\r\n" + 
							"							format_align_right </span>\r\n" + 
							"					</div>\r\n" + 
							"					<div class='btns' id='insert_btn'>\r\n" + 
							"						<span class=\"material-symbols-outlined\"> imagesmode </span>\r\n" + 
							"					</div>\r\n" + 
							"				</div>\r\n" + 
							"				<div id=\"link_div\">\r\n" + 
							"					<input class=\"links\" type=\"text\" id=\"href\" placeholder=\"링크주소\"><br>\r\n" + 
							"					<input class=\"links\" type=\"text\" id=\"href_text\" placeholder=\"링크이름\"><br>\r\n" + 
							"					<input class=\"links\" type=\"button\" id=\"link_btn\" value=\"링크만들기\"></br>\r\n" + 
							"				</div>\r\n" + 
							"				<div contenteditable=\"true\" id=\"content\"></div>\r\n" + 
							"			</div>\r\n" + 
							"			<input type='file' id='insert_img' multiple> <input\r\n" + 
							"				type=\"button\" value=\"작성하기\" id=\"write_btn\">"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_boardwrite.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();
					
				}else {
					System.out.println("boardwrite File already exists");
				}
				
				if(boarddetail.createNewFile()) {
					System.out.println("boarddetail File created");
					FileWriter fw = new FileWriter(boarddetail);
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
							"    <title>"+url+" boarddetail</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_boarddetail.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"<link rel=\"stylesheet\"\r\n" + 
							"	href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"<input type='hidden' value='${userInfo.status}' id='status'>\r\n"+
							"	<div id='boarddetail_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='boarddetail_content'>\r\n" + 
							"			<input type=\"hidden\" value=\"${detail.bno}\" id=\"bno\">\r\n" + 
							"			<input type=\"hidden\" value=\"${detail.id}\" id=\"b_id\">\r\n" + 
							"			<div id=\"entry\">\r\n" + 
							"				<div id=\"rm\">\r\n" + 
							"					<input type=\"button\" value=\"수정\" id=\"modi_btn\">\r\n" + 
							"					<input type=\"button\" value=\"삭제\" id=\"remo_btn\">\r\n" + 
							"				</div>\r\n" + 
							"				<label>제목</label>\r\n" + 
							"				<p id=\"bname\">${detail.bname}</p>\r\n" + 
							"				<label>내용</label>\r\n" + 
							"				<p id=\"content\">${detail.content}</p>\r\n" + 
							"			</div>\r\n" + 
							"			<div id=\"like_div\">\r\n" + 
							"				<span class=\"material-symbols-outlined\" id=\"like_btn\" style=\"font-size: 32px;\"> thumb_up </span>\r\n" + 
							"			</div>\r\n"+
							"			<div id=\"reply\">\r\n" + 
							"				<textarea rows=\"3\" cols=\"60\" id=\"reply_content\"></textarea>\r\n" + 
							"				<input type=\"button\" value=\"reply\" id=\"reply_btn\">\r\n" + 
							"				<table id=\"reply_table\">\r\n" + 
							"\r\n" + 
							"				</table>\r\n" + 
							"				<ul id=\"pagingul\">\r\n" + 
							"				</ul>\r\n" + 
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_boarddetail.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();
					
				}else {
					System.out.println("boarddetail File already exists");
				}
				
				if(modifyboard.createNewFile()) {
					System.out.println("modifyboard File created");
					FileWriter fw = new FileWriter(modifyboard);
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
							"    <title>"+url+" modifyboard</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_modifyboard.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"<link rel=\"stylesheet\"\r\n" + 
							"	href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='modifyboard_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='modifyboard_content'>\r\n"+
							"			<input type=\"hidden\" value=\"${detail.bno}\" id=\"bno\">\r\n"+
							"			<div id=\"entry\">\r\n" + 
							"				<label>제목</label>\r\n" + 
							"				<input type=\"text\" id=\"bname\" value=\"${detail.bname}\">\r\n" + 
							"				<input type=\"checkbox\" id=\"able_box\" checked>\r\n" + 
							"				<div id=\"btnss\">\r\n" + 
							"					<div class=\"btns\" data-att=\"b\">\r\n" + 
							"						<b>B</b>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"i\">\r\n" + 
							"						<i>I</i>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"u\">\r\n" + 
							"						<u>U</u>\r\n" + 
							"					</div>\r\n" + 
							"					<div class=\"btns\" data-att=\"a\">L</div>\r\n" + 
							"					<div class=\"btns\" id=\"font_size_btn\">\r\n" + 
							"						<span class=\"material-symbols-outlined\"> format_size </span>\r\n" + 
							"					</div>\r\n" + 
							"					<select id=\"font_size\">\r\n" + 
							"						<option value=\"8\">8</option>\r\n" + 
							"						<option value=\"12\">12</option>\r\n" + 
							"						<option value=\"16\">16</option>\r\n" + 
							"						<option value=\"20\">20</option>\r\n" + 
							"						<option value=\"24\">24</option>\r\n" + 
							"						<option value=\"28\">28</option>\r\n" + 
							"					</select>\r\n" + 
							"					<div class=\"btns\" id=\"font_color_btn\">\r\n" + 
							"						<span class=\"material-symbols-outlined\"> format_color_text\r\n" + 
							"						</span>\r\n" + 
							"					</div>\r\n" + 
							"					<div id=\"cp_div\">\r\n" + 
							"						<input type=\"text\" id=\"cp\"> <input type=\"button\"\r\n" + 
							"							value=\"선택\" id=\"font_color_choice\">\r\n" + 
							"					</div>\r\n" + 
							"					<div id=\"sort\">\r\n" + 
							"						<span class=\"material-symbols-outlined sort btns\" data-sort=\"left\">\r\n" + 
							"							format_align_left </span> <span\r\n" + 
							"							class=\"material-symbols-outlined sort btns\" data-sort=\"center\">\r\n" + 
							"							format_align_center </span> <span\r\n" + 
							"							class=\"material-symbols-outlined sort btns\" data-sort=\"right\">\r\n" + 
							"							format_align_right </span>\r\n" + 
							"					</div>\r\n" + 
							"					<div class='btns' id='insert_btn'>\r\n" + 
							"						<span class=\"material-symbols-outlined\"> imagesmode </span>\r\n" + 
							"					</div>\r\n" + 
							"				</div>\r\n" + 
							"				<div id=\"link_div\">\r\n" + 
							"					<input class=\"links\" type=\"text\" id=\"href\" placeholder=\"링크주소\"><br>\r\n" + 
							"					<input class=\"links\" type=\"text\" id=\"href_text\" placeholder=\"링크이름\"><br>\r\n" + 
							"					<input class=\"links\" type=\"button\" id=\"link_btn\" value=\"링크만들기\"></br>\r\n" + 
							"				</div>\r\n" + 
							"				<div contenteditable=\"true\" id=\"content\">${detail.content}</div>\r\n" + 
							"			</div>\r\n" + 
							"			<input type='file' id='insert_img' multiple> <input\r\n" + 
							"				type=\"button\" value=\"수정하기\" id=\"write_btn\">"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_modifyboard.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();
					
				}else {
					System.out.println("modifyboard File already exists");
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
							"    <title>"+url+" ${"+url+"_userId} mypage</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_mypage.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='mypage_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='mypage_content'>\r\n"+
							"			<table id='mypage_table'>\r\n"+
							"				<tr>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/modifyprofile'>정보수정</a>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/checkwrite'>작성 글 보기</a>\r\n"+
							"					</td>\r\n"+
							"					<td>\r\n"+
							"						<a href='/${url}/checkreply'>작성 댓글 보기</a>\r\n"+
							"					</td>\r\n"+
							"				</tr>\r\n"+
							"			</table>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_mypage.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("mypage File already exists");
				}
				
				if(checkwrite.createNewFile()) {
					System.out.println("checkwrite File created");
					FileWriter fw = new FileWriter(checkwrite);
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
							"    <title>"+url+" ${"+url+"_userId} checkwrite</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_checkwrite.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='checkwrite_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='checkwrite_content'>\r\n"+
							"			<table id=\"checkwrite_table\">\r\n" + 
							"				<tr>\r\n" + 
							"					<td>게시물 번호</td>\r\n" + 
							"					<td>제목</td>\r\n" + 
							"					<td>작성일</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<c:forEach items=\"${cw}\" var=\"cw\">\r\n" + 
							"					<tr>\r\n" + 
							"						<td>${cw.bno}</td>\r\n" + 
							"						<td><a href=\"/${url}/boarddetail?bno=${cw.bno}\">${cw.bname}</a></td>\r\n" + 
							"						<td>${cw.reg_date}</td>\r\n" + 
							"					</tr>\r\n" + 
							"				</c:forEach>\r\n" + 
							"			</table>\r\n" + 
							"			<form action=\"/${url}/checkwrite\" id=\"search_form\">\r\n" + 
							"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\">\r\n" + 
							"				<input type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\">\r\n" + 
							"				<select name=\"type\">\r\n" + 
							"					<option value=\"t\">제목</option>\r\n" + 
							"					<option value=\"c\">내용</option>\r\n" + 
							"					<option value=\"tc\">제목+내용</option>\r\n" + 
							"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\">\r\n" + 
							"				<input type=\"submit\" value=\"찾기\">\r\n" + 
							"			</form>\r\n" + 
							"			<br> <br>\r\n" + 
							"			<div id='paging'>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/checkwrite?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
							"				<c:if test=\"${paging.prev}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkwrite?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"					var=\"num\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkwrite?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
							"				</c:forEach>\r\n" + 
							"				<c:if test=\"${paging.next}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkwrite?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/checkwrite?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a>\r\n" + 
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_checkwrite.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("checkwrite File already exists");
				}
				
				if(checkreply.createNewFile()) {
					System.out.println("checkreply File created");
					FileWriter fw = new FileWriter(checkreply);
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
							"    <title>"+url+" ${"+url+"_userId} checkreply</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_checkreply.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='checkreply_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='checkreply_content'>\r\n"+
							"			<table id=\"checkreply_table\">\r\n" + 
							"				<tr>\r\n" + 
							"					<td>게시물 번호</td>\r\n" + 
							"					<td>내용</td>\r\n" + 
							"					<td>작성일</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<c:forEach items=\"${cr}\" var=\"cr\">\r\n" + 
							"					<tr>\r\n" + 
							"						<td><a href=\"/${url}/boarddetail?bno=${cw.bno}\">${cr.bno}</a></td>\r\n" + 
							"						<td><a href=\"/${url}/boarddetail?bno=${cw.bno}\">${cr.content}</a></td>\r\n" + 
							"						<td>${cr.reply_date}</td>\r\n" + 
							"					</tr>\r\n" + 
							"				</c:forEach>\r\n" + 
							"			</table>\r\n" + 
							"			<form action=\"/${url}/checkreply\" id=\"search_form\">\r\n" + 
							"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\">\r\n" + 
							"				<input type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\">\r\n" + 
							"				<select name=\"type\">\r\n" + 
							"					<option value=\"c\">내용</option>\r\n" + 
							"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\">\r\n" + 
							"				<input type=\"submit\" value=\"찾기\">\r\n" + 
							"			</form>\r\n" + 
							"			<br> <br>\r\n" + 
							"			<div id='paging'>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/checkreply?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
							"				<c:if test=\"${paging.prev}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkreply?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"					var=\"num\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkreply?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
							"				</c:forEach>\r\n" + 
							"				<c:if test=\"${paging.next}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/checkreply?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/checkreply?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a>\r\n" + 
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_checkreply.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("checkreply File already exists");
				}
				
				if(boardmanagement.createNewFile()) {
					System.out.println("boardmanagement File created");
					FileWriter fw = new FileWriter(boardmanagement);
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
							"    <title>"+url+" ${"+url+"_userId} boardmanagement</title>\r\n" + 
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_boardmanagement.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/css/url_home.css\">\r\n" +
							"    <link rel=\"stylesheet\" href=\"../resources/color_picker/jquery.minicolors.css\">\r\n" +
							"	 <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
							"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
							"    <link\r\n" + 
							"        href=\"https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Poor+Story&display=swap\"\r\n" + 
							"        rel=\"stylesheet\">\r\n"+
							"	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0\" />\r\n"+
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<input type='hidden' value='${userInfo.admin}' id='admin'>\r\n"+
							"<input type='hidden' value='"+url+"' id='url'>\r\n"+
							"<input type='hidden' value='"+opt+"' id='opt'>\r\n"+
							"<input type='hidden' value='${ewbUser.id}' id='ewb_id'>\r\n"+
							"<input type='hidden' value='${"+url+"_userId}' id='user_id'>\r\n"+
							"	<div id='boardmanagement_entry'>\r\n"+
							"		<div id='header'></div>\r\n"+
							"		<div id='boardmanagement_content'>\r\n"+
							"			<table id=\"boardmanagement_table\">\r\n" + 
							"				<tr>\r\n" + 
							"					<td>게시물 번호</td>\r\n" + 
							"					<td>제목</td>\r\n" + 
							"					<td>작성일</td>\r\n" + 
							"					<td></td>\r\n"+
							"				</tr>\r\n" + 
							"				<c:forEach items=\"${bm}\" var=\"bm\">\r\n" + 
							"					<tr>\r\n" + 
							"						<td>${bm.bno}</td>\r\n" + 
							"						<td><a href=\"/${url}/boarddetail?bno=${bm.bno}\">${bm.bname}</a></td>\r\n" + 
							"						<td>${bm.reg_date}</td>\r\n" +
							"						<td class=\"remove_btn\" data-bno=\"${bm.bno}\">❌</td>\r\n" +
							"					</tr>\r\n" + 
							"				</c:forEach>\r\n" + 
							"			</table>\r\n" + 
							"			<form action=\"/${url}/boardmanagement\" id=\"search_form\">\r\n" + 
							"				<input type=\"hidden\" name=\"pageNum\" value=\"${paging.cri.pageNum}\">\r\n" + 
							"				<input type=\"hidden\" name=\"amount\" value=\"${paging.cri.amount}\">\r\n" + 
							"				<select name=\"type\">\r\n" + 
							"					<option value=\"t\">제목</option>\r\n" + 
							"					<option value=\"c\">내용</option>\r\n" + 
							"					<option value=\"tc\">제목+내용</option>\r\n" + 
							"				</select> <input type=\"text\" name=\"search\" value=\"${paging.cri.search}\">\r\n" + 
							"				<input type=\"submit\" value=\"찾기\">\r\n" + 
							"			</form>\r\n" + 
							"			<br> <br>\r\n" + 
							"			<div id='paging'>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/boardmanagement?pageNum=1&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">처음으로</a>\r\n" + 
							"				<c:if test=\"${paging.prev}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/boardmanagement?pageNum=${paging.endPage-10}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">이전</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<c:forEach begin=\"${paging.startPage}\" end=\"${paging.endPage}\"\r\n" + 
							"					var=\"num\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/boardmanagement?pageNum=${num}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">${num}</a>\r\n" + 
							"				</c:forEach>\r\n" + 
							"				<c:if test=\"${paging.next}\">\r\n" + 
							"					<a\r\n" + 
							"						href=\"/${url}/boardmanagement?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">다음</a>\r\n" + 
							"				</c:if>\r\n" + 
							"				<a\r\n" + 
							"					href=\"/${url}/boardmanagement?pageNum=${paging.realEnd}&amount=${paging.cri.amount}&type=${paging.cri.type}&search=${paging.cri.search}\">맨끝으로</a>\r\n" + 
							"			</div>\r\n"+
							"		</div>\r\n"+
							"		<div id='footer'></div>\r\n"+
							"	</div>\r\n"+
							"	<div id=\"chat_btn\">💬</div>\r\n" + 
							"	<iframe id=\"if\"	width=\"400\" height=\"500\" src=\"http://localhost:8080/chat?chat_url=${url}&id=${"+url+"_userId}\"></iframe>\r\n" + 
							"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
							"    <script src=\"../resources/color_picker/jquery.minicolors.js\"></script>\r\n" + 
							"	<script src=\"../resources/js/url_chat.js\"></script>\r\n"+
							"    <script src=\"../resources/js/url_boardmanagement.js\"></script>\r\n"+
							"</body>\r\n"+
							"</html>");
					bw.close();

				}else {
					System.out.println("boardmanagement File already exists");
				}
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// ewb main에서 생성되어있는 페이지를 불러오는 과정
	@RequestMapping(value = "/loadpage", method = RequestMethod.GET)
	public void loadPage(HttpSession session, String url, String opt) {
		session.setAttribute("url",url);
		session.setAttribute("opt",opt);
	}
	// management 페이지
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public void management(HttpSession session) {
		
	}
	// chat 페이지
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String view_chat(String id ,String chat_url,HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {
		session.setAttribute("url", chat_url);
		session.setAttribute("userId", id);
		model.addAttribute("lastchat",hs.lastchat(chat_url));
		return "/chat";
	}
	// 채팅 보낼 때 DB에 저장
	@RequestMapping(value = "/insertchat", method = RequestMethod.POST)
	public ResponseEntity<String> insertChat(@RequestBody ChatVO cvo){
		int result = hs.insertChat(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// url home 페이지
	@RequestMapping(value = "/{url}/home", method = RequestMethod.GET)
	public void urlHome(@PathVariable String url, HttpSession session) {
		session.setAttribute("url", url);
		
		MemberVO mvo = new MemberVO();
		mvo.setId((String)session.getAttribute(url+"_userId"));
		mvo.setUrl(url);
		
		if(us.dupCheck(mvo) == null && session.getAttribute(url+"_userId") != null) {
			session.removeAttribute(url+"_userId");
		}
	}
	// 페이지 내용을 DB에 저장
	@RequestMapping(value = "/savecontent", method = RequestMethod.POST)
	public ResponseEntity<String> saveContent(@RequestBody ContentVO cvo){
		int result = es.saveContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// 페이지 수정 내용을 DB에 저장
	@RequestMapping(value = "/modifycontent", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyContent(@RequestBody ContentVO cvo){
		int result = es.modifyContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// 페이지 삭제
	@RequestMapping(value = "/deletecontent", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteContent(@RequestBody ContentVO cvo){
		//String uploadFolder = "C:\\Users\\master\\Desktop\\sp\\test\\src\\main\\webapp\\WEB-INF\\views";
		String uploadFolder = "D:\\01-STUDY\\workspace\\ewb\\src\\main\\webapp\\WEB-INF\\views";
//		String uploadFolder = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ewb\\WEB-INF\\views";
		File file = new File(uploadFolder+"\\"+cvo.getUrl());
		// 해당 경로에 파일이 있으면 전부 삭제
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
			// DB table 삭제
			String target_mem = "member_"+cvo.getUrl();
			String target_chat = "chat_"+cvo.getUrl();
			String target_pro = "product_"+cvo.getUrl();
			String target_prot = "product_type_"+cvo.getUrl();
			String target_brd = "board_"+cvo.getUrl();
			String target_brdt = "board_type_"+cvo.getUrl();
			String target_reply = "reply_"+cvo.getUrl();
			String target_rereply = "rereply_"+cvo.getUrl();
			String target_proi = "product_img_"+cvo.getUrl();
			String target_cart = "cart_"+cvo.getUrl();
			String target_pay = "payment_"+cvo.getUrl();
			String target_order = "order_"+cvo.getUrl();
			String target_rev = "review_"+cvo.getUrl();
			String target_revi = "review_img_"+cvo.getUrl();
			String target_des = "destination_"+cvo.getUrl();
			String target_like = "like_"+cvo.getUrl();
			
			
			es.dropTable(target_like);
			es.dropTable(target_des);
			es.dropTable(target_revi);
			es.dropTable(target_rev);
			es.dropTable(target_order);
			es.dropTable(target_pay);
			es.dropTable(target_cart);
			es.dropTable(target_proi);
			es.dropTable(target_rereply);
			es.dropTable(target_reply);
			es.dropTable(target_brdt);
			es.dropTable(target_brd);
			es.dropTable(target_prot);
			es.dropTable(target_pro);
			es.dropTable(target_chat);
			es.dropTable(target_mem);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// DB에 저장된 페이지 수정 내용 삭제
		int result = es.deleteContent(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// DB에 저장된 페이지 수정 내용 불러오기
	@RequestMapping(value = "/loadcontent", method = RequestMethod.GET)
	public ResponseEntity<ContentVO> loadContent(ContentVO cvo){

		return new ResponseEntity<>(es.loadContent(cvo),HttpStatus.OK);
	}
}