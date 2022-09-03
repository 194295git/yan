package com.rose.loginUser.server.modular.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-08-29
 */
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("USER_ID")
    private Integer userId;
    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;
    /**
     * 账号
     */
    @TableField("ACCOUNT")
    private String account;
    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;
    /**
     * md5密码盐
     */
    @TableField("SALT")
    private String salt;
    /**
     * 名字
     */
    @TableField("NAME")
    private String name;
    /**
     * 生日
     */
    @TableField("BIRTHDAY")
    private Date birthday;
    /**
     * 性别（M：男 F：女）
     */
    @TableField("SEX")
    private String sex;
    /**
     * 电子邮件
     */
    @TableField("EMAIL")
    private String email;
    /**
     * 电话
     */
    @TableField("PHONE")
    private String phone;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    @TableField("STATUS")
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                ", userId=" + userId +
                ", avatar=" + avatar +
                ", account=" + account +
                ", password=" + password +
                ", salt=" + salt +
                ", name=" + name +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", email=" + email +
                ", phone=" + phone +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
