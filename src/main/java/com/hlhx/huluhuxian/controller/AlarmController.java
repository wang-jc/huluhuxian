package com.hlhx.huluhuxian.controller;

import com.hlhx.huluhuxian.DataBaseConfig.DataSourceContextHolder;
import com.hlhx.huluhuxian.model.*;
import com.hlhx.huluhuxian.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author wangjunchao
 * @create 2020/2/2 23:30
 */
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private  DbInfoService dbInfoService;
    @Autowired
    private ControlUnitNumService controlUnitNumService;
    @Autowired
    private CameraNumService cameraNumService;

    private static final Logger logger= LoggerFactory.getLogger(AlarmController.class);

    //获取每个点位的报警详情列表
    @RequestMapping("/getPointAlermList")
    public RespPageBean getPointAlermList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size, AlarmLog alarmLog, Date[] beginDateScope){
        RespPageBean bean=new RespPageBean();
        DataSourceContextHolder.setDBType(alarmLog.getCityNo());//指定数据源
        if(!alarmLog.getCityNo().isEmpty()){
            bean=alarmService.getPointAlermList(page,size,alarmLog,beginDateScope);
        }
        return bean;
    }
    //获取点位列表及点位报警次数
    @RequestMapping("/getPointList")
    public RespPageBean getPointList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        return alarmService.getCameraList(page,size,cameraInfo,beginDateScope);
    }
    //获取所有区域
    @RequestMapping("/getArea")
    public List<ControlUnit> getArea(){
        List<ControlUnit> list=new ArrayList<ControlUnit>();
        DataSourceContextHolder.setDBType("default");
        List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();
        for(DbInfo dbInfo:dbInfoList){
            DataSourceContextHolder.setDBType(dbInfo.getBaseName());
            List<ControlUnit> areaList=new ArrayList<ControlUnit>();
            areaList=alarmService.getArea();
            list.addAll(areaList);
        }
        return list;
    }
    //统计所有区域的报警次数总和
    @RequestMapping("/getAreaAlarmCount")
    public RespPageBean getAreaAlarmCount(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "30") Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        return alarmService.getControlUnitList(page,size ,controlUnit ,beginDateScope);
    }

    //获取路线类别
    @RequestMapping("/getRegionInfo")
    public List<RegionInfo> getRegionInfo(Integer controlUnitId,String cityNo){
        DataSourceContextHolder.setDBType(cityNo);
        List<RegionInfo> list=alarmService.getRegionInfo(controlUnitId);
        return list;
    }

}
