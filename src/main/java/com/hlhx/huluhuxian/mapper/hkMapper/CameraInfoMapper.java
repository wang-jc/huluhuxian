package com.hlhx.huluhuxian.mapper.hkMapper;

import com.hlhx.huluhuxian.model.CameraInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author think
 * @create 2020/3/24 14:47
 */
public interface CameraInfoMapper {
    List<CameraInfo> getCameraList(@Param("page") Integer page, @Param("size") Integer size, @Param("cameraInfo") CameraInfo cameraInfo, @Param("beginDateScope") Date[] beginDateScope);
}
