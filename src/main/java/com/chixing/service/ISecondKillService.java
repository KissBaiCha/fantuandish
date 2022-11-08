package com.chixing.service;

import com.chixing.entity.vo.SecondKillVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface ISecondKillService {
    void getAllFromMysql();
    List<SecondKillVo> getAllPro();
    SecondKillVo decreaseProductNumFromRedis(Integer secondKillId);
}
