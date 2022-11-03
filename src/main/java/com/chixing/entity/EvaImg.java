package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.mysql.cj.jdbc.Blob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Data
@TableName("eva_img")
public class EvaImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评价图片id
     */
    @TableId(value = "eva_img_id", type = IdType.AUTO)
    private Integer evaImgId;

    /**
     * 评价id
     */
    private Integer evaId;

    /**
     * 图片路径
     */
    private String evaImgPath;

    /**
     * 状态  0:有效 1：无效
     */
    private Integer imgStatus;

    /**
     * 创建时间
     */
    private LocalDateTime evaImgCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime evaImgUpdateTime;
}
