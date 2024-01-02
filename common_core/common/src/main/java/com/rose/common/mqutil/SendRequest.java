package com.rose.common.mqutil;

import com.alibaba.fastjson.JSONObject;
import com.rose.common.mqutil.AbstractRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value="推送请求对象模型")
public class SendRequest extends AbstractRequest implements Serializable {

    /**
     * 发送目的地(客户端)
     */
    @ApiModelProperty(value="推送目的地的数组" ,required=true)
    private List<String> to = new ArrayList<>();
    /**
     * 发送内容
     */
    @ApiModelProperty(value="推送消息的内容" ,required=true)
    private JSONObject msg;

    /**
     * 推送发起者默认系统
     */
    private String from = "system";

    @ApiModelProperty(value="延时推送(单位秒,暂未实现)" ,required=false)
    private Integer delay;

    @ApiModelProperty(value="是否发送给所有人" ,required=false)
    private Boolean sendToAll = false;
}
