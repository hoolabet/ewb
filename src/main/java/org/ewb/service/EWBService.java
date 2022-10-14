package org.ewb.service;

import org.ewb.model.MemberVO;

public interface EWBService {

	public MemberVO ewbLogin(MemberVO mvo);

	public int ewbSignUp(MemberVO mvo);

}
