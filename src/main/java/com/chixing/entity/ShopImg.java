package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("shop_img")
public class ShopImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品图片id
     */
    @TableId(value = "shop_img_id", type = IdType.AUTO)
    private Integer shopImgId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 图片路径
     */
    private String shopImgPath;

    /**
     * 状态  0:有效 1：无效
     */
    private Integer imgStatus;

    private String other1;

    private String other2;

    /**
     * 创建时间
     */
    private LocalDateTime shopImgCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime shopImgUpdateTime;
}
