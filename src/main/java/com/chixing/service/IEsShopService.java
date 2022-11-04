package com.chixing.service;

import com.chixing.entity.Shop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface IEsShopService extends ElasticsearchRepository<Shop,Integer> {
    List<Shop> findByShopName(String shopName);
}
