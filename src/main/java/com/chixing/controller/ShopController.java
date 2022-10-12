package com.chixing.controller;

import com.chixing.entity.Shop;
import com.chixing.service.IShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

// Shop getById(Integer shopId);
//    int save(Shop shop);
//    int update(Shop shop);
//    int remove(Integer shopId);
// 查 get  增 save  改 update   删 remove
@RestController
public class ShopController {
    @Resource
    private IShopService shopService;

    @GetMapping("/shop/{id}")
    private Shop getById(@PathVariable("id") Integer shopId){
        return shopService.getById(shopId);
    }

    @PostMapping("/shop")
    private int save(Shop shop){
        return shopService.save(shop);
    }

    @PostMapping("/shop/update")
    private int update(Shop shop){
        return shopService.update(shop);
    }

    @DeleteMapping("/shop")
    private int remove(@RequestParam("id") Integer shopId){
        return shopService.remove(shopId);
    }

    @GetMapping("/shop/foodType")
    private List<Shop> getByFoodType(@RequestParam("foodType")String foodType){
        List<Shop> shopList = shopService.getByFoodType(foodType);
        shopList.forEach(System.out::println);
        return shopList;
    }
    @GetMapping("/shop/shopAvgCost")
    private List<Shop> getByShopAvgCost(@RequestParam("shopMinCost")Float shopMinCost,@RequestParam("shopMaxCost")Float shopMaxCost){
        List<Shop> shopList = shopService.getByShopAvgCost(shopMinCost,shopMaxCost);
        return shopList;
    }
    @GetMapping("/shop/sift")
    private List<Shop> getBySift(@RequestParam("foodType")String foodType,@RequestParam("shopMinCost")Float shopMinCost,@RequestParam("shopMaxCost")Float shopMaxCost){
        List<Shop> shopList = shopService.getBySift(foodType,shopMinCost,shopMaxCost);
        return shopList;
    }
    @GetMapping("/shop/getByPrice")
    public List<Shop> getByPrice(){
        return shopService.getByPrice();
    }

    @GetMapping("/shop/getByScore")
    public List<Shop> getByScore(){
        return shopService.getByScore();
    }
}
