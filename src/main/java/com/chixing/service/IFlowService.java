package com.chixing.service;

import com.chixing.entity.Flow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IFlowService  {
    /**
     * 根据订单编号查询流水信息
     * @param orderNum 订单编号
     * @return Flow对象
     */
    Flow getByOrderNum(String orderNum);
}
