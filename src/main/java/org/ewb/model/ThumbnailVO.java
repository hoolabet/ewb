package org.ewb.model;

public class ThumbnailVO {
	private int pno;
	private String fullpath;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getFullpath() {
		return fullpath;
	}
	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
	@Override
	public String toString() {
		return "ThumbnailVO [pno=" + pno + ", fullpath=" + fullpath + ", url=" + url + "]";
	}
	
	
}
