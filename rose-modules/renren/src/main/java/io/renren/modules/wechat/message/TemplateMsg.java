package io.renren.modules.wechat.message;

import java.util.HashMap;
import java.util.Map;

public class TemplateMsg {
    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private Map<String,TempData> data;
    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Map<String, TempData> getData() {
        return data;
    }
    public void setData(Map<String, TempData> data) {
        this.data = data;
    }
    public String getTopcolor() {
        return topcolor;
    }
    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }


    public static TemplateMsg initTemplateMsg(String openId,String keyword) {
        Map<String,TempData> m = new HashMap<String,TempData>();
        TempData first = new TempData();
        first.setColor("#000000");
        first.setValue("您好，您有一条新通知。");
        m.put("username", first);
        TempData keyword1 = new TempData();
        keyword1.setColor("#328392");
        keyword1.setValue(keyword);
        m.put("keyword", keyword1);

        TemplateMsg template = new TemplateMsg();
        template.setUrl("http://www.senruansoft.com/"); //模板详情链接
        template.setTouser(openId);  //接收者
        template.setTopcolor("#000000");
        template.setTemplate_id("f_b3kM2_FXcNEjrzEiH2BP26hjwif_O-wNtEr3ArjLI");  //模板id
        template.setData(m);
        return template;
    }


}
