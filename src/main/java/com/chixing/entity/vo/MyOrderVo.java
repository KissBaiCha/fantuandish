package com.chixing.entity.vo;

import com.chixing.entity.Flow;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xu Zhang
 * @date 2022/11/7
 */
@Data
public class MyOrderVo {
    private MyOrder myOrder;
    private Food food;


    public MyOrderVo(MyOrder myOrder, Food food) {
        this.myOrder = myOrder;
        this.food = food;

    }

    public MyOrderVo() {
    }
}
