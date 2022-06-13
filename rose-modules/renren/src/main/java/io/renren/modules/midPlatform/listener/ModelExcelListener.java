package io.renren.modules.midPlatform.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rose
 * @create 2022/6/2
 */
@Slf4j
public  class ModelExcelListener extends AnalysisEventListener {
    private List<Object> datas = new ArrayList<>();
    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object data, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        log.info("读取到数据{}",data);
        datas.add(data);
        //根据业务自行处理，可以写入数据库等等
    }

    //所以的数据解析完了调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info(datas.toString());
        log.info("所有数据解析完成");
    }
}