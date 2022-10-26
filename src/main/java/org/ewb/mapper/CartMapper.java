package org.ewb.mapper;

import org.ewb.model.CartVO;

public interface CartMapper {

	public CartVO searchCart(CartVO cvo);

	public int insertCart(CartVO cvo);

	public int updateCart(CartVO cvo);

	public int orderSelected(CartVO cvo);

	public int deleteCart(CartVO cvo);

}
