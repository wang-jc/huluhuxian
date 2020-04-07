package com.hlhx.huluhuxian.mapper.hkMapper;

import com.hlhx.huluhuxian.model.ControlUnit;
import com.hlhx.huluhuxian.model.RegionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author think
 * @create 2020/3/24 14:57
 */
public interface ControlUnitMapper {
    List<ControlUnit> getControlUnitList(@Param("page") Integer page, @Param("size") Integer size, @Param("controlUnit") ControlUnit controlUnit, @Param("beginDateScope") Date[] beginDateScope);
    List<ControlUnit> getArea();
    List<RegionInfo> getRegionInfo(Integer controlUnitId);
}
