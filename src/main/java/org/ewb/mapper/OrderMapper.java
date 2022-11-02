package org.ewb.mapper;

import java.util.ArrayList;

import org.ewb.model.CartVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;

public interface OrderMapper {

	public int payment(PaymentVO pvo);

	public int insertOrder(OrderVO ovo);

	public int updateQuan(ProductVO pvo);

	public ArrayList<OrderVO> orderlist(OrderVO ovo);

	public int directOrder1(CartVO cvo);

	public int directOrder2(CartVO cvo);

}
