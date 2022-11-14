package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.BoardVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.ReplyVO;

public interface BoardService {
	public int writeBoard(BoardVO bvo);

	public ArrayList<BoardVO> boardlist(CriteriaVO cri);

	public int boardlistMaxNumSearch(CriteriaVO cri);

	public BoardVO boardDetail(BoardVO bvo);

	public int writeReply(ReplyVO rvo);

	public ArrayList<ReplyVO> loadReply(ReplyVO rvo);

	public int deleteReply(ReplyVO rvo);

	public int deleteBoard(BoardVO bvo);

	public int updateBoard(BoardVO bvo);

	public int countBoard(BoardVO bvo);
}
