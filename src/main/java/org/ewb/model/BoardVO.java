package org.ewb.model;

public class BoardVO {
	private int bno;
	private String id;
	private String bname;
	private String content;
	private String reg_date;
	private int type;
	private int cnt;
	private int like_;
	private String url;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public int getLike_() {
		return like_;
	}
	public void setLike_(int like_) {
		this.like_ = like_;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", id=" + id + ", bname=" + bname + ", content=" + content + ", reg_date="
				+ reg_date + ", type=" + type + ", cnt=" + cnt + ", like_=" + like_ + ", url=" + url + "]";
	}
	
	
}
