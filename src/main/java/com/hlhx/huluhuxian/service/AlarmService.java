package com.hlhx.huluhuxian.service;

import com.hlhx.huluhuxian.mapper.hkMapper.AlarmLogMapper;
import com.hlhx.huluhuxian.mapper.hkMapper.CameraInfoMapper;
import com.hlhx.huluhuxian.mapper.hkMapper.ControlUnitMapper;
import com.hlhx.huluhuxian.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.hlhx.huluhuxian.orclmapper.AlarmMapper;

/**
 * @Author wangjunchao
 * @create 2020/2/3 23:50
 */
@Service
public class AlarmService {

    @Autowired
    private ControlUnitMapper controlUnitMapper;
    @Autowired
    private CameraInfoMapper cameraInfoMapper;
    @Autowired
    private AlarmLogMapper alarmLogMapper;
   /* @Autowired
    private AlarmMapper alarmMapper;*/

    //获取每个摄像头的报警详情
    public RespPageBean getPointAlermList(Integer page, Integer size, AlarmLog alarmLog, Date[] beginDateScope){
        RespPageBean respPageBean=new RespPageBean();
        if (page !=null && size !=null){
            page=(page-1)*size;
        }
        List<AlarmLog> alarmList=new ArrayList<AlarmLog>();
        alarmList=alarmLogMapper.getAlarmLog(page,size,alarmLog,beginDateScope);
        Long total=alarmLogMapper.getTotal(alarmLog,beginDateScope);
        respPageBean.setData(alarmList);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    //获取点位列表和报警次数
    public List<CameraInfo> getCameraList(Integer page, Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        List<CameraInfo> list=new ArrayList<>();
        try{
            if (page !=null && size !=null){
                page=(page-1)*size;
            }
            list=cameraInfoMapper.getCameraList(page,size,cameraInfo,beginDateScope);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取区域和报警次数
    public List<ControlUnit> getControlUnitList(Integer page, Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        List<ControlUnit> list=new ArrayList<>();
        try{
            if (page !=null && size !=null){
                page=(page-1)*size;
            }
            list=controlUnitMapper.getControlUnitList(page,size,controlUnit,beginDateScope);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取所有区域
    public List<ControlUnit> getArea(){
        List<ControlUnit> list=new ArrayList<>();
        try{
            list=controlUnitMapper.getArea();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取路线类别
    public List<RegionInfo> getRegionInfo(Integer controlUnitId){
        List<RegionInfo> list=controlUnitMapper.getRegionInfo(controlUnitId);
        return list;
    }

    //测试oracle
    /*public List<Alarm> getAlarmList(){
        return alarmMapper.getAlarmList();
    }*/

}
