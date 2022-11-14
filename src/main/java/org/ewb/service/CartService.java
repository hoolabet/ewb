package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.CartVO;

public interface CartService {
	public ArrayList<CartVO> loadCart(CartVO cvo);
	
	public CartVO searchCart(CartVO cvo);

	public int insertCart(CartVO cvo);

	public int updateCart(CartVO cvo);

	public int orderSelected(CartVO cvo);

	public int deleteCart(CartVO cvo);

}
