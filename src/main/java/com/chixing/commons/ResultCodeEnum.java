package com.chixing.commons;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 * @author Yang Song
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    FAIL(201, "失败"),
    /**
     * 数据为空
     */
    DATA_EMPTY(202,"数据为空"),
    /**
     * 热数据兜底数据
     */
    SO_HOT(203,"当前数据访问量过多,请稍后再试"),
    /**
     * 成功
     */
    SEND_SUCCESS(204,"发送成功"),
    /**
     * 登录错误
     */
    CUSTOMER_ERR(400,"用户信息错误请检查用户名或密码"),
    /**
     * 该手机号未注册
     */
    NO_FIND_ANT_ERR(401,"该手机号暂未注册,请先注册"),
    /**
     *  没有美食收藏
     */
    NO_FOODCOLLECTION(501,"您还没有收藏美食"),
    /**
     * 已领取过此优惠券
     */
    HAS_COUPON(601,"每位用户只能领取一次"),
    /**
     * 验证码错误
     */
    CODE_ERR(701,"验证码错误"),
    /**
     * 手机号为空
     */
    TEL_NULL(801,"请输入手机号码");
    /**
     *
     * 状态码
     */
    private final Integer code;
    /**
     * 状态信息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
