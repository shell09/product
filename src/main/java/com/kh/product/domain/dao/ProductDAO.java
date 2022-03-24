package com.kh.product.domain.dao;

import java.util.List;

public interface ProductDAO {

  /**
   * 상품등록
   * @param product
   * @return
   */
  Long write(Product product);

  /**
   * 전체조회
   * @return
   */
  List<Product> findAll();


  /**
   * 상세조회
   * @param productId
   * @return
   */
  Product selectOne(Long productId);

  /**
   * 수정
   * @param product
   * @return
   */
  int modify(Long productId, Product product);

  /**
   * 삭제
   * @param productId
   * @return
   */
  int delete(Long productId);
}
