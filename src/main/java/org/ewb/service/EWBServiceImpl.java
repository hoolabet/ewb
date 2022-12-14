package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.EWBMapper;
import org.ewb.model.ContentVO;
import org.ewb.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EWBServiceImpl implements EWBService{
	@Autowired
	EWBMapper em;
	
	public MemberVO ewbLogin(MemberVO mvo) {
		return em.ewbLogin(mvo);
	}
	
	public int ewbSignUp(MemberVO mvo) {
		return em.ewbSignUp(mvo);
	}
	
	public int saveContent(ContentVO cvo) {
		return em.saveContent(cvo);
	}
	
	public ContentVO loadContent(ContentVO cvo) {
		return em.loadContent(cvo);
	}
	
	public int modifyContent(ContentVO cvo) {
		return em.modifyContent(cvo);
	}
	
	public int deleteContent(ContentVO cvo) {
		return em.deleteContent(cvo);
	}
	
	public ArrayList<ContentVO> getPage(String id){
		return em.getPage(id);
	}
	
	public void createTable(String create_table) {
		em.createTable(create_table);
	}
	
	public void createFirstAccount(MemberVO mvo) {
		em.createFirstAccount(mvo);
	}
	
	public void dropTable(String target) {
		em.dropTable(target);
	}
	
}
