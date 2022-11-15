package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.UserMapper;
import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.DestinationVO;
import org.ewb.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper um;
	
	public int insertReg(ContentVO cvo) {
		return um.insertReg(cvo);
	}

	public int updateReg(ContentVO cvo) {
		return um.updateReg(cvo);
	}

	public ContentVO loadReg(ContentVO cvo) {
		return um.loadReg(cvo);
	}
	
	public int insertDes(DestinationVO dvo) {
		return um.insertDes(dvo);
	}

	public int updateDes(DestinationVO dvo) {
		return um.updateDes(dvo);
	}

	public int deleteDes(DestinationVO dvo) {
		return um.deleteDes(dvo);
	}

	public ArrayList<DestinationVO> loadDes(DestinationVO dvo){
		return um.loadDes(dvo);
	}
	
	public DestinationVO loadDes1(DestinationVO dvo) {
		return um.loadDes1(dvo);
	}
	
	public MemberVO dupCheck(MemberVO mvo) {
		return um.dupCheck(mvo);
	}
	
	public int signUp(MemberVO mvo) {
		return um.signUp(mvo);
	}
	
	public MemberVO login(MemberVO mvo) {
		return um.login(mvo);
	}
	
	public int banUser(MemberVO mvo) {
		return um.banUser(mvo);
	}
	
	public MemberVO loadUserInfo(MemberVO mvo) {
		return um.loadUserInfo(mvo);
	}
	
	public int modifyProfile(MemberVO mvo) {
		return um.modifyProfile(mvo);
	}
	
	public ArrayList<MemberVO> memberList(CriteriaVO cri){
		return um.memberList(cri);
	}
	
	
	public int memberMaxNumSearch(CriteriaVO cri) {
		return um.memberMaxNumSearch(cri);
	}
	
	public int updateStatus(MemberVO mvo) {
		return um.updateStatus(mvo);
	}
}
