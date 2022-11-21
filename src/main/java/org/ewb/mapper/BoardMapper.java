package org.ewb.mapper;

import java.util.ArrayList;

import org.ewb.model.BoardVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.ReplyVO;

public interface BoardMapper {

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

	public int llike(BoardVO bvo);

	public int unlike(BoardVO bvo);

	public BoardVO checkLike(BoardVO bvo);

	public int countLike(BoardVO bvo);

	public ArrayList<BoardVO> checkWrite(CriteriaVO cri);

	public int checkWriteMaxNumSearch(CriteriaVO cri);

	public ArrayList<ReplyVO> checkReply(CriteriaVO cri);

	public int checkReplyMaxNumSearch(CriteriaVO cri);

}
