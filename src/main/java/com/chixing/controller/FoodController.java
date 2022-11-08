package com.chixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Evaluation;
import com.chixing.entity.Food;
import com.chixing.service.IEvaluationService;
import com.chixing.service.IFoodService;
import com.chixing.service.IShopService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @GetMapping("Test1")
    public Map getTest(){
        return evaluationService.getByFoodId(1,1);
    }

    /**
     * 用户点击美食后访问此方法
     * @param foodId 美食id
     * @param shopId 店铺id
     * @param pageNum 评论页码
     * @return 美食详情页面
     */
    @GetMapping("/shop/{shopId}/{foodId}/{pageNum}")
    private ModelAndView getById(@PathVariable("foodId")Integer foodId,
                                 @PathVariable("shopId") Integer shopId,
                                 @PathVariable("pageNum") Integer pageNum,
                                 HttpServletRequest request){
        String cusName = JwtUtil.getCusNameBySession(request);
        Integer cusId = JwtUtil.getCusIdBySession(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("food",foodService.getById(foodId));
        modelAndView.addObject("cusName",cusName);
        modelAndView.addObject("cusId",cusId);
        modelAndView.addObject("shop",shopService.getById(shopId));
        modelAndView.addObject("evaMap",evaluationService.getByFoodId(foodId,pageNum));
        modelAndView.addObject("recommend",foodService.getByScore().stream().limit(3).collect(Collectors.toList()));
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
