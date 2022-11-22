package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.ChatVO;

public interface HandlerService {

	public int insertChat(ChatVO cvo);

	public ArrayList<ChatVO> lastchat(String chat_url);

}
