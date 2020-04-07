package com.hlhx.huluhuxian.DataBaseConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.hlhx.huluhuxian.model.DbInfo;
import com.hlhx.huluhuxian.service.DbInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/4/6
 * @Version: 1.0
 */
@Component
public class DynamicDataSourceInit implements CommandLineRunner {

    @Autowired
    private DbInfoService dbInfoService;

    @Override
    public void run(String... args) throws Exception {
        List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();
        for (DbInfo dbInfo:dbInfoList){
            DruidDataSource dynamicDataSource = new DruidDataSource();
            if("postgresql".equals(dbInfo.getType())){
                dynamicDataSource.setDriverClassName("org.postgresql.Driver");
                dynamicDataSource.setUrl("jdbc:postgresql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getName() + "?useSSL=false");
            }else if("oracle".equals(dbInfo.getType())){
                dynamicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                dynamicDataSource.setUrl("jdbc:oracle://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getName());
            }else if("mysql".equals(dbInfo.getType())){
                dynamicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dynamicDataSource.setUrl("jdbc:postgresql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getName() + "?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
            }
            dynamicDataSource.setUsername(dbInfo.getUserName());
            dynamicDataSource.setPassword(dbInfo.getPassword());
            //创建动态数据源
            Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
            dataSourceMap.put(dbInfo.getBaseName(), dynamicDataSource);
            DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
            System.out.println("dbName is -> " + dbInfo.getName() + "; dbIP is  -> " + dbInfo.getIp() + "; dbUser is  -> " + dbInfo.getUserName() + "; dbPasswd is -> " + dbInfo.getPassword());

        }
    }
}
