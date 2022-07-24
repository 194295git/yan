package com.rose.yaj.common;

public enum ServiceError {

    NORMAL(200, "操作成功"),
    UN_KNOW_ERROR(-1, "未知错误"),
    INSERT_ERROR(-100,"代码保存错误"),
    PARAM_ERROR(-101,"参数错误！"),

    LOGIN_ERROR(-201,"登录出错"),
    Expire_LOGIN_ERROR(-202,"登录过期,请重新登录"),
    DB_ERROR(-203,"database error"),
    DATA_NOT_EXIST(-204,"未查询到记录！"),
    REQUEST_FORBIDEN_ERROR(-205,"禁止该操作！"),
    OPERATE_ERROR(-206,"操作失败！"),

    OSS_ERROR(-300,"oss错误"),
    UPDATE_ERROR(-400,"代码更新错误"),
    GLOBAL_ERROR(-250,"执行了全局处理异常"),
    EMAIL_ERROT(-500,"该邮箱已经注册过"),

    PARAM_EXCEPTION(-50001,"参数出现异常"),
    GOODS_PUT_DOWN(-50002,"商品已下架！"),
    GOODS_NOT_EXIST(-50003,"商品不存在！"),


    SHOPPING_CART_ITEM_EXIST_ERROR(-50004,"已存在！无需重复添加！"),
    SHOPPING_CART_ITEM_NUMBER_ERROR(-50005,"商品数量不能小于 1 ！"),
    SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR(-50005,"超出单个商品的最大购买数量！"),
    SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR(-50006,"超出购物车最大容量！"),
    SHOPPING_CART_NOT_EXIST(-50007,"购物项不能为空"),
    SHOPPING_ITEM_COUNT_ERROR(-50008,"库存不足！"),
    SHOPPING_ITEM_ERROR(-50007,"购物车数据异常！"),

    ORDER_PRICE_ERROR(-60001,"订单价格异常！"),


    /**
     * Global Error
     */
    GLOBAL_ERR_NO_SIGN_IN(-10001, "未登录或登录过期/Not sign in"),
    GLOBAL_ERR_NO_CODE(-10002, "code错误/error code"),
    GLOBAL_ERR_NO_AUTHORITY(-10003, "没有操作权限/No operating rights"),


    ;


    private int code;
    private String msg;

    private ServiceError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
