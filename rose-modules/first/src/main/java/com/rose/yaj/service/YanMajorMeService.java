package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.entity.YanMajorMe;

import java.util.List;

public interface YanMajorMeService  extends IService<YanMajorMe> {
    List<YanMajorMe> getMajorMe();
}
