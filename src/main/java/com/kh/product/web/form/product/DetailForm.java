package com.kh.product.web.form.product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter@Getter
@ToString
public class DetailForm {
  private Long productId;
  private String productName;
  private Long productPrice;
  private Long productFig;
}
