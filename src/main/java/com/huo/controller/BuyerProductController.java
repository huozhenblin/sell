package com.huo.controller;

import com.huo.entity.ProductCategory;
import com.huo.entity.ProductInfo;
import com.huo.service.CategoryService;
import com.huo.service.ProductService;
import com.huo.until.ResultVOUtil;
import com.huo.vo.ProductInfoVO;
import com.huo.vo.ProductVO;
import com.huo.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Huo
 * @Description: 商品信息控制层
 * @Date: Create in 13:12 2020/4/23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
@Autowired
private CategoryService categoryService;
    @GetMapping("list")
    public ResultVO list() {
        //1.查询所有上架产品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
//        传统方法
//        for (ProductInfo pro : upAll){
//            categoryTypeList.add(pro.getCategoryType());
//        }
//        精简做法lambom
        List<Integer> categoryTypeList = productInfoList.stream().
                map((e -> e.getCategoryType())).
                collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory  productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
//   设置商品详情
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
//        进行封装
//        ResultVO result = new ResultVO();
//        result.setData(productVOList);
//        result.setCode(200);
//        result.setMsg("成功");

        return ResultVOUtil.success(productVOList);
    }

}
