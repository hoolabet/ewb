package org.ewb.service;

import org.ewb.mapper.OrderMapper;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderMapper om;
	
	public int payment(PaymentVO pvo) {
		return om.payment(pvo);
	}
	
	public int insertOrder(OrderVO ovo) {
		return om.insertOrder(ovo);
	}
	
	public int updateQuan(ProductVO pvo) {
		return om.updateQuan(pvo);
	}
}
