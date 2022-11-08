package com.chixing.controller;

import com.chixing.entity.Shop;
import com.chixing.service.*;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.chixing.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangJiuJiu
 */
@RestController
@Slf4j
public class ShopController {
    @Autowired
    private IShopService shopService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IShopImgService shopImgService;
    @Autowired
    private ICouponService couponService;
    @Autowired
    private IShopCollectionService shopCollectionService;
    /**
     * 筛选页跳转查询店铺信息
     * @param shopId
     * @return
     */
    @GetMapping("/shop/{id}")
    public ModelAndView getById(@PathVariable("id") Integer shopId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String cusName = JwtUtil.getCusNameBySession(request);
        Integer cusId = JwtUtil.getCusIdBySession(request);
        mav.addObject("shop",shopService.getById(shopId));
        mav.addObject("skpros",foodService.getSKProById(shopId));
        mav.addObject("foods",foodService.getShopProByShopId(shopId));
        mav.addObject("sellfoods",foodService.getShopProByScore(shopId));
        mav.addObject("shopimgs",shopImgService.getShopSrc(shopId));
        mav.addObject("couponList",couponService.getByShopId(shopId));
        mav.addObject("conllectionStatus",shopCollectionService.userToCollection(shopId,cusId));
        mav.addObject("cusName",cusName);
        mav.addObject("cusId",cusId);
        mav.setViewName("details/details_shop");
        return mav;
    }

    @PostMapping("/shop")
    public int save(Shop shop){
        return shopService.save(shop);
    }

    @PostMapping("/shop/update")
    public int update(Shop shop){
        return shopService.update(shop);
    }

    @DeleteMapping("/shop")
    public int remove(@RequestParam("id") Integer shopId){
        return shopService.remove(shopId);
    }


    /**
     * 点击筛选项动态查询店铺信息
     * @param pageNum
     * @param foodType
     * @param foodPrice
     * @param sort
     * @param request
     * @return
     */
    @GetMapping("/shop/shopsift/{pageNum}")
    public ModelAndView getBySift(@PathVariable("pageNum")Integer pageNum,
                                     @RequestParam(value = "foodType",required = false)String foodType,
                                     @RequestParam(value = "foodPrice",required = false)String foodPrice,
                                     @RequestParam(value = "sort",required = false)String sort,
                                  HttpServletRequest request){
        String cusName = JwtUtil.getCusNameBySession(request);
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getBySift(pageNum,foodType,foodPrice,sort));
        mav.addObject("skfood",foodService.getSKPro());
        mav.addObject("foodtypes",foodService.foodTypes());
        mav.addObject("cusName",cusName);
        mav.setViewName("shop_list");
        return mav;
    }

}
