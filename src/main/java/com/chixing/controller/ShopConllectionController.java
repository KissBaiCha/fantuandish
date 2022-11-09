package com.chixing.controller;

import com.chixing.service.IShopCollectionService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ShopConllectionController {
    @Autowired
    private IShopCollectionService shopCollectionService;

    /**
     * 收藏
     * @param shopId
     * @param request
     * @return
     */
    @GetMapping("addCon")
    public void addCon(Integer shopId, HttpServletRequest request){
        Integer userId = JwtUtil.getCusIdBySession(request);
        shopCollectionService.save(shopId,userId);

    }
    /**
     * 取消收藏
     * @param shopId
     * @param request
     * @return
     */
    @GetMapping("delCon")
    public void delCon(Integer shopId, HttpServletRequest request){
        Integer userId = JwtUtil.getCusIdBySession(request);
        shopCollectionService.delete(shopId,userId);
    }

}
