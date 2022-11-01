package org.ewb.model;

public class ReviewVO {
	private String id;
	private String url;
	private String content;
	private String review_date;
	private int rno;
	private int pno;
	private ThumbnailVO tvo;
	
	public ThumbnailVO getTvo() {
		return tvo;
	}
	public void setTvo(ThumbnailVO tvo) {
		this.tvo = tvo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	@Override
	public String toString() {
		return "ReviewVO [id=" + id + ", url=" + url + ", content=" + content + ", review_date=" + review_date
				+ ", rno=" + rno + ", pno=" + pno + ", tvo=" + tvo + "]";
	}
	
	
}
