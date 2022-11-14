package org.ewb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.ewb.model.CriteriaVO;
import org.ewb.model.PageVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;
import org.ewb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@RequestMapping(value = "/{url}/product", method = RequestMethod.GET)
	public void urlProduct(HttpSession session, Model model, CriteriaVO cri) {

		try {
			model.addAttribute("product",ps.productList(cri));
			model.addAttribute("paging", new PageVO(cri, ps.productMaxNumSearch(cri)));
			session.setAttribute("criValue", new PageVO(cri, ps.productMaxNumSearch(cri)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{url}/productwrite", method = RequestMethod.GET)
	public void urlProductWrite(@PathVariable String url, HttpSession session) {
		
	}

	@RequestMapping(value = "/{url}/productdetail", method = RequestMethod.GET)
	public void urlProductDetail(ProductVO pvo, Model model) {
		model.addAttribute("detail", ps.loadProductDetail(pvo));
	}
	
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	public ResponseEntity<ProductVO> productDetail(ProductVO pvo) {
		return new ResponseEntity<>(ps.loadProductDetail(pvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{url}/modifyproduct", method = RequestMethod.GET)
	public void urlModifyProduct(ProductVO pvo, Model model) {
		model.addAttribute("modify", ps.loadProductDetail(pvo));
	}
	
	@RequestMapping(value = "/writeproduct", method = RequestMethod.POST)
	public ResponseEntity<String> writeProduct(@RequestBody ProductVO pvo) {
		int result = ps.writeProduct(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/modifyproduct", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyProduct(@RequestBody ProductVO pvo) {
		int result = ps.modifyProduct(pvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/savethumbnail", method = RequestMethod.POST)
	public ResponseEntity<String> saveThumbnail(@RequestBody ThumbnailVO tvo) {
		int result = ps.saveThumbnail(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/savethumbnail2", method = RequestMethod.POST)
	public ResponseEntity<String> saveThumbnail2(@RequestBody ThumbnailVO tvo) {
		int result = ps.saveThumbnail2(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/modifythumbnail", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyThumbnail(@RequestBody ThumbnailVO tvo) {
		int result = ps.modifyThumbnail(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@RequestBody ProductVO pvo) {
		if(pvo.getReg_date().equals("true")) {
			int result = ps.deleteProduct(pvo);
			return result==1? new ResponseEntity<>("success",HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/writereview", method = RequestMethod.POST)
	public ResponseEntity<String> writeReview(@RequestBody ReviewVO rvo) {
		int result = ps.writeReview(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/savereviewimg", method = RequestMethod.POST)
	public ResponseEntity<String> saveReviewImg(@RequestBody ThumbnailVO tvo) {
		int result = ps.saveReviewImg(tvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/loadreview", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReviewVO>> loadReview(ReviewVO rvo) {
		return new ResponseEntity<>(ps.loadReview(rvo),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletereview", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@RequestBody ReviewVO rvo) {
		int result = ps.deleteReview(rvo);
		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
