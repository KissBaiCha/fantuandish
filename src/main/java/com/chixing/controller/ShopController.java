package com.chixing.controller;

import com.chixing.entity.Shop;
import com.chixing.service.IShopService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// Shop getById(Integer shopId);
//    int save(Shop shop);
//    int update(Shop shop);
//    int remove(Integer shopId);
// 查 get  增 save  改 update   删 remove
@Controller
public class ShopController {
    @Resource
    private IShopService shopService;

    @GetMapping("/shop")
    @ResponseBody
    private Shop getById(@RequestParam("id") Integer shopId){
        return shopService.getById(shopId);
    }

    @PostMapping("/shop")
    @ResponseBody
    private int save(Shop shop){
        return shopService.save(shop);
    }

    @PostMapping("/shop/update")
    @ResponseBody
    private int update(Shop shop){
        return shopService.update(shop);
    }

    @DeleteMapping("/shop")
    @ResponseBody
    private int remove(@RequestParam("id") Integer shopId){
        return shopService.remove(shopId);
    }

    @GetMapping("/shop/{foodId}{foodPrice}")
    private List<Shop> getBySift(){
        List<Shop> shopList = new ArrayList<>();
        return shopList;
    }
}
