package com.chixing.controller;

import com.chixing.entity.Evaluation;
import com.chixing.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/evluation")
public class EvluationController {

    @Autowired
    private IEvaluationService evaluationService;

    @PostMapping("/save")
    public String save(Evaluation evaluation){
        evaluation.setEvaDateTime(LocalDateTime.now());
        evaluation.setEvaCreateTime(LocalDateTime.now());
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

    @GetMapping("/getByPage/{pageNum}")
    public List<Evaluation> getByPage(@PathVariable("pageNum")Integer pageNum){
        return evaluationService.getByPage(pageNum);
    }
}
