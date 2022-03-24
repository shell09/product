package com.kh.product.web.api;

import lombok.Data;

@Data
public class ProductRequest {
  private String productName;
  private Long productPrice;
  private Long productFig;
}
