package org.ewb.mapper;

import java.util.ArrayList;

import org.ewb.model.ChatVO;

public interface HandlerMapper {

	public int insertChat(ChatVO cvo);

	public ArrayList<ChatVO> lastchat(String chat_url);

}
