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
     * 发布评论
     * @param evaluation
     * @param imgArr
     * @return
     */
    @PostMapping("/save")
    public String save(Evaluation evaluation,String[] imgArr){
//        Integer custId = JwtUtil.getCusIdBySession(request);

        evaluation.setCustomerId((int)(Math.random()*100+1));
        evaluation.setFoodId((int)(Math.random()*100+1));
        evaluation.setOrderId(UUID.randomUUID().toString().replace("-",""));
        evaluationService.save(evaluation);
        evaImgService.save(imgArr);

        return evaluation.toString();
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


    /**
     * 订单待评价跳转评价页面
     * @return
     */
    @GetMapping("/eva/{id}")
    public ModelAndView getByIdEva(@PathVariable("id") String orderId){
        MyOrder myOrder = myOrderService.getById(orderId);
        Integer foodId = myOrder.getFoodId();
        Food food = foodService.getById(foodId);
        Integer shopId = food.getShopId();
        ModelAndView mav = new ModelAndView();
        mav.addObject("shop",shopService.getById(shopId));
        mav.addObject("food",foodService.getById(foodId));
//        mav.setViewName("root/personal_center/appraise");
        mav.setViewName("test/2");
        return mav;
    }
}
