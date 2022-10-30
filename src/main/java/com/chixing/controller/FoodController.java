package com.chixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Evaluation;
import com.chixing.entity.Food;
import com.chixing.service.IEvaluationService;
import com.chixing.service.IFoodService;
import com.chixing.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FoodController {

    private final IFoodService foodService;
    private final IShopService shopService;
    private final IEvaluationService evaluationService;
    @Autowired
    public FoodController(IFoodService foodService, IShopService shopService,IEvaluationService evaluationService) {
        this.foodService = foodService;
        this.shopService = shopService;
        this.evaluationService = evaluationService;
    }
//查 get  增 save 改update 删 remove
    //int save(Food food);
//     int update(Food food);
//     int delete(Integer foodId);

    @GetMapping("/shop/{shopId}/{foodId}")
    private ModelAndView getById(@PathVariable("foodId")Integer foodId,@PathVariable("shopId") Integer shopId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("food",foodService.getById(foodId));
        modelAndView.addObject("shop",shopService.getById(shopId));
        modelAndView.addObject("evaList",evaluationService.getByFoodId(foodId));
        modelAndView.setViewName("details/details_product");
        return modelAndView;
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
