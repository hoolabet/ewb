package org.ewb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.DestinationVO;
import org.ewb.model.MemberVO;
import org.ewb.model.PageVO;
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
public class UserController {
	@Autowired
	UserService us;
	
	@RequestMapping(value = "/{url}/member", method = RequestMethod.GET)
	public void urlMember(CriteriaVO cri, HttpSession session, Model model) {
		try {
			model.addAttribute("member",us.memberList(cri));
			model.addAttribute("paging", new PageVO(cri, us.memberMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, us.memberMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{url}/signup", method = RequestMethod.GET)
	public void urlSignUp(@PathVariable String url, HttpSession session) {
		
	}

	@RequestMapping(value = "/{url}/login", method = RequestMethod.GET)
	public void urlLogin(@PathVariable String url, HttpSession session) {
		
	}
	
	@RequestMapping(value = "/{url}/mypage", method = RequestMethod.GET)
	public void urlMyPage() {
		
	}
	
	@RequestMapping(value = "/{url}/modifyprofile", method = RequestMethod.GET)
	public void urlModifyProfile() {
		
	}
	
	@RequestMapping(value = "/insertreg", method = RequestMethod.POST)
	public ResponseEntity<String> insertReg(@RequestBody ContentVO cvo){
		int result = us.insertReg(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/updatereg", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReg(@RequestBody ContentVO cvo){
		int result = us.updateReg(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/loadreg", method = RequestMethod.GET)
	public ResponseEntity<ContentVO> loadReg(ContentVO cvo){

		return new ResponseEntity<>(us.loadReg(cvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dupcheck", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> dupCheck(MemberVO mvo, HttpSession session) {
		return new ResponseEntity<>(us.dupCheck(mvo),HttpStatus.OK);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody MemberVO mvo) {

		int result = us.signUp(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> login(MemberVO mvo, HttpSession session) {
		session.setAttribute("userId", us.login(mvo).getId());
		session.setAttribute("userInfo", us.login(mvo));
		return new ResponseEntity<>(us.login(mvo),HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userInfo");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/banuser", method = RequestMethod.DELETE)
	public ResponseEntity<String> banUser(@RequestBody MemberVO mvo) {
		int result = us.banUser(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/insertdes", method = RequestMethod.POST)
	public ResponseEntity<String> insertDes(@RequestBody DestinationVO dvo) {
		int result = us.insertDes(dvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updatedes", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDes(@RequestBody DestinationVO dvo) {
		int result = us.updateDes(dvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/deletedes", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDes(@RequestBody DestinationVO dvo) {
		int result = us.deleteDes(dvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/loaddes", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<DestinationVO>> loadDes(DestinationVO dvo) {
		return new ResponseEntity<>(us.loadDes(dvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/loaddes1", method = RequestMethod.GET)
	public ResponseEntity<DestinationVO> loadDes1(DestinationVO dvo) {
		return new ResponseEntity<>(us.loadDes1(dvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/loaduserinfo", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> loadUserInfo(MemberVO mvo) {
		return new ResponseEntity<>(us.loadUserInfo(mvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modifyprofile", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyProfile(@RequestBody MemberVO mvo) {
		int result = us.modifyProfile(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updatestatus", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStatus(@RequestBody MemberVO mvo) {
		int result = us.updateStatus(mvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
