package com.kh.product.web;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import com.kh.product.web.api.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class APIProductController {

  private final ProductSVC productSVC;

  // 등록
  @PostMapping
  public ApiResult<Product> write(@RequestBody ProductRequest productRequest) {

    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    Long productId = productSVC.write(product);

    Product findProduct = null;
    if(productId > 0){
      findProduct = productSVC.selectOne(productId);
      BeanUtils.copyProperties(findProduct,product);
    }
    ApiResult<Product> result = new ApiResult<>("00","success",product);
    return result;

  }

    // 전체목록
  @GetMapping
  public ApiResult<List<Product>> findAll(){

    List<Product> products = productSVC.findAll();

    ApiResult<List<Product>> result = null;
    if(products.size() != 0){
      result = new ApiResult<>("00","success",products);
    }else{
      result = new ApiResult<>("99","fail",products);
    }

    return result;
  }

    // 상세조회
  @GetMapping("/{id}")
  public ApiResult<Product> selectOne(@PathVariable("id") Long productId){
    Product findProduct = productSVC.selectOne(productId);
    ApiResult<Product> result = null;
    if(findProduct != null){
      result = new ApiResult<>("00","success",findProduct);
    }else{
      result = new ApiResult<>("99","fail",findProduct);
    }
    return result;
  }


    // 수정
  @PatchMapping("/{id}")
  public ApiResult<Product> modify(
          @PathVariable("id") Long productId,
          @RequestBody ProductRequest productRequest){

    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    int affectedRow = productSVC.modify(productId, product);

    Product updateProduct = null;
    ApiResult<Product> result = null;
    if(affectedRow == 1){
      updateProduct = productSVC.selectOne(productId);
      BeanUtils.copyProperties(updateProduct,product);

      result = new ApiResult<>("00","success",product);
    }else{
      result = new ApiResult<>("99","fail",product);
    }
    return result;
  }

    // 삭제
  @DeleteMapping("{id}")
  public ApiResult<Boolean> delete(@PathVariable("id") Long productId){
    int affedtedRow = productSVC.delete(productId);

    ApiResult<Boolean> result = null;
    if(affedtedRow == 1){
      result = new ApiResult<>("00","success",true);
    }else{
      result = new ApiResult<>("99","fail",false);
    }
    return result;
  }

  }