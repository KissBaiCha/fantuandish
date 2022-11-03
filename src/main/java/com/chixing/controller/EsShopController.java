//package com.chixing.controller;
//
//import com.chixing.entity.Shop;
//import com.chixing.service.IEsShopService;
//import com.chixing.service.IShopService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class EsShopController {
//    @Autowired
//    private ElasticsearchRestTemplate restTemplate;
//    @Autowired
//    private IShopService shopService;
//    @Autowired
//    private IEsShopService esShopService;
//
//    @GetMapping("test")
//    public String save(){
//        List<Shop>  shopList = shopService.getAll();
//        esShopService.saveAll(shopList);
//        return "success";
//    }
//    @GetMapping("es/{pageNum}")
//    public Page<Shop> getByPage(@PathVariable("pageNum")Integer pageNum){
////        esShopService.findByShopName(shopName);
//        Pageable page = PageRequest.of(pageNum,4, Sort.Direction.ASC,"shopId");
//        Page<Shop> allShop = esShopService.findAll(page);
//        System.out.println(allShop);
//        return allShop;
//    }
//}
