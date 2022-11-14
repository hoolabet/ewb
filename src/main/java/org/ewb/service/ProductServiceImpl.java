package org.ewb.service;

import java.util.ArrayList;

import org.ewb.mapper.ProductMapper;
import org.ewb.model.CriteriaVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductMapper pm;
	
	public ArrayList<ProductVO> productList(CriteriaVO cri){
		return pm.productList(cri);
	}
	
	public int productMaxNumSearch(CriteriaVO cri) {
		return pm.productMaxNumSearch(cri);
	}
	
	public int writeProduct(ProductVO pvo) {
		return pm.writeProduct(pvo);
	}
	
	public ProductVO loadProductDetail(ProductVO pvo) {
		return pm.loadProductDetail(pvo);
	}
	
	public int saveThumbnail(ThumbnailVO tvo) {
		return pm.saveThumbnail(tvo);
	}
	
	public int deleteProduct(ProductVO pvo) {
		return pm.deleteProduct(pvo);
	}
	
	public int modifyThumbnail(ThumbnailVO tvo) {
		return pm.modifyThumbnail(tvo);
	}
	
	public int modifyProduct(ProductVO pvo) {
		return pm.modifyProduct(pvo);
	}
	
	public int saveThumbnail2(ThumbnailVO tvo) {
		return pm.saveThumbnail2(tvo);
	}
	
	public int writeReview(ReviewVO rvo) {
		return pm.writeReview(rvo);
	}

	public int saveReviewImg(ThumbnailVO tvo) {
		return pm.saveReviewImg(tvo);
	}
	
	public ArrayList<ReviewVO> loadReview(ReviewVO rvo){
		return pm.loadReview(rvo);
	}
	
	public int deleteReview(ReviewVO rvo) {
		return pm.deleteReview(rvo);
	}
	
}
