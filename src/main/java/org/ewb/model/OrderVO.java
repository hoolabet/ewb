package org.ewb.model;

public class OrderVO {
	private String id;
	private int ono;
	private int pno;
	private int payno;
	private int b_quantity;
	private String order_date;
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
	public int getOno() {
		return ono;
	}
	public void setOno(int ono) {
		this.ono = ono;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getPayno() {
		return payno;
	}
	public void setPayno(int payno) {
		this.payno = payno;
	}
	public int getB_quantity() {
		return b_quantity;
	}
	public void setB_quantity(int b_quantity) {
		this.b_quantity = b_quantity;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", ono=" + ono + ", pno=" + pno + ", payno=" + payno + ", b_quantity=" + b_quantity
				+ ", order_date=" + order_date + ", url=" + url + ", pvo=" + pvo + "]";
	}
	
	
}
