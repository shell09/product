package com.kh.product.domain.svc;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  // 상품등록
  @Override
  public Long write(Product product) {
    return productDAO.write(product);
  }

  // 전체조회
  @Override
  public List<Product> findAll() {

    return productDAO.findAll();
  }

  // 상세조회
  @Override
  public Product selectOne(Long productId) {

    return productDAO.selectOne(productId);
  }

  // 수정
  @Override
  public int modify(Long productId, Product product) {

    return productDAO.modify(productId,product);
  }

  // 삭제
  @Override
  public int delete(Long productId) {

    return productDAO.delete(productId);
  }
}
