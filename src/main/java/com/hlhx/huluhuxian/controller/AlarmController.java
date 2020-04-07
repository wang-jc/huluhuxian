package com.hlhx.huluhuxian.controller;

import com.hlhx.huluhuxian.DataBaseConfig.DataSourceContextHolder;
import com.hlhx.huluhuxian.model.*;
import com.hlhx.huluhuxian.service.*;
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
    public List<CameraInfo> getPointList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        List<CameraInfo> list=new ArrayList<>();
        DataSourceContextHolder.setDBType(cameraInfo.getCityNo());//指定数据源
        if(!cameraInfo.getCityNo().isEmpty()||!cameraInfo.getIndexCode().isEmpty()){
            list=alarmService.getCameraList(page,size,cameraInfo,beginDateScope);
            if(list==null||list.size()==0){
                DataSourceContextHolder.setDBType("default");//指定数据源
                list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
            }
        }
        return list;
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
    public List<ControlUnit> getAreaAlarmCount(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "30") Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        DataSourceContextHolder.setDBType("default");
        List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();
        List<ControlUnit> list=new ArrayList<>();
        if(controlUnit.getIndexCode()==null||"".equals(controlUnit.getIndexCode())){
            for(DbInfo dbInfo:dbInfoList){
                DataSourceContextHolder.setDBType(dbInfo.getBaseName());
                List<ControlUnit> controlUnitList = alarmService.getControlUnitList(page, size, controlUnit, beginDateScope);
                if(controlUnitList==null||controlUnitList.size()==0){
                    controlUnit.setIndexCode(dbInfo.getBaseName());
                    DataSourceContextHolder.setDBType("default");
                    controlUnitList=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
                    if(controlUnitList!=null|| controlUnitList.size()>0){
                        list.addAll(controlUnitList);
                    }
                }else {
                    list.addAll(controlUnitList);
                }
            }
        }else {
            DataSourceContextHolder.setDBType(controlUnit.getIndexCode());
            list = alarmService.getControlUnitList(page, size, controlUnit, beginDateScope);
            if(list==null||list.size()==0){
                DataSourceContextHolder.setDBType("default");
                list=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
            }
        }
        //降序排序
        list.sort(Comparator.comparing(ControlUnit::getNum).reversed());
        return list;
    }

    //获取路线类别
    @RequestMapping("/getRegionInfo")
    public List<RegionInfo> getRegionInfo(Integer controlUnitId,String cityNo){
        DataSourceContextHolder.setDBType(cityNo);
        List<RegionInfo> list=alarmService.getRegionInfo(controlUnitId);
        return list;
    }

}
