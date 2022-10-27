package org.ewb.mapper;

import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;

public interface OrderMapper {

	public int payment(PaymentVO pvo);

	public int insertOrder(OrderVO ovo);

	public int updateQuan(ProductVO pvo);

}
