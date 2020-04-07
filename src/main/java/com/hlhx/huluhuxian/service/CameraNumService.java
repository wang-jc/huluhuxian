package com.hlhx.huluhuxian.service;

import com.hlhx.huluhuxian.mapper.orclMapper.CameraNumMapper;
import com.hlhx.huluhuxian.model.CameraInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/4/2
 * @Version: 1.0
 */
@Service
public class CameraNumService {
    @Autowired
    private CameraNumMapper cameraNumMapper;

    public Integer insertCameraNum(List<CameraInfo> list){
        return cameraNumMapper.insertCameraNum(list);
    };

    public List<CameraInfo> getCameraNum(Integer page, Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        List<CameraInfo> list=new ArrayList<>();
        try{
           list=cameraNumMapper.getCameraNum(page,size,cameraInfo,beginDateScope);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
