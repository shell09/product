package com.kh.product.domain.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Product {
  private Long productId;
  private String productName;
  private Long productPrice;
  private Long productFig;
}
