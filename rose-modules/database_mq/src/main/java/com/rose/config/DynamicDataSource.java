package com.rose.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rose
 * @create 2022/11/13
 */
@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 当前使用数据源标识
     */
    public static ThreadLocal<String> name  = new ThreadLocal<String>();

    @Autowired
    DataSource datasource1;

    @Autowired
    DataSource datasource2;

    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    @Override
    public void afterPropertiesSet(){
        Map<Object, Object> properties = new HashMap<Object, Object>();
        properties.put("w",datasource1);
        properties.put("r",datasource2);

        super.setTargetDataSources(properties);
        //设置默认的数据源
        super.setDefaultTargetDataSource(datasource1);
        super.afterPropertiesSet();
    }

}
