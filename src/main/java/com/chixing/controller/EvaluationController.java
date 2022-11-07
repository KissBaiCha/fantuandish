package com.chixing.controller;

import com.chixing.entity.EvaImg;
import com.chixing.entity.Evaluation;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.service.*;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private IShopService shopService;
    @Autowired
    private IMyOrderService myOrderService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IEvaluationService evaluationService;
    @Autowired
    private IEvaImgService evaImgService;


    /**
     * 订单待评价跳转评价页面
     * @return
     */
    @GetMapping("/{id}")
    public ModelAndView getByIdEva(@PathVariable("id") String orderId,HttpServletRequest request){
        String cusName = JwtUtil.getCusNameBySession(request);
        Integer cusId = JwtUtil.getCusIdBySession(request);

        MyOrder myOrder = myOrderService.getById(orderId);
        Integer foodId = myOrder.getFoodId();
        Food food = foodService.getById(foodId);
        Integer shopId = food.getShopId();
        ModelAndView mav = new ModelAndView();
        mav.addObject("myOrder",myOrder);
        mav.addObject("shop",shopService.getById(shopId));
        mav.addObject("food",foodService.getById(foodId));
        mav.addObject("cusName",cusName);
        mav.addObject("cusId",cusId);
        mav.setViewName("root/personal_center/appraise");
        return mav;
    }
    /**
     * 发布评论
     * @param imgArr
     * @return
     */
    @PostMapping("/save")
    public String save(String orderId,Integer foodId,Double evaScore,String evaContent,String[] imgArr,HttpServletRequest request){
        Integer custId = JwtUtil.getCusIdBySession(request);
        int evaId = evaluationService.save(orderId,foodId,custId,evaScore,evaContent);
        myOrderService.updStatus(orderId);
        evaImgService.save(evaId,imgArr);

        return "success";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id")int evaId){
        int row = evaluationService.remove(evaId);
        return "删除评论 " + evaId;
    }

    @PutMapping("/update")
    public String update(Evaluation evaluation){
        evaluation.setEvaUpdateTime(LocalDateTime.now());
        int row = evaluationService.update(evaluation);
        return evaluation.toString();
    }

    @GetMapping("/getById/{id}")
    public Evaluation getById(@PathVariable("id") int evaId){
        return evaluationService.getById(evaId);
    }

}
