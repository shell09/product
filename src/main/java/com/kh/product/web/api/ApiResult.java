package com.kh.product.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ApiResult<T> {
  private String rtcd;
  private String rtmsg;
  private T data;
}
