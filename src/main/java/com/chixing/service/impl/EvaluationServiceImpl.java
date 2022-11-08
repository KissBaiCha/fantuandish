package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.*;
import com.chixing.entity.vo.EvaluationVo;
import com.chixing.mapper.CustomerMapper;
import com.chixing.mapper.EvaImgMapper;
import com.chixing.mapper.EvaluationMapper;
import com.chixing.service.IEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chixing.service.IFoodService;
import com.chixing.service.IMyOrderService;
import com.chixing.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class EvaluationServiceImpl  implements IEvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EvaImgMapper evaImgMapper;

    @Override
    public int save(String orderId,Integer foodId,Integer custId,Double evaScore,String evaContent) {
        Evaluation evaluation = new Evaluation();
        evaluation.setOrderId(orderId);
        evaluation.setFoodId(foodId);
        evaluation.setCustomerId(custId);
        evaluation.setEvaScore(evaScore);
        evaluation.setEvaContent(evaContent);
        evaluation.setPraiseNum(Long.valueOf(0));
        evaluation.setEvaDateTime(LocalDateTime.now());
        evaluation.setEvaCreateTime(LocalDateTime.now());
        int row = evaluationMapper.insert(evaluation);

        return evaluation.getEvaId();
    }

    @Override
    public int remove(int evaId) {
        return evaluationMapper.deleteById(evaId);
    }

    @Override
    public int update(Evaluation evaluation) {
        return evaluationMapper.updateById(evaluation);
    }

    @Override
    public Evaluation getById(int evaId) {
        return evaluationMapper.selectById(evaId);
    }

    @Override
    public Map<String,Object> getByFoodId(Integer foodId,Integer pageNum) {
        //根据美食ID查出所有评论信息
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<>();
        evaluationQueryWrapper.eq("food_id",foodId);
        Page<Evaluation> page = new Page<>(pageNum,10);
        Page<Evaluation> pageEvaluations = evaluationMapper.selectPage(page, evaluationQueryWrapper);
        Map<String,Object> map = new HashMap<>(3);
        map.put("total",pageEvaluations.getTotal());
        map.put("curr",pageEvaluations.getCurrent());
        List<EvaluationVo> evaluationVoList = new ArrayList<>();
        for (Evaluation evaluation : pageEvaluations.getRecords()) {
            QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
            customerQueryWrapper.select("customer_head_img","customer_name").eq("customer_id",evaluation.getCustomerId());
            Customer customer = customerMapper.selectOne(customerQueryWrapper);
            QueryWrapper<EvaImg> imgQueryWrapper = new QueryWrapper<>();
            imgQueryWrapper.eq("eva_id",evaluation.getEvaId())
                           .eq("img_status",1);
            List<EvaImg> evaImgs = evaImgMapper.selectList(imgQueryWrapper);
            EvaluationVo evaluationVo = new EvaluationVo(customer.getCustomerHeadImg()
                    ,customer.getCustomerName()
                    ,evaluation.getEvaScore().floatValue()
                    ,evaluation.getEvaContent()
                    ,evaluation.getEvaDateTime().toLocalDate()
                    ,evaImgs);
            evaluationVoList.add(evaluationVo);
        }
        map.put("evaluationVoList",evaluationVoList);
        return map;
    }
}
