package com.rose.yaj.config;

import com.alibaba.excel.context.AnalysisContext;

import com.alibaba.excel.event.AnalysisEventListener;
import com.rose.yaj.dto.YanMajor1;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserExcelListener extends AnalysisEventListener<YanMajor1> {
    @Override
    public void invoke(YanMajor1 data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) { }
}