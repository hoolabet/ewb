package org.ewb.service;

import org.ewb.mapper.HandlerMapper;
import org.ewb.model.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerServiceImpl implements HandlerService{
	@Autowired
	HandlerMapper hm;
	
	public void insertChat(ChatVO cvo) {
		hm.insertChat(cvo);
	}
	
}
