package com.chixing.controller;

import com.chixing.entity.Food;
import com.chixing.service.IFoodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FoodController {
    @Resource
    private IFoodService foodService;

    //查 get  增 save 改update 删 remove
    //int save(Food food);
//     int update(Food food);
//     int delete(Integer foodId);

    @GetMapping("/food")
    private Food getById(@RequestParam("id")Integer foodId){
        return foodService.getById(foodId);
    }

    @PostMapping("/food")
    private int save(Food food){
        return foodService.save(food);
    }

    @PostMapping("/food/update")
    private int update(Food food){
        return foodService.update(food);
    }

    @DeleteMapping("/food")
    private int remove(@RequestParam("id")Integer foodId){
        return foodService.remove(foodId);
    }

    @GetMapping("/food/getByPage/{pageNum}")
    public List<Food> getByPage(@PathVariable("pageNum")Integer pageNum){
        return foodService.getByPage(pageNum);
    }

    @GetMapping("/food/getByPrice")
    public List<Food> getByPrice(){
        return foodService.getByPrice();
    }

    @GetMapping("/food/getByScore")
    public List<Food> getByScore(){
        return foodService.getByScore();
    }
}
