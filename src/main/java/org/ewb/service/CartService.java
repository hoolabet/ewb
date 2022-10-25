package org.ewb.service;

import org.ewb.model.CartVO;

public interface CartService {

	public CartVO searchCart(CartVO cvo);

	public int insertCart(CartVO cvo);

	public int updateCart(CartVO cvo);

}
