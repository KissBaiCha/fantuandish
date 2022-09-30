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
@TableName("shop_collection")
public class ShopCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺收藏id
     */
    @TableId(value = "shop_collection_id", type = IdType.AUTO)
    private Integer shopCollectionId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 收藏时间
     */
    private LocalDateTime shopCollectionTime;

    private String other1;

    private String other2;

    /**
     * 创建时间
     */
    private LocalDateTime shopCollectionCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime shopCollectionUpdateTime;
}
