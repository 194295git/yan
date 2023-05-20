package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.GroupDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanGroup;
import com.rose.yaj.entity.YanUser;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/8
 */
public interface YanGroupService extends IService<YanGroup> {
    Boolean addGroup(String openid, YanGroup yanGroup);

    Boolean addGroupMember(GroupDto.GroupMember groupMember);

    List<String> getGroupOpenid(Integer id);

    List<YanUser> getGroupMemberDetail(Integer id);
}
