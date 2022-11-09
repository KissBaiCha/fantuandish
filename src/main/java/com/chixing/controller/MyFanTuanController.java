package com.chixing.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.commons.R;
import com.chixing.entity.*;
import com.chixing.entity.vo.MyCouponCenterVo;
import com.chixing.entity.vo.MyOrderVo;
import com.chixing.service.*;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class MyFanTuanController {
    @Autowired
    private IMyOrderService myOrderService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IMyCouponService myCouponService;
    @Autowired
    private IFlowService flowService;
    @Autowired
    private ICouponService couponService;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IShopCollectionService shopCollectionService;

    /**
     * 个人信息访问方法
     * @param request
     * @return
     */
    @GetMapping("/user_details")
    public ModelAndView fantuan(HttpServletRequest request){
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        Customer customer = customerService.getCustomerById(cusId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("cusName",cusName);
        modelAndView.setViewName("root/personal_center/user_msg");
        return modelAndView;
    }

    /**
     * 个人订单中心访问
     * @param pageNum 页码
     * @param status 状态
     * @param request 请求
     * @return 订单数据
     */
    @GetMapping("/getAllOrder")
    public ModelAndView getByPage(Integer pageNum, Integer status,HttpServletRequest request){
        //订单ID
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        ModelAndView mav = new ModelAndView();
        Page<MyOrder> myOrderData = myOrderService.getByPage(pageNum, cusId,status);
        List<MyOrderVo> MyOrderVoList = new ArrayList<>();
        for (MyOrder myOrder : myOrderData.getRecords()) {
            MyOrderVo myOrderVo = new MyOrderVo();
            myOrderVo.setMyOrder(myOrder);
            myOrderVo.setFood(foodService.getById(myOrder.getFoodId()));
            MyOrderVoList.add(myOrderVo);
        }
        mav.addObject("cusName",cusName);
        mav.addObject("MyOrderVoList",MyOrderVoList);
        mav.setViewName("root/personal_center/allorder");
        return mav;
    }
    @GetMapping("/getFlow/{orderId}")
    public R<Flow> getFlowByOrderId(@PathVariable("orderId") String orderId){
        Flow flow = flowService.getByOrderNum(orderId);
        if(flow == null){
            return R.fail();
        }
        return R.ok("flow",flow);
    }

    /**
     * 根据优惠券状态获得所有优惠券
     * @param pageNum 页码
     * @param status 优惠券状态
     * @param request 请求
     * @return
     */
    @GetMapping("/getAllCoupon")
    public ModelAndView getCouponByStatus(Integer pageNum, Integer status,HttpServletRequest request){
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        Page<MyCoupon> myCouponList = myCouponService.getByPage(pageNum, cusId, status);
        List<MyCouponCenterVo> myCouponVOList = new ArrayList<>();
        for (MyCoupon myCoupon : myCouponList.getRecords()) {
            Coupon coupon = couponService.getById(myCoupon.getCouponId());
            Shop shop = shopService.getById(coupon.getShopId());
            MyCouponCenterVo myCouponCenterVo = new MyCouponCenterVo();
            myCouponCenterVo.setShopId(coupon.getShopId());
            myCouponCenterVo.setShopName(shop.getShopName());
            myCouponCenterVo.setCoupon(myCoupon);
            myCouponCenterVo.setCouponPrice(myCouponService.getCouponPriceByMyCouponId(myCoupon.getMyCouponId()));
            myCouponVOList.add(myCouponCenterVo);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cusName",cusName);
        modelAndView.addObject("myCouponList",myCouponVOList);
        modelAndView.setViewName("root/personal_center/all_quan");
        return modelAndView;
    }

    @GetMapping("/getShopCollect")
    public ModelAndView getShopCollect(HttpServletRequest request){
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        List<Shop> shopList = shopCollectionService.getAllShopByUser(cusId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cusName",cusName);
        modelAndView.addObject("myCollectShop",shopList);
        modelAndView.setViewName("root/personal_center/collectshop");
        return modelAndView;
    }
}
