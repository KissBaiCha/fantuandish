package com.chixing.controller;


import com.chixing.entity.Food;
import com.chixing.entity.MyCoupon;
import com.chixing.entity.MyOrder;
import com.chixing.entity.vo.MyCouponVO;
import com.chixing.service.IFoodService;
import com.chixing.service.IMyCouponService;
import com.chixing.service.IMyOrderService;
import com.chixing.util.JwtUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ZhangJiuJiu
 */
@RestController
@Slf4j
public class MyOrderController {
    @Autowired
    private IFoodService iFoodService;
    @Autowired
    private IMyOrderService myOrderService;
    @Autowired
    private IMyCouponService myCouponService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/order")
    public ModelAndView getOrder() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/pay/order_pay.html");
        return modelAndView;
    }



    @PostMapping("/creatOrder")
    public ModelAndView creatOrder(@RequestParam("foodId") Integer foodId,
                                   @RequestParam("newCouponId") Integer newCouponId,
                                   @RequestParam("isSecondKillVal") Integer isSecondKillVal,
                                   HttpServletRequest request) {
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        Food food = iFoodService.getById(foodId);
        if (newCouponId == 0) {
            newCouponId = null;
        }
        String orderNum = myOrderService.save(cusId, newCouponId, foodId, isSecondKillVal == 1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("food", food);
        MyOrder myOrder = myOrderService.getById(orderNum);
        modelAndView.addObject("orderNum", orderNum);
        modelAndView.addObject("cusName", cusName);
        modelAndView.addObject("cusId", cusId);
        modelAndView.addObject("myOrder", myOrder);
        modelAndView.addObject("myOrderCreatTime", myOrder.getOrderCreateTime());
        //向队列发送订单编号
        rabbitTemplate.convertAndSend("order-exchange", "order-create", orderNum);
        if (newCouponId != null) {
            //向队列发送我的优惠券ID
            rabbitTemplate.convertAndSend("coupon-Exchange", "coupon", newCouponId);
        }
        modelAndView.setViewName("root/pay/order_pay");
        return modelAndView;
    }

    /**
     * 用户点击购买之后访问这个方法
     *
     * @param foodId  美食id
     * @param request 请求对象用来获取用户id
     * @return 返回订单确认界面
     */
    @GetMapping("/getOrderDetails")
    public ModelAndView getOrderDetails(@RequestParam("foodId") Integer foodId,
                                        @RequestParam("shopId") Integer shopId, HttpServletRequest request) {
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        Food food = iFoodService.getById(foodId);
        List<MyCouponVO> myCouponVoList = myCouponService.getMyCouponByShopId(cusId, shopId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("myCouponVoList", myCouponVoList);
        modelAndView.addObject("food", food);
        modelAndView.addObject("cusName", cusName);
        modelAndView.setViewName("customer/order/order");
        return modelAndView;
    }

    @GetMapping("getOrderDateTime")
    @ResponseBody
    public LocalDateTime getOrderDateTime(@RequestParam("orderId") String orderId) {
        return myOrderService.getOrderDateTime(orderId);
    }

    /**
     * 监听死信队列用来处理过期订单
     *
     * @param orderNum 订单编号
     * @param message  队列消息
     * @param channel  管道
     * @throws IOException IO异常
     */
    @RabbitListener(queues = "order-release-queue")
    public void orderListener(String orderNum, Message message, Channel channel) throws IOException {
        System.out.println("接收到过期未支付的订单信息" + orderNum);
        System.out.println("处理过期订单....");
        MyOrder myOrder = null;
        try {
            //TODO 判断订单是否已经支付成功，如果支付成功，无需再取消订单
            myOrder = myOrderService.getById(orderNum);
            if (myOrder.getOrderStatus() == 1) {
                myOrder.setOrderStatus(0);
                myOrder.setOrderUpdateTime(LocalDateTime.now());
                myOrderService.update(myOrder);
            }
            //是否批量签收
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            //是否重新入队
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
        System.out.println("处理后的订单：" + myOrder);
    }


    @GetMapping("myorder/{id}")
    public ModelAndView getById(@PathVariable("id") String orderId) {
        ModelAndView mav = new ModelAndView();
        Integer foodId = myOrderService.getById(orderId).getFoodId();
        Integer shopId = iFoodService.getById(foodId).getShopId();
        mav.addObject("order", myOrderService.getById(orderId));
        mav.addObject("food", iFoodService.getById(foodId));
        mav.addObject("foodImg", iFoodService.getById(foodId));
        System.out.println(iFoodService.getById(foodId));
        mav.setViewName("root/personal_center/allorder");
        return mav;
    }

    /**
     * 全部订单
     * @param pageNum
     * @param orderId
     * @return
     */

    @GetMapping("/order/{orderId}/{pageNum}")
    public ModelAndView getAllOrderByPage(@PathVariable("pageNum") Integer pageNum,
                                  @PathVariable("orderId") String orderId) {
        ModelAndView mav = new ModelAndView();
        Integer foodId = myOrderService.getById(orderId).getFoodId();
        Integer shopId = iFoodService.getById(foodId).getShopId();
        mav.addObject("order", myOrderService.getById(orderId));
        mav.addObject("food", iFoodService.getById(foodId));
        mav.addObject("foodImg", iFoodService.getById(foodId));
        System.out.println(iFoodService.getById(foodId));
        mav.addObject("orders", myOrderService.getByPage(pageNum));
        mav.setViewName("root/personal_center/allorder");
        return mav;
    }



    /**
     * 我的订单
     * @param pageNum
     * @param orderId
     * @return
     */
    @GetMapping("/myorder/{orderId}/{pageNum}")
    public ModelAndView getMyOrderByPage(@PathVariable("pageNum") Integer pageNum,
                                  @PathVariable("orderId") String orderId) {
        ModelAndView mav = new ModelAndView();
        Integer foodId = myOrderService.getById(orderId).getFoodId();
        Integer shopId = iFoodService.getById(foodId).getShopId();
        mav.addObject("order", myOrderService.getById(orderId));
        mav.addObject("food", iFoodService.getById(foodId));
        mav.addObject("foodImg", iFoodService.getById(foodId));
        System.out.println(iFoodService.getById(foodId));
        mav.addObject("orders", myOrderService.getByPage(pageNum));
        mav.setViewName("root/personal_center/myorder");
        return mav;
    }
    /**
     * 待付款
     * @param pageNum
     * @param orderId
     * @return
     */
    @GetMapping("/waitPayOrder/{orderId}/{pageNum}")
    public ModelAndView getWaitPayByPage(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("orderId") String orderId) {

        ModelAndView mav = new ModelAndView();
        Integer foodId = myOrderService.getById(orderId).getFoodId();
        Integer shopId = iFoodService.getById(foodId).getShopId();
        mav.addObject("order", myOrderService.getById(orderId));
        mav.addObject("food", iFoodService.getById(foodId));
        mav.addObject("foodImg", iFoodService.getById(foodId));
        System.out.println(iFoodService.getById(foodId));
        mav.addObject("orders", myOrderService.getByPage(pageNum));
        mav.setViewName("root/personal_center/order_wait_pay");
        return mav;
    }

    /**
     * 待评价
     * @param pageNum
     * @param orderId
     * @return
     */
    @GetMapping("/waitJudgeOrder/{orderId}/{pageNum}")
    public ModelAndView getWaitJudgeByPage(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("orderId") String orderId) {
        ModelAndView mav = new ModelAndView();
        Integer foodId = myOrderService.getById(orderId).getFoodId();
        Integer shopId = iFoodService.getById(foodId).getShopId();
        mav.addObject("order", myOrderService.getById(orderId));
        mav.addObject("food", iFoodService.getById(foodId));
        mav.addObject("foodImg", iFoodService.getById(foodId));
        System.out.println(iFoodService.getById(foodId));
        mav.addObject("orders", myOrderService.getByPage(pageNum));
        mav.setViewName("root/personal_center/order_wait_judge");
        return mav;
    }

}