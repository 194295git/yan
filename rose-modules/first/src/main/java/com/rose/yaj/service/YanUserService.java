package com.rose.yaj.service;

import com.rose.yaj.dto.UserDto;
import com.rose.yaj.entity.YanUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
