package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Praise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞id
     */
    @TableId(value = "praise_id", type = IdType.AUTO)
    private Integer praiseId;

    /**
     * 评论id
     */
    private Integer evaId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 点赞时间
     */
    private LocalDateTime praiseTime;

    /**
     * 1:点赞 0：赞过已取消 
     */
    private Integer praiseStatus;

    /**
     * 创建时间
     */
    private LocalDateTime praiseCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime praiseUpdateTime;

    private String other1;

    private String other2;
}
