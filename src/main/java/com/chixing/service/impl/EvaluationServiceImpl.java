package com.chixing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Evaluation;
import com.chixing.mapper.EvaluationMapper;
import com.chixing.service.IEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int save(Evaluation evaluation) {
        return evaluationMapper.insert(evaluation);
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
    public List<Evaluation> getByPage(Integer pageNum) {
        Page<Evaluation> page = new Page<>(pageNum,3);
        return evaluationMapper.selectPage(page,null).getRecords();
    }
}
