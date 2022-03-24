package com.kh.product.domain.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품등록")
  void create() {
    Product product = new Product();
    product.setProductName("자전거");
    product.setProductPrice(700000L);
    product.setProductFig(10L);

    Long productId = productDAO.write(product);
    Product findProduct = productDAO.selectOne(productId);

    Assertions.assertThat(findProduct.getProductName()).isEqualTo("자전거");
    Assertions.assertThat(findProduct.getProductPrice()).isEqualTo(700000L);
    Assertions.assertThat(findProduct.getProductFig()).isEqualTo(10L);

  }


  @Test
  @DisplayName("전체조회")
  void selectAll() {

    List<Product> products = productDAO.findAll();
    Assertions.assertThat(products.size()).isEqualTo(6);
    log.info("products={}", products);

  }


  @Test
  @DisplayName("상세조회")
  void selectOne() {
    Long productId = 22L;
    Product product = productDAO.selectOne(productId);
    Assertions.assertThat(product).isNotNull();
    log.info("product={}",product);
  }

  @Test
  @DisplayName("수정")
  void update() {
    //when
    Long productId = 23L;
    Product findProduct = productDAO.selectOne(productId);
    findProduct.setProductName("자전거(수정중)");
    findProduct.setProductPrice(0L);
    findProduct.setProductFig(0L);

    //try
    productDAO.modify(productId,findProduct);

    //then
    Product updateProduct = productDAO.selectOne(productId);
    Assertions.assertThat(updateProduct.getProductName()).isEqualTo("자전거(수정중)");
    Assertions.assertThat(updateProduct.getProductPrice()).isEqualTo(0L);
    Assertions.assertThat(updateProduct.getProductFig()).isEqualTo(0L);


  }


  @Test
  @DisplayName("삭제")
  void delete() {
    //when
    Long productId = 23L;
    //try
    int cnt = productDAO.delete(productId);
    //then
    //Assertions.assertThat(cnt).isEqualTo(1);
    Assertions.assertThat(productDAO.selectOne(23L)).isNull();
  }
}