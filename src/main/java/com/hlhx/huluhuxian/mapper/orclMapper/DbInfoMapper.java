package com.hlhx.huluhuxian.mapper.orclMapper;

import com.hlhx.huluhuxian.model.DbInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author think
 * @create 2020/3/29 18:19
 */
@Component
public interface DbInfoMapper {
    List<DbInfo> getDbInfoList();
}
