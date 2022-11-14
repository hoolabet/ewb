package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.BoardMapper;
import org.ewb.model.BoardVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardMapper bm;
	
	public int writeBoard(BoardVO bvo) {
		return bm.writeBoard(bvo);
	}
	
	public ArrayList<BoardVO> boardlist(CriteriaVO cri){
		return bm.boardlist(cri);
	}

	public int boardlistMaxNumSearch(CriteriaVO cri) {
		return bm.boardlistMaxNumSearch(cri);
	}
	
	public BoardVO boardDetail(BoardVO bvo) {
		return bm.boardDetail(bvo);
	}
	
	public int writeReply(ReplyVO rvo) {
		return bm.writeReply(rvo);
	}

	public ArrayList<ReplyVO> loadReply(ReplyVO rvo){
		return bm.loadReply(rvo);
	}
	
	public int deleteReply(ReplyVO rvo) {
		return bm.deleteReply(rvo);
	}
	
	public int deleteBoard(BoardVO bvo) {
		return bm.deleteBoard(bvo);
	}

	public int updateBoard(BoardVO bvo) {
		return bm.updateBoard(bvo);
	}
	
	public int countBoard(BoardVO bvo) {
		return bm.countBoard(bvo);
	}
}
