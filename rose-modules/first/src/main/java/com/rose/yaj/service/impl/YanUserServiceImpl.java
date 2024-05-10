package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.dto.UserDto;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.entity.YanUserChat;
import com.rose.yaj.mapper.YanUserMapper;
import com.rose.yaj.service.YanUserChatService;
import com.rose.yaj.service.YanUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */
@Service
public class YanUserServiceImpl extends ServiceImpl<YanUserMapper, YanUser> implements YanUserService {


    @Autowired
    YanUserChatService yanUserChatService;

    @Autowired
    YanUserMapper yanUserMapperl;
    @Override
    public void changeLiveStatus(String openid,Integer alive) {
        YanUser yanUser = new YanUser();
        if (openid != null){
            yanUser.setOpenid(openid);
            yanUser.setIsAlive(alive);

            this.updateById(yanUser);
        }else {
            throw new RuntimeException("openid不存在");
        }


    }

    @Override
    public List<UserDto.LiveUser> queryLiveUser() {
        /***
         * 我这种先查询出全部再给其余的复制等到表中字段特别多的时候可能性能上面就过不去了，目前只适合表数据少量的时候
         */
        List<YanUser> is_alive = this.list(new QueryWrapper<YanUser>().eq("is_alive", 1));
        List<UserDto.LiveUser> liveUsers = is_alive.stream().map(
                (item) -> {
                    UserDto.LiveUser liveUser = new UserDto.LiveUser();
                    BeanUtils.copyProperties(item, liveUser);
                    return liveUser;
                }
        ).collect(Collectors.toList());
        return liveUsers;
    }

    @Override
    public UserDto.UserInfo getUserInfo(String openid) {
        YanUser yanUser = this.getById(openid);
        UserDto.UserInfo userInfo = new UserDto.UserInfo();
        BeanUtils.copyProperties(yanUser,userInfo);
        return userInfo;
    }

    @Override
    public List<UserDto.EyeUser> queryEyeUser(String openid) {
        // 查询我关注的好友 分别展示
        List<YanUser> is_alive = this.list(new QueryWrapper<YanUser>());
        //将好友过滤掉自己
        List<UserDto.EyeUser> eyeUsers = is_alive.stream()
                .filter(
                        (eye) -> {
                            return !eye.getOpenid().equals(openid) ;
                        }
                )
                .map(
                (item) -> {
                    UserDto.EyeUser eyeUser = new UserDto.EyeUser();
                    BeanUtils.copyProperties(item, eyeUser);
                    // 查询一下针对每个人有多少条未读消息；
                    Map<String, Object> map = new HashMap<>();
                    map.put("to_openid", openid);
                    map.put("is_read", 0);
                    map.put("user_openid",eyeUser.getOpenid());

                    int count = yanUserChatService.count(new QueryWrapper<YanUserChat>()
                            .allEq(map));
                    eyeUser.setNoRead(count);
                    return eyeUser;
                }
        ).collect(Collectors.toList());

        return eyeUsers;
}

    /**
     * 接口暂时改造
     * @param openid
     * @param addInfo
     */
    @Override
    public void addInfo(String openid, UserDto.AddInfo addInfo) {
        YanUser yanUser = new YanUser();
//        if (yanUser.getPassword()!= null && !yanUser.getPassword().equals("") ){
//            BeanUtils.copyProperties(addInfo,yanUser);
//        }else {
            yanUser.setUsername(addInfo.getUsername());
            yanUser.setAvatarUrl(addInfo.getAvatarUrl());
            yanUser.setMajor(addInfo.getMajor());
            yanUser.setCollege(addInfo.getCollege());

//        }
//
//        yanUser.setOpenid(openid);
        this.updateById(yanUser);
    }

    @Override
    public String getAvatarUrlByOpenid(String openid) {
        YanUser yanUser = this.getById(openid);
        if (yanUser == null){
            return "https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eric2ZvHFMLWSGwkspibURzL12HHicSAx09XhC5BJx1LQEXR7icIB3usUEkIgtKSbibvMjwtFNWWGuK28Q/132";
        }else {
            if (StringUtils.isNotBlank(yanUser.getAvatarUrl())){
                return yanUser.getAvatarUrl();
            }else {

                return "https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eric2ZvHFMLWSGwkspibURzL12HHicSAx09XhC5BJx1LQEXR7icIB3usUEkIgtKSbibvMjwtFNWWGuK28Q/132";
            }
        }



    }

    @Override
    public List<Map> getAvatarUrlAll() {
        return yanUserMapperl.listAvatar();
    }
}
