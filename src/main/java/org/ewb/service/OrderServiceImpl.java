package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.OrderMapper;
import org.ewb.model.CartVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderMapper om;
	
	public ArrayList<PaymentVO> orderlist(CriteriaVO cri){
		return om.orderlist(cri);
	}
	
	public int orderlistMaxNumSearch(CriteriaVO cri) {
		return om.orderlistMaxNumSearch(cri);
	}
	
	public ArrayList<PaymentVO> orderlistAll(CriteriaVO cri){
		return om.orderlistAll(cri);
	}

	public int orderlistAllMaxNumSearch(CriteriaVO cri) {
		return om.orderlistAllMaxNumSearch(cri);
	}
	
	public int payment(PaymentVO pvo) {
		return om.payment(pvo);
	}
	
	public int insertOrder(OrderVO ovo) {
		return om.insertOrder(ovo);
	}
	
	public int updateQuan(ProductVO pvo) {
		return om.updateQuan(pvo);
	}
	
	public ArrayList<OrderVO> orderlist1(OrderVO ovo){
		return om.orderlist1(ovo);
	}
	
	public int directOrder1(CartVO cvo) {
		return om.directOrder1(cvo);
	}

	public int directOrder2(CartVO cvo) {
		return om.directOrder2(cvo);
	}
}
