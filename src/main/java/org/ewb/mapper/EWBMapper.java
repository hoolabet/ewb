package org.ewb.mapper;

import org.ewb.model.MemberVO;

public interface EWBMapper {
	public MemberVO ewbLogin(MemberVO mvo);

	public int ewbSignUp(MemberVO mvo);
}
