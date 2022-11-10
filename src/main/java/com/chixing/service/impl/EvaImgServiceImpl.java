package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public void save(Integer evaId,String[] imgArr) {
        EvaImg evaImg = new EvaImg();
        QueryWrapper<EvaImg> wrapper = new QueryWrapper<>();
        wrapper.select("eva_img_id").orderByDesc("eva_img_id").last("limit 1");
//        Integer evaId = (int)(Math.random()*100+1);
        LocalDateTime nowTime = LocalDateTime.now();
        for (int i = 0; i < imgArr.length; i++) {
            Integer evaImgId_last = evaImgMapper.selectOne(wrapper).getEvaImgId();
            evaImg.setEvaImgId(evaImgId_last+1);
            System.out.println(imgArr[i]);
            evaImg.setEvaId(evaId);
            evaImg.setImgStatus(1);
            evaImg.setEvaImgCreateTime(nowTime);
            evaImg.setEvaImgPath(imgArr[i]);
            evaImgMapper.insert(evaImg);
        }
    }
}
