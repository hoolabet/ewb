package org.ewb.model;

public class RereplyVO {
	private int rrno;
	private int rno;
	private String id;
	private String content;
	private String rereply_date;
	private int type;
	private String url;
	public int getRrno() {
		return rrno;
	}
	public void setRrno(int rrno) {
		this.rrno = rrno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
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
	public String getRereply_date() {
		return rereply_date;
	}
	public void setRereply_date(String rereply_date) {
		this.rereply_date = rereply_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "RereplyVO [rrno=" + rrno + ", rno=" + rno + ", id=" + id + ", content=" + content + ", rereply_date="
				+ rereply_date + ", type=" + type + ", url=" + url + "]";
	}
	
	
}
