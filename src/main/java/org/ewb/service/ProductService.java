package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.CriteriaVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;

public interface ProductService {
	public ArrayList<ProductVO> productList(CriteriaVO cri);

	public int productMaxNumSearch(CriteriaVO cri);

	public int writeProduct(ProductVO pvo);

	public ProductVO loadProductDetail(ProductVO pvo);

	public int saveThumbnail(ThumbnailVO tvo);
	
	public int deleteProduct(ProductVO pvo);

	public int modifyThumbnail(ThumbnailVO tvo);

	public int modifyProduct(ProductVO pvo);

	public int saveThumbnail2(ThumbnailVO tvo);
	
	public int writeReview(ReviewVO rvo);

	public int saveReviewImg(ThumbnailVO tvo);

	public ArrayList<ReviewVO> loadReview(ReviewVO rvo);

	public int deleteReview(ReviewVO rvo);
}
