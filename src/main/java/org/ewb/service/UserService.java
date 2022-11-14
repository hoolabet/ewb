package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.DestinationVO;
import org.ewb.model.MemberVO;

public interface UserService {

	public MemberVO dupCheck(MemberVO mvo);

	public int signUp(MemberVO mvo);

	public MemberVO login(MemberVO mvo);
	
	public int banUser(MemberVO mvo);
	
	public int insertReg(ContentVO cvo);

	public int updateReg(ContentVO cvo);

	public ContentVO loadReg(ContentVO cvo);

	public int insertDes(DestinationVO dvo);

	public int updateDes(DestinationVO dvo);

	public int deleteDes(DestinationVO dvo);

	public ArrayList<DestinationVO> loadDes(DestinationVO dvo);

	public DestinationVO loadDes1(DestinationVO dvo);
	
	public MemberVO loadUserInfo(MemberVO mvo);

	public int modifyProfile(MemberVO mvo);
	
	public ArrayList<MemberVO> memberList(CriteriaVO cri);

	public int memberMaxNumSearch(CriteriaVO cri);
}
