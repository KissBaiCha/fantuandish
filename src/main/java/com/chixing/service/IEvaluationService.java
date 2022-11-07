package com.chixing.service;

import com.chixing.entity.Evaluation;
import com.chixing.entity.vo.EvaluationVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IEvaluationService{
    int save(String orderId,Integer foodId,Integer custId,Double evaScore,String evaContent);
    int remove(int evaId);
    int update(Evaluation evaluation);
    Evaluation getById(int evaId);

    /**
     *  根据美食Id返回所有评论信息以及对应的用户头像，用户名，以及分页信息
     * @param pageNum 页码
     * @param foodId 美食Id
     * @return 根据美食Id返回所有该美食评论信息（EvaluationVo）包括 用户头像，用户名，评分，评论时间，以及对应的图片
     */
    Map<String,Object> getByFoodId(Integer foodId, Integer pageNum);
}

