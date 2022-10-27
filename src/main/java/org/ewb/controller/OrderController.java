package org.ewb.controller;

import java.util.ArrayList;

import org.ewb.model.CartVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.ewb.service.EWBService;
import org.ewb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	@Autowired
	OrderService os;
	
	@Autowired
	EWBService es;
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public ResponseEntity<String> payment(@RequestBody PaymentVO pvo) {
		int result = os.payment(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/searchordered", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CartVO>> searchCart(CartVO cvo) {
		return new ResponseEntity<>(es.loadCart(cvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/insertorder", method = RequestMethod.POST)
	public ResponseEntity<String> insertOrder(@RequestBody OrderVO ovo) {
		int result = os.insertOrder(ovo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updatequan", method = RequestMethod.PUT)
	public ResponseEntity<String> updateQuan(@RequestBody ProductVO pvo) {
		int result = os.updateQuan(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
