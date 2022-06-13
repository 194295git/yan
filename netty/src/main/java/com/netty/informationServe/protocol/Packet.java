package com.netty.informationServe.protocol;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 基础信息包 虽然还不知道version有什么用，大概是识别防止重复 这其实也是一个模板类
 */
public abstract class Packet {
    private Byte version = 1;

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

//    通过此方法获取是什么包
    public abstract Byte getCommand();
}
