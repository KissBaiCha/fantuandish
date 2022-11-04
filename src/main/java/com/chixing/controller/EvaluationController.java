package com.chixing.controller;

import com.chixing.entity.EvaImg;
import com.chixing.entity.Evaluation;
import com.chixing.service.IEvaImgService;
import com.chixing.service.IEvaluationService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/evluation")
public class EvaluationController {

    @Autowired
    private IEvaluationService evaluationService;
    @Autowired
    private IEvaImgService evaImgService;

    /**
     * 发布评论
     * @param evaluation
     * @param evaImg
     * @param imgArr
     * @return
     */
    @PostMapping("/save")
    public String save(Evaluation evaluation,EvaImg evaImg,String[] imgArr){
//        Integer custId = JwtUtil.getCusIdBySession(request);

        evaluation.setCustomerId((int)(Math.random()*100+1));
        evaluation.setFoodId((int)(Math.random()*100+1));
        evaluation.setOrderId(UUID.randomUUID().toString().replace("-",""));

        evaImgService.save(evaImg,imgArr);
        int row = evaluationService.save(evaluation);
        return evaluation.toString();
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id")int evaId){
        int row = evaluationService.remove(evaId);
        return "删除评论 " + evaId;
    }

    @PutMapping("/update")
    public String update(Evaluation evaluation){
        evaluation.setEvaUpdateTime(LocalDateTime.now());
        int row = evaluationService.update(evaluation);
        return evaluation.toString();
    }

    @GetMapping("/getById/{id}")
    public Evaluation getById(@PathVariable("id") int evaId){
        return evaluationService.getById(evaId);
    }


}
