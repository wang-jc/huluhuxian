package com.hlhx.huluhuxian.service;

import com.hlhx.huluhuxian.mapper.orclMapper.ControlUnitNumMapper;
import com.hlhx.huluhuxian.model.ControlUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/4/1
 * @Version: 1.0
 */
@Service
public class ControlUnitNumService {

    @Resource
    private ControlUnitNumMapper controlUnitNumMapper;

    public Integer insertAreaAlarmNum(List<ControlUnit> list){
        return controlUnitNumMapper.insertAreaAlarmNum(list);
    }

    public List<ControlUnit> getAreaAlarmNum(Integer page, Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        List<ControlUnit> list=new ArrayList<>();
        try{
            if (page !=null && size !=null){
                page=(page-1)*size;
            }
            list=controlUnitNumMapper.getAreaAlarmNum(page,size,controlUnit,beginDateScope);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
