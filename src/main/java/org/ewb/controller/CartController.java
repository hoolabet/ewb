package org.ewb.controller;


import org.ewb.model.CartVO;
import org.ewb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {
	@Autowired
	CartService cs;
	
	@RequestMapping(value = "/searchcart", method = RequestMethod.GET)
	public ResponseEntity<CartVO> searchCart(CartVO cvo) {
		return new ResponseEntity<>(cs.searchCart(cvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/insertcart", method = RequestMethod.POST)
	public ResponseEntity<String> insertCart(@RequestBody CartVO cvo) {
		int result = cs.insertCart(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updatecart", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCart(@RequestBody CartVO cvo) {
		int result = cs.updateCart(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/orderselected", method = RequestMethod.PUT)
	public ResponseEntity<String> orderSelected(@RequestBody CartVO cvo) {
		int result = cs.orderSelected(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/deletecart", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCart(@RequestBody CartVO cvo) {
		int result = cs.deleteCart(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
