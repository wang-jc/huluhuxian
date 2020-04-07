package com.hlhx.huluhuxian.mapper.orclMapper;

import com.hlhx.huluhuxian.model.ControlUnit;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author think
 * @create 2020/4/1 14:18
 */
public interface ControlUnitNumMapper {
    int insertAreaAlarmNum(@Param("list") List<ControlUnit> list);
    List<ControlUnit> getAreaAlarmNum(@Param("page") Integer page, @Param("size") Integer size, @Param("controlUnit") ControlUnit controlUnit, @Param("beginDateScope") Date[] beginDateScope);
}
