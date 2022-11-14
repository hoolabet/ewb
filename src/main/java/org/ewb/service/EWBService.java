package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.BoardVO;
import org.ewb.model.CartVO;
import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.DestinationVO;
import org.ewb.model.MemberVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReplyVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;

public interface EWBService {

	public MemberVO ewbLogin(MemberVO mvo);

	public int ewbSignUp(MemberVO mvo);

	public int saveContent(ContentVO cvo);

	public ContentVO loadContent(ContentVO cvo);

	public int modifyContent(ContentVO cvo);

	public int deleteContent(ContentVO cvo);

	public ArrayList<ContentVO> getPage(String id);

	public void createTable(String create_table);

	public void createFirstAccount(MemberVO mvo);

	public void dropTable(String target);

	public ArrayList<CartVO> loadCart(CartVO cvo);

}
