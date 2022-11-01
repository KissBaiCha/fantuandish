package com.chixing.service.impl;

import com.chixing.entity.EvaImg;
import com.chixing.mapper.EvaImgMapper;
import com.chixing.service.IEvaImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class EvaImgServiceImpl implements IEvaImgService {

    @Autowired
    private EvaImgMapper evaImgMapper;
    @Override
    public int save(EvaImg evaImg) {

        return evaImgMapper.insert(evaImg);
    }
}
