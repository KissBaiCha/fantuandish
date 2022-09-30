package com.chixing.controller;

import com.chixing.entity.Food;
import com.chixing.service.IFoodService;
import com.chixing.service.impl.FoodServiceImpl;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class FoodController {
    @Resource
    private IFoodService foodService;

    //查 get  增 save 改update 删 remove
    //int save(Food food);
//     int update(Food food);
//     int delete(Integer foodId);

    @GetMapping("/food")
    @ResponseBody
    private Food getById(@RequestParam("id")Integer foodId){
        return foodService.getById(foodId);
    }

    @PostMapping("/food")
    @ResponseBody
    private int save(Food food){
        return foodService.save(food);
    }

    @PostMapping("/food/update")
    @ResponseBody
    private int update(Food food){
        return foodService.update(food);
    }

    @DeleteMapping("/food")
    @ResponseBody
    private int remove(@RequestParam("id")Integer foodId){
        return foodService.remove(foodId);
    }
}
