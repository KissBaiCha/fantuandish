package com.chixing.controller;

import com.chixing.entity.Shop;
import com.chixing.service.IFoodService;
import com.chixing.service.IShopImgService;
import com.chixing.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Shop getById(Integer shopId);
//    int save(Shop shop);
//    int update(Shop shop);
//    int remove(Integer shopId);
// 查 get  增 save  改 update   删 remove
@RestController
public class ShopController {
    @Autowired
    private IShopService shopService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IShopImgService shopImgService;
    /**
     * 筛选页跳转查询店铺信息
     * @param shopId
     * @return
     */
    @GetMapping("/shop/{id}")
    public ModelAndView getById(@PathVariable("id") Integer shopId){
        ModelAndView mav = new ModelAndView();
        //店铺信息
        mav.addObject("shop",shopService.getById(shopId));
        //优惠美食
        mav.addObject("skpros",foodService.getSKProById(shopId));
        //全部美食
        mav.addObject("foods",foodService.getShopProByShopId(shopId));
        //推荐美食
        mav.addObject("sellfoods",foodService.getShopProByScore(shopId));
        //店铺图片
        mav.addObject("shopimgs",shopImgService.getShopSrc(shopId));

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


//    @GetMapping("/shop/getIndex/{pageNum}")
//    public ModelAndView getByPage(@PathVariable("pageNum")Integer pageNum){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("shop",shopService.getByPage(pageNum));
//        mav.addObject("skfood",foodService.getSKPro());
//        mav.addObject("foodtypes",foodService.foodTypes());
//        mav.setViewName("shop_list");
//        return mav;
//    }
//
//    /**
//     * 条件筛选（食品类型）
//     * @param foodType  食品类型
//     * @return  店铺集合
//     */
//    @GetMapping("/shop/foodType")
//    public ModelAndView getByFoodType(@RequestParam("foodType")String foodType){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("shop",shopService.getByFoodType(foodType));
//        mav.addObject("skfood",foodService.getSKPro());
//        mav.addObject("foodtypes",foodService.foodTypes());
//        mav.setViewName("shop_list");
//        return mav;
//    }
//
//    /**
//     * 条件筛选（人均消费）
//     * @param shopMinCost   最低人均消费
//     * @param shopMaxCost   最高人均消费
//     * @return  店铺集合
//     */
//    @GetMapping("/shop/shopAvgCost")
//    public ModelAndView getByShopAvgCost(@RequestParam(value = "shopMinCost",required = false)Float shopMinCost,@RequestParam(value = "shopMaxCost",required = false)Float shopMaxCost){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("shop",shopService.getByShopAvgCost(shopMinCost,shopMaxCost));
//        mav.addObject("skfood",foodService.getSKPro());
//        mav.addObject("foodtypes",foodService.foodTypes());
//        mav.setViewName("shop_list");
//        return mav;
//    }
//
//    /**
//     * 多条件筛选（食品类型，人均消费）
//     * @param foodType      食品类型
//     * @param shopMinCost   最低人均消费
//     * @param shopMaxCost   最高人均消费
//     * @return  店铺集合
//     */
//    @GetMapping("/shop/sift")
//    public List<Shop> getBySift(@RequestParam("foodType")String foodType,@RequestParam("shopMinCost")Float shopMinCost,@RequestParam("shopMaxCost")Float shopMaxCost){
//        List<Shop> shopList = shopService.getBySift(foodType,shopMinCost,shopMaxCost);
//        return shopList;
//    }
//
//    /**
//     * 排序（价格降序）
//     * @return  店铺集合
//     */
//    @GetMapping("/shop/getByPrice/{pageNum}")
//    public ModelAndView getByPrice(@PathVariable("pageNum")Integer pageNum){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("shop",shopService.getByPrice(pageNum));
//        mav.addObject("skfood",foodService.getSKPro());
//        mav.addObject("foodtypes",foodService.foodTypes());
//        mav.setViewName("shop_list");
//        return mav;
//    }
//
//    /**
//     * 排序（评分降序）
//     * @return  店铺集合
//     */
//    @GetMapping("/shop/getByScore/{pageNum}")
//    public ModelAndView getByScore(@PathVariable("pageNum")Integer pageNum){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("shop",shopService.getByScore(pageNum));
//        mav.addObject("skfood",foodService.getSKPro());
//        mav.addObject("foodtypes",foodService.foodTypes());
//        mav.setViewName("shop_list");
//        return mav;
//    }

    @GetMapping("/shop/shopsift/{pageNum}")
    public ModelAndView getBySift(@PathVariable("pageNum")Integer pageNum,
                                     @RequestParam(value = "foodType",required = false)String foodType,
                                     @RequestParam(value = "foodPrice",required = false)String foodPrice,
                                     @RequestParam(value = "sort",required = false)String sort){
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getBySift(pageNum,foodType,foodPrice,sort));
        mav.addObject("skfood",foodService.getSKPro());
        mav.addObject("foodtypes",foodService.foodTypes());
        mav.setViewName("shop_list");
        return mav;
    }

}
