package com.netty.common.domain;

/**
 * @创建人 rose
 * @创建时间 2021/12/11
 * @描述 消息队列中储存的日志
 */
public class LogInfo {
    /** 操作模块 */
    private String title;

    /** 业务类型 */
    private String businessType;

    /** 请求方式 */
    private String requestMethod;

    /** 操作人员 */
    private String operName;

    /** 操作类别（0其它 1后台用户 2一般用户） */
    private Integer operatorType;

    /** 请求url */
    private String operUrl;

    /** 操作地址 */
    private String operIp;

    /** 操作状态（0正常 1异常） */
    private Integer operStatus;

    /** 操作时间 */
    private String operTime;

    public Integer getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(Integer operStatus) {
        this.operStatus = operStatus;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }
}
