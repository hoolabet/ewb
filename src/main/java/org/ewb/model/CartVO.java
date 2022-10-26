package org.ewb.model;

public class CartVO {
	private String id;
	private int pno;
	private int b_quantity;
	private String add_date;
	private boolean doOrder;
	private String url;
	private ProductVO pvo;
	
	public ProductVO getPvo() {
		return pvo;
	}
	public void setPvo(ProductVO pvo) {
		this.pvo = pvo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getB_quantity() {
		return b_quantity;
	}
	public void setB_quantity(int b_quantity) {
		this.b_quantity = b_quantity;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	public boolean isDoOrder() {
		return doOrder;
	}
	public void setDoOrder(boolean doOrder) {
		this.doOrder = doOrder;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "CartVO [id=" + id + ", pno=" + pno + ", b_quantity=" + b_quantity + ", add_date=" + add_date
				+ ", doOrder=" + doOrder + ", url=" + url + ", pvo=" + pvo + "]";
	}
	
	
}
