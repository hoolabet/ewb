package org.ewb.model;

public class ContentVO {
	private String id;
	private String url;
	private String content;
	private String type;
	private String opt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ContentVO [id=" + id + ", url=" + url + ", content=" + content + ", type=" + type + ", opt=" + opt
				+ "]";
	}
	
	
}