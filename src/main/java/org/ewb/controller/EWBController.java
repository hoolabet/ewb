package org.ewb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.servlet.http.HttpSession;

import org.ewb.model.MemberVO;
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
		session.setAttribute("url", "");
	}

	@RequestMapping(value = "/newpage", method = RequestMethod.GET)
	public void newPage(String id,String url, String option, HttpSession session) {
		session.setAttribute("url", url);
		
//		String uploadFolder = "C:\\Users\\master\\Desktop\\sp\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		String uploadFolder = "D:\\01-STUDY\\e\\ewb\\src\\main\\webapp\\WEB-INF\\views";
		File uploadPath = new File(uploadFolder, url);
		if(!uploadPath.exists()) {
			System.out.println("jsp Folder created");
			uploadPath.mkdirs();
		}else {
			System.out.println("jsp Folder already exists");
		}
		File jsp = new File(uploadFolder+"\\"+url+"\\home.jsp");
		try {
			if(jsp.createNewFile()) {
				System.out.println("jsp File created");
				FileWriter fw = new FileWriter(jsp);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
						"    pageEncoding=\"UTF-8\"%>\r\n" + 
						"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>      \r\n" + 
						"<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"UTF-8\">\r\n" + 
						"<title>"+option+" home</title>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" +
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n"+
						"<script src=\"../resources/js/"+option+".js\">"+
						"</script>\r\n"+
						"</body>\r\n" + 
						"</html>");
				bw.close();
			}else {
				System.out.println("jsp File already exists");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{url}/home", method = RequestMethod.GET)
	public void urlHome() {
	
	}
	
}
