package org.ewb.service;

import java.util.ArrayList;

import org.ewb.model.CartVO;
import org.ewb.model.ContentVO;
import org.ewb.model.CriteriaVO;
import org.ewb.model.MemberVO;
import org.ewb.model.OrderVO;
import org.ewb.model.PaymentVO;
import org.ewb.model.ProductVO;
import org.ewb.model.ReviewVO;
import org.ewb.model.ThumbnailVO;

public interface EWBService {

	public MemberVO ewbLogin(MemberVO mvo);

	public int ewbSignUp(MemberVO mvo);

	public int saveContent(ContentVO cvo);

	public ContentVO loadContent(ContentVO cvo);

	public int modifyContent(ContentVO cvo);

	public int deleteContent(ContentVO cvo);

	public ArrayList<ContentVO> getPage(String id);

	public void createTable(String create_table);

	public void createFirstAccount(MemberVO mvo);

	public void dropTable(String target);

	public MemberVO dupCheck(MemberVO mvo);

	public int signUp(MemberVO mvo);

	public MemberVO login(MemberVO mvo);

	public ArrayList<ProductVO> productList(CriteriaVO cri);

	public int productMaxNumSearch(CriteriaVO cri);

	public int writeProduct(ProductVO pvo);

	public ProductVO loadProductDetail(ProductVO pvo);

	public int saveThumbnail(ThumbnailVO tvo);

	public ArrayList<CartVO> loadCart(CartVO cvo);

	public int deleteProduct(ProductVO pvo);

	public int modifyThumbnail(ThumbnailVO tvo);

	public int modifyProduct(ProductVO pvo);

	public int saveThumbnail2(ThumbnailVO tvo);

	public ArrayList<PaymentVO> orderlist(CriteriaVO cri);

	public int orderlistMaxNumSearch(CriteriaVO cri);

	public int writeReview(ReviewVO rvo);

	public int saveReviewImg(ThumbnailVO tvo);

	public ArrayList<ReviewVO> loadReview(ReviewVO rvo);

	public int deleteReview(ReviewVO rvo);

	public MemberVO loadUserInfo(MemberVO mvo);

	public int modifyProfile(MemberVO mvo);





}
