package com.chixing.service;

import com.chixing.entity.Evaluation;

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
    List<Evaluation> getByPage(Integer pageNum);
}
