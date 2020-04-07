package com.hlhx.huluhuxian.service;

import com.hlhx.huluhuxian.mapper.orclMapper.DbInfoMapper;
import com.hlhx.huluhuxian.model.DbInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/29
 * @Version: 1.0
 */
@Service
public class DbInfoService {
    @Autowired
    private DbInfoMapper dbInfoMapper;

    public List<DbInfo> getDbInfoList(){
        return dbInfoMapper.getDbInfoList();
    }
}
