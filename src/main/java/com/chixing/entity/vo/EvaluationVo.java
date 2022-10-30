package com.chixing.entity.vo;

import com.chixing.entity.EvaImg;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Xu Zhang
 * @date 2022/10/30
 */
@Data
public class EvaluationVo {
    private String cusHeadImgPath;
    private String cusName;
    private Float score;
    private String evaContent;
    private LocalDate createDate;
    private List<EvaImg> evaImgList;

    public EvaluationVo(String cusHeadImgPath, String cusName, Float score, String evaContent, LocalDate createDate, List<EvaImg> evaImgList) {
        this.cusHeadImgPath = cusHeadImgPath;
        this.cusName = cusName;
        this.score = score;
        this.evaContent = evaContent;
        this.createDate = createDate;
        this.evaImgList = evaImgList;
    }
    public EvaluationVo(){}
}
