package com.kh.product.web;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.form.product.DetailForm;
import com.kh.product.web.form.product.SaveForm;
import com.kh.product.web.form.product.UpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductSVC productSVC;

  // 등록화면
  @GetMapping("")
  public String saveForm(Model model){
    model.addAttribute("saveForm",new SaveForm());
    return "product/saveform";
  }

  // 등록처리
  @PostMapping
  public String save(@Valid @ModelAttribute SaveForm saveForm,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes){

    if(bindingResult.hasErrors()){
      return "product/saveForm";
    }

    Product product = new Product();
    BeanUtils.copyProperties(saveForm, product);
    Long productId = productSVC.write(product);
    redirectAttributes.addAttribute("id", productId);
    return "redirect:/product/{id}/detail";
  }


  // 전체조회(/list)
  @GetMapping("/all")
  public String findALl(Model model){

    List<Product> list = productSVC.findAll();
    model.addAttribute("list",list);
    return "product/all";

  }


  // 상세조회(/productId)
  @GetMapping("/{id}/detail")
  public String selectOne(@PathVariable("id") Long productId, Model model){

    Product findProduct = productSVC.selectOne(productId);

    DetailForm detailForm = new DetailForm();
    BeanUtils.copyProperties(findProduct,detailForm);

    model.addAttribute("detailForm",detailForm);

    return "product/detailForm";
  }


  // 수정(/edit)
  //  수정화면
  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable ("id") Long productId, Model model){

    Product findProduct = productSVC.selectOne(productId);

    UpdateForm updateForm = new UpdateForm();
    BeanUtils.copyProperties(findProduct,updateForm);

    model.addAttribute("updateForm", updateForm);

    return "product/updateForm";
  }

  //  수정처리
  @PostMapping("/{id}/edit")
  public String edit(@PathVariable("id") Long productId,
                     @Valid @ModelAttribute UpdateForm updateForm,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes){

    if(bindingResult.hasErrors()){
      return "product/updateForm";
    }

    Product product = new Product();
    BeanUtils.copyProperties(updateForm,product);
    productSVC.modify(productId,product);

    redirectAttributes.addAttribute("id", productId);

    return "redirect:/product/{id}/detail";
  }


  // 삭제(delete)
  @GetMapping("/{id}/del")
  public String del(@PathVariable("id") Long productId){

    productSVC.delete(productId);

    return "redirect:/product/all";
  }
}
