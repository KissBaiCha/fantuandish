package com.chixing.controller;

import com.chixing.entity.SecondKill;
import com.chixing.entity.Shop;
import com.chixing.service.IFoodService;
import com.chixing.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/shop/{id}")
    public ModelAndView getById(@PathVariable("id") Integer shopId){
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getById(shopId));
        mav.addObject("skpros",foodService.getSKProById(shopId));
        mav.addObject("foods",foodService.getShopProByShopId(shopId));
        mav.addObject("sellfoods",foodService.getShopProByScore(shopId));
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
     * 条件筛选（食品类型）
     * @param foodType  食品类型
     * @return  店铺集合
     */
    @GetMapping("/shop/foodType")
    public List<Shop> getByFoodType(@RequestParam("foodType")String foodType){
        List<Shop> shopList = shopService.getByFoodType(foodType);
        shopList.forEach(System.out::println);
        return shopList;
    }

    @GetMapping("/shop/getIndex/{pageNum}")
    public ModelAndView getByPage(@PathVariable("pageNum")Integer pageNum){
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getByPage(pageNum));
        mav.addObject("skfood",foodService.getSKPro());
        mav.setViewName("shop_list");
        return mav;
    }

    /**
     * 条件筛选（人均消费）
     * @param shopMinCost   最低人均消费
     * @param shopMaxCost   最高人均消费
     * @return  店铺集合
     */
    @GetMapping("/shop/shopAvgCost")
    public List<Shop> getByShopAvgCost(@RequestParam("shopMinCost")Float shopMinCost,@RequestParam("shopMaxCost")Float shopMaxCost){
        List<Shop> shopList = shopService.getByShopAvgCost(shopMinCost,shopMaxCost);
        return shopList;
    }

    /**
     * 多条件筛选（食品类型，人均消费）
     * @param foodType      食品类型
     * @param shopMinCost   最低人均消费
     * @param shopMaxCost   最高人均消费
     * @return  店铺集合
     */
    @GetMapping("/shop/sift")
    public List<Shop> getBySift(@RequestParam("foodType")String foodType,@RequestParam("shopMinCost")Float shopMinCost,@RequestParam("shopMaxCost")Float shopMaxCost){
        List<Shop> shopList = shopService.getBySift(foodType,shopMinCost,shopMaxCost);
        return shopList;
    }

    /**
     * 排序（价格降序）
     * @return  店铺集合
     */
    @GetMapping("/shop/getByPrice/{pageNum}")
    public ModelAndView getByPrice(@PathVariable("pageNum")Integer pageNum){
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getByPrice(pageNum));
        mav.addObject("skfood",foodService.getSKPro());
        mav.setViewName("shop_list");
        return mav;
    }

    /**
     * 排序（评分降序）
     * @return  店铺集合
     */
    @GetMapping("/shop/getByScore/{pageNum}")
    public ModelAndView getByScore(@PathVariable("pageNum")Integer pageNum){
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getByScore(pageNum));
        mav.addObject("skfood",foodService.getSKPro());
        mav.setViewName("shop_list");
        return mav;
    }
}
