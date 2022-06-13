package io.renren.modules.wechat.service.impl;

import io.renren.modules.wechat.constant.AccessToken;
import io.renren.modules.wechat.constant.ConstantWeChat;
import io.renren.modules.wechat.menu.*;

import io.renren.modules.wechat.service.MenuService;
import io.renren.modules.wechat.utils.CommonWechatUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author zhoumin
 * @create 2018-07-11 15:40
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    //    @Override
    public static Boolean createMenu() {
        // 第三方用户唯一凭证
        String appId = ConstantWeChat.APPID;
        // 第三方用户唯一凭证密钥
        String appSecret = ConstantWeChat.APPSECRET;
        // 调用接口获取access_token
        AccessToken at = CommonWechatUtil.getToken(appId, appSecret);
        if (null != at) {
            // 调用接口创建菜单
            int result = CommonWechatUtil.createMenu(getMenu(), at.getAccessToken());
            // 判断菜单创建结果
            if (0 == result){
                LOGGER.info("菜单创建成功！");
                return true;
            }
            else{
                LOGGER.info("菜单创建失败，错误码：" + result);
                return false;
            }
        }
        return false;
    }

    //    @Override
    public static JSONObject getMenuBtn() {
        // 第三方用户唯一凭证
        String appId = ConstantWeChat.APPID;
        // 第三方用户唯一凭证密钥
        String appSecret = ConstantWeChat.APPSECRET;
        // 调用接口获取access_token
        AccessToken at = CommonWechatUtil.getToken(appId, appSecret);
        if (null != at) {
            // 调用接口获取菜单
            JSONObject result = CommonWechatUtil.getMenu(at.getAccessToken());
            // 判断菜单创建结果
            if (null != result && result.size()>0){
                LOGGER.info("菜单查询成功！");
                return result;
            }
            else{
                LOGGER.info("菜单查询失败，错误码：" + result);
                return null;
            }

        }
        return null;
    }

    //    @Override
    public static Boolean deleteMenu() {
        // 第三方用户唯一凭证
        String appId = ConstantWeChat.APPID;
        // 第三方用户唯一凭证密钥
        String appSecret = ConstantWeChat.APPSECRET;
        // 调用接口获取access_token
        AccessToken at = CommonWechatUtil.getToken(appId, appSecret);
        if (null != at) {
            // 调用接口删除菜单
            int result = CommonWechatUtil.deleteMenu(at.getAccessToken());
            // 判断菜单删除结果
            if (0 == result){
                LOGGER.info("菜单删除成功！");
                return true;
            }
            else{
                LOGGER.info("菜单删除失败，错误码：" + result);
                return false;
            }
        }
        return false;
    }

    /**
     * 组装菜单数据
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Menu getMenu() {


        ViewButton btn11 = new ViewButton();
        btn11.setName("我是");
        btn11.setType("view");
        btn11.setUrl("https://segmentfault.com/u/panzi_5abcaf30a5e6b");

        ViewButton btn21 = new ViewButton();
        btn21.setName("盘子");
        btn21.setType("view");
        btn21.setUrl("https://segmentfault.com/u/panzi_5abcaf30a5e6b");

        ViewButton btn31 = new ViewButton();
        btn31.setName("谢谢");
        btn31.setType("view");
        btn31.setUrl("https://segmentfault.com/u/panzi_5abcaf30a5e6b");

        ViewButton btn41 = new ViewButton();
        btn41.setName("关注");
        btn41.setType("view");
        btn41.setUrl("https://segmentfault.com/u/panzi_5abcaf30a5e6b");

        CommonButton btn12 = new CommonButton();
        btn12.setName("赞");
        btn12.setType("click");
        btn12.setKey("return_content");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("自我介绍");
        mainBtn1.setSub_button(new BasicButton[] { btn11, btn21,btn31});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("谢谢！");
        mainBtn2.setSub_button(new BasicButton[] { btn41, btn12 });

        /**

         *在某个一级菜单下没有二级菜单的情况，menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new BasicButton[] { mainBtn1, mainBtn2});

        return menu;
    }

    private static Menu getMenu2() {


        CommonButton btn11 = new CommonButton();
        btn11.setName("app二维码");
        btn11.setType("click");
        btn11.setKey("code");

        ViewButton btn21 = new ViewButton();
        btn21.setName("管理员登陆");
        btn21.setType("view");
        btn21.setUrl("http://gps.senruansoft.com/");

        ViewButton btn1 = new ViewButton();
        btn1.setName("公司官网");
        btn1.setType("view");
        btn1.setUrl("http://www.senruansoft.com/");

        ViewButton btn3 = new ViewButton();
        btn3.setName("林长");
        btn3.setType("view");
        btn3.setUrl("http://gps.senruansoft.com/WeChat/Login.html");


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("GPS巡检");
        mainBtn2.setSub_button(new BasicButton[] { btn11,btn21 });

        /**

         *在某个一级菜单下没有二级菜单的情况，menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new BasicButton[] { btn1, mainBtn2,btn3});


        return menu;
    }


    public static void main(String[] args) {
//        createMenu();
//        Menu menu2 = getMenu2();
//        String jsonMenu = JSONObject.fromObject(menu2).toString();
//        System.out.println(jsonMenu);


        // 第三方用户唯一凭证
        String appId = ConstantWeChat.APPID;
        String appSecret = ConstantWeChat.APPSECRET;
        AccessToken at = CommonWechatUtil.getToken(appId, appSecret);
        System.out.println(at.toString());
    }


}
