package com.hlhx.huluhuxian.service;

import com.hlhx.huluhuxian.DataBaseConfig.DataSourceContextHolder;
import com.hlhx.huluhuxian.mapper.hkMapper.AlarmLogMapper;
import com.hlhx.huluhuxian.mapper.hkMapper.CameraInfoMapper;
import com.hlhx.huluhuxian.mapper.hkMapper.ControlUnitMapper;
import com.hlhx.huluhuxian.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    @Autowired
    private CameraNumService cameraNumService;
    @Autowired
    private  DbInfoService dbInfoService;
    @Autowired
    private  ControlUnitNumService controlUnitNumService;

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
    public RespPageBean getCameraList(Integer page, Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        List<CameraInfo> list=new ArrayList<>();
        RespPageBean respPageBean = new RespPageBean();
        Long total=0L;
        try{
            if (page !=null && size !=null){
                page=(page-1)*size;
            }
            DataSourceContextHolder.setDBType(cameraInfo.getCityNo());//指定数据源
            if(!cameraInfo.getCityNo().isEmpty()||!cameraInfo.getIndexCode().isEmpty()){
                list=cameraInfoMapper.getCameraList(page,size,cameraInfo,beginDateScope);
                total=cameraInfoMapper.getTotal(cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    DataSourceContextHolder.setDBType("default");//指定数据源
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                    total=cameraNumService.getCameraTotal(cameraInfo,beginDateScope);
                }
            }
            list=cameraInfoMapper.getCameraList(page,size,cameraInfo,beginDateScope);
            respPageBean.setData(list);
            respPageBean.setTotal(total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respPageBean;
    }

    //获取区域和报警次数
    public RespPageBean getControlUnitList(Integer page, Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        RespPageBean respPageBean = new RespPageBean();
        Long total=0L;
        List<ControlUnit> list=new ArrayList<>();
        DataSourceContextHolder.setDBType("default");
        List<DbInfo> dbInfoList = dbInfoService.getDbInfoList();
        try{
            if (page !=null && size !=null){
                page=(page-1)*size;
            }
            if(controlUnit.getIndexCode()==null||"".equals(controlUnit.getIndexCode())){
                for(DbInfo dbInfo:dbInfoList){
                    DataSourceContextHolder.setDBType(dbInfo.getBaseName());
                    List<ControlUnit> controlUnitList =new ArrayList<>();
                    controlUnitList = controlUnitMapper.getControlUnitList(page, size, controlUnit, beginDateScope);
                    if(controlUnitList==null||controlUnitList.size()==0){
                        ControlUnit controlUnit1 = new ControlUnit();
                        controlUnit1.setIndexCode(dbInfo.getBaseName());
                        DataSourceContextHolder.setDBType("default");
                        controlUnitList=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit1,beginDateScope);
                        total=controlUnitNumService.getAreaAlarmTotal(controlUnit1,beginDateScope);
                        if(controlUnitList.size()>0){
                            list.addAll(controlUnitList);
                        }
                    }else {
                        list.addAll(controlUnitList);
                    }
                }
            }else {
                DataSourceContextHolder.setDBType(controlUnit.getIndexCode());
                list = controlUnitMapper.getControlUnitList(page, size, controlUnit, beginDateScope);
                if(list==null||list.size()==0){
                    DataSourceContextHolder.setDBType("default");
                    list=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
                    total=controlUnitNumService.getAreaAlarmTotal(controlUnit,beginDateScope);
                }
            }
            //降序排序
            list.sort(Comparator.comparing(ControlUnit::getNum).reversed());
            if(total==0L){
                total=Long.valueOf(list.size());
            }
            respPageBean.setData(list);
            respPageBean.setTotal(total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respPageBean;
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
