package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.HandlerMapper;
import org.ewb.model.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerServiceImpl implements HandlerService{
	@Autowired
	HandlerMapper hm;
	
	public int insertChat(ChatVO cvo) {
		return hm.insertChat(cvo);
	}
	
	public ArrayList<ChatVO> lastchat(String chat_url){
		return hm.lastchat(chat_url);
	}
	
}
