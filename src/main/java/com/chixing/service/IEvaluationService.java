package com.chixing.service;

import com.chixing.entity.Evaluation;
import com.chixing.entity.vo.EvaluationVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IEvaluationService{
    int save(Evaluation evaluation);
    int remove(int evaId);
    int update(Evaluation evaluation);
    Evaluation getById(int evaId);

    /**
     *
     * @param foodId 美食Id
     * @return 根据美食Id返回所有该美食评论信息（EvaluationVo）包括 用户头像，用户名，评分，评论时间，以及对应的图片
     */
    List<EvaluationVo> getByFoodId(Integer foodId);


}

