package com.hlhx.huluhuxian.mapper.hkMapper;

import com.hlhx.huluhuxian.model.AlarmLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author think
 * @create 2020/3/23 15:53
 */
public interface AlarmLogMapper {
    List<AlarmLog> getAlarmLog(@Param("page") Integer page, @Param("size") Integer size, @Param("alarmLog") AlarmLog alarmLog, @Param("beginDateScope") Date[] beginDateScope);
    Long getTotal(@Param("alarmLog") AlarmLog alarmLog, @Param("beginDateScope") Date[] beginDateScope);

}
