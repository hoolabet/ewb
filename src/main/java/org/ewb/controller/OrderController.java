package org.ewb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.CartVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PageVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.ewb.service.CartService;
import org.ewb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs;
	
	@RequestMapping(value = "/{url}/order", method = RequestMethod.GET)
	public void urlOrder(CartVO cvo, Model model, HttpSession session) {
		cvo.setId((String)session.getAttribute("userId"));
		cvo.setDoOrder(true);
		model.addAttribute("order", cs.loadCart(cvo));
	}
	
	@RequestMapping(value = "/{url}/orderlist", method = RequestMethod.GET)
	public void urlOrderList(CriteriaVO cri, Model model, HttpSession session) {
		cri.setSearch((String)session.getAttribute("userId"));
		cri.setAmount(5);
		try {
			model.addAttribute("orderlist",os.orderlist(cri));
			model.addAttribute("paging", new PageVO(cri, os.orderlistMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, os.orderlistMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{url}/ordermanagement", method = RequestMethod.GET)
	public void urlOrderManagement(CriteriaVO cri, Model model, HttpSession session) {
		cri.setAmount(10);
		try {
			model.addAttribute("orderlist",os.orderlistAll(cri));
			model.addAttribute("paging", new PageVO(cri, os.orderlistAllMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, os.orderlistAllMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public ResponseEntity<String> payment(@RequestBody PaymentVO pvo) {
		int result = os.payment(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/searchordered", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CartVO>> searchCart(CartVO cvo) {
		return new ResponseEntity<>(cs.loadCart(cvo),HttpStatus.OK);
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
	
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<OrderVO>> orderlist1(OrderVO ovo) {
		return new ResponseEntity<>(os.orderlist1(ovo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/directorder1", method = RequestMethod.PUT)
	public ResponseEntity<String> directOrder1(@RequestBody CartVO cvo) {
		int result = os.directOrder1(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/directorder2", method = RequestMethod.POST)
	public ResponseEntity<String> directOrder2(@RequestBody CartVO cvo) {
		int result = os.directOrder2(cvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
