package org.ewb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.BoardVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.PageVO;
import org.ewb.model.ReplyVO;
import org.ewb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	@Autowired
	BoardService bs;
	
	@RequestMapping(value = "/{url}/board", method = RequestMethod.GET)
	public void urlBoard(CriteriaVO cri, Model model, HttpSession session) {
		cri.setAmount(10);
		try {
			model.addAttribute("boardlist",bs.boardlist(cri));
			model.addAttribute("paging", new PageVO(cri, bs.boardlistMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, bs.boardlistMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{url}/boardwrite", method = RequestMethod.GET)
	public void urlBoardWrite() {
		
	}
	
	@RequestMapping(value = "/{url}/boarddetail", method = RequestMethod.GET)
	public void urlBoardDetail(BoardVO bvo, Model model) {
		model.addAttribute("detail", bs.boardDetail(bvo));
	}
	
	@RequestMapping(value = "/{url}/modifyboard", method = RequestMethod.GET)
	public void urlModifyBoard(BoardVO bvo, Model model) {
		model.addAttribute("detail", bs.boardDetail(bvo));
	}
	
	@RequestMapping(value = "/{url}/checkwrite", method = RequestMethod.GET)
	public void urlCheckWrite(CriteriaVO cri, Model model, HttpSession session) {
		cri.setAmount(10);
		cri.setArray((String)session.getAttribute(cri.getUrl()+"_userId"));
		try {
			model.addAttribute("cw",bs.checkWrite(cri));
			model.addAttribute("paging", new PageVO(cri, bs.checkWriteMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, bs.checkWriteMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{url}/checkreply", method = RequestMethod.GET)
	public void urlCheckReply(CriteriaVO cri, Model model, HttpSession session) {
		cri.setAmount(10);
		cri.setArray((String)session.getAttribute(cri.getUrl()+"_userId"));
		try {
			model.addAttribute("cr",bs.checkReply(cri));
			model.addAttribute("paging", new PageVO(cri, bs.checkReplyMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, bs.checkReplyMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/writeboard", method = RequestMethod.POST)
	public ResponseEntity<String> writeBoard(@RequestBody BoardVO bvo) {
		int result = bs.writeBoard(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/writereply", method = RequestMethod.POST)
	public ResponseEntity<String> writeReply(@RequestBody ReplyVO rvo) {
		int result = bs.writeReply(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/loadreply", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReplyVO>> loadReply(ReplyVO rvo) {
		return new ResponseEntity<>(bs.loadReply(rvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletereply", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReply(@RequestBody ReplyVO rvo) {
		int result = bs.deleteReply(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/deleteboard", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@RequestBody BoardVO bvo) {
		int result = bs.deleteBoard(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updateboard", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBoard(@RequestBody BoardVO bvo) {
		int result = bs.updateBoard(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/countboard", method = RequestMethod.PUT)
	public ResponseEntity<String> countBoard(@RequestBody BoardVO bvo) {
		int result = bs.countBoard(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/checklike", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> checkLike(BoardVO bvo) {
		return new ResponseEntity<>(bs.checkLike(bvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/llike", method = RequestMethod.POST)
	public ResponseEntity<String> llike(@RequestBody BoardVO bvo) {
		int result = bs.llike(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/unlike", method = RequestMethod.DELETE)
	public ResponseEntity<String> unlike(@RequestBody BoardVO bvo) {
		int result = bs.unlike(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/countlike", method = RequestMethod.PUT)
	public ResponseEntity<String> countLike(@RequestBody BoardVO bvo) {
		int result = bs.countLike(bvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
