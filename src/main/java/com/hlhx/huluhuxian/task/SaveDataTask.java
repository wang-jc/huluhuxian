package com.hlhx.huluhuxian.task;

import com.hlhx.huluhuxian.DataBaseConfig.DataSourceContextHolder;
import com.hlhx.huluhuxian.model.CameraInfo;
import com.hlhx.huluhuxian.model.ControlUnit;
import com.hlhx.huluhuxian.model.DbInfo;
import com.hlhx.huluhuxian.service.*;
import com.hlhx.huluhuxian.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 定时存储统计的结果信息
 * @Author: wangjc
 * @CreateDate: 2020/3/31
 * @Version: 1.0
 */
@Component
public class SaveDataTask {
    @Autowired
    private ControlUnitNumService controlUnitNumService;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private CameraNumService cameraNumService;
    @Autowired
    DbInfoService dbInfoService;

    @Scheduled(cron = "0 10 00 * * ?")
    public void saveAlarmNum(){
        try {
            Date time= DateUtil.lastDayTime(new Date());
            Date[] beginDateScope= DateUtil.beginDateScope();
            ControlUnit controlUnit1=new ControlUnit();
            DataSourceContextHolder.setDBType("default");
            List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();
            List<ControlUnit> list=new ArrayList<>();
            for(DbInfo dbInfo:dbInfoList){
                DataSourceContextHolder.setDBType(dbInfo.getBaseName());
                List<ControlUnit> controlUnitList = alarmService.getControlUnitList(null,null,controlUnit1,beginDateScope);
                list.addAll(controlUnitList);
            }
            for (ControlUnit controlUnit:list){
                controlUnit.setTime(time);
            }
            controlUnitNumService.insertAreaAlarmNum(list);
            System.out.println("#################################");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Scheduled(cron = "0 10 00 * * ?")
    public void saveCameraAlarmNum(){
        try {
            Date time= DateUtil.lastDayTime(new Date());
            Date[] beginDateScope= DateUtil.beginDateScope();
            CameraInfo cameraInfo1=new CameraInfo();
            DataSourceContextHolder.setDBType("default");
            List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();//获取所有数据源
            List<CameraInfo> list=new ArrayList<>();
            for(DbInfo dbInfo:dbInfoList) {
                DataSourceContextHolder.setDBType(dbInfo.getBaseName());
                //获取每个县的统计结果
                List<CameraInfo> cameraInfoList=alarmService.getCameraList(null,null,cameraInfo1,beginDateScope);
                for (CameraInfo cameraInfo:cameraInfoList){
                    cameraInfo.setCityNo(dbInfo.getBaseName());
                    cameraInfo.setTime(time);
                }
                list.addAll(cameraInfoList);
            }
           if(list.size()>0){
               cameraNumService.insertCameraNum(list);
           }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    @Scheduled(cron = "0 */2 * * * ?")
    public void getAlarmNum(){
        try {
            Date[] beginDateScope= DateUtil.beginDateScope();
            ControlUnit controlUnit=new ControlUnit();
            List<ControlUnit> list=controlUnitNumService.getAreaAlarmNum(null,null,controlUnit,beginDateScope);
            System.out.println(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    @Scheduled(cron = "0 */2 * * * ?")
    public void getCameraNum(){
        try {
            Date[] beginDateScope= DateUtil.beginDateScope();
            CameraInfo cameraInfo=new CameraInfo();
            cameraInfo.setRegionId(3);
            List<CameraInfo> list = cameraNumService.getCameraNum(null, null, cameraInfo, beginDateScope);
            System.out.println(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
