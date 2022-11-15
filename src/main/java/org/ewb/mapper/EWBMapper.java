package org.ewb.mapper;

import java.util.ArrayList;

import org.ewb.model.CartVO;
import org.ewb.model.ContentVO;
import org.ewb.model.MemberVO;

public interface EWBMapper {
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
