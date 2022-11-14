package org.ewb.service;


import java.util.ArrayList;

import org.ewb.mapper.CartMapper;
import org.ewb.model.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartMapper cm;
	
	public ArrayList<CartVO> loadCart(CartVO cvo){
		return cm.loadCart(cvo);
	}
	
	public CartVO searchCart(CartVO cvo) {
		return cm.searchCart(cvo);
	}
	
	public int insertCart(CartVO cvo) {
		return cm.insertCart(cvo);
	}

	public int updateCart(CartVO cvo) {
		return cm.updateCart(cvo);
	}
	
	public int orderSelected(CartVO cvo) {
		return cm.orderSelected(cvo);
	}
	
	public int deleteCart(CartVO cvo) {
		return cm.deleteCart(cvo);
	}
	
}
