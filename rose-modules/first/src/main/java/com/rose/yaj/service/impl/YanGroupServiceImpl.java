package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.dto.GroupDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanGroup;
import com.rose.yaj.mapper.YanAnswerMapper;
import com.rose.yaj.mapper.YanGroupMapper;
import com.rose.yaj.service.YanAnswerService;
import com.rose.yaj.service.YanGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/8
 */
@Service
public class YanGroupServiceImpl extends ServiceImpl<YanGroupMapper, YanGroup> implements YanGroupService {


    @Autowired
    YanGroupMapper groupMapper;

    /**
     * 通过登录的openid 和需要创建的信息来创建群组
     * @param openid
     * @param yanGroup
     * @return
     */
    @Override
    public Boolean addGroup(String openid, YanGroup yanGroup) {
        yanGroup.setAdmin(openid);
        return this.save(yanGroup);

    }

    @Override
    public Boolean addGroupMember(GroupDto.GroupMember groupMember) {

        return groupMapper.addMember(groupMember.getGroupId(),groupMember.getMemberIds());
    }

    @Override
    public List<String> getGroupOpenid(Integer id) {
        List<String> res = groupMapper.getGroupOpenid(id);
        return res;
    }
}
