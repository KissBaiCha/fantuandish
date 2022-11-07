package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Flow;
import com.chixing.mapper.FlowMapper;
import com.chixing.service.IFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
@Slf4j
public class FlowServiceImpl implements IFlowService {
    @Autowired
    private FlowMapper flowMapper;
    @Override
    public Flow getByOrderNum(String orderNum) {
        QueryWrapper<Flow> flowQueryWrapper = new QueryWrapper<>();
        flowQueryWrapper.eq("order_id",orderNum);
        return flowMapper.selectOne(flowQueryWrapper);
    }
}
