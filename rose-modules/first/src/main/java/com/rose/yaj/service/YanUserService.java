package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.UserDto;
import com.rose.yaj.entity.YanUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */

public interface YanUserService extends IService<YanUser> {
    void changeLiveStatus(String openid,Integer alive);

    List<UserDto.LiveUser> queryLiveUser();

    UserDto.UserInfo getUserInfo(String openid);

    List<UserDto.EyeUser> queryEyeUser(String openid);

    void addInfo(String openid, UserDto.AddInfo addInfo);

    String getAvatarUrlByOpenid(String openid);

    /**
     * 获取所有的头像，然后存储到前端里面，然后进行计算和调用
     * @return
     */
    List<Map> getAvatarUrlAll();
}
