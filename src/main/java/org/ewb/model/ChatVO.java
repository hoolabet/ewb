package org.ewb.model;

public class ChatVO {
	private String url;
	private String id;
	private String content;
	private String chat_date;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getChat_date() {
		return chat_date;
	}
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	@Override
	public String toString() {
		return "ChatVO [url=" + url + ", id=" + id + ", content=" + content + ", chat_date=" + chat_date + "]";
	}
	
	
}
