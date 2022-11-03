//package com.chixing.controller;
//
//import com.chixing.entity.Shop;
//import com.chixing.service.IEsShopService;
//import com.chixing.service.IShopService;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
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
//
//    @GetMapping("esshopname/{shopName}")
//    public List<Shop> getByPage(@PathVariable("shopName")String shopName){
////        esShopService.findByShopName(shopName);
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .should(QueryBuilders.matchQuery("shopName",shopName));
//
//        //构建高亮查询
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(boolQueryBuilder)
//                .withHighlightFields(
//                        new HighlightBuilder.Field("shopName"))
//                .withHighlightBuilder(new HighlightBuilder()
//                        .preTags("<label style='color:red'>")
//                        .postTags("</label>"))
//                .build();
//        //查询
//        SearchHits<Shop> search = restTemplate.search(searchQuery,Shop.class);
//
//        List<SearchHit<Shop>> searchHits = search.getSearchHits();
//
//        List<Shop> ShopList = new ArrayList<>();
//        for (SearchHit<Shop> searchHit : searchHits){
//            Map<String ,List<String>> highlightFields = searchHit.getHighlightFields();
//
//            String name = highlightFields.get("shopName") == null ?
//                    searchHit.getContent().getShopName() : highlightFields.get("shopName").get(0);
//            searchHit.getContent().setShopName(name);
//
//            ShopList.add(searchHit.getContent());
//        }
//        return ShopList;
//    }
//}
